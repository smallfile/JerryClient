package com.jerry.sample.widget;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.jerry.sample.R;
import com.jerry.uilib.widget.NavigationBar;


public class NavigationBarActivity extends Activity {

	private static final String TAG = NavigationBarActivity.class.getSimpleName();
	NavigationBar navBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_navigation_bar);
		navBar = (NavigationBar) findViewById(R.id.nav_bar);
		
		OnClickListener listener0 = new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Log.i(TAG,"right button 0");
			}
		};
		navBar.setLeftBtnListener(listener0);
		
		OnClickListener listener1 = new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Log.i(TAG,"right button 1");
			}
		};
		navBar.setRightBtn1Listener(listener1);
		
		OnClickListener listener2 = new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Log.i(TAG,"right button 2");
			}
		};
		navBar.setRightBtn2Listener(listener2);
		
		OnClickListener listener3 = new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Log.i(TAG,"right button 3");
			}
		};
		navBar.setRightBtn3Listener(listener3);
	}

}
