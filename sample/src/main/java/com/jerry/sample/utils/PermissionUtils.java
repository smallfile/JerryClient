package com.jerry.sample.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import com.jerry.sample.MyApplication;

public class PermissionUtils {

    static PermissionUtils instance;

    public PermissionUtils() {
    }

    public static PermissionUtils getInstance() {
        if (instance == null) {
            synchronized (PermissionUtils.class) {
                PermissionUtils inst = instance;
                if (inst == null) {
                    synchronized (PermissionUtils.class) {
                        instance = new PermissionUtils();
                    }
                }
            }
        }
        return instance;
    }

    //检查权限，判断集合中的权限是否全部已授权
    public boolean checkPermissionSet(String... permissions) {
        for (String permission : permissions) {
            if (isLackPermission(permission)) {
                return true;
            }
        }
        return false;
    }

    //判断是否缺少权限(PERMISSION_DENIED:权限是否足够)
    private boolean isLackPermission(String permission) {
        return ContextCompat.checkSelfPermission(MyApplication.getContext(), permission) == PackageManager.PERMISSION_DENIED;
    }
}
