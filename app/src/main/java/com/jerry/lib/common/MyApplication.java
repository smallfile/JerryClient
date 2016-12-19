package com.jerry.lib.common;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jude.utils.JUtils;

/**
 * Created by jerry on 2016/12/14.
 */

public class MyApplication extends Application {

    private static final String TAG = MyApplication.class.getSimpleName();

    public static MyApplication instance;

    public MyApplication() {
        Log.d(TAG, "contructor");
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }

    public static MyApplication getApplication() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Application onCreate");

        Fresco.initialize(this);
        JUtils.initialize(this);
    }
}