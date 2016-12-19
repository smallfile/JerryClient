package com.jerry.lib.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.jerry.lib.common.MyApplication;

public class PreferenceUtils {

    private Context mContext;
    private static PreferenceUtils instance;
    private SharedPreferences mPreferences;

    private PreferenceUtils(Context context){
        this.mContext = context;
        mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    public static PreferenceUtils getInstance(){
        if (instance == null){
            synchronized (PreferenceUtils.class){
                instance = new PreferenceUtils(MyApplication.getContext());
            }
        }
        return instance;
    }

    public static final String KEY_LOC_LONGITUDE = "loc_longitude";
    public static final String KEY_LOC_LATITUDE = "loc_latitude";
    public static final String KEY_LOC_PROVINCE = "loc_province";
    public static final String KEY_LOC_CITY = "loc_city";
    public static final String KEY_LOC_DISTRICT = "loc_district";
    public static final String KEY_LOC_ADDRESS = "loc_address";
    public static final String KEY_LOC_STREET = "loc_street";
    public static final String KEY_SCREEN_WIDTH = "screen_width";
    public static final String KEY_SCREEN_HEIGHT = "screen_height";
    public static final String KEY_DENSITY_DPI = "density_dpi";
    public static final String KEY_DENSITY = "density";

    public static final String KEY_SESSION_KEY = "session_key";
    public static final String KEY_FIRST_START = "first_start";

    public void saveSessionKey(String session_key) {
        mPreferences.edit().putString(KEY_SESSION_KEY, session_key.trim()).commit();
    }
    public String getSessionKey() {
        String sessionKeyValue = mPreferences.getString(KEY_SESSION_KEY, "");
        return sessionKeyValue;
    }

    public boolean getIsFirstStart() {
        boolean autoLoginValue = mPreferences.getBoolean(KEY_FIRST_START, true);
        return autoLoginValue;
    }
    public void saveFirstStart(boolean flag) {
        mPreferences.edit().putBoolean(KEY_FIRST_START, flag).commit();
    }

    public void saveLocLongitude(String longitude) {
        if (!TextUtils.isEmpty(longitude)) {
            mPreferences.edit().putString(KEY_LOC_LONGITUDE, longitude.trim()).commit();
        }
    }
    public String getLocLongitude() {
        String longitudeValue = mPreferences.getString(KEY_LOC_LONGITUDE, "");
        return longitudeValue;
    }

    public void saveLocLatitude(String latitude) {
        if (!TextUtils.isEmpty(latitude)) {
            mPreferences.edit().putString(KEY_LOC_LATITUDE, latitude.trim()).commit();
        }
    }
    public String getLocLatitude() {
        String latitudeValue = mPreferences.getString(KEY_LOC_LATITUDE, "");
        return latitudeValue;
    }

    public void saveLocProvince(String province) {
        if (!TextUtils.isEmpty(province)) {
            mPreferences.edit().putString(KEY_LOC_PROVINCE, province.trim()).commit();
        }
    }
    public String getLocProvince() {
        String provinceValue = mPreferences.getString(KEY_LOC_PROVINCE, "");
        return provinceValue;
    }

    public void saveLocDistrict(String district) {
        if (!TextUtils.isEmpty(district)) {
            mPreferences.edit().putString(KEY_LOC_DISTRICT, district.trim()).commit();
        }
    }
    public String getLocDistrict() {
        String districtValue = mPreferences.getString(KEY_LOC_DISTRICT, "");
        return districtValue;
    }

    public void saveLocCity(String city) {
        if (!TextUtils.isEmpty(city)) {
            mPreferences.edit().putString(KEY_LOC_CITY, city.trim()).commit();
        }
    }
    public String getLocCity() {
        String cityValue = mPreferences.getString(KEY_LOC_CITY, "");
        return cityValue;
    }

    public void saveLocAddress(String address) {
        if (!TextUtils.isEmpty(address)) {
            mPreferences.edit().putString(KEY_LOC_ADDRESS, address.trim()).commit();
        }
    }
    public String getLocAddress() {
        String addressValue = mPreferences.getString(KEY_LOC_ADDRESS, "");
        return addressValue;
    }

    public void saveLocStreet(String street) {
        if (!TextUtils.isEmpty(street)) {
            mPreferences.edit().putString(KEY_LOC_STREET, street.trim()).commit();
        }
    }
    public String getLocStreet() {
        String streetValue = mPreferences.getString(KEY_LOC_STREET, "");
        return streetValue;
    }

    public void saveScreenWidth(int width) {
        mPreferences.edit().putInt(KEY_SCREEN_WIDTH, width).commit();
    }
    public int getScreenWidth() {
        int widthValue = mPreferences.getInt(KEY_SCREEN_WIDTH, 480);
        return widthValue;
    }

    public void saveScreenHeight(int height) {
        mPreferences.edit().putInt(KEY_SCREEN_HEIGHT, height).commit();
    }
    public int getScreenHeight() {
        int heightValue = mPreferences.getInt(KEY_SCREEN_HEIGHT, 800);
        return heightValue;
    }

    public void saveDensityDpi(int densityDpi) {
        mPreferences.edit().putInt(KEY_DENSITY_DPI, densityDpi).commit();
    }
    public int getDensityDpi() {
        int densityDpiValue = mPreferences.getInt(KEY_DENSITY_DPI, 240);
        return densityDpiValue;
    }

    public void saveDensity(float density) {
        mPreferences.edit().putFloat(KEY_DENSITY, density).commit();
    }
    public float getDensity() {
        float densityValue = mPreferences.getFloat(KEY_DENSITY, 1.5f);
        return densityValue;
    }



}
