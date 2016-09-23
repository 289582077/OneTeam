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
import com.lhh.apst.library.Margins;
import com.xcc.advancedday13.R;
import com.xcc.advancedday13.utils.PxUtils;

import java.util.List;


public class UserViewPagerAdapter extends FragmentPagerAdapter implements AdvancedPagerSlidingTabStrip.ViewTabProvider,AdvancedPagerSlidingTabStrip.LayoutProvider{
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
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(PxUtils.dp2px(context, 30), PxUtils.dp2px(context, 30));
//            params.topMargin=PxUtils.dp2px(context,25);
            params.alignWithParent = true;
            imageView.setLayoutParams(params);

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
//            imageView.setPadding(0,PxUtils.dp2px(context,25),0,0);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(PxUtils.dp2px(context, 30), PxUtils.dp2px(context, 30));
//            params.topMargin=PxUtils.dp2px(context,25);
            imageView.setLayoutParams(params);
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

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

    @Override
    public float getPageWeight(int position) {
        return 0;
    }

    @Override
    public int[] getPageRule(int position) {
        return new int[]{RelativeLayout.ALIGN_PARENT_LEFT};
    }

    @Override
    public Margins getPageMargins(int position) {
//        return new Margins(0,PxUtils.dp2px(context, 10),0,0);
        return null;
    }
}
