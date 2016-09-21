package com.xcc.advancedday13;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.VideoView;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    private VideoView mVideo;
    private Button mBtn;
    private ImageButton mPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        mVideo = (VideoView) findViewById(R.id.vv_splash);
        mBtn = (Button) findViewById(R.id.btn_splash);
        mPlay = (ImageButton) findViewById(R.id.ib_splash_play);
        mPlay.setOnClickListener(this);
        mBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_splash:
                finish();
                break;
            case R.id.ib_splash_play:
                mPlay.setVisibility(View.GONE);
                playMedia();
                break;
        }
    }

    private void playMedia() {
        mVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.media));
        mVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
    }
}
