package com.example.xiangmu2.utils;

import android.text.TextUtils;
import android.widget.TextView;

public class TvUtils {
    public static void setText(TextView tv, String value){
        if(!TextUtils.isEmpty(value)){
            tv.setText(value);
        }
    }

    public static void setText(TextView tv,int value){
        tv.setText(String.valueOf(value));
    }
}
