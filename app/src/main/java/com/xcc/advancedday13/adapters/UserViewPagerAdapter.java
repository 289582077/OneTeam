package com.xcc.advancedday13.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.viewpagerindicator.IconPagerAdapter;
import com.xcc.advancedday13.R;

import java.util.List;


public class UserViewPagerAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
    private final Context context;
    private List<Fragment> data;

    public UserViewPagerAdapter(FragmentManager fm, List<Fragment> data, Context context) {
        super(fm);
        this.data=data;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getIconResId(int index) {
        switch (index) {
            case 0:
                return R.drawable.user_first_fragment_tab;

            case 1:
                return R.drawable.user_second_fragment_tab;
            case 2:
                return R.drawable.user_third_fragment_tab;
        }
        return 0;
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

}
