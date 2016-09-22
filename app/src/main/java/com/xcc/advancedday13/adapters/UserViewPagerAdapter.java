package com.xcc.advancedday13.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;
import com.xcc.advancedday13.R;

import java.util.List;


public class UserViewPagerAdapter extends FragmentPagerAdapter implements AdvancedPagerSlidingTabStrip.ViewTabProvider{
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
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public View onSelectIconView(int position, View view, ViewGroup parent) {
        ImageView imageView;
        if (view==null) {
            imageView=new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new RelativeLayout.LayoutParams(20,20));
            view=imageView;
        }
        imageView= (ImageView) view;
        switch (position) {
            case 0:
                imageView.setImageResource(R.mipmap.tab_user_trip_grid_highlight);
                break;
            case 1:
                imageView.setImageResource(R.mipmap.tab_user_trip_text_highlight);
                break;
            case 2:
                imageView.setImageResource(R.mipmap.tab_user_trip_list_highlight);
                break;
        }
        return imageView;
    }

    @Override
    public View onIconView(int position, View view, ViewGroup parent) {
        ImageView imageView;
        if (view==null) {
            imageView=new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new RelativeLayout.LayoutParams(20,20));
            view=imageView;
        }
        imageView= (ImageView) view;
        switch (position) {
            case 0:
                imageView.setImageResource(R.mipmap.tab_user_trip_grid_normal);
                break;
            case 1:
                imageView.setImageResource(R.mipmap.tab_user_trip_text_normal);
                break;
            case 2:
                imageView.setImageResource(R.mipmap.tab_user_trip_list_normal);
                break;
        }
        return imageView;
    }
}
