package com.jerry.sample.frame;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jerry.sample.R;
import com.jerry.sample.utils.DialogUtils;
import com.jerry.sample.utils.PermissionUtils;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

public class PermissionActivity extends Activity {

    private Button btn1;
    private Button btn2;

    static final String[] PERMISSION = new String[] {
            Manifest.permission.WRITE_EXTERNAL_STORAGE, // 写存储
            Manifest.permission.CAMERA, // 相机
			Manifest.permission.CALL_PHONE, // 打电话
			Manifest.permission.ACCESS_FINE_LOCATION, // 位置
			Manifest.permission.ACCESS_COARSE_LOCATION };

    private static final int PERMISSION_REQUEST_CODE = 0;   //系统授权管理页面时的结果参数
    private static final int PERMISSION_REQUEST_SMS = 0;    //短信

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        btn1 = (Button)findViewById(R.id.btn1);
        View.OnClickListener btn1Listener = new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // 判断是否缺少权限
		        if (PermissionUtils.getInstance().checkPermissionSet(PERMISSION)) {
                    ActivityCompat.requestPermissions(PermissionActivity.this, PERMISSION, PERMISSION_REQUEST_CODE);
		        } else {
                    allPermissionGranted();//获取全部权限
                }

            }
        };
        btn1.setOnClickListener(btn1Listener);

//        btn2 = (Button)findViewById(R.id.btn2);
//        View.OnClickListener btn2Listener = new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                MPermissions.requestPermissions(PermissionActivity.this, PERMISSION_REQUEST_SMS, Manifest.permission.SEND_SMS);
//            }
//        };
//        btn2.setOnClickListener(btn2Listener);

    }

    //*********************　多权限时，使用以下代码　开始******************************//
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //多权限时，使用以下代码
        if (PERMISSION_REQUEST_CODE == requestCode && hasAllPermissionGranted(grantResults)) {
            allPermissionGranted();
        } else {
            startAppSettings();
        }
    }

    //获取全部权限
    private boolean hasAllPermissionGranted(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    //获取全部权限
    private void allPermissionGranted() {
        DialogInterface.OnClickListener yesListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        };
        new DialogUtils(this).showOkDialog("获取全部权限", yesListener);
    }

    //打开系统应用设置(ACTION_APPLICATION_DETAILS_SETTINGS:系统设置权限)
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }
    //*********************　多权限时，使用以下代码　结束******************************//



    //*********************　单权限时，使用以下代码　开始******************************//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
//    @PermissionGrant(PERMISSION_REQUEST_SMS)
//    public void requestSmsSuccess() {
//        Toast.makeText(this, "GRANT ACCESS SDCARD!", Toast.LENGTH_SHORT).show();
//    }
//    @PermissionDenied(PERMISSION_REQUEST_SMS)
//    public void requestSmsFailed() {
//        Toast.makeText(this, "DENY ACCESS SDCARD!", Toast.LENGTH_SHORT).show();
//    }
    //*********************　单权限时，使用以下代码　结束******************************//




}
