package com.jerry.sample.listview.BaseAdapterRecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jerry.sample.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jerry on 2017/3/9.
 */

public class BARecyclerViewEmptyView extends Activity {

    private RecyclerView mRecyclerView;
    private CommonAdapter mCommonAdapter;
    private EmptyWrapper mEmptyWrapper;
    private List<String> mList = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ba_recyclerview);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCommonAdapter = new CommonAdapter<String>(this, R.layout.ba_single, mList) {
            @Override
            protected void convert(ViewHolder holder, String item, int position) {
                holder.setText(R.id.text, item);
            }
        };
        mEmptyWrapper = new EmptyWrapper(mCommonAdapter);
        mEmptyWrapper.setEmptyView(R.layout.ba_empty_view);
        mRecyclerView.setAdapter(mEmptyWrapper );

        //************************************************************************//
//        支持链式添加多种功能，示例代码：
//        mAdapter = new EmptyViewWrapper(
//                        new LoadMoreWrapper(
//                        new HeaderAndFooterWrapper(mOriginAdapter)));
        //************************************************************************//

    }
}
