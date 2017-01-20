package com.jerry.sample.frame.tab;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jerry.sample.ListInfoAdapter;
import com.jerry.sample.ListInfoBean;
import com.jerry.sample.R;
import com.jerry.sample.frame.CrashActivity;
import com.jerry.sample.frame.album.AlbumBucketActivity;
import com.jerry.sample.frame.litepal.LitePalActivity;
import com.jerry.sample.frame.okhttp.OkHttpActivity;
import com.jerry.sample.frame.tab.fragment.TabFragmentActivity;
import com.jerry.sample.frame.tab.tabhost.TabHostActivity;
import com.jerry.sample.frame.tab.viewpager.TabViewPagerActivity;
import com.jerry.sample.frame.xutils.XUtilsActivity;
import com.jerry.sample.utils.MyActivityManager;

import java.util.ArrayList;
import java.util.List;

public class TabActivity extends Activity {

    private Context mContext;
    private ListView mListView;
    private List<ListInfoBean> mListData;
    private ListInfoAdapter mListInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = TabActivity.this;
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
                if("tabhost".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, TabHostActivity.class, null);
                } else  if("viewpager".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, TabViewPagerActivity.class, null);
                } else  if("fragment".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, TabFragmentActivity.class, null);
                } else  if("crash".equals(jumpActivity)){
//                    MyActivityManager.getInstance().startActivity(mContext, CrashActivity.class, null);
                } else  if("litepal".equals(jumpActivity)){
//                    MyActivityManager.getInstance().startActivity(mContext, LitePalActivity.class, null);
                }
            }
        });
    }

    private void initData(){

        ListInfoBean tabhost = new ListInfoBean();
        tabhost.setTitle("TabHost实现Tab");
        tabhost.setOperate("tabhost");
        mListData.add(tabhost);

        ListInfoBean viewpager = new ListInfoBean();
        viewpager.setTitle("ViewPager实现Tab");
        viewpager.setOperate("viewpager");
        mListData.add(viewpager);

        ListInfoBean fragment = new ListInfoBean();
        fragment.setTitle("Fragment实现Tab");
        fragment.setOperate("fragment");
        mListData.add(fragment);

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
