package com.jerry.client.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jerry.client.R;
import com.jerry.client.activity.AboutActivity;
import com.jerry.client.activity.SettingsActivity;
import com.jerry.client.common.MyActivityManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeFragment extends Fragment {

    private Context mContext;
    @BindView(R.id.me_header)
    ImageView mHeader;
    @BindView(R.id.me_name)
    TextView mName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_me, container, false);
        ButterKnife.bind(this,mView);
        mContext = this.getActivity();

        return mView;
    }

    @OnClick({R.id.profile_layout, R.id.setting_layout, R.id.service_layout, R.id.about_layout})
    public void clickView(View view){
        switch (view.getId()) {
            case R.id.profile_layout:

                break;
            case R.id.setting_layout:
                MyActivityManager.getInstance().startActivity(mContext, SettingsActivity.class,null);
                break;
            case R.id.service_layout:

                break;
            case R.id.about_layout:
                MyActivityManager.getInstance().startActivity(mContext, AboutActivity.class,null);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
