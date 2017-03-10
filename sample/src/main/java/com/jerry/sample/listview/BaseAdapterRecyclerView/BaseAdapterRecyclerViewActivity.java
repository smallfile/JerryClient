package com.jerry.sample.listview.BaseAdapterRecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jerry.sample.ListInfoAdapter;
import com.jerry.sample.ListInfoBean;
import com.jerry.sample.R;
import com.jerry.sample.listview.pulltorefresh.PullToRefreshActivity;
import com.jerry.sample.listview.recyclerview.RecyclerViewActivity;
import com.jerry.sample.utils.MyActivityManager;

import java.util.ArrayList;
import java.util.List;

public class BaseAdapterRecyclerViewActivity extends Activity {

    private Context mContext;
    private ListView mListView;
    private List<ListInfoBean> mListData;
    private ListInfoAdapter mListInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = BaseAdapterRecyclerViewActivity.this;
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



    }

}
