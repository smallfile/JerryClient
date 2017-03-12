package com.jerry.sample.listview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.jerry.sample.ListInfoAdapter;
import com.jerry.sample.ListInfoBean;
import com.jerry.sample.R;
import com.jerry.sample.listview.BaseAdapterListView.BaseAdapterListViewActivity;
import com.jerry.sample.listview.BaseAdapterRecyclerView.BaseAdapterRecyclerViewActivity;
import com.jerry.sample.listview.SampleRecyclerView.RecyclerViewActivity;
import com.jerry.sample.listview.ptlListView.PullToRefreshActivity;
import com.jerry.sample.listview.ptlRecyclerView.PullToRefreshRecyclerViewActivity;
import com.jerry.sample.utils.MyActivityManager;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends Activity {

    private Context mContext;
    private ListView mListView;
    private List<ListInfoBean> mListData;
    private ListInfoAdapter mListInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = ListViewActivity.this;
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
                if("ListViewPtr".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, PullToRefreshActivity.class, null);
                } else if("RecyclerViewPtr".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, PullToRefreshRecyclerViewActivity.class, null);
                } else if("baseAdapterListView".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, BaseAdapterListViewActivity.class, null);
                } else if("baseAdapterRecyclerView".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, BaseAdapterRecyclerViewActivity.class, null);
                } else if("recyclerViewSample".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, RecyclerViewActivity.class, null);
                }
            }
        });
    }

    private void initData(){

        ListInfoBean pulltorefresh = new ListInfoBean();
        pulltorefresh.setTitle("ListView下拉刷新，下拉加载");
        pulltorefresh.setOperate("ListViewPtr");
        mListData.add(pulltorefresh);

        ListInfoBean recyclerview = new ListInfoBean();
        recyclerview.setTitle("RecyclerView下拉刷新，下拉加载");
        recyclerview.setOperate("RecyclerViewPtr");
        mListData.add(recyclerview);

        ListInfoBean baseAdapterListView = new ListInfoBean();
        baseAdapterListView.setTitle("快速开发ListView");
        baseAdapterListView.setOperate("baseAdapterListView");
        mListData.add(baseAdapterListView);

        ListInfoBean baseAdapterRecyclerView = new ListInfoBean();
        baseAdapterRecyclerView.setTitle("快速开发RecyclerView");
        baseAdapterRecyclerView.setOperate("baseAdapterRecyclerView");
        mListData.add(baseAdapterRecyclerView);

        ListInfoBean recyclerViewSample = new ListInfoBean();
        recyclerViewSample.setTitle("RecyclerView实例");
        recyclerViewSample.setOperate("recyclerViewSample");
        mListData.add(recyclerViewSample);


    }

}
