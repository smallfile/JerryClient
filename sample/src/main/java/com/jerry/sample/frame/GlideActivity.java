package com.jerry.sample.frame;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.jerry.sample.ListInfoAdapter;
import com.jerry.sample.ListInfoBean;
import com.jerry.sample.R;
import com.jerry.sample.frame.album.AlbumBucketActivity;
import com.jerry.sample.frame.eventbus.EventBusActivity;
import com.jerry.sample.frame.litepal.LitePalActivity;
import com.jerry.sample.frame.okhttp.OkHttpActivity;
import com.jerry.sample.frame.tab.TabActivity;
import com.jerry.sample.frame.wxalbum.WXAlbumActivity;
import com.jerry.sample.frame.xutils.XUtilsActivity;
import com.jerry.sample.utils.MyActivityManager;

import java.util.ArrayList;
import java.util.List;

public class GlideActivity extends Activity {

    private Context mContext;
    private ImageView mImageView;
    private String imageUrl = "https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D220/sign=55691eb096510fb367197095e932c893/a8014c086e061d95b9df673173f40ad162d9ca1a.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        mContext = GlideActivity.this;

        initView();
        initData();

    }

    private void initView(){
        mImageView = (ImageView)findViewById(R.id.image);
    }

    private void initData(){
        Glide.with(mContext)
                .load(imageUrl)
                .into(mImageView);


    }

}
