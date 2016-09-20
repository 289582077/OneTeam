package com.xcc.advancedday13.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.xcc.advancedday13.R;
import com.xcc.advancedday13.adapters.MainViewPagerAdapter;
import com.xcc.advancedday13.base.BaseActivity;
import com.xcc.advancedday13.ui.fragments.MyFragment;
import com.xcc.advancedday13.ui.fragments.StrategyFragment;
import com.xcc.advancedday13.ui.fragments.TravelsFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private MainViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTablayout = (TabLayout) findViewById(R.id.main_tab);


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
}
