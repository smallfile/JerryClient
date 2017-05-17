package com.jerry.sample;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import org.litepal.LitePal;
import com.android.volley.cache.DiskLruBasedCache;
import com.android.volley.cache.plus.SimpleImageLoader;
import com.jerry.sample.utils.CrashHandler;
import com.jerry.uilib.frame.okhttp.OkHttpUtils;
import com.jerry.uilib.frame.okhttp.cookie.CookieJarImpl;
import com.jerry.uilib.frame.okhttp.cookie.store.MemoryCookieStore;
import com.jerry.uilib.frame.okhttp.utils.HttpsUtils;

import com.jerry.uilib.frame.okhttp.utils.LoggerInterceptor;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.connection.FileDownloadUrlConnection;
import com.liulishuo.filedownloader.services.DownloadMgrInitialParams;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import okio.Buffer;

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
//        CrashHandler.getInstance().init(getApplicationContext());

        DiskLruBasedCache.ImageCacheParams cacheParams = new DiskLruBasedCache.ImageCacheParams(getApplicationContext(), "gzCache");
        cacheParams.setMemCacheSizePercent(0.5f);
        mImageLoader = new SimpleImageLoader(getApplicationContext(),cacheParams);

        //LitePal初使化
        LitePal.initialize(this);

        //OKHttp工具类初使化
        initOkHttpClient();

        //文件下载器初使化
        initFileDownload();
    }

    private String CER_12306 = "-----BEGIN CERTIFICATE-----\n" +
            "MIICmjCCAgOgAwIBAgIIbyZr5/jKH6QwDQYJKoZIhvcNAQEFBQAwRzELMAkGA1UEBhMCQ04xKTAn\n" +
            "BgNVBAoTIFNpbm9yYWlsIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MQ0wCwYDVQQDEwRTUkNBMB4X\n" +
            "DTA5MDUyNTA2NTYwMFoXDTI5MDUyMDA2NTYwMFowRzELMAkGA1UEBhMCQ04xKTAnBgNVBAoTIFNp\n" +
            "bm9yYWlsIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MQ0wCwYDVQQDEwRTUkNBMIGfMA0GCSqGSIb3\n" +
            "DQEBAQUAA4GNADCBiQKBgQDMpbNeb34p0GvLkZ6t72/OOba4mX2K/eZRWFfnuk8e5jKDH+9BgCb2\n" +
            "9bSotqPqTbxXWPxIOz8EjyUO3bfR5pQ8ovNTOlks2rS5BdMhoi4sUjCKi5ELiqtyww/XgY5iFqv6\n" +
            "D4Pw9QvOUcdRVSbPWo1DwMmH75It6pk/rARIFHEjWwIDAQABo4GOMIGLMB8GA1UdIwQYMBaAFHle\n" +
            "tne34lKDQ+3HUYhMY4UsAENYMAwGA1UdEwQFMAMBAf8wLgYDVR0fBCcwJTAjoCGgH4YdaHR0cDov\n" +
            "LzE5Mi4xNjguOS4xNDkvY3JsMS5jcmwwCwYDVR0PBAQDAgH+MB0GA1UdDgQWBBR5XrZ3t+JSg0Pt\n" +
            "x1GITGOFLABDWDANBgkqhkiG9w0BAQUFAAOBgQDGrAm2U/of1LbOnG2bnnQtgcVaBXiVJF8LKPaV\n" +
            "23XQ96HU8xfgSZMJS6U00WHAI7zp0q208RSUft9wDq9ee///VOhzR6Tebg9QfyPSohkBrhXQenvQ\n" +
            "og555S+C3eJAAVeNCTeMS3N/M5hzBRJAoffn3qoYdAO1Q8bTguOi+2849A==\n" +
            "-----END CERTIFICATE-----";

    private void initOkHttpClient(){
        //        设置Cookie,Cookie包含以下几个：
//        PersistentCookieStore    //持久化cookie
//        SerializableHttpCookie    //持久化cookie
//        MemoryCookieStore       //cookie信息存在内存中
//        ClearableCookieJar cookieJar1 = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));
        CookieJarImpl cookieJar2 = new CookieJarImpl(new MemoryCookieStore());

//        设置可访问所有的https网站
//        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
//                //其他配置
//                .build();
//        OkHttpUtils.initClient(okHttpClient);

//        设置具体的证书
//        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(证书的inputstream, null, null);
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager))
//                //其他配置
//                .build();
//        OkHttpUtils.initClient(okHttpClient);

//        双向认证
//        HttpsUtils.getSslSocketFactory(
//                证书的inputstream,
//                本地证书的inputstream,
//                本地证书的密码)

//        1. 设置可访问所有的https网站
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
//        2. 以证书字符串的方式访问12306网站
//        InputStream[] cerInputStreams = {new Buffer().writeUtf8(CER_12306).inputStream()};
//        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(cerInputStreams, null, null);
//        3. 以证书的方式访问12306网站
//        HttpsUtils.SSLParams sslParams = null;
//        try {
//            InputStream[] cerInputStreams = {getAssets().open("cer12306.cer")};
//            sslParams = HttpsUtils.getSslSocketFactory(cerInputStreams, null, null);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)

                .addInterceptor(new LoggerInterceptor("TAG"))  //设置拦截器
                .cookieJar(cookieJar2)    //设置Cookie
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)   //设置可访问所有的https网站
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    public SimpleImageLoader getmImageLoader() {
        return mImageLoader;
    }

    private void initFileDownload(){
        /**
         * just for cache Application's Context, and ':filedownloader' progress will NOT be launched
         * by below code, so please do not worry about performance.
         * @see FileDownloader#init(Context)
         */
        FileDownloader.init(getApplicationContext(), new DownloadMgrInitialParams.InitCustomMaker()
                .connectionCreator(new FileDownloadUrlConnection
                        .Creator(new FileDownloadUrlConnection.Configuration()
                        .connectTimeout(15_000) // set connection timeout.
                        .readTimeout(15_000) // set read timeout.
                        .proxy(Proxy.NO_PROXY) // set proxy
                )));

    }




}
