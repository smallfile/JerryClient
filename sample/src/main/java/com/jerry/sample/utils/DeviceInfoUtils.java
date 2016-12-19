package com.jerry.sample.utils;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import com.jerry.sample.MyApplication;

public class DeviceInfoUtils {

	private static DeviceInfoUtils instance;
	private Context mContext;
	private DisplayMetrics mDisplayMetrics;
	private TelephonyManager mTelephonyManager;
	private LocationManager mLocationManager;
	private Location mLocation;
	
	public DeviceInfoUtils() {
		mContext = MyApplication.getContext();
		mLocationManager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);
		
		mTelephonyManager = (TelephonyManager)mContext.getSystemService(Context.TELEPHONY_SERVICE);  
		locateUserLocation();
	}
	public DeviceInfoUtils(Activity activity) {
		mDisplayMetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
	}
	
	public static DeviceInfoUtils getInstance() {
		if (instance == null) {
			synchronized (DeviceInfoUtils.class) {
				DeviceInfoUtils inst = instance;
				if (inst == null) {
					synchronized (DeviceInfoUtils.class) {
						instance = new DeviceInfoUtils();
					}
				}
			}
		}
		return instance;
	}

	LocationListener myLocListener = new LocationListener() {
		@Override
		public void onLocationChanged(Location location) {
			mLocation = location;
		}

		@Override
		public void onProviderDisabled(String provider) {

		}

		@Override
		public void onProviderEnabled(String provider) {

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {

		}

	};
	
	private void locateUserLocation() {
		if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
					5 * 60 * 1000, 100, myLocListener);
		} 
		
		if (mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
					5 * 60 * 1000, 100, myLocListener);
		}
	}
	
	private Location getUserLocation() {
		mLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	      if(mLocation == null){
	    	  mLocation = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	      }
	    return mLocation;
	}
	
	public double getUserLatitude(){
		if (getUserLocation() != null) {
			return getUserLocation().getLatitude();
		}
		return 0.0;
	}
	
	public double getUserLongitude(){
		if (getUserLocation() != null) {
			return getUserLocation().getLongitude();
		}
		return 0.0;
	}
	
	public int getScreenWidth(){
		return mDisplayMetrics.widthPixels;
	}
	
	public int getScreenHeight(){
		return mDisplayMetrics.heightPixels;
	}
	
	public String getDeviceId(){
		return mTelephonyManager.getDeviceId();
	}
	
}

