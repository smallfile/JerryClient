package com.jerry.sample.listview.BaseAdapterRecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.jerry.sample.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jerry on 2017/3/9.
 */

public class BARecyclerViewComplex extends Activity {

    private RecyclerView mRecyclerView;
    private List<TestData> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ba_recyclerview);

        mList = initData();

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new CommonAdapter<TestData>(getApplicationContext(),R.layout.ba_complex,mList) {
            @Override
            protected void convert(ViewHolder viewHolder, TestData item, int position) {
                viewHolder.setText(R.id.tv_title, item.title);
                viewHolder.setText(R.id.tv_describe, item.summary);
                viewHolder.setText(R.id.tv_time, item.date);
                viewHolder.setText(R.id.tv_phone, item.phone);
            }
        });


    }

    private List<TestData> initData(){
        List<TestData> list = new ArrayList<TestData>();

        TestData testData1 = new TestData("111","111","111","111");
        TestData testData2 = new TestData("222","222","222","222");
        TestData testData3 = new TestData("333","333","333","333");
        TestData testData4 = new TestData("444","444","444","444");
        TestData testData5 = new TestData("555","555","555","555");
        TestData testData6 = new TestData("666","666","666","666");
        TestData testData7 = new TestData("777","777","777","777");
        TestData testData8 = new TestData("888","888","888","888");

        list.add(testData1);
        list.add(testData2);
        list.add(testData3);
        list.add(testData4);
        list.add(testData5);
        list.add(testData6);
        list.add(testData7);
        list.add(testData8);

        return list;
    }

    class TestData {
        String title;
        String summary;
        String date;
        String phone;

        public TestData(String title, String summary, String date, String phone){
            this.title = title;
            this.summary = summary;
            this.date = date;
            this.phone = phone;
        }
    }
}
