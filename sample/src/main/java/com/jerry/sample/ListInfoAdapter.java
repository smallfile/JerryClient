package com.jerry.sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jerry on 16/5/14.
 */
public class ListInfoAdapter extends BaseAdapter {

    private List<ListInfoBean> mList;
    private Context mContext;

    public ListInfoAdapter(Context context) {
        this.mContext = context;
    }

    public void setListData(List<ListInfoBean> list) {
        this.mList = list;
    }

    @Override
    public int getCount() {
        if (null != mList) {
            return mList.size();
        } else {
            return 0;
        }
    }

    @Override
    public ListInfoBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.activity_main_item, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView
                    .findViewById(R.id.title);
            holder.subTitle = (TextView) convertView
                    .findViewById(R.id.subTitle);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ListInfoBean item = getItem(position);
        holder.title.setText(item.getTitle());
        holder.subTitle.setText(item.getSubTitle());

        return convertView;
    }

    class ViewHolder {
        TextView title;
        TextView subTitle;
    }
}
