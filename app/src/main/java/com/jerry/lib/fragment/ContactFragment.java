package com.jerry.lib.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jerry.lib.R;

public class ContactFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_contact, container, false);

        return mView;
    }

    @Override
    public void onClick(View v) {


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
