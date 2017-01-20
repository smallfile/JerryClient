package com.jerry.sample.frame.tab.tabhost;

import android.content.Context;
import android.view.View;
import android.widget.TabHost.TabContentFactory;

public class DefaultTabContentFragment implements TabContentFactory{
	private Context mContext;
	
	public DefaultTabContentFragment(Context context){
		mContext = context;
	}
			

	@Override
	public View createTabContent(String tag) {
		View v = new View(mContext);
		return v;
	}
	

}
