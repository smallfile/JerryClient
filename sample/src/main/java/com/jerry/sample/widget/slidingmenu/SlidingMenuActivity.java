package com.jerry.sample.widget.slidingmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.jerry.sample.R;

public class SlidingMenuActivity extends Activity {

	Button slidingMenuBtn1;
	Button slidingMenuBtn2;
	Button slidingMenuBtn3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sliding_menu);
		
		slidingMenuBtn1=(Button) findViewById(R.id.sliding_menu_button1);
		slidingMenuBtn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(SlidingMenuActivity.this, SlidingMenuColorActivity.class);
				startActivity(intent);
			}
		});
		
		slidingMenuBtn2=(Button) findViewById(R.id.sliding_menu_button2);
		slidingMenuBtn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(SlidingMenuActivity.this, SlidingMenuBirdActivity.class);
				startActivity(intent);
			}
		});
		
		slidingMenuBtn3=(Button) findViewById(R.id.sliding_menu_button3);
		slidingMenuBtn3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(SlidingMenuActivity.this, SlidingMenuQQActivity.class);
				startActivity(intent);
			}
		});
		
	}
	
	
}
