package com.example.xiangmu2.Model;

import android.content.Context;
import android.util.Log;

import com.example.xiangmu2.Model.api.DSapi;
import com.example.xiangmu2.common.Constants;
import com.example.xiangmu2.utils.SystemUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
    private static HttpManager instance;

    public static HttpManager getInstance(){
        if (instance == null){
            synchronized (HttpManager.class){
                if (instance == null){
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    //网络请求接口
    private static Retrofit getRetrofit(String url){
        Retrofit build = new Retrofit.Builder()
                .baseUrl(url)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return build;
    }

    private static OkHttpClient getOkHttpClient(){
        //定义本地缓存文件
        File file = new File(Constants.PATH_CACHE);
        //cache缓存文件的操作
        Cache cache = new Cache(file, 1024 * 1204 * 100);
        OkHttpClient build = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(new LoggingInterceptor())
                .addNetworkInterceptor(new NetInterceptor())
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        return build;
    }

    private static final String TAG = "HttpManager";
    private static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long l = System.nanoTime();
            Log.i("interceptor",String.format("Sending request %s on %s%n%s",request.url(),chain.connection(),request.headers()));

            Response proceed = chain.proceed(request);
            long l1 = System.nanoTime();
            Log.i("Received:",String.format("Received response for %s in %.1fms%n%s",proceed.request().url(),(l1-l)/1e6d,proceed.headers()));
            if (proceed.header("session_id")!=null){

            }
            return proceed;
        }
    }

    private static class NetInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (SystemUtils.checkNetWork()){
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response proceed = chain.proceed(request);
            //通过判断网络连接是否存在本地或者服务器的数据
            if (!SystemUtils.checkNetWork()){
                int maxAge = 0;
                return proceed.newBuilder()
                        .removeHeader("pragma")
                        .header("Cache-Control","public,max-age="+maxAge).build();
            }else{
                int maxStale = 60*60*24*28;
                return proceed.newBuilder()
                        .removeHeader("pragma")
                        .header("Cache-Control","public, onlyif-cached, max-stale="+maxStale).build();
            }
        }
    }

    DSapi dSapi;
    public DSapi getdSapi(){
        if (dSapi == null){
            dSapi = getRetrofit(Constants.BASE_URL).create(DSapi.class);
        }
        return dSapi;
    }
}
