package com.jerry.sample.frame.tab.indicator.year;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jerry.sample.R;
import com.jerry.uilib.frame.indicator.view.indicator.Indicator;
import com.jerry.uilib.frame.indicator.view.indicator.IndicatorViewPager;
import com.jerry.uilib.frame.indicator.view.indicator.RecyclerIndicatorView;
import com.jerry.uilib.frame.indicator.view.indicator.slidebar.SpringBar;
import com.jerry.uilib.frame.indicator.view.indicator.transition.OnTransitionTextListener;

/**
 * Created by LuckyJayce on 2016/6/23.
 */
public class YearActivity extends FragmentActivity {
    private IndicatorViewPager indicatorViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_indicator_year);

        ViewPager viewPager = (ViewPager) findViewById(R.id.year_viewPager);
        Indicator indicator = (RecyclerIndicatorView) findViewById(R.id.year_indicator);
        int selectColorId = Color.parseColor("#f8f8f8");
        int unSelectColorId = Color.parseColor("#010101");
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColorId, unSelectColorId));
        indicator.setScrollBar(new SpringBar(getApplicationContext(), Color.GRAY));
        viewPager.setOffscreenPageLimit(4);
        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        indicatorViewPager.setAdapter(new YearAdapter(1800, 10000000));
        indicatorViewPager.setCurrentItem(2016-1800, false);
    }

    /**
     * 根据dip值转化成px值
     *
     * @param dip
     * @return
     */
    private int dipToPix(float dip) {
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
        return size;
    }

    private class YearAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
        private int startYear;
        private int endYear;

        public YearAdapter(int startYear, int endYear) {
            super(getSupportFragmentManager());
            this.startYear = startYear;
            this.endYear = endYear;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.tab_indicator_top, container, false);
            }
            TextView textView = (TextView) convertView;
            int padding = dipToPix(12);
            textView.setPadding(padding, 0, padding, 0);
            textView.setText(String.valueOf(startYear + position));
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            YearFragment yearFragment = new YearFragment();
            Bundle a = new Bundle();
            a.putInt(YearFragment.INTENT_INT_POSITION, position);
            yearFragment.setArguments(a);
            return yearFragment;
        }

        @Override
        public int getCount() {
            return endYear - startYear;
        }
    }
}
