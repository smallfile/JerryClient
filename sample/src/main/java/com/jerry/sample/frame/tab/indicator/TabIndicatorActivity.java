package com.jerry.sample.frame.tab.indicator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.jerry.sample.R;
import com.jerry.sample.frame.tab.indicator.tab.TabMore2Activity;
import com.jerry.sample.frame.tab.indicator.tab.TabMoreActivity;
import com.jerry.sample.frame.tab.indicator.tab.TabActivity;
import com.jerry.sample.frame.tab.indicator.year.YearActivity;

public class TabIndicatorActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_indicator);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * BannerComponent(FixedIndicatorView)
     *
     * @param view
     */
    public void onClickBanner(View view) {
        startActivity(new Intent(getApplicationContext(), BannerActivity.class));
    }

    /**
     * 引导界面(FixedIndicatorView)
     *
     * @param view
     */
    public void onClickGuide(View view) {
        startActivity(new Intent(getApplicationContext(), GuideActivity.class));
    }

    /**
     * tab主界面(FixedIndicatorView)
     *
     * @param view
     */
    public void onClickTabMain(View view) {
        startActivity(new Intent(getApplicationContext(), TabActivity.class));
    }

    /**
     * 可滑动tab界面(ScrollIndicatorView)
     *
     * @param view
     */
    public void onClickSlideTab(View view) {
        startActivity(new Intent(getApplicationContext(), TabMore2Activity.class));
    }

    /**
     * 可滑动tab界面(ScrollIndicatorView详细配置)
     *
     * @param view
     */
    public void onClickSlideTab2(View view) {
        startActivity(new Intent(getApplicationContext(), TabMoreActivity.class));
    }


    /**
     * springtab界面(ScrollIndicatorView)
     *
     * @param view
     */
    public void onClickSpringTab(View view) {
        startActivity(new Intent(getApplicationContext(), SpringActivity.class));
    }


    /**
     * 无数tab的界面(RecyclerIndicatorView)
     *
     * @param view
     */
    public void onClickYear(View view) {
        startActivity(new Intent(getApplicationContext(), YearActivity.class));
    }

    /**
     * 不集成ViewPager的Indicator使用方式
     *
     * @param view
     */
    public void onClickTabs(View view) {
        startActivity(new Intent(getApplicationContext(), SingleTabActivity.class));
    }


}
