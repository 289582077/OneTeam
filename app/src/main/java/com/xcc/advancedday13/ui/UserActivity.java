package com.xcc.advancedday13.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;
import com.xcc.advancedday13.R;
import com.xcc.advancedday13.adapters.UserViewPagerAdapter;
import com.xcc.advancedday13.ui.fragments.UserViewPagerFirstFragment;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<Fragment> data;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 1);
        AdvancedPagerSlidingTabStrip tabStrip = (AdvancedPagerSlidingTabStrip) findViewById(R.id.user_tab);
        mViewPager = (ViewPager) findViewById(R.id.user_grouped);
        mViewPager.setAdapter(new UserViewPagerAdapter(getSupportFragmentManager(), getData(),this));
//        tabStrip.setViewPager(mViewPager);
    }

    public List<Fragment> getData() {
        List<Fragment> data=new ArrayList<>();
        UserViewPagerFirstFragment userViewPagerFirstFragment = new UserViewPagerFirstFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        userViewPagerFirstFragment.setArguments(bundle);
        data.add(userViewPagerFirstFragment);
        return data;
    }
}
