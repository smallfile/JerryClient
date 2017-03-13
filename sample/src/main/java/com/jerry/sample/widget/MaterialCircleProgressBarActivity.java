package com.jerry.sample.widget;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jerry.sample.R;
import com.jerry.uilib.widget.MaterialCircleProgressBar.MyMaterialCircleProgressBar;


public class MaterialCircleProgressBarActivity extends ActionBarActivity {
    int progress = 0;
    private Handler handler;
    MyMaterialCircleProgressBar progress1;
    MyMaterialCircleProgressBar progress2;
    MyMaterialCircleProgressBar progressWithArrow;
    MyMaterialCircleProgressBar progressWithoutBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_circle_progressbar);
        progress1 = (MyMaterialCircleProgressBar) findViewById(R.id.progress1);
        progress2 = (MyMaterialCircleProgressBar) findViewById(R.id.progress2);
        progressWithArrow = (MyMaterialCircleProgressBar) findViewById(R.id.progressWithArrow);
        progressWithoutBg = (MyMaterialCircleProgressBar) findViewById(R.id.progressWithoutBg);

//        progress1.setColorSchemeResources(android.R.color.holo_blue_bright);
        progress2.setColorSchemeResources(android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        progressWithArrow.setColorSchemeResources(android.R.color.holo_orange_light);
        progressWithoutBg.setColorSchemeResources(android.R.color.holo_red_light);

        handler = new Handler();
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(finalI *10>=90){
                        progress1.setVisibility(View.VISIBLE);
                        progress2.setVisibility(View.INVISIBLE);
                    }else {
                        progress2.setProgress(finalI * 10);
                    }
                }
            },1000*(i+1));
        }

    }


}
