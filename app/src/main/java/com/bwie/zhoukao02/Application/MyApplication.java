package com.bwie.zhoukao02.Application;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;



public class MyApplication  extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
    }
}
