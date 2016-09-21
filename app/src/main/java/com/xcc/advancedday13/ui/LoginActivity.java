package com.xcc.advancedday13.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.xcc.advancedday13.R;
import com.xcc.advancedday13.base.BaseActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        mBack = (ImageButton) findViewById(R.id.ib_my_login_back);
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_my_login_back:
                finish();
                break;
        }
    }
}
