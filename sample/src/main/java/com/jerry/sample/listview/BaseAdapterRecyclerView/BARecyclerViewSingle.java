package com.jerry.sample.listview.BaseAdapterRecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
    private List<String> mList = new ArrayList<String>(
            Arrays.asList("aaa", "bbb", "ccc", "ddd","eee"));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ba_recyclerview);


        mRecyclerView.setAdapter(new CommonAdapter<String>(this, R.layout.ba_single, mList) {
            @Override
            protected void convert(ViewHolder holder, String item, int position) {
                holder.setText(R.id.text, item);
            }
        });


    }
}
