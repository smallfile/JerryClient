package com.jerry.sample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jerry.sample.utils.MyActivityManager;
import com.jerry.sample.frame.FrameActivity;
import com.jerry.sample.listview.ListViewActivity;
import com.jerry.sample.widget.WidgetActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private Context mContext;
    private ListView mListView;
    private List<ListInfoBean> mListData;
    private ListInfoAdapter mListInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
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
                if("Frame".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, FrameActivity.class, null);
                } else if("ListView".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, ListViewActivity.class, null);
                } else if("Widget".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, WidgetActivity.class, null);
                }  else if("materialDesign".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, WidgetActivity.class, null);
                }


            }
        });
    }

    private void initData(){

        ListInfoBean frame = new ListInfoBean();
        frame.setTitle("框架");
        frame.setSubTitle("常用的框架");
        frame.setOperate("Frame");
        mListData.add(frame);

        ListInfoBean listView = new ListInfoBean();
        listView.setTitle("列表");
        listView.setSubTitle("常用的ListView");
        listView.setOperate("ListView");
        mListData.add(listView);

        ListInfoBean widget = new ListInfoBean();
        widget.setTitle("Widget");
        widget.setSubTitle("常用的控件");
        widget.setOperate("Widget");
        mListData.add(widget);

        ListInfoBean materialDesign = new ListInfoBean();
        materialDesign.setTitle("Material Design");
        materialDesign.setOperate("materialDesign");
        mListData.add(materialDesign);



    }

}
