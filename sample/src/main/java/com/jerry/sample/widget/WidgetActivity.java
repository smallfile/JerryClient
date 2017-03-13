package com.jerry.sample.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jerry.sample.ListInfoAdapter;
import com.jerry.sample.ListInfoBean;
import com.jerry.sample.R;
import com.jerry.sample.utils.MyActivityManager;
import com.jerry.sample.widget.banner.BannerActivity;
import com.jerry.sample.widget.calendar.Calendar2Activity;
import com.jerry.sample.widget.calendar.CalendarActivity;
import com.jerry.sample.widget.datetime.DateTimeActivity;
import com.jerry.sample.widget.progressbutton.ProgressButtonActivity;
import com.jerry.sample.widget.slidingmenu.SlidingMenuActivity;

import java.util.ArrayList;
import java.util.List;

public class WidgetActivity extends Activity {

    private Context mContext;
    private ListView mListView;
    private List<ListInfoBean> mListData;
    private ListInfoAdapter mListInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = WidgetActivity.this;
        mListData = new ArrayList<ListInfoBean>();

        initView();
        initData();

        mListInfoAdapter = new ListInfoAdapter(mContext);
        mListInfoAdapter.setListData(mListData);
        mListView.setAdapter(mListInfoAdapter);
    }

    private void initView(){
        mListView = (ListView) findViewById(R.id.listView);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long rowId) {
                ListInfoBean infoBean = mListData.get(position);
                String jumpActivity = infoBean.getOperate();
                if("nar_bar".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, NavigationBarActivity.class, null);
                } else  if("post_code".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, PostCodeActivity.class, null);
                } else  if("progress_bar".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, ProgressBarActivity.class, null);
                } else  if("my_grid_view".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, MyGridViewActivity.class, null);
                } else  if("my_list_view".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, MyListViewActivity.class, null);
                } else  if("rounded_image".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, RoundedImageActivity.class, null);
                } else  if("uitableview".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, UITableViewActivity.class, null);
                } else  if("password".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, PasswordActivity.class, null);
                } else  if("progress_button".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, ProgressButtonActivity.class, null);
                } else  if("zxing".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, ZXingActivity.class, null);
                } else  if("sliding_menu".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, SlidingMenuActivity.class, null);
                } else  if("segmented_radio".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, SegmentedRadioActivity.class, null);
                } else  if("banner".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, BannerActivity.class, null);
                } else  if("datetime".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, DateTimeActivity.class, null);
                } else  if("calendar".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, CalendarActivity.class, null);
                } else  if("calendar2".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, Calendar2Activity.class, null);
                } else  if("materialCircleProgressBar".equals(jumpActivity)){
                    MyActivityManager.getInstance().startActivity(mContext, MaterialCircleProgressBarActivity.class, null);
                }
            }
        });
    }

    private void initData(){

        ListInfoBean narBar = new ListInfoBean();
        narBar.setTitle("导航条");
        narBar.setOperate("nar_bar");
        mListData.add(narBar);

        ListInfoBean postCode = new ListInfoBean();
        postCode.setTitle("邮编");
        postCode.setOperate("post_code");
        mListData.add(postCode);

        ListInfoBean progressBar = new ListInfoBean();
        progressBar.setTitle("菊花加载提示");
        progressBar.setOperate("progress_bar");
        mListData.add(progressBar);

        ListInfoBean myGridView = new ListInfoBean();
        myGridView.setTitle("自定义GridView");
        myGridView.setOperate("my_grid_view");
        mListData.add(myGridView);

        ListInfoBean myListView = new ListInfoBean();
        myListView.setTitle("自定义ListView");
        myListView.setOperate("my_list_view");
        mListData.add(myListView);

        ListInfoBean roundedImage = new ListInfoBean();
        roundedImage.setTitle("圆形图片控件");
        roundedImage.setOperate("rounded_image");
        mListData.add(roundedImage);

        ListInfoBean uitableview = new ListInfoBean();
        uitableview.setTitle("UI Table View");
        uitableview.setOperate("uitableview");
        mListData.add(uitableview);

        ListInfoBean password = new ListInfoBean();
        password.setTitle("密码控件");
        password.setOperate("password");
        mListData.add(password);

        ListInfoBean progressButton = new ListInfoBean();
        progressButton.setTitle("Progress Button");
        progressButton.setOperate("progress_button");
        mListData.add(progressButton);

        ListInfoBean zxing = new ListInfoBean();
        zxing.setTitle("二维码扫描");
        zxing.setOperate("zxing");
        mListData.add(zxing);

        ListInfoBean slidingMenu = new ListInfoBean();
        slidingMenu.setTitle("侧边栏效果");
        slidingMenu.setOperate("sliding_menu");
        mListData.add(slidingMenu);

        ListInfoBean segmentedRadio = new ListInfoBean();
        segmentedRadio.setTitle("Segmented Radio");
        segmentedRadio.setOperate("segmented_radio");
        mListData.add(segmentedRadio);

        ListInfoBean banner = new ListInfoBean();
        banner.setTitle("Banner");
        banner.setOperate("banner");
        mListData.add(banner);

        ListInfoBean datetimer = new ListInfoBean();
        datetimer.setTitle("日期时间控件");
        datetimer.setOperate("datetime");
        mListData.add(datetimer);

        ListInfoBean calendar = new ListInfoBean();
        calendar.setTitle("日历控件");
        calendar.setOperate("calendar");
        mListData.add(calendar);

        ListInfoBean calendar2 = new ListInfoBean();
        calendar2.setTitle("日历控件2");
        calendar2.setOperate("calendar2");
        mListData.add(calendar2);

        ListInfoBean materialCircleProgressBar = new ListInfoBean();
        materialCircleProgressBar.setTitle("Material Circle ProgressBar");
        materialCircleProgressBar.setOperate("materialCircleProgressBar");
        mListData.add(materialCircleProgressBar);
    }

}
