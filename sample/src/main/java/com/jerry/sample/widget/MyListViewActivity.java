package com.jerry.sample.widget;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.jerry.sample.R;
import com.jerry.uilib.widget.MyListView;

public class MyListViewActivity extends Activity {

	MyListView mListView;
	private static final String[] array = { "sunday", "monday", "tuesday","wednesday", "thursday", "friday", "saturday",
											"sunday", "monday", "tuesday","wednesday", "thursday", "friday", "saturday",
											"sunday", "monday", "tuesday","wednesday", "thursday", "friday", "saturday",
											"sunday", "monday", "tuesday","wednesday", "thursday", "friday", "saturday",
											"sunday", "monday", "tuesday","wednesday", "thursday", "friday", "saturday",
											"sunday", "monday", "tuesday","wednesday", "thursday", "friday", "saturday"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_list_view);

		mListView = (MyListView) findViewById(R.id.listView);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, array);
		mListView.setAdapter(adapter);

		// 榧犳爣婊氳疆杞姩鏃惰Е鍙�?
		mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {

					}

					public void onNothingSelected(AdapterView<?> arg0) {

					}
				});

		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

			}
		});

	}
}
