package com.jerry.sample.widget;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jerry.sample.R;
import com.jerry.uilib.widget.uitableview.BasicItem;
import com.jerry.uilib.widget.uitableview.UITableView;
import com.jerry.uilib.widget.uitableview.ViewItem;

public class UITableViewActivity extends Activity {

	private UITableView mTableView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_uitableview);
		mTableView = (UITableView) findViewById(R.id.tableView);
		createList();
		mTableView.commit();
	}

	private void createList() {
		CustomClickListener listener = new CustomClickListener();
		mTableView.setClickListener(listener);

		BasicItem i1 = new BasicItem("User Dummy 1");
		i1.setDrawable(R.mipmap.ic_launcher);
		mTableView.addBasicItem(i1);

		BasicItem i3 = new BasicItem("User Dummy 2");
		i3.setDrawable(R.mipmap.ic_launcher);
		i3.setSubtitle("inactive");
		mTableView.addBasicItem(i3);

		TextView textview = new TextView(UITableViewActivity.this);
		textview.setText("这是一个TextView");
		ViewItem v1 = new ViewItem(textview);
		v1.setClickable(false);
		mTableView.addViewItem(v1);

	}

	private class CustomClickListener implements UITableView.ClickListener {
		@Override
		public void onClick(View view) {
			Toast.makeText(UITableViewActivity.this, "item clicked",
					Toast.LENGTH_SHORT).show();
		}

	}

}
