package com.circle.stu.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.circle.stu.base.App;
import com.circle.stu.base.Constants;

/**
 * Created by oyty on 10/13/16.
 */

public class PreferenceHelper {

    public static final String PRFERENCE_NAME_CIRCLE = "sharepreference_stu";

    private static SharedPreferences getSharedPreference() {
        Context context = App.getInstance();
        return context.getSharedPreferences(PRFERENCE_NAME_CIRCLE, Context.MODE_PRIVATE);
    }

    public static boolean putString(String tag, String value) {
        SharedPreferences prefs = getSharedPreference();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(tag, value);
        return editor.commit();
    }

    public static String getString(String tag) {
        return getString(tag, "");
    }

    public static String getString(String tag, String defaultValue) {
        SharedPreferences prefs = getSharedPreference();
        return prefs.getString(tag, defaultValue);
    }

    public static boolean putBoolean(String tag, boolean value) {
        SharedPreferences prefs = getSharedPreference();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(tag, value);
        return editor.commit();
    }

    public static boolean getBoolean(String tag) {
        return getBoolean(tag, false);
    }

    public static boolean getBoolean(String tag, boolean defaultValue) {
        SharedPreferences prefs = getSharedPreference();
        return prefs.getBoolean(tag, defaultValue);
    }

    public static boolean isFirstIn() {
        return getBoolean(Constants.IS_FIRST_IN, true);
    }

    public static boolean isLogin() {
        
        return false;
    }
}
