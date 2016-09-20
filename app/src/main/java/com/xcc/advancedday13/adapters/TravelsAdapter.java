package com.xcc.advancedday13.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xcc.advancedday13.R;
import com.xcc.advancedday13.model.TravelRoot;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by z on 2016/9/20.
 */
public class TravelsAdapter extends RecyclerView.Adapter<TravelsAdapter.ViewHolder> {
    private List<TravelRoot.DataBean> data;
    private LayoutInflater inflater;

    public TravelsAdapter(Context context,List<TravelRoot.DataBean> data) {
        if (data!=null) {
            this.data = data;
        }else {
            this.data=new ArrayList<>();
        }
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemCount() {
        return data!=null?data.size():0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.travels_recycler_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.travelsActivityPhotoUrl;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.travels_recycler_item_activity_photo_url)
        ImageView travelsActivityPhotoUrl;
        @BindView(R.id.travels_recycler_item_activity_name)
        TextView travelsActivityName;
        @BindView(R.id.travels_recycler_item_name)
        TextView travelsName;
        @BindView(R.id.travels_recycler_item_follow)
        Button travelsFollow;
        @BindView(R.id.travels_recycler_item_activity_contents_photo_url_one)
        ImageView travelsActivityContentsPhotoUrlOne;
        @BindView(R.id.travels_recycler_item_activity_contents_photo_url_others)
        LinearLayout travelsActivityContentsPhotoUrlOthers;
        @BindView(R.id.travels_recycler_item_activity_topic)
        TextView travelsActivityTopic;
        @BindView(R.id.travels_recycler_item_activity_description)
        TextView travelsActivityDescription;
        @BindView(R.id.travels_recycler_item_read_all)
        TextView travelsReadAll;
        @BindView(R.id.travels_recycler_item_activity_districts_to_detail)
        TextView travelsActivityDistrictsToDetail;
        @BindView(R.id.travels_recycler_item_activity_poi)
        LinearLayout travelsActivityPoi;
        @BindView(R.id.travels_recycler_item_activity_likes_count)
        CheckBox travelsActivityLikesCount;
        @BindView(R.id.travels_recycler_item_activity_comments_count)
        CheckBox travelsActivityCommentsCount;
        @BindView(R.id.travels_recycler_item_activity_favorites_count)
        CheckBox travelsActivityFavoritesCount;
        @BindView(R.id.travels_recycler_item_more)
        TextView travelsMore;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
