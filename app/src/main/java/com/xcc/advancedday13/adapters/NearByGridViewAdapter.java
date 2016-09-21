package com.xcc.advancedday13.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xcc.advancedday13.R;
import com.xcc.advancedday13.model.City;
import com.xcc.advancedday13.model.NearByCity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bukeyishidecheng on 16/9/20.
 */
public class NearByGridViewAdapter extends BaseAdapter {

    private static final String TAG = NearByGridViewAdapter.class.getSimpleName();
    private List<NearByCity.DataBean> data;
    private LayoutInflater inflater;
    private Context context;

    public NearByGridViewAdapter(Context context, List<NearByCity.DataBean> data) {
        if (data!=null) {
            this.data = data;
        }else {
            this.data=new ArrayList<>();
        }

        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    public void updateRes(List<NearByCity.DataBean> data){
        if (data!=null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }

    }


    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    public NearByCity.DataBean getItem(int position){
        return data.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView=inflater.inflate(R.layout.strategy_item_gv_item,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(data.get(position).getName());
        holder.name_en.setText(data.get(position).getName_en());

        if (data.get(position).getPhoto_url() != null) {

            Picasso.with(context).load(data.get(position).getPhoto().getPhoto_url())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(holder.image);

        }
        return convertView;

    }


    class ViewHolder {
        ImageView image;
        TextView name;
        TextView name_en;

        public ViewHolder(View itemView) {
            image = (ImageView) itemView.findViewById(R.id.strategy_item_gv_item_image);
            name = (TextView) itemView.findViewById(R.id.strategy_item_gv_item_name);
            name_en = (TextView) itemView.findViewById(R.id.strategy_item_gv_item_name_en);
        }
    }
}
