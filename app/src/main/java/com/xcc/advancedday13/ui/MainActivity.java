package com.xcc.advancedday13.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xcc.advancedday13.R;
import com.xcc.advancedday13.adapters.MainViewPagerAdapter;
import com.xcc.advancedday13.base.BaseActivity;
import com.xcc.advancedday13.ui.fragments.MyFragment;
import com.xcc.advancedday13.ui.fragments.StrategyFragment;
import com.xcc.advancedday13.ui.fragments.TravelsFragment;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener,TabLayout.OnTabSelectedListener {

    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private MainViewPagerAdapter adapter;
    private String[] text={"攻略","游记","行程单","我的"};
    private int[] imageArray={R.mipmap.icon_tab_home,R.mipmap.icon_tab_trip,R.mipmap.icon_tab_plan,R.mipmap.icon_tab_my};

    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTablayout = (TabLayout) findViewById(R.id.main_tab);

        for (int i = 0; i < 4; i++) {
            View tabItem= LayoutInflater.from(this).inflate(R.layout.tab_item, null);
            TextView title = (TextView) tabItem.findViewById(R.id.tab_item_title);
            ImageView image = (ImageView) tabItem.findViewById(R.id.tab_item_image);
            title.setText(text[i]);
            image.setImageResource(imageArray[i]);
            TabLayout.Tab tab = mTablayout.newTab();
            if (i==0) {
                tab.select();
            }
            tab.setCustomView(tabItem);
            mTablayout.addTab(tab);
        }



        mViewPager = (ViewPager) findViewById(R.id.main_vp);
        adapter = new MainViewPagerAdapter(getSupportFragmentManager(),getData());
        mViewPager.setAdapter(adapter);

        //mTablayout.setupWithViewPager(mViewPager);
        mTablayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTablayout));
        mTablayout.setOnTabSelectedListener(this);

        mViewPager.addOnPageChangeListener(this);
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
        MyFragment myFragment1 = new MyFragment();
        data.add(myFragment1);

        //第四个Fragment
        MyFragment myFragment = new MyFragment();
        data.add(myFragment);
        return data;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position==0) {
            ImageView image = (ImageView) mTablayout.getTabAt(position).getCustomView().findViewById(R.id.tab_item_image);
             TextView title = (TextView) mTablayout.getTabAt(position).getCustomView().findViewById(R.id.tab_item_title);
            image.setVisibility(View.VISIBLE);
            title.setVisibility(View.GONE);
        }
//        ImageView image = (ImageView) mTablayout.getTabAt(position).getCustomView().findViewById(R.id.tab_item_image);
//        TextView title = (TextView) mTablayout.getTabAt(position).getCustomView().findViewById(R.id.tab_item_title);
//        if (image.getVisibility()== View.GONE) {
//            image.setVisibility(View.VISIBLE);
//            title.setVisibility(View.GONE);
//        }else{
//            image.setVisibility(View.GONE);
//            title.setVisibility(View.VISIBLE);
//        }


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        TextView title = (TextView) tab.getCustomView().findViewById(R.id.tab_item_title);
        ImageView image = (ImageView) tab.getCustomView().findViewById(R.id.tab_item_image);
//        if (image.getVisibility()== View.GONE) {
//            image.setVisibility(View.VISIBLE);
//            title.setVisibility(View.GONE);
//        }else{
//            image.setVisibility(View.GONE);
//            title.setVisibility(View.VISIBLE);
//        }

        image.setVisibility(View.VISIBLE);
            title.setVisibility(View.GONE);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        TextView title = (TextView) tab.getCustomView().findViewById(R.id.tab_item_title);
        ImageView image = (ImageView) tab.getCustomView().findViewById(R.id.tab_item_image);

        image.setVisibility(View.GONE);
        title.setVisibility(View.VISIBLE);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
