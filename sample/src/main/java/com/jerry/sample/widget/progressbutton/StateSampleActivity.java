package com.jerry.sample.widget.progressbutton;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.jerry.sample.R;
import com.jerry.uilib.widget.progressbutton.ActionProcessButton;
import com.jerry.uilib.widget.progressbutton.GenerateProcessButton;
import com.jerry.uilib.widget.progressbutton.SubmitProcessButton;

public class StateSampleActivity extends Activity {

    private ActionProcessButton mBtnAction;
    private GenerateProcessButton mBtnGenerate;
    private SubmitProcessButton mBtnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process_button_states);

        mBtnAction = (ActionProcessButton) findViewById(R.id.btnAction);
        mBtnSubmit = (SubmitProcessButton) findViewById(R.id.btnSubmit);
        mBtnGenerate = (GenerateProcessButton) findViewById(R.id.btnGenerate);

        findViewById(R.id.btnProgressLoading).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mBtnAction.setProgress(50);
                mBtnSubmit.setProgress(50);
                mBtnGenerate.setProgress(50);
			}
        });
        findViewById(R.id.btnProgressDisable).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mBtnAction.setProgress(-1);
                mBtnSubmit.setProgress(-1);
                mBtnGenerate.setProgress(-1);
			}
        });
        findViewById(R.id.btnProgressComplete).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mBtnAction.setProgress(100);
                mBtnSubmit.setProgress(100);
                mBtnGenerate.setProgress(100);
			}
        });
        findViewById(R.id.btnProgressNormal).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mBtnAction.setProgress(0);
                mBtnSubmit.setProgress(0);
                mBtnGenerate.setProgress(0);
			}
        });
    }
}
