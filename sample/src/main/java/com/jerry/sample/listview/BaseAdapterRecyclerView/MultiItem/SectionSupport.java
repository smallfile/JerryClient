package com.jerry.sample.listview.BaseAdapterRecyclerView.MultiItem;

/**
 * Created by jerry on 2017/3/12.
 */

public interface SectionSupport<T> {

    // header所对应的布局文件
    public int sectionHeaderLayoutId();

    // 显示header的title对应的TextView
    public int sectionTitleTextViewId();

    // 显示的title是什么（一般肯定根据Bean生成）
    public String getTitle(T t);
}
