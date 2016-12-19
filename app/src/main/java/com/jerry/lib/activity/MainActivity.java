package com.jerry.lib.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jerry.lib.R;
import com.jerry.lib.fragment.ChatFragment;
import com.jerry.lib.fragment.ContactFragment;
import com.jerry.lib.fragment.FoundFragment;
import com.jerry.lib.fragment.MeFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jerry.lib.R.id.chat_layout;

public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener{

    private Context mContext;
    private List<Fragment> mFragment = new ArrayList<>();

    @BindView(R.id.main_viewpager)
    ViewPager mViewPager;

    @BindView(chat_layout)
    RelativeLayout mChatLayout;
    @BindView(R.id.contact_layout)
    RelativeLayout mContactLayout;
    @BindView(R.id.find_layout)
    RelativeLayout mFindLayout;
    @BindView(R.id.me_layout)
    RelativeLayout mMeLayout;

    @BindView(R.id.tab_img_chat)
    ImageView mImageChat;
    @BindView(R.id.tab_img_contact)
    ImageView mImageContact;
    @BindView(R.id.tab_img_find)
    ImageView mImageFind;
    @BindView(R.id.tab_img_me)
    ImageView mImageMe;

    @BindView(R.id.tab_text_chat)
    TextView mTextChat;
    @BindView(R.id.tab_text_contact)
    TextView mTextContact;
    @BindView(R.id.tab_text_find)
    TextView mTextFind;
    @BindView(R.id.tab_text_me)
    TextView mTextMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        ButterKnife.bind(this);

        initTabItemImageAndColor();
        changeSelectedTabState(0);
        initMainViewPager();
    }

    private void initMainViewPager() {
        mFragment.add(new ChatFragment());
        mFragment.add(new ContactFragment());
        mFragment.add(new FoundFragment());
        mFragment.add(new MeFragment());
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }
        };
        mViewPager.setAdapter(fragmentPagerAdapter);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        initTabItemImageAndColor();
        changeSelectedTabState(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void initTabItemImageAndColor() {
        mImageChat.setBackgroundResource(R.drawable.tab_chat);
        mImageContact.setBackgroundResource(R.drawable.tab_contact);
        mImageFind.setBackgroundResource(R.drawable.tab_found);
        mImageMe.setBackgroundResource(R.drawable.tab_me);
        mTextChat.setTextColor(Color.parseColor("#abadbb"));
        mTextContact.setTextColor(Color.parseColor("#abadbb"));
        mTextFind.setTextColor(Color.parseColor("#abadbb"));
        mTextMe.setTextColor(Color.parseColor("#abadbb"));
    }

    private void changeSelectedTabState(int position) {
        switch (position) {
            case 0:
                mTextChat.setTextColor(Color.parseColor("#0099ff"));
                mImageChat.setBackgroundResource(R.mipmap.tab_chat_selected);
                break;
            case 1:
                mTextContact.setTextColor(Color.parseColor("#0099ff"));
                mImageContact.setBackgroundResource(R.mipmap.tab_contact_selected);
                break;
            case 2:
                mTextFind.setTextColor(Color.parseColor("#0099ff"));
                mImageFind.setBackgroundResource(R.mipmap.tab_found_selected);
                break;
            case 3:
                mTextMe.setTextColor(Color.parseColor("#0099ff"));
                mImageMe.setBackgroundResource(R.mipmap.tab_me_selected);
                break;
        }
    }

    @OnClick({R.id.me_layout, R.id.find_layout, R.id.contact_layout, R.id.chat_layout})
    public void clickTabItem(View v) {
        switch (v.getId()) {
            case chat_layout:
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.contact_layout:
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.find_layout:
                mViewPager.setCurrentItem(2, false);
                break;
            case R.id.me_layout:
                mViewPager.setCurrentItem(3, false);
                break;
        }
    }

    private static Boolean isQuit = false;
    Timer timer = new Timer();
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isQuit == false) {
                isQuit = true;
                Toast.makeText(getBaseContext(), "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();

                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        isQuit = false;
                    }
                };
                timer.schedule(task, 2000);
            } else {
                finish();
                System.exit(0);
            }
        }
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
