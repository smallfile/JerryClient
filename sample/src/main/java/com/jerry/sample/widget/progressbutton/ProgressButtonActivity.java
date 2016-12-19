package com.jerry.sample.widget.progressbutton;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.jerry.sample.R;

public class ProgressButtonActivity extends FragmentActivity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_button);
        
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            		Intent intent = new Intent(ProgressButtonActivity.this, SignInActivity.class);
                intent.putExtra(SignInActivity.EXTRAS_ENDLESS_MODE, false);
                startActivity(intent);
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            		Intent intent = new Intent(ProgressButtonActivity.this, SignInActivity.class);
                intent.putExtra(SignInActivity.EXTRAS_ENDLESS_MODE, true);
                startActivity(intent);
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            		Intent intent = new Intent(ProgressButtonActivity.this, MessageActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent intent = new Intent(ProgressButtonActivity.this, UploadActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            		Intent intent = new Intent(ProgressButtonActivity.this, StateSampleActivity.class);
            		startActivity(intent);
            }
        });
        
    }
}
