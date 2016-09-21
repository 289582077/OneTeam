package com.xcc.advancedday13.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bukeyishidecheng on 16/9/21.
 */
public class StrategyHeaderAdapter extends PagerAdapter {
    private List<View> data;

    public StrategyHeaderAdapter(List<View> data) {
        if (data!=null) {
            this.data = data;
        }
        else {
            this.data=new ArrayList<>();
        }
    }

    public void updateRes(List<View> data){
        if (data!=null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }

    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        int currentPosition = position % data.size();
        container.addView(data.get(currentPosition));


        return data.get(currentPosition);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        int currentPosition = position % data.size();
        container.removeView(data.get(currentPosition));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
