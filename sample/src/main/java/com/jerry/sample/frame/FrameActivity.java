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
import com.jerry.sample.frame.album.AlbumBucketActivity;
import com.jerry.sample.frame.litepal.LitePalActivity;
import com.jerry.sample.frame.tab.TabFragmentActivity;
import com.jerry.sample.frame.xutils.XUtilsActivity;
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
                if("xutils".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, XUtilsActivity.class, null);
                } else  if("tab".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, TabFragmentActivity.class, null);
                } else  if("album".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, AlbumBucketActivity.class, null);
                } else  if("crash".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, CrashActivity.class, null);
                } else  if("litepal".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, LitePalActivity.class, null);
                }
            }
        });
    }

    private void initData(){

        ListInfoBean xutils = new ListInfoBean();
        xutils.setTitle("XUtils框架");
        xutils.setOperate("xutils");
        mListData.add(xutils);

        ListInfoBean tab = new ListInfoBean();
        tab.setTitle("Tab框架");
        tab.setOperate("tab");
        mListData.add(tab);

        ListInfoBean album = new ListInfoBean();
        album.setTitle("相册");
        album.setOperate("album");
        mListData.add(album);

        ListInfoBean crash = new ListInfoBean();
        crash.setTitle("Crash异常");
        crash.setOperate("crash");
        mListData.add(crash);

        ListInfoBean litepal = new ListInfoBean();
        litepal.setTitle("数据库框架LitePal");
        litepal.setOperate("litepal");
        mListData.add(litepal);

    }

}
