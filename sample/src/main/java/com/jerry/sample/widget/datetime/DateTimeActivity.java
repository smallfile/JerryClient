/*
 * Copyright (C) 2011 Make Ramen, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jerry.sample.widget.datetime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.jerry.sample.R;
import com.jerry.uilib.widget.NavigationBar;
import com.jerry.uilib.widget.datetime.DatePicker;

import java.util.Calendar;

public class DateTimeActivity extends Activity {

	PopupWindow popupWindow;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_datetime);

		findViewById(R.id.dark_button).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						startActivity(new Intent(DateTimeActivity.this,
								DarkThemeActivity.class));
					}
				});

		findViewById(R.id.light_button).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						startActivity(new Intent(DateTimeActivity.this,
								LightThemeActivity.class));
					}
				});

		final Button button3 = (Button) findViewById(R.id.date_button);
		button3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				popupWindow = new MyPopupWindow(DateTimeActivity.this,
						button3);
			}
		});

	}

	public class MyPopupWindow extends PopupWindow {

		private View view;
		private Context mContext;
		private DatePicker datePicker;
		private Calendar mCalendar;
		private NavigationBar navBar;
		private Animation animIn,animOut;
		
		public MyPopupWindow(Context context, View parent) {
			mContext = context;
			view = View.inflate(context, R.layout.date_view, null);
			initView();
			addViewConfig(parent);
			initListener();
		}
		
		private void initView() {
			datePicker = (DatePicker) view.findViewById(R.id.datePicker);
			navBar = (NavigationBar) view.findViewById(R.id.nav_bar);
			animIn = AnimationUtils.loadAnimation(mContext,R.anim.anim_down_in);
			animOut = AnimationUtils.loadAnimation(mContext,R.anim.anim_down_out);
		}
		
		private void addViewConfig(View parent) {
			setWidth(LayoutParams.MATCH_PARENT);
			setHeight(LayoutParams.WRAP_CONTENT);
			setFocusable(true);
			setOutsideTouchable(true);
			setBackgroundDrawable(new BitmapDrawable());
			setContentView(view);
			showAtLocation(parent, Gravity.BOTTOM, 0, 0);
			
			view.startAnimation(animIn);
			update();
		}
		
		private void initListener() {
			mCalendar = Calendar.getInstance();
			OnClickListener listener0 = new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					view.startAnimation(animOut);
					popupWindow.dismiss();
				}
			};
			navBar.setLeftBtnListener(listener0);

			OnClickListener listener3 = new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					mCalendar.set(Calendar.YEAR, datePicker.getYear());
					mCalendar.set(Calendar.MONTH, datePicker.getMonth());
					mCalendar.set(Calendar.DAY_OF_MONTH, datePicker.getDay());
					Toast.makeText(DateTimeActivity.this,
							mCalendar.getTime().toLocaleString(),
							Toast.LENGTH_LONG).show();
				}
			};
			navBar.setRightBtn3Listener(listener3);
		}
	}

}