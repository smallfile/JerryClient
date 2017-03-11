package com.jerry.sample.listview.BaseAdapterRecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.jerry.sample.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jerry on 2017/3/9.
 */

public class BARecyclerViewHeaderFooter extends Activity {

    private RecyclerView mRecyclerView;
    private CommonAdapter mCommonAdapter;
    private List<String> mList = new ArrayList<String>(
            Arrays.asList("aaa", "bbb", "ccc", "ddd","eee"));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ba_recyclerview);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mCommonAdapter = new CommonAdapter<String>(this, R.layout.ba_single, mList) {
            @Override
            protected void convert(ViewHolder holder, String item, int position) {
                holder.setText(R.id.text, item);
            }
        };

        //设置Header和Footer
        HeaderAndFooterWrapper mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mCommonAdapter);
        TextView t1 = new TextView(this);
        t1.setText("Header 1");
        TextView t2 = new TextView(this);
        t2.setText("Header 2");
        mHeaderAndFooterWrapper.addHeaderView(t1);
        mHeaderAndFooterWrapper.addHeaderView(t2);
        TextView t3 = new TextView(this);
        t3.setText("Footer 1");
        mHeaderAndFooterWrapper.addFootView(t3);
        mRecyclerView.setAdapter(mHeaderAndFooterWrapper);

        mHeaderAndFooterWrapper.notifyDataSetChanged();


    }
}
