package com.xcc.advancedday13.adapters;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lhh.ptrrv.library.PullToRefreshRecyclerView;
import com.xcc.advancedday13.R;
import com.xcc.advancedday13.model.City;
import com.xcc.advancedday13.widget.CustomRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bukeyishidecheng on 16/9/20.
 */
public class StrategyAdapter extends RecyclerView.Adapter<StrategyAdapter.ViewHolder> {
    private List<City.DataBean> data;
    private LayoutInflater inflater;
    private Context context;


    public StrategyAdapter(Context context, List<City.DataBean> data) {
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
        this.context=context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addRes(List<City.DataBean> data){
        if (data!=null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }

    }

    public void updateRes(List<City.DataBean> data){
        if (data!=null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }

    }
    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public City.DataBean getItem(int position) {
        return data.get(position);

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= inflater.inflate(R.layout.fragment_strategy_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //添加数据
        holder.title.setText(data.get(position).getName());
        holder.more.setText(data.get(position).getButton_text());

        GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
        holder.city.setLayoutManager(layoutManager);
        if (data.get(position).getDestinations()!=null) {

            holder.city.setAdapter(new ItemGridViewAdapter(context,data.get(position).getDestinations()));
        }





    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView more;
        CustomRecyclerView city;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.strategy_item_title);
            more = (TextView) itemView.findViewById(R.id.strategy_item_more);
            city = (CustomRecyclerView) itemView.findViewById(R.id.strategy_item_city);
        }
    }
}
