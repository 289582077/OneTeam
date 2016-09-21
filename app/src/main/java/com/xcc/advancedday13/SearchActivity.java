package com.xcc.advancedday13;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton mBack;
    private EditText mEt;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();
    }

    private void init() {
        mBack = (ImageButton) findViewById(R.id.ib_search_back);
        mEt = (EditText) findViewById(R.id.et_search_content);
        mTv = (TextView) findViewById(R.id.tv_cancel_search);

        mBack.setOnClickListener(this);
        mEt.setOnClickListener(this);
        mTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_search_back:
                finish();
                break;
            case R.id.et_search_content:
                break;
            case R.id.tv_cancel_search:
                break;
        }
    }
}
