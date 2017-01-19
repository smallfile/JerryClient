package com.jerry.sample.frame.okhttp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jerry.sample.R;
import com.jerry.uilib.frame.okhttp.OkHttpUtils;
import com.jerry.uilib.frame.okhttp.callback.BitmapCallback;
import com.jerry.uilib.frame.okhttp.callback.FileCallBack;
import com.jerry.uilib.frame.okhttp.callback.StringCallback;
import com.jerry.uilib.frame.okhttp.cookie.CookieJarImpl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.CookieJar;
import okhttp3.MediaType;
import okhttp3.Request;

public class OkHttpActivity extends AppCompatActivity {

    private String mBaseUrl = "http://192.168.1.103:8080/OkHttpServer/okhttp/";

    private TextView mTv;
    private ImageView mImageView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);


        mTv = (TextView) findViewById(R.id.id_textview);
        mImageView = (ImageView) findViewById(R.id.id_imageview);
        mProgressBar = (ProgressBar) findViewById(R.id.id_progress);
        mProgressBar.setMax(100);
    }

    public void getHtml(View view) {
        String url = "http://www.baidu.com";
        OkHttpUtils.get()
                   .url(url)
                   .id(100)
                   .build()
                   .execute(new StringCallback(){
                       @Override
                       public void onError(Call call, Exception e, int id) {
                           mTv.setText("onError:" + e.getMessage());
                       }

                       @Override
                       public void onResponse(String response, int id) {
                           mTv.setText("onResponse:" + response);
                       }
                   });
    }

    public void getHttpsHtml(View view) {
        String url = "https://kyfw.12306.cn/otn/";
        OkHttpUtils
                .get()//
                .url(url)//
                .id(101)
                .build()//
                .execute(new StringCallback(){
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mTv.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mTv.setText("onResponse:" + response);
                    }
                });

    }

    //获取字符串
    public void getString(View view) {
        String url = mBaseUrl + "getString";
        OkHttpUtils
                .get()
                .url(url)
                .id(102)
                .addParams("str","jerry")
                .build()
                .execute(new StringCallback(){
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mTv.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mTv.setText("onResponse:" + response);
                    }
                });

    }

    //获取对象
    public void getUser(View view) {
        String url = mBaseUrl + "getUser";
        OkHttpUtils
                .get()
                .url(url)
                .addParams("name", "jerry")
                .addParams("age", "123")
                .build()
                .execute(new UserCallback(){
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mTv.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(User response, int id) {
                        mTv.setText("onResponse:" + response);
                    }
                });
    }

    //获取对象列表
    public void getUsers(View view) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "zhy");
        params.put("age","123");

        String url = mBaseUrl + "getUserList";
        OkHttpUtils
                .get()
                .url(url)
                .params(params)
                .build()
                .execute(new UserListCallback(){
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mTv.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(List<User> response, int id) {
                        mTv.setText("onResponse:" + response);
                    }
                });
    }

    //获取图片
    public void getImage(View view) {
        mTv.setText("");
        String url = "http://images.csdn.net/20150817/1.jpg";
        OkHttpUtils
                .get()
                .url(url)
                .tag(this)
                .build()
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mTv.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(Bitmap bitmap, int id) {
                        mImageView.setImageBitmap(bitmap);
                    }
                });
    }


    //上传字符串或json字符串
    public void postString(View view) {
        String url = mBaseUrl + "postString";
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content("jerry ===  post string! ")
                .build()
                .execute(new StringCallback(){
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mTv.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mTv.setText("onResponse:" + response);
                    }
                });

    }

    //上传表单数据
    public void postUser(View view) {
        Map<String, String> params = new HashMap<>();
        params.put("name", "张鸿洋");
        params.put("age", "123");

        String url = mBaseUrl + "postUser";
        OkHttpUtils
                .post()
                .url(url)
                .params(params)
                .build()
                .execute(new StringCallback(){
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mTv.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mTv.setText("onResponse:" + response);
                    }
                });

    }

    //上传文件
    public void postFile(View view) {
        File file = new File(Environment.getExternalStorageDirectory(), "upload_image.jpg");
        if (!file.exists()) {
            Toast.makeText(OkHttpActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        String url = mBaseUrl + "postFile";
        OkHttpUtils
                .postFile()
                .url(url)
                .file(file)
                .build()
                .execute(new StringCallback(){
                    @Override
                    public void inProgress(float progress, long total, int id){
                        mProgressBar.setProgress((int) (100 * progress));
                    }
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mTv.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mTv.setText("onResponse:" + response);
                    }
                });
    }

    //上传表单数据（带文件）
    public void postUserAndFile(View view) {

        File file = new File(Environment.getExternalStorageDirectory(), "upload_image.jpg");
        if (!file.exists()) {
            Toast.makeText(OkHttpActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("name", "张鸿洋");
        params.put("age", "23");

        Map<String, String> headers = new HashMap<>();
        headers.put("APP-Key", "APP-Secret222");
        headers.put("APP-Secret", "APP-Secret111");

        String url = mBaseUrl + "postUserAndFile";

        OkHttpUtils
                .post()
                .addFile("mFile", "upload_image.png", file)
                .url(url)
                .params(params)
                .headers(headers)
                .build()
                .execute(new StringCallback(){
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mTv.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mTv.setText("onResponse:" + response);
                    }
                });
    }

    //下载文件
    public void downloadFile(View view) {
        String url = "http://h.hiphotos.baidu.com/image/pic/item/a2cc7cd98d1001e9460fd63bbd0e7bec54e797d7.jpg";
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "download_img.jpg"){

                    @Override
                    public void onBefore(Request request, int id){
                    }

                    @Override
                    public void inProgress(float progress, long total, int id){
                        mProgressBar.setProgress((int) (100 * progress));
                    }

                    @Override
                    public void onError(Call call, Exception e, int id){
                        mTv.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(File file, int id){
                        mTv.setText("onResponse:" + file.getAbsolutePath());
                    }
                });
    }


    public void otherRequestDemo(View view) {
        //also can use delete ,head , patch
        /*
        OkHttpUtils
                .put()//
                .url("http://11111.com")
                .requestBody
                        ("may be something")//
                .build()//
                .execute(new MyStringCallback());

        OkHttpUtils
                .head()//
                .url(url)
                .addParams("name", "zhy")
                .build()
                .execute();

       */

    }

    public void clearSession(View view) {
        CookieJar cookieJar = OkHttpUtils.getInstance().getOkHttpClient().cookieJar();
        if (cookieJar instanceof CookieJarImpl) {
            ((CookieJarImpl) cookieJar).getCookieStore().removeAll();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
