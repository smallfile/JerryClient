package com.jerry.sample;

import android.app.Application;
import android.content.Context;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * Created by wyouflf on 15/10/28.
 */
public class MyApplication extends Application {

    private static MyApplication instance;
    public MyApplication() {
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
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 开启debug会影响性能
    }


}
