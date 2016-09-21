package com.xcc.advancedday13.adapters;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bukeyishidecheng on 16/9/21.
 */
public class StrategyHeaderAdapter extends PagerAdapter {
    private static final String TAG = StrategyHeaderAdapter.class.getSimpleName();
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
        Log.e(TAG, "instantiateItem: "+data.size());
        if (data.size()!=0) {
            int currentPosition = position % data.size();
            int i = container.indexOfChild(data.get(currentPosition));
            if (i==-1) {
                container.addView(data.get(currentPosition));
            }
            return data.get(currentPosition);
        }
          return  null;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        int currentPosition = position % data.size();
        int i = container.indexOfChild(data.get(currentPosition));
        if (i>0) {
            container.removeView(data.get(currentPosition));

        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
