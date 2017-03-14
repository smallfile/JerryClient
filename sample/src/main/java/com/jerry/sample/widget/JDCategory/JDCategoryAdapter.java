package com.jerry.sample.widget.JDCategory;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jerry.sample.R;

public class JDCategoryAdapter extends BaseAdapter {

	private Context context;
	private String[] strings;
	public static int mPosition;
	
	public JDCategoryAdapter(Context context, String[] strings){
		this.context =context;
		this.strings = strings;
	}
	
	@Override
	public int getCount() {
		return strings.length;
	}

	@Override
	public Object getItem(int position) {
		return strings[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		convertView = LayoutInflater.from(context).inflate(R.layout.jd_category_listview_item, null);
		TextView tv = (TextView) convertView.findViewById(R.id.tv);
		mPosition = position;
		tv.setText(strings[position]);
		if (position == JDCategoryActivity.mPosition) {
			convertView.setBackgroundResource(R.drawable.jd_category_listview_selected_bg);
		} else {
			convertView.setBackgroundColor(Color.parseColor("#f4f4f4"));
		}
		
		return convertView;
	}
}
