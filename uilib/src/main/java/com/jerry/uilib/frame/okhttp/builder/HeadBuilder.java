package com.jerry.uilib.frame.okhttp.builder;

import com.jerry.uilib.frame.okhttp.OkHttpUtils;
import com.jerry.uilib.frame.okhttp.request.OtherRequest;
import com.jerry.uilib.frame.okhttp.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
