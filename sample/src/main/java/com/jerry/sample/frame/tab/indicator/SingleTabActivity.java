package com.jerry.sample.frame.tab.indicator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jerry.sample.R;
import com.jerry.uilib.frame.indicator.view.indicator.FixedIndicatorView;
import com.jerry.uilib.frame.indicator.view.indicator.Indicator;
import com.jerry.uilib.frame.indicator.view.indicator.RecyclerIndicatorView;
import com.jerry.uilib.frame.indicator.view.indicator.ScrollIndicatorView;
import com.jerry.uilib.frame.indicator.view.indicator.slidebar.ColorBar;
import com.jerry.uilib.frame.indicator.view.indicator.transition.OnTransitionTextListener;

/**
 * Created by LuckyJayce on 2016/6/24.
 */
public class SingleTabActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_indicator_singletab);

        ScrollIndicatorView scrollIndicatorView = (ScrollIndicatorView) findViewById(R.id.singleTab_scrollIndicatorView);
        FixedIndicatorView fixedIndicatorView = (FixedIndicatorView) findViewById(R.id.singleTab_fixedIndicatorView);
        RecyclerIndicatorView recyclerIndicatorView = (RecyclerIndicatorView) findViewById(R.id.singleTab_reyclerIndicatorView);

        set(scrollIndicatorView, 16);
        set(fixedIndicatorView, 5);
        set(recyclerIndicatorView, 10000);
    }

    private void set(Indicator indicator, int count) {
        indicator.setAdapter(new MyAdapter(count));

        indicator.setScrollBar(new ColorBar(getApplicationContext(), Color.RED, 5));

        float unSelectSize = 16;
        float selectSize = unSelectSize * 1.2f;
        int selectColor = getResources().getColor(R.color.tab_top_text_2);
        int unSelectColor = getResources().getColor(R.color.tab_top_text_1);
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColor, unSelectColor).setSize(selectSize, unSelectSize));

        indicator.setCurrentItem(2,true);
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

    private class MyAdapter extends Indicator.IndicatorAdapter {

        private final int count;

        public MyAdapter(int count) {
            super();
            this.count = count;
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.tab_indicator_top, parent, false);
            }
            TextView textView = (TextView) convertView;
            //用了固定宽度可以避免TextView文字大小变化，tab宽度变化导致tab抖动现象
            textView.setWidth(dipToPix(50));
            textView.setText(String.valueOf(position));
            return convertView;
        }
    }
}
