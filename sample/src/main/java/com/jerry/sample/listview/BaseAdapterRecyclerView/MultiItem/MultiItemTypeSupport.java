package com.jerry.sample.listview.BaseAdapterRecyclerView.MultiItem;

/**
 * 多种ItemViewType，一般我们的写法是：
   1. 复写getItemViewType，根据我们的bean去返回不同的类型
   2. 复写onCreateViewHolder，根据itemView去生成不同的ViewHolder
 　　　我们的ViewHolder是通用的，唯一依赖的就是个layoutId。那么就变成，根据不同的itemView告诉我用哪个layoutId即可，
 　　　生成viewholder这种事我们通用adapter来做。
 */
public interface MultiItemTypeSupport<T> {

    int getLayoutId(int itemType);

    int getItemViewType(int position, T t);
}
