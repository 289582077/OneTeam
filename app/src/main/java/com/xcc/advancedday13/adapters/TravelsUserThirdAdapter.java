package com.xcc.advancedday13.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xcc.advancedday13.R;
import com.xcc.advancedday13.model.UserGrouped;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TravelsUserThirdAdapter extends RecyclerView.Adapter<TravelsUserThirdAdapter.ViewHolder> {
    private final Context context;
    private List<UserGrouped.DataBean> data;
    private LayoutInflater inflater;
    private RecyclerView recyclerView;

    public TravelsUserThirdAdapter(Context context, List<UserGrouped.DataBean> data) {
        this.context = context;
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addRes(List<UserGrouped.DataBean> data) {
        if (data != null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void updateRes(List<UserGrouped.DataBean> data) {
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
        View itemView = inflater.inflate(R.layout.travels_user_third_recycler_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.userThirdName.setText(data.get(position).getDistrict().getName());
        holder.userThirdDuring.setText(data.get(position).getDistrict().getDuring());
        holder.userThirdRecycler.setLayoutManager(new LinearLayoutManager(context));
        holder.userThirdRecycler.setAdapter(new TravelsUserThirdRecyclerAdapter(context,data.get(position).getActivities()));
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.user_third_name)
        TextView userThirdName;
        @BindView(R.id.user_third_during)
        TextView userThirdDuring;
        @BindView(R.id.user_third_recycler)
        RecyclerView userThirdRecycler;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}