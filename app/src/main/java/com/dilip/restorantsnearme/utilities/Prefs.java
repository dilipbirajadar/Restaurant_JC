package com.dilip.restorantsnearme.utilities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Dilip Birajadar.
 */
public class Prefs {

    private SharedPreferences systemPrefs;
    private SharedPreferences.Editor editor;
    private static Prefs prefs;

    public static Prefs getInstance() {
        if (prefs == null) {
            prefs = new Prefs(AppClass.get().getApplicationContext());
        }
        return prefs;
    }

    @SuppressLint("CommitPrefEdits")
    private Prefs(Context context) {
        this.systemPrefs = context.getSharedPreferences(Constant.PREFS, Activity.MODE_PRIVATE);
        this.editor = systemPrefs.edit();
    }

    public void setCurrentLat(String lat) {
        editor.putString(Constant.CURRENT_LAT, lat);
        editor.commit();
    }

    public String getCurrentLat() {
        String currentLat = systemPrefs.getString(Constant.CURRENT_LAT, "");
        if (currentLat.equals("")) {
            return currentLat = "0";
        } else {
            return currentLat;
        }
    }

    public void setCurrentLng(String lng) {
        editor.putString(Constant.CURRENT_LNG, lng);
        editor.commit();
    }

    public String getCurrentLng() {
        String currentLng = systemPrefs.getString(Constant.CURRENT_LNG, "");
        if (currentLng.equals("")) {
            return currentLng = "0";
        } else {
            return currentLng;
        }
    }

    public boolean isMileFilter() {
        return systemPrefs.getBoolean(Constant.IS_MILE_FILTER, Constant.IS_MILE_FILTER_DEFAULT);
    }

    public void setMileFilter(boolean isMileFilter) {
        editor.putBoolean(Constant.IS_MILE_FILTER, isMileFilter);
        editor.commit();
    }

    /*This method is ued to clear all preferences under this class*/
    public void clear() {
        editor.clear();
        editor.commit();
    }



}
