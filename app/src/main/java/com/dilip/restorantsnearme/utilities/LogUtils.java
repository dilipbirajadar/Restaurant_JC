package com.dilip.restorantsnearme.utilities;

import android.util.Log;

import com.dilip.restorantsnearme.BuildConfig;
/**
 * A fragment representing a list of Items.
 */
public class LogUtils {
    public static String debug(final String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message);
        }

        return tag + " , " + message;
    }

    public static String errorLog(final String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message);
        }

        return tag + " , " + message;
    }
}
