package com.jerry.sample.listview.BaseAdapterRecyclerView.adapter;

import android.content.Context;

import com.jerry.sample.listview.ChatMessage;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;

/**
 * Created by zhy on 15/9/4.
 */
public class ChatAdapterForRecyclerView extends MultiItemTypeAdapter<ChatMessage> {

    public ChatAdapterForRecyclerView(Context context, List<ChatMessage> datas) {
        super(context, datas);

        addItemViewDelegate(new MsgSendItemDelagateForRecyclerView());
        addItemViewDelegate(new MsgComingItemDelagateForRecyclerView());
    }
}
