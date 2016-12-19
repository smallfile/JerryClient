package com.jerry.lib.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.jerry.lib.R;
import com.jerry.lib.common.MyActivityManager;
import com.jerry.lib.utils.PreferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    private Context mContext;
    /**
     * 定义三个切换动画
     */
    private Animation mFadeIn;
    private Animation mFadeOut;
    private Animation mFadeInScale;

    @BindView(R.id.splash_bg)
    ImageView splashBg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = SplashActivity.this;
        checkAction();
    }

    private void checkAction() {
        boolean isFirst = PreferenceUtils.getInstance().getIsFirstStart();
        if (isFirst == true) {
            PreferenceUtils.getInstance().saveFirstStart(false);
            openWelcomePage();
        } else {
            openMainPage();
        }
    }

    /**
     * 进入欢迎界面
     */
    private void openWelcomePage() {
        MyActivityManager.getInstance().startActivity(mContext,WelcomeActivity.class,null);
        finish();
    }

    /**
     * 直接进入app
     */
    private void openMainPage() {
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        splashBg.setImageResource(R.mipmap.splash_bg);
        initAnim();
        setAnimListener();
    }

    /**
     * 建立监听事件
     */
    private void setAnimListener() {
        mFadeIn.setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {

            }

            public void onAnimationRepeat(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {
                splashBg.startAnimation(mFadeInScale);
            }
        });
        mFadeInScale.setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {


            }

            public void onAnimationRepeat(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {
                splashBg.startAnimation(mFadeOut);
            }
        });
        mFadeOut.setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {
                MyActivityManager.getInstance().startActivity(mContext,MainActivity.class,null);
                finish();
            }

            public void onAnimationRepeat(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {

            }
        });
    }

    /**
     * 初始化动画
     */
    private void initAnim() {
        mFadeIn = AnimationUtils.loadAnimation(this, R.anim.splash_fade_in);
        mFadeIn.setDuration(500);
        mFadeInScale = AnimationUtils.loadAnimation(this,
                R.anim.splash_fade_in_scale);
        mFadeInScale.setDuration(2000);
        mFadeOut = AnimationUtils.loadAnimation(this, R.anim.splash_fade_out);
        mFadeOut.setDuration(500);
        splashBg.setAnimation(mFadeIn);
    }

}
