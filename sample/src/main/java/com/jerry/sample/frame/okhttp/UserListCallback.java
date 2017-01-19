package com.jerry.sample.frame.okhttp;

import com.google.gson.Gson;
import com.jerry.uilib.frame.okhttp.callback.Callback;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;

/**
 * Created by zhy on 15/12/14.
 */
public abstract class UserListCallback extends Callback<List<User>> {

    @Override
    public List<User> parseNetworkResponse(Response response,int id) throws IOException {
        String string = response.body().string();
        List<User> user = new Gson().fromJson(string, List.class);
        return user;
    }


}
