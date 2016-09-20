package com.xcc.advancedday13.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by bukeyishidecheng on 16/9/20.
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> data;

    public MainViewPagerAdapter(FragmentManager fm,List<Fragment> data) {
        super(fm);
        this.data=data;
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
