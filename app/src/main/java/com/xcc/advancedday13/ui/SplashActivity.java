package com.xcc.advancedday13.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.xcc.advancedday13.R;
import com.xcc.advancedday13.ui.MainActivity;
import com.xcc.advancedday13.utils.JudgeSharePreference;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener ,Handler.Callback{

    private static final int WHAT = 1;
    private static final long DELAY = 2000;
    private static final int FIRST_IN = 2;
    private static final long DELAY_MEDIA = 30 * 1000;
    private VideoView mVideo;
    private Button mBtn;
    private ImageButton mPlay;
    private RelativeLayout mBg;
    private ImageButton mEnterApp;
    private Handler mHandler=new Handler(this);
    private FrameLayout mfl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        mVideo = (VideoView) findViewById(R.id.vv_splash);
        mBtn = (Button) findViewById(R.id.btn_splash);
        mPlay = (ImageButton) findViewById(R.id.ib_splash_play);
        mBg = (RelativeLayout) findViewById(R.id.rl_splash_media_bg);
        mEnterApp = (ImageButton) findViewById(R.id.ib_enter_app);
        mfl = (FrameLayout) findViewById(R.id.fl_launch);

        mEnterApp.setOnClickListener(this);
        mPlay.setOnClickListener(this);
        mBtn.setOnClickListener(this);

        if (getIntent().getBooleanExtra("isSetting",false)) {
            mfl.setVisibility(View.GONE);
        }else {
            if (JudgeSharePreference.isFistLogin(this)) {
                mfl.setVisibility(View.GONE);
            }else {
                //other
                mBtn.setVisibility(View.GONE);
                mEnterApp.setVisibility(View.GONE);
                mHandler.sendEmptyMessageDelayed(WHAT,DELAY);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_splash:
                if (!getIntent().getBooleanExtra("isSetting",false)) {
                    mVideo.stopPlayback();
                    mBtn.setVisibility(View.GONE);
                    mEnterApp.setVisibility(View.VISIBLE);
                    mfl.setVisibility(View.VISIBLE);
                }else {
                    finish();
                }
                break;
            case R.id.ib_splash_play:
                playMedia();
                mHandler.sendEmptyMessageDelayed(FIRST_IN,DELAY_MEDIA);
                break;
            case R.id.ib_enter_app:
                SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("isFist",false);
                editor.commit();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void playMedia() {
        mPlay.setVisibility(View.GONE);
        mBg.setVisibility(View.GONE);
        mVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.media));
        mVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case WHAT:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case FIRST_IN:
                if (!getIntent().getBooleanExtra("isSetting",false)) {
                    mEnterApp.setVisibility(View.VISIBLE);
                    mfl.setVisibility(View.VISIBLE);
                }
                break;
        }
        return true;
    }
}
