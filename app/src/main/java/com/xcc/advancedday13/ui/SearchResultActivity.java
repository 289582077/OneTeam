package com.xcc.advancedday13.ui;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    private ProgressDialog mPd;
    private TextView mCityName;
    private TextView mCityEn;
    private TextView mDestination;
    private TextView mOverVIew;
    private TextView mAuthor;
    private TextView mTopic;
    private TextView mDescription;
    private TextView mTravels;
    private TextView mCity;
    private TextView mTopTitle;
    private LinearLayout mTopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        mPd = new ProgressDialog(this);
        mPd.setMessage("正在加载...");
        mPd.show();
        id = getIntent().getIntExtra("id",93);
        init();
    }

    private void init() {
        mCover = (ImageView) findViewById(R.id.iv_result_cover);
        mPicSet = (ImageView) findViewById(R.id.iv_result_set);
        mCityName = (TextView) findViewById(R.id.tv_city_name);
        mCityEn = (TextView) findViewById(R.id.tv_city_en_name);
        mDestination = (TextView) findViewById(R.id.tv_result_destination_name);
        mOverVIew = (TextView) findViewById(R.id.tv_result_over_view);
        mAuthor = (TextView) findViewById(R.id.tv_result_author);
        mTopic = (TextView) findViewById(R.id.tv_result_topic);
        mDescription = (TextView) findViewById(R.id.tv_result_description);
        mTravels = (TextView) findViewById(R.id.tv_result_travels);
        mCity = (TextView) findViewById(R.id.tv_title_city_name);
        mTopTitle = (TextView) findViewById(R.id.tv_result_topList_title);
        mTopList = (LinearLayout) findViewById(R.id.ll_result_topList);

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
                mCityName.setText(destination.getData().getDestination().getName());
                mCityEn.setText(destination.getData().getDestination().getName_en());
                mDestination.setText(destination.getData().getSections().get(0).getTitle());
                mOverVIew.setText(destination.getData().getSections().get(0).getButton_text());
                mAuthor.setText("by"+destination.getData().getSections().get(3).getModels().get(0).getUser().getName());
                mTopic.setText(destination.getData().getSections().get(3).getModels().get(0).getTopic());
                mDescription.setText(destination.getData().getSections().get(3).getModels().get(0).getDescription());
                mTravels.setText(destination.getData().getSections().get(3).getButton_text());
                mCity.setText(destination.getData().getDestination().getName());
                mTopTitle.setText(destination.getData().getSections().get(2).getTitle());
                for (int i = 0; i < destination.getData().getSections().get(2).getCount(); i++) {
                    View view = LayoutInflater.from(getApplication()).inflate(R.layout.top_list_view, null);
                    ImageView iv = (ImageView) view.findViewById(R.id.iv_topList_item);
                    TextView topic = (TextView) view.findViewById(R.id.tv_topList_item);
                    TextView topic2 = (TextView) view.findViewById(R.id.tv_topList_item2);

                    x.image().bind(iv,destination.getData().getSections().get(2).getModels().get(i).getPhoto_url());
                    topic.setText(destination.getData().getSections().get(2).getModels().get(i).getTitle());
                    topic2.setText(destination.getData().getSections().get(2).getModels().get(i).getSummary());
                    mTopList.addView(view);
                }
                mPd.dismiss();
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
