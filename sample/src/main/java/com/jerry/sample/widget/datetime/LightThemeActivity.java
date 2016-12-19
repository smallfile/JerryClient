package com.jerry.sample.widget.datetime;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.jerry.sample.R;
import com.jerry.uilib.widget.datetime.DatePicker;
import com.jerry.uilib.widget.datetime.TimePicker;

import java.util.Calendar;

public class LightThemeActivity extends Activity {

	DatePicker datePicker;
	TimePicker timePicker;
	TextView timeView;
	Button submitView;
	
	Calendar mCalendar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.datetime_light);
		mCalendar = Calendar.getInstance();

		datePicker = (DatePicker) findViewById(R.id.datePicker);
		timePicker = (TimePicker) findViewById(R.id.timePicker);
		timeView = (TextView) findViewById(R.id.time_view);
		submitView = (Button) findViewById(R.id.get_time_btn);

		submitView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				mCalendar.set(Calendar.YEAR, datePicker.getYear());
				mCalendar.set(Calendar.MONTH, datePicker.getMonth());
				mCalendar.set(Calendar.DAY_OF_MONTH, datePicker.getDay());
				mCalendar.set(Calendar.HOUR_OF_DAY, timePicker.getHourOfDay());
				mCalendar.set(Calendar.MINUTE, timePicker.getMinute());
				timeView.setText(mCalendar.getTime().toLocaleString());
			}
		});
		
	}
}
