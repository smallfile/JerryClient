package com.jerry.sample.listview.ptlRecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jerry.sample.R;

public class PullToRefreshRecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("PullToRefreshRecyclerViewActivity");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitle("Sub title");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
    }

    public void pull_push(View view){
        startActivity(new Intent(this,PullAndPushActivity.class));
    }

    public void load_image(View view){
         startActivity(new Intent(this,LoadImageActivity.class));
    }

    public void swipe_menu(View view){
        startActivity(new Intent(this,SwipeMenuActivity.class));
    }

}
