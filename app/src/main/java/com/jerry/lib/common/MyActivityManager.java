package com.jerry.lib.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Stack;

public class MyActivityManager {

	public static final String BUNDLE_STRING = "bundle_string";
	public static final String BUNDLE_INT = "bundle_int";
	public static final String BUNDLE_LIST = "bundle_list";
	public static final String BUNDLE_MAP = "bundle_map";
	public static final String BUNDLE_BEAN = "bundle_bean";

	private static Stack<Activity> activityStack;
	private static MyActivityManager instance;

	public static MyActivityManager getInstance() {
		if (instance == null){
			synchronized (MyActivityManager.class){
				instance = new MyActivityManager();
			}
		}
		return instance;
	}

	/**
	 * 退出栈顶Activity
	 * 栈顶Activity
	 */
	public void popActivity(Activity activity) {
		if (activity != null) {
			activity.finish();
			if (activityStack != null && !activityStack.empty()) {
				activityStack.remove(activity);
			}
			activity = null;
		}
	}

	/**
	 * 获得当前栈顶Activity
	 */
	public Activity currentActivity() {
		Activity activity = null;
		if (activityStack != null) {
			if (!activityStack.empty())
				activity = activityStack.lastElement();
		}
		return activity;
	}

	/**
	 * 退出栈中所有Activity
	 */
	public void popAllActivity() {
		Activity activity = null;
		if (activityStack != null && !activityStack.empty()) {
			int count = activityStack.size();
			for (int i = 0; i < count; i++) {
				activity = activityStack.elementAt(i);
				if (activity != null && !activity.isFinishing()) {
					activity.finish();
				}
			}
		}
		if (activityStack != null && !activityStack.empty()) {
			activityStack.clear();
			activityStack = null;
		}
	}

	/**
	 * 将当前Activity推入栈中
	 * 当前Activity
	 */
	public void pushActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	/**
	 * 除了当前Activity退出栈中所有其他Activity
	 * 当前Activity
	 */
	public void popAllActivityExceptOne(Class<?> cls) {
		while (true) {
			Activity activity = currentActivity();
			if (activity == null) {
				break;
			}
			if (activity.getClass().equals(cls)) {
				break;
			}
			popActivity(activity);
		}
	}

	/**
	 * 退出应用
	 */
	public void exitApp(Context con) {
		popAllActivity();
		System.exit(0);
	}

	private Intent assembleIntent(Context context, Class<?> cls, Object bean, Bundle bundle, int flag) {
		Intent intent = new Intent(context, cls);
		intent.addFlags(flag);
		if (bundle != null ) {
			intent.putExtras(bundle);
		} else if (bean != null) {
			bundle = new Bundle();
			if (bean instanceof Serializable) {
				bundle.putSerializable(BUNDLE_BEAN, (Serializable)bean);
			} if (bean instanceof Parcelable) {
				bundle.putParcelable(BUNDLE_BEAN, (Parcelable)bean);
			}
			intent.putExtras(bundle);
		}
		return intent;
	}
	
	public void startActivity(Context context, Class<?> cls, Object bean) {
		startActivity(context, cls, bean, Intent.FLAG_ACTIVITY_CLEAR_TOP);
	}
	
	public void startActivity(Context context, Class<?> cls, Object bean, int flag) {
		Intent intent = assembleIntent(context, cls, bean, null, flag);
		context.startActivity(intent);
	}
	
	public void startActivity(Context context, Class<?> cls, Bundle bundle) {
		startActivity(context, cls, bundle, Intent.FLAG_ACTIVITY_CLEAR_TOP);
	}
	
	public void startActivity(Context context, Class<?> cls, Bundle bundle, int flag) {
		Intent intent = assembleIntent(context, cls, null, bundle, flag);
		context.startActivity(intent);
	}
	
	public void startActivityForResult(Activity activity, Class<?> cls, Object bean, int requestCode) {
		Intent intent = assembleIntent(activity, cls, bean, null, Intent.FLAG_ACTIVITY_CLEAR_TOP);
		activity.startActivityForResult(intent, requestCode);
	}
	
}