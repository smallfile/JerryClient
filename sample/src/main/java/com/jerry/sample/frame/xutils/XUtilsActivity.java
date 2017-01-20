package com.jerry.sample.frame.xutils;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.jerry.sample.R;
import com.jerry.sample.frame.tab.tabhost.DefaultTabContentFragment;

public class XUtilsActivity extends FragmentActivity {

    private static final String TAB_TAG_HTTP = "http";
    private static final String TAB_TAG_DOWNLOAD = "download";
    private static final String TAB_TAG_BIGIMAGE = "big_image";

    private TabHost tabHost;
    private FragmentTransaction fragmentTransaction;
    private HttpFragment httpFragment;
    private DbFragment dbFragment;
    private ImageFragment imageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_tab_tabhost);
        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();

        initTabHost();
    }

    TabHost.OnTabChangeListener tabChangeListener = new TabHost.OnTabChangeListener() {
        @Override
        public void onTabChanged(String tabTag) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            httpFragment = (HttpFragment)fragmentManager.findFragmentByTag(TAB_TAG_HTTP);
            dbFragment = (DbFragment)fragmentManager.findFragmentByTag(TAB_TAG_DOWNLOAD);
            imageFragment = (ImageFragment)fragmentManager.findFragmentByTag(TAB_TAG_BIGIMAGE);

            fragmentTransaction = fragmentManager.beginTransaction();
            detachFragment();
            showTabFragment(tabTag);
            fragmentTransaction.commit();
        }
    };

    private void detachFragment() {
        if (httpFragment != null) {
            fragmentTransaction.detach(httpFragment);
        }
        if (dbFragment != null) {
            fragmentTransaction.detach(dbFragment);
        }
        if (imageFragment != null) {
            fragmentTransaction.detach(imageFragment);
        }
    }

    private void showTabFragment(String tabTag) {
        int tabId = getTabId(tabTag);
        switch (tabId) {
            case 1:
                showHttpFragment();
                break;
            case 2:
                showDownloadFragment();
                break;
            case 3:
                showBigImageFragment();
                break;
            default :
                showHttpFragment();
                break;
        }
    }

    private int getTabId(String tabTag) {
        int tabId = 0;
        if(tabTag.equalsIgnoreCase(TAB_TAG_HTTP)){
            tabId = 1;
        } else if(tabTag.equalsIgnoreCase(TAB_TAG_DOWNLOAD)){
            tabId = 2;
        } else if(tabTag.equalsIgnoreCase(TAB_TAG_BIGIMAGE)){
            tabId = 3;
        }
        return tabId;
    }

    public void showHttpFragment() {
        if (httpFragment == null) {
            fragmentTransaction.add(android.R.id.tabcontent, new HttpFragment(), TAB_TAG_HTTP);
        } else {
            fragmentTransaction.attach(httpFragment);
        }
    }

    public void showDownloadFragment() {
        if (dbFragment == null) {
            fragmentTransaction.add(android.R.id.tabcontent, new DbFragment(), TAB_TAG_DOWNLOAD);
        } else {
            fragmentTransaction.attach(dbFragment);
        }
    }

    public void showBigImageFragment() {
        if (imageFragment == null) {
            fragmentTransaction.add(android.R.id.tabcontent, new ImageFragment(), TAB_TAG_BIGIMAGE);
        } else {
            fragmentTransaction.attach(imageFragment);
        }
    }

    private void initTabHost() {

        tabHost.addTab(tabHost
                .newTabSpec(TAB_TAG_HTTP)
                .setIndicator(new TabIndicator(this, TAB_TAG_HTTP))
                .setContent(new DefaultTabContentFragment(getBaseContext())));

        tabHost.addTab(tabHost
                .newTabSpec(TAB_TAG_DOWNLOAD)
                .setIndicator(new TabIndicator(this, TAB_TAG_DOWNLOAD))
                .setContent(new DefaultTabContentFragment(getBaseContext())));

        tabHost.addTab(tabHost
                .newTabSpec(TAB_TAG_BIGIMAGE)
                .setIndicator(new TabIndicator(this, TAB_TAG_BIGIMAGE))
                .setContent(new DefaultTabContentFragment(getBaseContext())));

        tabHost.setCurrentTab(1);
        tabHost.setOnTabChangedListener(tabChangeListener);
        tabHost.setCurrentTab(0);
    }

    public class TabIndicator extends LinearLayout {
        public TabIndicator(Context context, String title) {
            super(context);
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View tab = inflater.inflate(
                    R.layout.tab_indicator, this);
            if (title != null && title.length() > 0) {
                TextView txtView = (TextView) tab.findViewById(R.id.tab_title);
                txtView.setText(title);
            }
        }
    }

}
