package com.dilip.restorantsnearme.utilities;

import android.app.Application;
import android.content.Context;
/**
 * Created by Dilip Birajadar.
 */
public class AppClass extends Application {

    private static AppClass instance;
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = this;
    }

    public static AppClass get() {
        return instance;
    }

    public static Context getFNContext() {
        return mContext;
    }
}
