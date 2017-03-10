package com.jerry.sample.listview.BaseAdapterListView.adapter;

import android.content.Context;

import com.jerry.sample.listview.BaseAdapterListView.adapter.MsgComingItemDelagateForListView;
import com.jerry.sample.listview.BaseAdapterListView.adapter.MsgSendItemDelagateForListView;
import com.jerry.sample.listview.ChatMessage;
import com.zhy.adapter.abslistview.MultiItemTypeAdapter;

import java.util.List;
/**
 * Created by zhy on 15/9/4.
 */
public class ChatAdapterForListView extends MultiItemTypeAdapter<ChatMessage> {
    public ChatAdapterForListView(Context context, List<ChatMessage> datas) {
        super(context, datas);

        addItemViewDelegate(new MsgSendItemDelagateForListView());
        addItemViewDelegate(new MsgComingItemDelagateForListView());
    }

}
