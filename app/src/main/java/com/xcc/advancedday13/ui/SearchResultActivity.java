package com.xcc.advancedday13.ui;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.xcc.advancedday13.R;
import com.xcc.advancedday13.base.BaseActivity;
import com.xcc.advancedday13.constants.HttpConstant;
import com.xcc.advancedday13.model.Destination;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mCover;
    private int id;
    private ImageView mPicSet;
    private String[] img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        id = getIntent().getIntExtra("id",93);
        init();
    }

    private void init() {
        mCover = (ImageView) findViewById(R.id.iv_result_cover);
        mPicSet = (ImageView) findViewById(R.id.iv_result_set);

        mPicSet.setOnClickListener(this);
        initData();
    }

    private void initData() {
        RequestParams entity = new RequestParams(HttpConstant.DESTINATION_URL.replace("665.json",id+".json"));
        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Destination destination = gson.fromJson(result, Destination.class);
                List<Destination.DataBean.SectionsBean.ModelsBean.PicBean> contents = destination.getData().getSections().get(3).getModels().get(0).getContents();
                img=new String[contents.size()];
                for (int i = 0; i < contents.size(); i++) {
                    String pic = contents.get(i).getPhoto_url();
                    img[i]=pic;
                }
                x.image().bind(mCover,destination.getData().getDestination().getPhoto_url());
                x.image().bind(mPicSet,destination.getData().getSections().get(3).getModels().get(0).getContents().get(0).getPhoto_url());

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_result_set:
                Intent intent = new Intent(this, PicActivity.class);
                intent.putExtra("pic",img);
                startActivity(intent);
                break;
        }
    }
}
