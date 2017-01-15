package com.jerry.sample;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;
import org.xutils.BuildConfig;
import org.xutils.x;
import com.android.volley.cache.DiskLruBasedCache;
import com.android.volley.cache.plus.SimpleImageLoader;
import com.jerry.sample.utils.CrashHandler;

public class MyApplication extends Application {

    private SimpleImageLoader mImageLoader;

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

        //初例化未捕获异常监听器
        CrashHandler.getInstance().init(getApplicationContext());

        //XUtils初使化
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 开启debug会影响性能

        DiskLruBasedCache.ImageCacheParams cacheParams = new DiskLruBasedCache.ImageCacheParams(getApplicationContext(), "gzCache");
        cacheParams.setMemCacheSizePercent(0.5f);
        mImageLoader = new SimpleImageLoader(getApplicationContext(),cacheParams);

        //LitePal初使化
        LitePal.initialize(this);

    }


    public SimpleImageLoader getmImageLoader() {
        return mImageLoader;
    }

}
