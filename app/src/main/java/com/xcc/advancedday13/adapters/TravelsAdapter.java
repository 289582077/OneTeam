package com.xcc.advancedday13.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.xcc.advancedday13.model.TravelRoot;

import java.util.List;

/**
 * Created by z on 2016/9/20.
 */
public class TravelsAdapter extends RecyclerView.Adapter<TravelsAdapter.ViewHolder>{
    private List<TravelRoot.DataBean> data;
    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
