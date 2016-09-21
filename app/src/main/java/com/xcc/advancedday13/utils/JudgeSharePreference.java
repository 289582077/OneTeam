package com.xcc.advancedday13.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by DaXianSheng on 2016/9/21.
 */
public class JudgeSharePreference {
    public static boolean isFistLogin(Context context){
        SharedPreferences sp = context.getSharedPreferences("Login", Context.MODE_PRIVATE);
        return sp.getBoolean("isFist", true);
    }
    public static boolean isLogin(Context context){
        SharedPreferences sp = context.getSharedPreferences("Login", Context.MODE_PRIVATE);
        return sp.getBoolean("isLogin", false);
    }
}
