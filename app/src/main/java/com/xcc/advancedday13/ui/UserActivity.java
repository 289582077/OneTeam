package com.xcc.advancedday13.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.IconPageIndicator;
import com.xcc.advancedday13.R;
import com.xcc.advancedday13.adapters.UserViewPagerAdapter;
import com.xcc.advancedday13.constants.HttpConstant;
import com.xcc.advancedday13.model.UserProfiles;
import com.xcc.advancedday13.ui.fragments.UserViewPagerFirstFragment;
import com.xcc.advancedday13.ui.fragments.UserViewPagerSecondFragment;
import com.xcc.advancedday13.ui.fragments.UserViewPagerThirdFragment;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserActivity extends AppCompatActivity {

    @BindView(R.id.user_icon)
    ImageView userIcon;
    @BindView(R.id.user_icon_v)
    ImageView userIconV;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_followings_count)
    TextView userFollowingsCount;
    @BindView(R.id.user_followers_count)
    TextView userFollowersCount;
    @BindView(R.id.user_back)
    ImageView userBack;
    @BindView(R.id.user_to_follow)
    TextView userToFollow;
    @BindView(R.id.user_gender)
    ImageView userGender;
    @BindView(R.id.user_header_photo)
    ImageView userHeaderPhoto;
    private ViewPager mViewPager;
    private int id;
    private ImageOptions options = new ImageOptions.Builder()
            .setCircular(true)
            .setLoadingDrawableId(R.mipmap.travels_image_default)
            .setRadius(30)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 1);
        initTopView();
        initView();
    }

    private void initTopView() {
        RequestParams params = new RequestParams(HttpConstant.TRAVEL_USER_PROFILES_URL+id+"/profiles.json");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                UserProfiles userProfiles = gson.fromJson(result, UserProfiles.class);
                UserProfiles.DataBean userProfilesData = userProfiles.getData();
                x.image().bind(userIcon, userProfilesData.getPhoto_url(), options);
                if (userProfilesData.getLevel() <= 2) {
                    userIconV.setVisibility(View.GONE);
                } else {
                    userIconV.setVisibility(View.VISIBLE);
                }
                userName.setText(userProfilesData.getName());
                if (userProfilesData.getGender() == 1) {
                    userToFollow.setText("关注他");
                    userGender.setImageResource(R.mipmap.gender_m);
                } else {
                    userToFollow.setText("关注她");
                    userGender.setImageResource(R.mipmap.gender_w);
                }
                userFollowersCount.setText(userProfilesData.getFollowers_count() + "粉丝");
                userFollowingsCount.setText(userProfilesData.getFollowings_count() + "关注");
                Picasso.with(UserActivity.this).load(userProfilesData.getHeader_photo().getPhoto_url())
                        .placeholder(R.mipmap.travels_image_default)
                        .into(userHeaderPhoto);
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

    private void initView() {

//        AdvancedPagerSlidingTabStrip tabStrip = (AdvancedPagerSlidingTabStrip) findViewById(R.id.user_tab);
        IconPageIndicator tabStrip = (IconPageIndicator) findViewById(R.id.user_tab);
        mViewPager = (ViewPager) findViewById(R.id.user_grouped);

        mViewPager.setAdapter(new UserViewPagerAdapter(getSupportFragmentManager(), getData(), this));
        tabStrip.setViewPager(mViewPager);
    }

    public List<Fragment> getData() {
        List<Fragment> data = new ArrayList<>();
        UserViewPagerFirstFragment userViewPagerFirstFragment = new UserViewPagerFirstFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        userViewPagerFirstFragment.setArguments(bundle);
        data.add(userViewPagerFirstFragment);

        UserViewPagerSecondFragment userViewPagerSecondFragment = new UserViewPagerSecondFragment();
        userViewPagerSecondFragment.setArguments(bundle);
        data.add(userViewPagerSecondFragment);
        UserViewPagerThirdFragment userViewPagerThirdFragment = new UserViewPagerThirdFragment();
        userViewPagerThirdFragment.setArguments(bundle);
        data.add(userViewPagerThirdFragment);
        return data;
    }

    @OnClick(R.id.user_back)
    public void onClick() {
        finish();
    }
}
