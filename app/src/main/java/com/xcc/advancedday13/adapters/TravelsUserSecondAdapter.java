package com.xcc.advancedday13.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.xcc.advancedday13.R;
import com.xcc.advancedday13.model.UserActivityModel;

import org.xutils.image.ImageOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TravelsUserSecondAdapter extends RecyclerView.Adapter<TravelsUserSecondAdapter.ViewHolder> {
    private final Context context;
    private List<UserActivityModel.DataBean> data;
    private LayoutInflater inflater;
    private ImageOptions options;
    private RecyclerView recyclerView;


    public TravelsUserSecondAdapter(Context context, List<UserActivityModel.DataBean> data) {
        this.context = context;
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
        options = new ImageOptions.Builder()
                .setCircular(true)
                .setLoadingDrawableId(R.mipmap.travels_image_default)
                .setRadius(30)
                .build();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addRes(List<UserActivityModel.DataBean> data) {
        if (data != null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void updateRes(List<UserActivityModel.DataBean> data) {
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
        View itemView = inflater.inflate(R.layout.travels_user_second_recycler_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context).load(data.get(position).getContents().get(0).getPhoto_url())
                .placeholder(R.mipmap.travels_image_default)
                .into(holder.userContent);

    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.user_content)
        ImageView userContent;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
