package com.jerry.sample.frame.okhttp;

import android.util.Log;
import android.widget.Toast;

import com.jerry.sample.MyApplication;
import com.jerry.uilib.frame.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by jerry on 2017/4/25.
 */

public class MyStringCallback extends StringCallback {
    @Override
    public void onBefore(Request request, int id) {
        Toast.makeText(MyApplication.getContext(),"loading",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAfter(int id) {
        Toast.makeText(MyApplication.getContext(),"Sample-okHttp",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        e.printStackTrace();
        Toast.makeText(MyApplication.getContext(),"onError:" + e.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(String response, int id) {
        Toast.makeText(MyApplication.getContext(),"onResponse:" + response,Toast.LENGTH_SHORT).show();

        switch (id) {
            case 100:
                Toast.makeText(MyApplication.getContext(), "http", Toast.LENGTH_SHORT).show();
                break;
            case 101:
                Toast.makeText(MyApplication.getContext(), "https", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void inProgress(float progress, long total, int id) {
        Toast.makeText(MyApplication.getContext(),(int) (100 * progress)+"",Toast.LENGTH_SHORT).show();
    }
}