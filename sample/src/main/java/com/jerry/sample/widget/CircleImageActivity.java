package com.jerry.sample.widget;

import android.app.Activity;
import android.os.Bundle;

import com.jerry.sample.R;
import com.jerry.uilib.widget.RoundedImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class CircleImageActivity extends Activity {

	private CircleImageView mCircleImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_circle_image);

		mCircleImageView = (CircleImageView)findViewById(R.id.circle_image);
		mCircleImageView.setBackgroundResource(R.drawable.sliding_menu_heron);


	}

}
