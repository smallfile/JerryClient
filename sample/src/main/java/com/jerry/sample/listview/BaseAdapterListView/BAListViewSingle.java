package com.jerry.sample.listview.BaseAdapterListView;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.jerry.sample.R;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jerry on 2017/3/9.
 */

public class BAListViewSingle extends Activity {

    private ListView mListView;
    private List<String> mList = new ArrayList<String>(
            Arrays.asList("aaa", "bbb", "ccc"));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ba_listview);

        mListView = (ListView)findViewById(R.id.list_view);
        mListView.setAdapter(new CommonAdapter<String>(getApplicationContext(),R.layout.ba_listview_single,mList) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {
                viewHolder.setText(R.id.text,item);
            }
        });

    }
}
