package com.jerry.sample.listview.BaseAdapterListView;

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

public class BaseAdapterListViewActivity extends Activity {

    private Context mContext;
    private ListView mListView;
    private List<ListInfoBean> mListData;
    private ListInfoAdapter mListInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = BaseAdapterListViewActivity.this;
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
                if("listview0".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, BAListViewCommon.class, null);
                } else if("listview1".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, BAListViewSingle.class, null);
                } else if("listview2".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, BAListViewComplex.class, null);
                } else if("listview3".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, BAListViewChat.class, null);
                }
            }
        });
    }

    private void initData(){

        ListInfoBean listview0 = new ListInfoBean();
        listview0.setTitle("加载单行数据(普通方式)");
        listview0.setOperate("listview0");
        mListData.add(listview0);

        ListInfoBean listview1 = new ListInfoBean();
        listview1.setTitle("加载单行数据");
        listview1.setOperate("listview1");
        mListData.add(listview1);

        ListInfoBean listview2 = new ListInfoBean();
        listview2.setTitle("加载复杂数据");
        listview2.setOperate("listview2");
        mListData.add(listview2);

        ListInfoBean listview3 = new ListInfoBean();
        listview3.setTitle("聊天布局");
        listview3.setOperate("listview3");
        mListData.add(listview3);

    }

}
