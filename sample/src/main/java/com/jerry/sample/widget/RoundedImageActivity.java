package com.jerry.sample.widget;

import android.app.Activity;
import android.os.Bundle;

import com.jerry.sample.R;
import com.jerry.uilib.widget.RoundedImageView;

public class RoundedImageActivity extends Activity {

	private RoundedImageView mRoundedImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rounded_image);

		mRoundedImageView = (RoundedImageView)findViewById(R.id.rounded_image);
		mRoundedImageView.setBackgroundResource(R.mipmap.sliding_menu_heron);


	}

}
