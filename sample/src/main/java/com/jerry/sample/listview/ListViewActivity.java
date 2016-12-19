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
import com.jerry.sample.listview.pulltorefresh.PullToRefreshActivity;
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
                String jumpActivity = infoBean.getActivity();
                if("PullToRefresh".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, PullToRefreshActivity.class, null);
                }
            }
        });
    }

    private void initData(){

        ListInfoBean pulltorefresh = new ListInfoBean();
        pulltorefresh.setTitle("Pull To Refresh");
        pulltorefresh.setActivity("PullToRefresh");
        mListData.add(pulltorefresh);





    }

}
