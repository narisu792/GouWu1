package com.example.xiangmu2.Base;

import com.example.xiangmu2.interfaces.IBasePersenter;
import com.example.xiangmu2.interfaces.IBaseView;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePersenter<V extends IBaseView> implements IBasePersenter<V> {

    //为什么这个是泛型不是IBaseView接口
    protected V mView;

    //弱引用v层 解决Activity或者Fragment使用mvp的内存泄漏问题
    WeakReference<V> weakReference;

    //Rxjava 背压网络请求处理
    CompositeDisposable compositeDisposable;


    @Override
    public void attachView(V view) {
        weakReference = new WeakReference<>(view);
        mView = weakReference.get();
    }

    //将请求网络数据的对象加入排列
    public void addSubscribe(Disposable disposable){
        if (compositeDisposable == null){
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    @Override
    public void detachView() {
        mView = null;
        clearSubscribe();
    }

    //把排列的请求对象清理
    private void clearSubscribe() {
        if (compositeDisposable!=null){
            compositeDisposable.clear();
        }
    }
}
