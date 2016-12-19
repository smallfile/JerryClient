package com.jerry.sample.utils;

import android.content.Context;
import android.os.Environment;

import com.jerry.sample.MyApplication;

import java.io.File;

public class StorageUtils {
	private static final String TAG = StorageUtils.class.getSimpleName();
	private static StorageUtils instance;
	private Context mContext;

	public static final String DIR_AUDIO = "audio";
	public static final String DIR_VIDEO = "video";
	public static final String DIR_IMAGE = "image";
	public static final String DIR_DB = "db";
	
	private StorageUtils(Context context) {
		mContext = context;
	}

	public static StorageUtils getInstance() {
		if (instance == null) {
			synchronized (StorageUtils.class) {
				if (instance == null) {
					synchronized (StorageUtils.class) {
						instance = new StorageUtils(MyApplication.getContext());
					}
				}
			}
		}
		return instance;
	}

	public File getAudioDir() {
		File audioDir = new File(mContext.getExternalFilesDir(Environment.DIRECTORY_DCIM), DIR_AUDIO);
		if (!audioDir.exists()) {
			audioDir.mkdirs();
		}
		return audioDir;
	}

	public File getVideoDir() {
		File videoDir = new File(mContext.getExternalFilesDir(Environment.DIRECTORY_DCIM), DIR_VIDEO);
		if (!videoDir.exists()) {
			videoDir.mkdirs();
		}
		return videoDir;
	}

	public File getImageDir() {
		File imageDir = new File(mContext.getExternalFilesDir(Environment.DIRECTORY_DCIM), DIR_IMAGE);
		if (!imageDir.exists()) {
			imageDir.mkdirs();
		}
		return imageDir;
	}

	public File getDbDir() {
		File DbDir = new File(mContext.getExternalFilesDir(Environment.DIRECTORY_DCIM), DIR_DB);
		if (!DbDir.exists()) {
			DbDir.mkdirs();
		}
		return DbDir;
	}
	
	public boolean isHasSdCard() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	

}
