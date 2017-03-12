package com.jerry.sample.listview.BaseAdapterRecyclerView.MultiItem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jerry on 2017/3/12.
 */

public abstract class SectionAdapter<T> extends MultiItemCommonAdapter<T> {

    private SectionSupport mSectionSupport;
    private static final int TYPE_SECTION = 0;
    private LinkedHashMap<String, Integer> mSections;

    // 初始化了成员变量headerItemTypeSupport，分别对getLayoutId和getItemViewType进行了实现。
    private MultiItemTypeSupport<T> headerItemTypeSupport = new MultiItemTypeSupport<T>() {

        //如果type是header类型，则返回mSectionSupport.sectionHeaderLayoutId()；否则则返回mLayout.
        @Override
        public int getLayoutId(int itemType) {
            if (itemType == TYPE_SECTION)
                return mSectionSupport.sectionHeaderLayoutId();
            else
                return mLayoutId;
        }
        //根据位置判断，如果当前是header所在位置，返回header类型常量；否则返回1.
        @Override
        public int getItemViewType(int position, T o) {
            return mSections.values().contains(position) ? TYPE_SECTION : 1;
        }
    };

    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position, null);
    }

    final RecyclerView.AdapterDataObserver observer = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            findSections();
        }
    };


    public SectionAdapter(Context context, int layoutId, List<T> datas, SectionSupport sectionSupport) {
        super(context, datas, null);
        mLayoutId = layoutId;
        mMultiItemTypeSupport = headerItemTypeSupport;
        mSectionSupport = sectionSupport;
        mSections = new LinkedHashMap<>();
        findSections();

        //注册数据改变
        registerAdapterDataObserver(observer);
    }

    @Override
    protected boolean isEnabled(int viewType) {
        if (viewType == TYPE_SECTION)
            return false;
        return super.isEnabled(viewType);
    }

    // 每当我们的数据发生变化，我们的title集合，即mSections就可能会发生变化，所以需要重新生成，
    // 本来准备复写notifyDataSetChanged方法，在里面重新生成，没想到这个方法是final的，于是利用了registerAdapterDataObserver(observer);，
    // 在数据发生变化回调中重新生成，记得在onDetachedFromRecyclerView里面对注册的observer进行解注册。
    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        //解绑数据改变
        unregisterAdapterDataObserver(observer);
    }

    // 构造方法中调用了findSections()，主要为了存储我们的title和对应的position，通过一个Map  mSections来存储。
    public void findSections() {
        int n = mDatas.size();
        int nSections = 0;
        mSections.clear();

        for (int i = 0; i < n; i++) {
            String sectionName = mSectionSupport.getTitle(mDatas.get(i));

            if (!mSections.containsKey(sectionName)) {
                mSections.put(sectionName, i + nSections);
                nSections++;
            }
        }

    }

    //我们多了几个title肯定总数会增加，所以需要复写。
    @Override
    public int getItemCount() {
        return super.getItemCount() + mSections.size();
    }

    public int getIndexForPosition(int position) {
        int nSections = 0;

        Set<Map.Entry<String, Integer>> entrySet = mSections.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet) {
            if (entry.getValue() < position) {
                nSections++;
            }
        }
        return position - nSections;
    }

    // 在onBindViewHolder中我们有一行重置position的代码，因为我们的position变大了，所以在实际上绑定我们数据时，这个position需要还原，
    // 代码逻辑见getIndexForPosition(position)。
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        position = getIndexForPosition(position);
        if (holder.getItemViewType() == TYPE_SECTION) {
            holder.setText(mSectionSupport.sectionTitleTextViewId(), mSectionSupport.getTitle(mDatas.get(position)));
            return;
        }
        super.onBindViewHolder(holder, position);
    }
}
