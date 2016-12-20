package com.jerry.client.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.jerry.client.R;

import butterknife.OnClick;

public class SettingsActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

    }

    @OnClick({R.id.change_password_layout, R.id.notification_layout, R.id.clean_cache_layout, R.id.exit_layout})
    public void clickView(View v) {
        switch (v.getId()) {
            case R.id.change_password_layout:
                break;
            case R.id.notification_layout:
                break;
            case R.id.clean_cache_layout:
                break;
            case R.id.exit_layout:
                break;
        }
    }

}
