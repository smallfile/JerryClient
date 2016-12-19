package com.jerry.sample.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.jerry.sample.MyApplication;

public class NetworkUtils {

	public static final String TYPE_MOBILE = "mobile";
	public static final String TYPE_WIFI = "wifi";
	public static final String TYPE_NO_NETWORK = "no_network";
	
	private static NetworkUtils instance;
	private Context mContext;
	private ConnectivityManager connectivityManager;
	
	public NetworkUtils() {
		mContext = MyApplication.getContext();
		connectivityManager = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
	}

	public static NetworkUtils getInstance() {
		if (instance == null) {
			synchronized (NetworkUtils.class) {
				NetworkUtils inst = instance;
				if (inst == null) {
					synchronized (NetworkUtils.class) {
						instance = new NetworkUtils();
					}
				}
			}
		}
		return instance;
	}

	public String checkNetworkState() {
		String networkType = TYPE_NO_NETWORK;
		try{
			NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
			if(activeNetInfo != null){
				if(activeNetInfo.getType()==ConnectivityManager.TYPE_MOBILE) {  
					networkType = TYPE_MOBILE;
				} else if(activeNetInfo.getType()==ConnectivityManager.TYPE_WIFI){
					networkType =  TYPE_WIFI;
				}
			}else{
				networkType = TYPE_NO_NETWORK;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return networkType;
	}
}

