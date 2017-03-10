package com.jerry.sample.listview.BaseAdapterListView;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.jerry.sample.R;
import com.jerry.sample.listview.BaseAdapterListView.adapter.ChatAdapterForListView;
import com.jerry.sample.listview.ChatMessage;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jerry on 2017/3/9.
 */

public class BAListViewChat extends Activity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ba_listview);


        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setDivider(null);
        mListView.setAdapter(new ChatAdapterForListView(this, ChatMessage.MOCK_DATAS));

    }

}
