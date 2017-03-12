package com.jerry.sample.listview.BaseAdapterRecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.jerry.sample.R;
import com.jerry.sample.listview.BaseAdapterRecyclerView.MultiItem.SectionAdapter;
import com.jerry.sample.listview.BaseAdapterRecyclerView.MultiItem.SectionSupport;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jerry on 2017/3/9.
 */

public class BARecyclerViewSection extends Activity {

    private RecyclerView mRecyclerView;
    private SectionAdapter mSectionAdapter;
    private List<String> mList = new ArrayList<String>(
            Arrays.asList("a1", "a2",
                          "b1", "b2","b3",
                          "c1", "c2",
                          "d2","d3", "d4",
                          "f1", "f2","f3", "f4", "f5", "f6",
                          "m3"));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ba_recyclerview);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mSectionAdapter = new SectionAdapter<String>(this, R.layout.ba_single, mList, sectionSupport) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.text, s);
            }
        };
        mRecyclerView.setAdapter(mSectionAdapter);
    }



    SectionSupport<String> sectionSupport = new SectionSupport<String>() {
        @Override
        public int sectionHeaderLayoutId() {
            return R.layout.ba_section;
        }

        @Override
        public int sectionTitleTextViewId() {
            return R.id.section_text;
        }

        @Override
        public String getTitle(String s) {
            return s.substring(0, 1);
        }
    };


}
