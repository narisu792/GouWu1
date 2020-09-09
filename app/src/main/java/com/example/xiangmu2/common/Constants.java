package com.example.xiangmu2.common;

import com.example.xiangmu2.app.MyApp;

import java.io.File;

public class Constants {
    public static String BASE_URL = "http://169.254.96.147:8085/";

    //网络缓存的地址
    public static String PATH_DATA = MyApp.app.getCacheDir().getAbsolutePath()+ File.separator+"data";
    public static String PATH_CACHE = PATH_DATA +"/tongpao";
}
