package com.xcc.advancedday13.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
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
import com.xcc.advancedday13.utils.DateUtil;
import com.xcc.advancedday13.utils.PxUtils;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TravelsAdapter extends RecyclerView.Adapter<TravelsAdapter.ViewHolder> implements View.OnClickListener {
    private final Context context;
    private List<TravelRoot.DataBean> data;
    private LayoutInflater inflater;
    private ImageOptions options;
    private OnReadAllClicked readAllClicked;
    private RecyclerView recyclerView;
    private OnUserIconClicked userIconClicked;

    public void setReadAllClicked(OnReadAllClicked readAllClicked) {
        this.readAllClicked = readAllClicked;
    }

    public void setUserIconClicked(OnUserIconClicked userIconClicked) {
        this.userIconClicked = userIconClicked;
    }

    public TravelsAdapter(Context context, List<TravelRoot.DataBean> data) {
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

    public void addRes(List<TravelRoot.DataBean> data) {
        if (data != null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void updateRes(List<TravelRoot.DataBean> data) {
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
        View itemView = inflater.inflate(R.layout.travels_recycler_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        x.image().bind(holder.travelsActivityPhotoUrl, data.get(position).getActivity().getUser().getPhoto_url(), options);
        holder.travelsActivityPhotoUrl.setOnClickListener(this);
//        ViewGroup.LayoutParams layoutParams1 = holder.travelsActivityPhotoUrl.getLayoutParams();
        holder.travelsActivityName.setText(data.get(position).getActivity().getUser().getName());
        holder.travelsName.setText(data.get(position).getUser().getName());
        int gender = data.get(position).getActivity().getUser().getGender();
        if (gender == 0) {
            holder.travelsFollow.setText("关注她");
        } else {
            holder.travelsFollow.setText("关注他");
        }
        List<TravelRoot.DataBean.ActivityBean.ContentsBean> photoContentsUrl = data.get(position).getActivity().getContents();
        holder.travelsActivityContentsPhotoUrlOthers.removeAllViews();
        for (int i = 1; i < photoContentsUrl.size(); i++) {
            ImageView imageView = new ImageView(context);
            holder.travelsActivityContentsPhotoUrlOthers.addView(imageView);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            marginLayoutParams.width = PxUtils.dp2px(context, 120);
            marginLayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            if (i != 1) {
                marginLayoutParams.leftMargin = PxUtils.dp2px(context, 5);
            }
            imageView.setLayoutParams(marginLayoutParams);
//            Picasso.with(context).load(data.get(position).getActivity().getContents().get(i).getPhoto_url())
////                .error()
//                    .placeholder(R.mipmap.travels_image_default)
//                    .into(imageView);
        }
        ViewGroup.LayoutParams layoutParams = holder.travelsActivityContentsPhotoUrlOne.getLayoutParams();
        layoutParams.height = data.get(position).getActivity().getContents().get(0).getHeight();
        holder.travelsActivityContentsPhotoUrlOne.setLayoutParams(layoutParams);
//        Picasso.with(context).load(data.get(position).getActivity().getContents().get(0).getPhoto_url())
////                .error()
//                .placeholder(R.mipmap.travels_image_default)
//                .into(holder.travelsActivityContentsPhotoUrlOne);
        holder.travelsActivityTopic.setText(data.get(position).getActivity().getTopic());
        String description = data.get(position).getActivity().getDescription();
        holder.travelsActivityDescription.setText(description);
        if (data.get(position).getActivity().isReadAllClicked()) {
            if (data.get(position).getActivity().isShowDetail()) {
                String toDetail = data.get(position).getActivity().getUser().getName() + ":"
                        + data.get(position).getActivity().getDistricts().get(0).getName() + "旅行记(" +
                        data.get(position).getActivity().getParent_district_count() + "篇)";
                holder.travelsActivityDistrictsToDetail.setVisibility(View.VISIBLE);
                holder.travelsActivityDistrictsToDetail.setText(toDetail);
            }else {
                holder.travelsActivityDistrictsToDetail.setVisibility(View.GONE);
            }
            holder.travelsActivityDescription.setSingleLine(false);
            holder.travelsReadAll.setVisibility(View.GONE);
        }else {
            if (description.length() > 180) {
                holder.travelsActivityDistrictsToDetail.setVisibility(View.GONE);
                holder.travelsActivityDescription.setMaxLines(6);
                holder.travelsReadAll.setVisibility(View.VISIBLE);
            } else {
                if (data.get(position).getActivity().getParent_district_count()>1) {

                    String toDetail = data.get(position).getActivity().getUser().getName() + ":"
                            + data.get(position).getActivity().getDistricts().get(0).getName() + "旅行记(" +
                            data.get(position).getActivity().getParent_district_count() + "篇)";
                    holder.travelsActivityDistrictsToDetail.setText(toDetail);
                    holder.travelsActivityDistrictsToDetail.setVisibility(View.VISIBLE);
                }else {
                    holder.travelsActivityDistrictsToDetail.setVisibility(View.GONE);
                }
                holder.travelsReadAll.setVisibility(View.GONE);
            }
        }

        holder.travelsReadAll.setOnClickListener(this);
        List<TravelRoot.DataBean.ActivityBean.DistrictsBean> districts = data.get(position).getActivity().getDistricts();
        holder.travelsActivityDistricts.removeAllViews();

        for (int i = 0; i < districts.size(); i++) {
            TextView district = new TextView(context);
            holder.travelsActivityDistricts.addView(district);
            district.setTextSize(TypedValue.COMPLEX_UNIT_PX, PxUtils.sp2px(context, 12));
            district.setPadding(PxUtils.dp2px(context,10),PxUtils.dp2px(context,5),PxUtils.dp2px(context,10),PxUtils.dp2px(context,5));
            district.setGravity(Gravity.CENTER);
            ViewGroup.MarginLayoutParams districtLayoutParams = (ViewGroup.MarginLayoutParams) district.getLayoutParams();
            districtLayoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            districtLayoutParams.height = PxUtils.dp2px(context, 25);
            if (i != 0) {
                districtLayoutParams.leftMargin = PxUtils.dp2px(context, 5);
            }
            district.setLayoutParams(districtLayoutParams);
            district.setText(districts.get(i).getName());
            district.setBackgroundResource(R.drawable.travels_district_shape);
        }

        TravelRoot.DataBean.ActivityBean.PoiBean pois = data.get(position).getActivity().getPoi();
        if (pois != null) {
            TextView poi = new TextView(context);
            holder.travelsActivityDistricts.addView(poi);
            poi.setTextSize(TypedValue.COMPLEX_UNIT_PX, PxUtils.sp2px(context, 12));
            poi.setPadding(PxUtils.dp2px(context,10),PxUtils.dp2px(context,5),PxUtils.dp2px(context,10),PxUtils.dp2px(context,5));
            poi.setGravity(Gravity.CENTER);
            ViewGroup.MarginLayoutParams poiLayoutParams = (ViewGroup.MarginLayoutParams) poi.getLayoutParams();
            poiLayoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            poiLayoutParams.height = PxUtils.dp2px(context, 25);
            poiLayoutParams.leftMargin = PxUtils.dp2px(context, 5);
            poi.setLayoutParams(poiLayoutParams);
            poi.setText(pois.getName());
            poi.setBackgroundResource(R.drawable.travels_district_shape);
        }

        List<TravelRoot.DataBean.ActivityBean.Categorie> categories = data.get(position).getActivity().getCategories();
        if (categories != null) {
            for (int i = 0; i < categories.size(); i++) {
                TextView categorie = new TextView(context);
                holder.travelsActivityDistricts.addView(categorie);
                categorie.setTextSize(TypedValue.COMPLEX_UNIT_PX, PxUtils.sp2px(context, 12));
                categorie.setPadding(PxUtils.dp2px(context,10),PxUtils.dp2px(context,5),PxUtils.dp2px(context,10),PxUtils.dp2px(context,5));
                categorie.setGravity(Gravity.CENTER);
                ViewGroup.MarginLayoutParams categorieLayoutParams = (ViewGroup.MarginLayoutParams) categorie.getLayoutParams();
                categorieLayoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                categorieLayoutParams.height = PxUtils.dp2px(context, 25);
                categorieLayoutParams.leftMargin = PxUtils.dp2px(context, 5);
                categorie.setLayoutParams(categorieLayoutParams);
                categorie.setText(categories.get(i).getName());
                categorie.setBackgroundResource(R.drawable.travels_district_shape);
            }
        }

        String createdAt = data.get(position).getActivity().getCreated_at();
        if (createdAt != null) {
            String[] split = createdAt.split("-");
            TextView time = new TextView(context);
            holder.travelsActivityDistricts.addView(time);
            time.setTextSize(TypedValue.COMPLEX_UNIT_PX, PxUtils.sp2px(context, 12));
            time.setPadding(PxUtils.dp2px(context,10),PxUtils.dp2px(context,5),PxUtils.dp2px(context,10),PxUtils.dp2px(context,5));
            time.setGravity(Gravity.CENTER);
            ViewGroup.MarginLayoutParams poiLayoutParams = (ViewGroup.MarginLayoutParams) time.getLayoutParams();
            poiLayoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            poiLayoutParams.height = PxUtils.dp2px(context, 25);
            poiLayoutParams.leftMargin = PxUtils.dp2px(context, 5);
            time.setLayoutParams(poiLayoutParams);
            time.setText(DateUtil.smallToBig(Integer.parseInt(split[1])));
            time.setBackgroundResource(R.drawable.travels_district_shape);
        }
        int likesCount = data.get(position).getActivity().getLikes_count();
        if (likesCount == 0) {
            holder.travelsActivityLikesCount.setText("");
        } else {
            holder.travelsActivityLikesCount.setText(String.valueOf(likesCount));
        }
        int commentsCount = data.get(position).getActivity().getComments_count();
        if (commentsCount == 0) {
            holder.travelsActivityCommentsCount.setText("");
        } else {
            holder.travelsActivityCommentsCount.setText(String.valueOf(commentsCount));
        }
        int favoritesCount = data.get(position).getActivity().getFavorites_count();
        if (favoritesCount == 0) {
            holder.travelsActivityFavoritesCount.setText("");
        } else {
            holder.travelsActivityFavoritesCount.setText(String.valueOf(favoritesCount));
        }
        int level = data.get(position).getActivity().getUser().getLevel();
        if (level <= 2) {
            holder.travelsUserV.setVisibility(View.GONE);
        } else {
            holder.travelsUserV.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.travels_recycler_item_activity_photo_url:
                userIconClicked.onUserIconClicked(data.get(recyclerView.getChildAdapterPosition((LinearLayout) v.getParent().getParent().getParent())));
                break;
            case R.id.travels_recycler_item_name:
                break;
            case R.id.travels_recycler_item_follow:
                break;
            case R.id.travels_recycler_item_activity_contents_photo_url_one:
                break;
            case R.id.travels_recycler_item_read_all:
                int childAdapterPosition = recyclerView.getChildAdapterPosition((LinearLayout) v.getParent().getParent());
                readAllClicked.onReadAllClicked(data.get(childAdapterPosition));
                break;
            case R.id.travels_recycler_item_activity_districts_to_detail:
                break;
            case R.id.travels_recycler_item_activity_likes_count:
                break;
            case R.id.travels_recycler_item_activity_comments_count:
                break;
            case R.id.travels_recycler_item_activity_favorites_count:
                break;
            case R.id.travels_recycler_item_more:
                break;
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
        @BindView(R.id.travels_recycler_item_activity_districts)
        LinearLayout travelsActivityDistricts;
        @BindView(R.id.travels_recycler_item_activity_likes_count)
        CheckBox travelsActivityLikesCount;
        @BindView(R.id.travels_recycler_item_activity_comments_count)
        CheckBox travelsActivityCommentsCount;
        @BindView(R.id.travels_recycler_item_activity_favorites_count)
        CheckBox travelsActivityFavoritesCount;
        @BindView(R.id.travels_recycler_item_more)
        TextView travelsMore;
        @BindView(R.id.travels_recycler_item_user_v)
        ImageView travelsUserV;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnReadAllClicked {
        void onReadAllClicked(TravelRoot.DataBean item);
    }
    public interface OnUserIconClicked {
        void onUserIconClicked(TravelRoot.DataBean item);
    }
}
