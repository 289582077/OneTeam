package com.xcc.advancedday13.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.xcc.advancedday13.R;
import com.xcc.advancedday13.base.BaseActivity;

public class CityDetailActivity extends BaseActivity {

    private static final String TAG = CityDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citydetail);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        Log.e(TAG, "onCreate: "+id);
    }
}
