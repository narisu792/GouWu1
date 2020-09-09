package com.example.xiangmu2.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.xiangmu2.common.CircleTransform;

public class GlideUtils {
    public static void loadImg(Context context, String url, ImageView img){
        if (!TextUtils.isEmpty(url) && img!=null){
            Glide.with(context).load(url).into(img);
        }
    }

    //加载圆形图片
    @SuppressLint("CheckResult")
    public static void loadRoundImg(Context context, String url, ImageView img){
        if (!TextUtils.isEmpty(url) && img!=null){
            RequestOptions requestOptions = RequestOptions.bitmapTransform(new CircleTransform(context));
            Glide.with(context).load(url).apply(requestOptions).into(img);
        }
    }
}
