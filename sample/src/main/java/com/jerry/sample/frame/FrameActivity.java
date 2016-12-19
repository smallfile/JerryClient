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
                String jumpActivity = infoBean.getActivity();
                if("xutils".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, XUtilsActivity.class, null);
                } else  if("tab".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, TabFragmentActivity.class, null);
                } else  if("album".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, AlbumBucketActivity.class, null);
                }
            }
        });
    }

    private void initData(){

        ListInfoBean xutils = new ListInfoBean();
        xutils.setTitle("XUtils框架");
        xutils.setActivity("xutils");
        mListData.add(xutils);

        ListInfoBean tab = new ListInfoBean();
        tab.setTitle("Tab框架");
        tab.setActivity("tab");
        mListData.add(tab);

        ListInfoBean album = new ListInfoBean();
        album.setTitle("相册");
        album.setActivity("album");
        mListData.add(album);


    }

}
