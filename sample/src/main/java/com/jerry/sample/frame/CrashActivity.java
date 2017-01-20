package com.jerry.sample.frame;

import android.app.Activity;
import android.os.Bundle;

public class CrashActivity extends Activity {

    private String str;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println(str.equals("any string"));
    }

}
