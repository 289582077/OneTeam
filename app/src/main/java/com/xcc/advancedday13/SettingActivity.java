package com.xcc.advancedday13;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Cache;
import com.xcc.advancedday13.base.BaseActivity;
import com.xcc.advancedday13.utils.CacheUtil;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private TextView mCache;
    private RelativeLayout mClearCache;
    private AlertDialog.Builder ab;
    private ImageButton mBack;
    private RelativeLayout mNotifi;
    private CheckBox mCb;
    private TextView mMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ab = new AlertDialog.Builder(this);
        ab.setTitle("系统提示：");
        ab.setMessage("是否删除所有缓存...");
        ab.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CacheUtil.cleanAppData(getApplicationContext());
                mCache.setText(getAppCache());
            }
        });
        ab.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        init();
    }

    private void init() {
        mClearCache = (RelativeLayout) findViewById(R.id.rl_setting_clear_cache);
        mCache = (TextView) findViewById(R.id.tv_setting_cache);
        mBack = (ImageButton) findViewById(R.id.ib_setting_back);
        mNotifi = (RelativeLayout) findViewById(R.id.rl_setting_notification);
        mCb = (CheckBox) findViewById(R.id.cb_setting);
        mMedia = (TextView) findViewById(R.id.tv_setting_media);


        mMedia.setOnClickListener(this);
        mNotifi.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mClearCache.setOnClickListener(this);
        mCache.setText(getAppCache());
        SharedPreferences sp = getSharedPreferences("cbState", MODE_PRIVATE);
        boolean cbState = sp.getBoolean("cbState", false);
        mCb.setChecked(cbState);
    }

    private String getAppCache() {
        long size = CacheUtil.getFolderSize(getApplicationContext().getCacheDir()) +
                CacheUtil.getFolderSize(getApplicationContext().getExternalCacheDir());
        return CacheUtil.getFormatSize(size);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_setting_clear_cache:
                ab.show();
                break;
            case R.id.ib_setting_back:
                finish();
                break;
            case R.id.rl_setting_notification:
                if (mCb.isChecked()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("友情提示:是否取消消息推送");
                    builder.setMessage("取消消息推送将不会得到任何系统消息");
                    builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mCb.setChecked(false);
                            saveCbState(false);
                            sendNotifi();
                        }
                    });
                    builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.show();
                }else {
                    mCb.setChecked(true);
                    saveCbState(true);
                }

                break;
            case R.id.tv_setting_media:
                Intent intent = new Intent(this, SplashActivity.class);
                intent.putExtra("isSetting",true);
                startActivity(intent);
                break;
        }
    }

    private void saveCbState(boolean b) {
        SharedPreferences sp = getSharedPreferences("cbState", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("cbState",b);
        edit.commit();
    }


    private void sendNotifi() {
        NotificationManager systemService = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setAutoCancel(true);
        builder.setContentTitle("系统提示");
        builder.setTicker("氢气球旅行");
        builder.setContentText("该应用将不会得到系统推送通知的服务");
        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notification = builder.build();
        }
        systemService.notify(1, notification);
    }

}
