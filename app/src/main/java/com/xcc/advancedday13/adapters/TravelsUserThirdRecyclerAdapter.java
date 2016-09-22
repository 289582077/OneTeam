package com.xcc.advancedday13.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xcc.advancedday13.R;
import com.xcc.advancedday13.model.UserGrouped;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TravelsUserThirdRecyclerAdapter extends RecyclerView.Adapter<TravelsUserThirdRecyclerAdapter.ViewHolder> {
    private final Context context;
    private List<UserGrouped.DataBean.ActivitiesBean> data;
    private LayoutInflater inflater;
    private RecyclerView recyclerView;

    public TravelsUserThirdRecyclerAdapter(Context context, List<UserGrouped.DataBean.ActivitiesBean> data) {
        this.context = context;
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addRes(List<UserGrouped.DataBean.ActivitiesBean> data) {
        if (data != null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void updateRes(List<UserGrouped.DataBean.ActivitiesBean> data) {
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.travels_user_third_recycler_recycler_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (data.get(position).getDistricts().size()>1) {
            if (position==0) {
                holder.userThirdLeftTime.setVisibility(View.VISIBLE);
            }else {
                if (data.get(position-1).getDistricts().size()>1) {
                    if (data.get(position).getDistricts().get(1).getName().equals(data.get(position-1).getDistricts().get(1).getName())) {
                        holder.userThirdLeftTime.setVisibility(View.INVISIBLE);
                    }else {
                        holder.userThirdDistrict.setText(data.get(position).getDistricts().get(1).getName());
                        holder.userThirdLeftTime.setVisibility(View.VISIBLE);
                    }
                }else {
                    holder.userThirdDistrict.setText(data.get(position).getDistricts().get(1).getName());
                    holder.userThirdLeftTime.setVisibility(View.VISIBLE);
                }
            }
        }else {
            holder.userThirdLeftTime.setVisibility(View.VISIBLE);
            holder.userThirdDistrict.setText(data.get(position).getDistricts().get(0).getName());
        }
        if (data.get(position).getMade_at()!=null) {
            String[] ts = data.get(position).getMade_at().split("T")[0].split("-");
            holder.userThirdMadeAt.setText(ts[1]+"."+ts[2]);
        }
        holder.userThirdTopic.setText(data.get(position).getTopic());
        List<UserGrouped.DataBean.ActivitiesBean.ContentsBean> contents = data.get(position).getContents();
//        if (contents.size()>3) {
//            Picasso.with(context).load(data.get(position).getContents().get(0).getPhoto_url())
//                    .placeholder(R.mipmap.travels_image_default)
//                    .into(holder.userThirdContentOne);
//            Picasso.with(context).load(data.get(position).getContents().get(1).getPhoto_url())
//                    .placeholder(R.mipmap.travels_image_default)
//                    .into(holder.userThirdContentTwo);
//            Picasso.with(context).load(data.get(position).getContents().get(2).getPhoto_url())
//                    .placeholder(R.mipmap.travels_image_default)
//                    .into(holder.userThirdContentThree);
//            Picasso.with(context).load(data.get(position).getContents().get(3).getPhoto_url())
//                    .placeholder(R.mipmap.travels_image_default)
//                    .into(holder.userThirdContentFour);
//        }

    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.user_third_made_at)
        TextView userThirdMadeAt;
        @BindView(R.id.user_third_district)
        TextView userThirdDistrict;
        @BindView(R.id.user_third_left_time)
        LinearLayout userThirdLeftTime;
        @BindView(R.id.user_third_topic)
        TextView userThirdTopic;
        @BindView(R.id.user_third_content_one)
        ImageView userThirdContentOne;
        @BindView(R.id.user_third_content_two)
        ImageView userThirdContentTwo;
        @BindView(R.id.user_third_content_three)
        ImageView userThirdContentThree;
        @BindView(R.id.user_third_content_four)
        ImageView userThirdContentFour;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}