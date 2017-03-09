package com.jerry.uilib.widget;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jerry.uilib.R;

public class MyDialog extends Dialog {

    private Context mContext;
    private TextView mTitle;
    private TextView mMessage;
    private Button mConfirm;
    private Button mCancel;

    public MyDialog(Context context) {
        super(context,R.style.MyDialog);
        mContext=context;
    }

    public MyDialog(Context context, int theme) {
        super(context, theme);
        mContext=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom);

        //设置为点击对话框之外的区域对话框不消失
        this.setCanceledOnTouchOutside(false);
        mTitle = (TextView) findViewById(R.id.dialog_title);
        mMessage = (TextView) findViewById(R.id.dialog_message);
        mConfirm= (Button) findViewById(R.id.dialog_ok);
        mCancel= (Button) findViewById(R.id.dialog_cancel);
        
        //设置事件
        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

}
