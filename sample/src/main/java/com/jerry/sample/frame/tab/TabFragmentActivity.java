package com.jerry.sample.frame.tab;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

import com.jerry.sample.R;

public class TabFragmentActivity extends FragmentActivity {

	private static final String TAB_TAG_ONE = "one";
	private static final String TAB_TAG_TWO = "two";
	private static final String TAB_TAG_THREE = "three";
	
	private TabHost tabHost;
	private FragmentTransaction fragmentTransaction;
	private OneFragment oneFragment;
	private TwoFragment twoFragment;
	private ThreeFragment threeFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab);
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();
		
		initTabHost();
	}

	OnTabChangeListener tabChangeListener = new OnTabChangeListener() {
		@Override
		public void onTabChanged(String tabTag) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			oneFragment = (OneFragment)fragmentManager.findFragmentByTag(TAB_TAG_ONE);
			twoFragment = (TwoFragment)fragmentManager.findFragmentByTag(TAB_TAG_TWO);
			threeFragment = (ThreeFragment)fragmentManager.findFragmentByTag(TAB_TAG_THREE);
			
			fragmentTransaction = fragmentManager.beginTransaction();
			detachFragment();
			showTabFragment(tabTag);
			fragmentTransaction.commit();	
		}
	};
	
	private void detachFragment() {
		if (oneFragment != null) {
			fragmentTransaction.detach(oneFragment);
		}
		if (twoFragment != null) {
			fragmentTransaction.detach(twoFragment);
		}
		if (threeFragment != null) {
			fragmentTransaction.detach(threeFragment);
		}
	}
	
	private void showTabFragment(String tabTag) {
		int tabId = getTabId(tabTag);
		switch (tabId) {
		case 1:
			showOneFragment();
			break;
		case 2:
			showTwoFragment();
			break;
		case 3:
			showThreeFragment();
			break;
		default :
			showOneFragment();
			break;
		}
	}
	
	private int getTabId(String tabTag) {
		int tabId = 0;
		if(tabTag.equalsIgnoreCase(TAB_TAG_ONE)){
			tabId = 1;
		} else if(tabTag.equalsIgnoreCase(TAB_TAG_TWO)){	
			tabId = 2;
		} else if(tabTag.equalsIgnoreCase(TAB_TAG_THREE)){	
			tabId = 3;
		} 
		return tabId;
	}
	
	public void showOneFragment() {
		if (oneFragment == null) {
			fragmentTransaction.add(android.R.id.tabcontent, new OneFragment(), TAB_TAG_ONE);
		} else {
			fragmentTransaction.attach(oneFragment);
		}
	}
	
	public void showTwoFragment() {
		if (twoFragment == null) {
			fragmentTransaction.add(android.R.id.tabcontent, new TwoFragment(), TAB_TAG_TWO);
		} else {
			fragmentTransaction.attach(twoFragment);
		}
	}
	
	public void showThreeFragment() {
		if (threeFragment == null) {
			fragmentTransaction.add(android.R.id.tabcontent, new ThreeFragment(), TAB_TAG_THREE);
		} else {
			fragmentTransaction.attach(threeFragment);
		}
	}
	
	private void initTabHost() {

		tabHost.addTab(tabHost
				.newTabSpec(TAB_TAG_ONE)
				.setIndicator(new TabIndicator(this, TAB_TAG_ONE))
				.setContent(new DefaultTabContentFragment(getBaseContext())));
		
		tabHost.addTab(tabHost
				.newTabSpec(TAB_TAG_TWO)
				.setIndicator(new TabIndicator(this, TAB_TAG_TWO))
				.setContent(new DefaultTabContentFragment(getBaseContext())));

		tabHost.addTab(tabHost
				.newTabSpec(TAB_TAG_THREE)
				.setIndicator(new TabIndicator(this, TAB_TAG_THREE))
				.setContent(new DefaultTabContentFragment(getBaseContext())));

		//璁剧疆鍒濆閫夐」鍗�?  
        tabHost.setCurrentTab(1);
        tabHost.setOnTabChangedListener(tabChangeListener);
        tabHost.setCurrentTab(0);
	}

	public class TabIndicator extends LinearLayout {
		public TabIndicator(Context context, String title) {
			super(context);
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View tab = inflater.inflate(
					R.layout.tab_indicator, this);
			if (title != null && title.length() > 0) {
				TextView txtView = (TextView) tab.findViewById(R.id.tab_title);
				txtView.setText(title);
			}
		}
	}

}
