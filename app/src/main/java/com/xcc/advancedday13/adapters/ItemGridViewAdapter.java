package com.xcc.advancedday13.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xcc.advancedday13.R;
import com.xcc.advancedday13.model.City;

import org.xutils.x;

import java.util.List;

/**
 * Created by bukeyishidecheng on 16/9/20.
 */
public class ItemGridViewAdapter extends RecyclerView.Adapter<ItemGridViewAdapter.ViewHolder> {

    private static final String TAG = ItemGridViewAdapter.class.getSimpleName();
    private List<City.DataBean.DestinationsBean> data;
    private LayoutInflater inflater;
    private Context context;

    public ItemGridViewAdapter(Context context,List<City.DataBean.DestinationsBean> data) {
        this.data = data;
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return data!=null?data.size():0;
    }

    public City.DataBean.DestinationsBean getItem(int position){
        return data.get(position);

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView=inflater.inflate(R.layout.strategy_item_gv_item,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (data.get(position).getName()!=null) {

            holder.name.setText(data.get(position).getName());
        }
        if (data.get(position).getPhoto_url()!=null) {

            Picasso.with(context).load(data.get(position).getPhoto_url()).into(holder.image);
            //Log.e(TAG, "onBindViewHolder: "+data.get(position).getPhoto_url());
            //x.image().bind(holder.image,data.get(position).getPhoto_url());
        }

    }

    public  static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.strategy_item_gv_item_image);
            name= (TextView) itemView.findViewById(R.id.strategy_item_gv_item_name);
        }
    }
}
