package com.xcc.advancedday13.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.xcc.advancedday13.R;
import com.xcc.advancedday13.constants.HttpConstant;
import com.xcc.advancedday13.model.Search;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener ,Handler.Callback, View.OnFocusChangeListener {

    private static final int ID = 10;
    private ImageButton mBack;
    private EditText mEt;
    private TextView mTv;
    private ImageView mIv;
    private Handler mHandler=new Handler(this);
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        progressDialog = new ProgressDialog(this);
        init();
    }

    private void init() {
        mBack = (ImageButton) findViewById(R.id.ib_search_back);
        mEt = (EditText) findViewById(R.id.et_search_content);
        mTv = (TextView) findViewById(R.id.tv_cancel_search);


        mBack.setOnClickListener(this);
        mEt.setOnClickListener(this);
        mTv.setOnClickListener(this);
        mEt.setOnEditorActionListener(this);
        mEt.setOnFocusChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_search_back:
                finish();
                break;
            case R.id.et_search_content:
                mEt.setFocusable(true);
                break;
            case R.id.tv_cancel_search:
                mEt.setText("");
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId== EditorInfo.IME_ACTION_SEARCH||(event!=null&&event.getKeyCode()== KeyEvent.KEYCODE_ENTER)) {
            String s = mEt.getText().toString();
            initHttp(s);
            progressDialog.setMessage("正在加载...");
            progressDialog.show();
            return true;
        }
        return false;
    }

    private void initHttp(final String s) {
        RequestParams entity = new RequestParams(HttpConstant.SEARCHURL+s);
        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Search search = gson.fromJson(result, Search.class);
                int id = search.getData().getHitted().getDestination().getId();
                Message obtain = Message.obtain();
                obtain.what=ID;
                obtain.arg1=id;
                mHandler.sendMessage(obtain);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case ID:
                Intent intent = new Intent(getApplication(), SearchResultActivity.class);
                intent.putExtra("id",msg.arg1);
                startActivity(intent);
                progressDialog.dismiss();
                break;
        }
        return false;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
