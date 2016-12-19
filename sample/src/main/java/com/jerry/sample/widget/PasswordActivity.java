package com.jerry.sample.widget;

import android.app.Activity;
import android.os.Bundle;

import com.jerry.sample.R;
import com.jerry.uilib.widget.password.GridPasswordView;

public class PasswordActivity extends Activity {
	
	GridPasswordView gpvNormal, gpvLength, gpvCustomUi;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_password);
		
		gpvNormal = (GridPasswordView)findViewById(R.id.gpv_normal);
		gpvLength = (GridPasswordView)findViewById(R.id.gpv_length);
		gpvCustomUi = (GridPasswordView)findViewById(R.id.gpv_customUi);
		
		
		
	}

}
