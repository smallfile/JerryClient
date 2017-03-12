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
                if("recyclerview0".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, BARecyclerViewCommon.class, null);
                } else if("recyclerview1".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, BARecyclerViewSingle.class, null);
                } else if("recyclerview2".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, BARecyclerViewComplex.class, null);
                } else if("recyclerview3".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, BARecyclerViewChat.class, null);
                } else if("recyclerview4".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, BARecyclerViewHeaderFooter.class, null);
                } else if("recyclerview5".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, BARecyclerViewPullToRefresh.class, null);
                } else if("recyclerview6".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, BARecyclerViewEmptyView.class, null);
                } else if("recyclerview7".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, BARecyclerViewSection.class, null);
                }
            }
        });
    }

    private void initData(){

        ListInfoBean recyclerview0 = new ListInfoBean();
        recyclerview0.setTitle("加载单行数据(普通方式)");
        recyclerview0.setOperate("recyclerview0");
        mListData.add(recyclerview0);

        ListInfoBean recyclerview1 = new ListInfoBean();
        recyclerview1.setTitle("加载单行数据");
        recyclerview1.setOperate("recyclerview1");
        mListData.add(recyclerview1);

        ListInfoBean recyclerview2 = new ListInfoBean();
        recyclerview2.setTitle("加载复杂数据");
        recyclerview2.setOperate("recyclerview2");
        mListData.add(recyclerview2);

        ListInfoBean recyclerview3 = new ListInfoBean();
        recyclerview3.setTitle("聊天布局");
        recyclerview3.setOperate("recyclerview3");
        mListData.add(recyclerview3);

        ListInfoBean recyclerview4 = new ListInfoBean();
        recyclerview4.setTitle("头部和尾部");
        recyclerview4.setOperate("recyclerview4");
        mListData.add(recyclerview4);

        ListInfoBean recyclerview5 = new ListInfoBean();
        recyclerview5.setTitle("Pull To Refresh");
        recyclerview5.setOperate("recyclerview5");
        mListData.add(recyclerview5);

        ListInfoBean recyclerview6 = new ListInfoBean();
        recyclerview6.setTitle("空视图");
        recyclerview6.setOperate("recyclerview6");
        mListData.add(recyclerview6);

        ListInfoBean recyclerview7 = new ListInfoBean();
        recyclerview7.setTitle("Section布局");
        recyclerview7.setOperate("recyclerview7");
        mListData.add(recyclerview7);


    }

}
