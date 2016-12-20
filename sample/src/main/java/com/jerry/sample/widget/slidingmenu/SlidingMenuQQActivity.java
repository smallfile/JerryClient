package com.jerry.sample.widget.slidingmenu;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;

import com.jerry.sample.R;
import com.jerry.uilib.widget.NavigationBar;
import com.jerry.uilib.widget.slidingmenu.SlidingMenu;
import com.jerry.uilib.widget.slidingmenu.app.SlidingFragmentActivity;

public class SlidingMenuQQActivity extends SlidingFragmentActivity {

	NavigationBar navBar;
    private Fragment mContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sliding_menu_content_frame);

        navBar = (NavigationBar) findViewById(R.id.nav_bar);
		OnClickListener listener0 = new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				toggle();
			}
		};
		navBar.setLeftBtnListener(listener0);
		
        // check if the content frame contains the menu frame
        if (findViewById(R.id.menu_frame) == null) {
            setBehindContentView(R.layout.sliding_menu_frame);
            getSlidingMenu().setSlidingEnabled(true);
            getSlidingMenu()
                    .setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        } else {
            // add a dummy view
            View v = new View(this);
            setBehindContentView(v);
            getSlidingMenu().setSlidingEnabled(false);
            getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }

        // set the Above View Fragment
        if (savedInstanceState != null) {
            mContent = getSupportFragmentManager().getFragment(
                    savedInstanceState, "mContent");
        }

        if (mContent == null) {
            mContent = new ContentFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, mContent).commit();

        // set the Behind View Fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.menu_frame, new QQMenuFragment()).commit();

        // customize the SlidingMenu
        SlidingMenu sm = getSlidingMenu();
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        sm.setFadeEnabled(false);
        sm.setBehindScrollScale(0.25f);
        sm.setFadeDegree(0.25f);

        sm.setBackgroundImage(R.drawable.background_transparent);
        sm.setBehindCanvasTransformer(new SlidingMenu.CanvasTransformer() {
            @Override
            public void transformCanvas(Canvas canvas, float percentOpen) {
                float scale = (float) (percentOpen * 0.25 + 0.75);
                canvas.scale(scale, scale, -canvas.getWidth() / 2,
                        canvas.getHeight() / 2);
            }
        });

        sm.setAboveCanvasTransformer(new SlidingMenu.CanvasTransformer() {
            @Override
            public void transformCanvas(Canvas canvas, float percentOpen) {
                float scale = (float) (1 - percentOpen * 0.25);
                canvas.scale(scale, scale, 0, canvas.getHeight() / 2);
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "mContent", mContent);
    }
}
