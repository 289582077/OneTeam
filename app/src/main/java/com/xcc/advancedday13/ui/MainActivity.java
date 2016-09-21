package com.xcc.advancedday13.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.xcc.advancedday13.R;
import com.xcc.advancedday13.SearchActivity;
import com.xcc.advancedday13.adapters.MainViewPagerAdapter;
import com.xcc.advancedday13.base.BaseActivity;
import com.xcc.advancedday13.ui.fragments.MyFragment;
import com.xcc.advancedday13.ui.fragments.StrategyFragment;
import com.xcc.advancedday13.ui.fragments.TravelsFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private MainViewPagerAdapter adapter;
    private TextView mTvSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTablayout = (TabLayout) findViewById(R.id.main_tab);
        mTvSearch = (TextView) findViewById(R.id.tv_main_search);


        mTvSearch.setOnClickListener(this);
        mViewPager = (ViewPager) findViewById(R.id.main_vp);
        adapter = new MainViewPagerAdapter(getSupportFragmentManager(),getData());
        mViewPager.setAdapter(adapter);

        mTablayout.setupWithViewPager(mViewPager);

    }

    private List<Fragment> getData() {
        List<Fragment> data=new ArrayList<>();
        //第一个Fragment
        StrategyFragment strategyFragment = new StrategyFragment();
        data.add(strategyFragment);

        //第二个Fragment
        TravelsFragment travelsFragment = new TravelsFragment();
        data.add(travelsFragment);

        //第三个Fragment
        StrategyFragment strategyFragment1 = new StrategyFragment();
        data.add(strategyFragment1);

        //第四个Fragment
        MyFragment myFragment = new MyFragment();
        data.add(myFragment);
        return data;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_main_search:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
        }
    }
}
