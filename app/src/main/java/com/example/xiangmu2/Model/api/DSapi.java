package com.example.xiangmu2.Model.api;

import com.example.xiangmu2.bean.Banner_Bean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface DSapi {
    @GET("home/content")
    Flowable<Banner_Bean> getBannerBean();
}

