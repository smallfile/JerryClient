package com.jerry.sample.listview.BaseAdapterRecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.jerry.sample.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jerry on 2017/3/9.
 */

public class BARecyclerViewSingle extends Activity {

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
        mRecyclerView.setAdapter(mCommonAdapter);

        // 点击事件和长按事件
        mCommonAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder,  int position) {
                Toast.makeText(BARecyclerViewSingle.this, "点击事件：" + position , Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                Toast.makeText(BARecyclerViewSingle.this, "长按事件：" + position , Toast.LENGTH_SHORT).show();
                return false;
            }
        });



    }
}
