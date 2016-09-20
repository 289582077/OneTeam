package com.xcc.advancedday13.ui;

import android.app.Application;

import org.xutils.x;

/**
 * Created by bukeyishidecheng on 16/9/20.
 */
public class TeamApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
