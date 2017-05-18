package com.jerry.sample.frame;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jerry.sample.ListInfoAdapter;
import com.jerry.sample.ListInfoBean;
import com.jerry.sample.R;
import com.jerry.sample.frame.FileDownload.FileDownloadMainActivity;
import com.jerry.sample.frame.album.AlbumBucketActivity;
import com.jerry.sample.frame.eventbus.EventBusActivity;
import com.jerry.sample.frame.glide.GlideActivity;
import com.jerry.sample.frame.litepal.LitePalActivity;
import com.jerry.sample.frame.okhttp.OkHttpActivity;
import com.jerry.sample.frame.ormlite.OrmLiteActivity;
import com.jerry.sample.frame.tab.TabActivity;
import com.jerry.sample.frame.wxalbum.WXAlbumActivity;
import com.jerry.sample.utils.MyActivityManager;

import java.util.ArrayList;
import java.util.List;

public class FrameActivity extends Activity {

    private Context mContext;
    private ListView mListView;
    private List<ListInfoBean> mListData;
    private ListInfoAdapter mListInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = FrameActivity.this;
        mListData = new ArrayList<ListInfoBean>();

        initView();
        initData();

        mListInfoAdapter = new ListInfoAdapter(mContext);
        mListInfoAdapter.setListData(mListData);
        mListView.setAdapter(mListInfoAdapter);
    }

    private void initView(){
        mListView = (ListView) findViewById(R.id.listView);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long rowId) {
                ListInfoBean infoBean = mListData.get(position);
                String jumpActivity = infoBean.getOperate();
                if("tab".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, TabActivity.class, null);
                } else  if("album".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, AlbumBucketActivity.class, null);
                } else  if("wxalbum".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, WXAlbumActivity.class, null);
                } else  if("crash".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, CrashActivity.class, null);
                } else  if("litepal".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, LitePalActivity.class, null);
                } else  if("ormlite".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, OrmLiteActivity.class, null);
                } else  if("okhttputils".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, OkHttpActivity.class, null);
                } else  if("permission".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, PermissionActivity.class, null);
                } else  if("eventbus".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, EventBusActivity.class, null);
                } else  if("glide".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, GlideActivity.class, null);
                } else  if("fileDownload".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, FileDownloadMainActivity.class, null);
                }
            }
        });
    }

    private void initData(){

        ListInfoBean tab = new ListInfoBean();
        tab.setTitle("Tab框架");
        tab.setOperate("tab");
        mListData.add(tab);

        ListInfoBean album = new ListInfoBean();
        album.setTitle("相册");
        album.setOperate("album");
        mListData.add(album);

        ListInfoBean wxalbum = new ListInfoBean();
        wxalbum.setTitle("仿微信相册");
        wxalbum.setOperate("wxalbum");
        mListData.add(wxalbum);

        ListInfoBean crash = new ListInfoBean();
        crash.setTitle("Crash异常");
        crash.setOperate("crash");
        mListData.add(crash);

        ListInfoBean litepal = new ListInfoBean();
        litepal.setTitle("数据库框架LitePal");
        litepal.setOperate("litepal");
        mListData.add(litepal);

        ListInfoBean ormlite = new ListInfoBean();
        ormlite.setTitle("数据库框架ORMLite");
        ormlite.setOperate("ormlite");
        mListData.add(ormlite);

        ListInfoBean okhttputils = new ListInfoBean();
        okhttputils.setTitle("网络框架OkHttp");
        okhttputils.setOperate("okhttputils");
        mListData.add(okhttputils);

        ListInfoBean permission = new ListInfoBean();
        permission.setTitle("运行时权限");
        permission.setOperate("permission");
        mListData.add(permission);

        ListInfoBean eventbus = new ListInfoBean();
        eventbus.setTitle("EventBus实例");
        eventbus.setOperate("eventbus");
        mListData.add(eventbus);

        ListInfoBean glide = new ListInfoBean();
        glide.setTitle("Glide实例");
        glide.setOperate("glide");
        mListData.add(glide);

        ListInfoBean fileDownload = new ListInfoBean();
        fileDownload.setTitle("FileDownload实例");
        fileDownload.setOperate("fileDownload");
        mListData.add(fileDownload);



    }

}
