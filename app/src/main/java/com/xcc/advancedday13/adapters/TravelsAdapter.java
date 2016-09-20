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

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by z on 2016/9/20.
 */
public class TravelsAdapter extends RecyclerView.Adapter<TravelsAdapter.ViewHolder> {
    private final Context context;
    private List<TravelRoot.DataBean> data;
    private LayoutInflater inflater;
    private ImageOptions options;

    public TravelsAdapter(Context context,List<TravelRoot.DataBean> data) {
        this.context = context;
        if (data!=null) {
            this.data = data;
        }else {
            this.data=new ArrayList<>();
        }
        options=new ImageOptions.Builder()
                .setCircular(true)
                .setLoadingDrawableId(R.mipmap.travels_image_default)
                .setRadius(40)
                .build();
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public void addRes(List<TravelRoot.DataBean> data){
        if (data!=null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }
    public void updateRes(List<TravelRoot.DataBean> data){
        if (data!=null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
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
        x.image().bind(holder.travelsActivityPhotoUrl,data.get(position).getActivity().getUser().getPhoto_url(),options);
        holder.travelsActivityName.setText(data.get(position).getActivity().getUser().getName());
        holder.travelsName.setText(data.get(position).getUser().getName());
        int gender = data.get(position).getActivity().getUser().getGender();
        if (gender==0) {
            holder.travelsFollow.setText("关注她");
        }else{
            holder.travelsFollow.setText("关注他");
        }
        ViewGroup.LayoutParams layoutParams = holder.travelsActivityContentsPhotoUrlOne.getLayoutParams();
        layoutParams.height=data.get(position).getActivity().getContents().get(0).getHeight();
        holder.travelsActivityContentsPhotoUrlOne.setLayoutParams(layoutParams);
//        Picasso.with(context).load(data.get(position).getActivity().getContents().get(0).getPhoto_url()).into(holder.travelsActivityContentsPhotoUrlOne);
        holder.travelsActivityTopic.setText(data.get(position).getActivity().getTopic());
        holder.travelsActivityDescription.setText(data.get(position).getActivity().getDescription());
        int lineCount = holder.travelsActivityDescription.getLineCount();
        if (lineCount>9) {
            holder.travelsActivityDistrictsToDetail.setVisibility(View.GONE);
            holder.travelsActivityDescription.setMaxLines(6);
        }else{
            holder.travelsReadAll.setVisibility(View.GONE);
            String toDetail = data.get(position).getActivity().getUser().getName() + ":"
                    + data.get(position).getActivity().getDistricts().get(0).getName();
            holder.travelsActivityDistrictsToDetail.setText(toDetail);
        }
        int likesCount = data.get(position).getActivity().getLikes_count();
        if (likesCount==0) {
            holder.travelsActivityLikesCount.setText("");
        }else {
            holder.travelsActivityLikesCount.setText(String.valueOf(likesCount));
        }
        int commentsCount = data.get(position).getActivity().getComments_count();
        if (commentsCount==0) {
            holder.travelsActivityCommentsCount.setText("");
        }else{
            holder.travelsActivityCommentsCount.setText(String.valueOf(commentsCount));
        }
        int favoritesCount = data.get(position).getActivity().getFavorites_count();
        if (favoritesCount==0) {
            holder.travelsActivityFavoritesCount.setText("");
        }else{
            holder.travelsActivityFavoritesCount.setText(String.valueOf(favoritesCount));
        }

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
