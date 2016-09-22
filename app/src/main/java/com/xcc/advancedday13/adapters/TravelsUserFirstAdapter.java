package com.xcc.advancedday13.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xcc.advancedday13.R;
import com.xcc.advancedday13.model.UserActivityModel;
import com.xcc.advancedday13.utils.DateUtil;
import com.xcc.advancedday13.utils.PxUtils;

import org.xutils.image.ImageOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TravelsUserFirstAdapter extends RecyclerView.Adapter<TravelsUserFirstAdapter.ViewHolder> implements View.OnClickListener {
    private final Context context;
    private List<UserActivityModel.DataBean> data;
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

    public TravelsUserFirstAdapter(Context context, List<UserActivityModel.DataBean> data) {
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
        View itemView = inflater.inflate(R.layout.travels_user_first_recycler_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        List<UserActivityModel.DataBean.ContentsBean> contents = data.get(position).getContents();
        holder.travelsUserFirstRecyclerItemActivityContentsPhotoUrlOthers.removeAllViews();
        for (int i = 1; i < contents.size(); i++) {
            ImageView imageView = new ImageView(context);
            holder.travelsUserFirstRecyclerItemActivityContentsPhotoUrlOthers.addView(imageView);
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
        ViewGroup.LayoutParams layoutParams = holder.travelsUserFirstRecyclerItemActivityContentsPhotoUrlOne.getLayoutParams();
        layoutParams.height = data.get(position).getContents().get(0).getHeight();
        holder.travelsUserFirstRecyclerItemActivityContentsPhotoUrlOne.setLayoutParams(layoutParams);
//        Picasso.with(context).load(data.get(position).getActivity().getContents().get(0).getPhoto_url())
////                .error()
//                .placeholder(R.mipmap.travels_image_default)
//                .into(holder.travelsActivityContentsPhotoUrlOne);
        holder.travelsUserFirstRecyclerItemActivityTopic.setText(data.get(position).getTopic());
        String description = data.get(position).getDescription();
        holder.travelsUserFirstRecyclerItemActivityDescription.setText(description);
        if (data.get(position).isReadAllClicked()) {
            String toDetail = "#"+data.get(position).getDistricts().get(0).getName() + "旅行记#";
            holder.travelsUserFirstRecyclerItemActivityDistrictsToDetail.setVisibility(View.VISIBLE);
            holder.travelsUserFirstRecyclerItemActivityDistrictsToDetail.setText(toDetail);
            holder.travelsUserFirstRecyclerItemReadAll.setVisibility(View.GONE);
            holder.travelsUserFirstRecyclerItemActivityDescription.setSingleLine(false);
        } else {
            if (description.length() > 180) {
                holder.travelsUserFirstRecyclerItemActivityDistrictsToDetail.setVisibility(View.GONE);
                holder.travelsUserFirstRecyclerItemActivityDescription.setMaxLines(6);
                holder.travelsUserFirstRecyclerItemReadAll.setVisibility(View.VISIBLE);
            } else {
                String toDetail = "#"+data.get(position).getDistricts().get(0).getName() + "旅行记#";
                holder.travelsUserFirstRecyclerItemActivityDistrictsToDetail.setText(toDetail);
                holder.travelsUserFirstRecyclerItemActivityDistrictsToDetail.setVisibility(View.VISIBLE);
                holder.travelsUserFirstRecyclerItemReadAll.setVisibility(View.GONE);
            }
        }

        holder.travelsUserFirstRecyclerItemReadAll.setOnClickListener(this);
        List<UserActivityModel.DataBean.DistrictsBean> districts = data.get(position).getDistricts();
        holder.travelsUserFirstRecyclerItemActivityDistricts.removeAllViews();

        for (int i = 0; i < districts.size(); i++) {
            TextView district = new TextView(context);
            holder.travelsUserFirstRecyclerItemActivityDistricts.addView(district);
            district.setTextSize(TypedValue.COMPLEX_UNIT_PX, PxUtils.sp2px(context, 12));
            district.setPadding(PxUtils.dp2px(context, 10), PxUtils.dp2px(context, 5), PxUtils.dp2px(context, 10), PxUtils.dp2px(context, 5));
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

        UserActivityModel.DataBean.PoiBean pois = data.get(position).getPoi();
        if (pois != null) {
            TextView poi = new TextView(context);
            holder.travelsUserFirstRecyclerItemActivityDistricts.addView(poi);
            poi.setTextSize(TypedValue.COMPLEX_UNIT_PX, PxUtils.sp2px(context, 12));
            poi.setPadding(PxUtils.dp2px(context, 10), PxUtils.dp2px(context, 5), PxUtils.dp2px(context, 10), PxUtils.dp2px(context, 5));
            poi.setGravity(Gravity.CENTER);
            ViewGroup.MarginLayoutParams poiLayoutParams = (ViewGroup.MarginLayoutParams) poi.getLayoutParams();
            poiLayoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            poiLayoutParams.height = PxUtils.dp2px(context, 25);
            poiLayoutParams.leftMargin = PxUtils.dp2px(context, 5);
            poi.setLayoutParams(poiLayoutParams);
            poi.setText(pois.getName());
            poi.setBackgroundResource(R.drawable.travels_district_shape);
        }

        List<UserActivityModel.DataBean.CategoriesBean> categories = data.get(position).getCategories();
        if (categories != null) {
            for (int i = 0; i < categories.size(); i++) {
                TextView categorie = new TextView(context);
                holder.travelsUserFirstRecyclerItemActivityDistricts.addView(categorie);
                categorie.setTextSize(TypedValue.COMPLEX_UNIT_PX, PxUtils.sp2px(context, 12));
                categorie.setPadding(PxUtils.dp2px(context, 10), PxUtils.dp2px(context, 5), PxUtils.dp2px(context, 10), PxUtils.dp2px(context, 5));
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

        String createdAt = data.get(position).getCreated_at();
        if (createdAt != null) {
            String[] split = createdAt.split("-");
            TextView time = new TextView(context);
            holder.travelsUserFirstRecyclerItemActivityDistricts.addView(time);
            time.setTextSize(TypedValue.COMPLEX_UNIT_PX, PxUtils.sp2px(context, 12));
            time.setPadding(PxUtils.dp2px(context, 10), PxUtils.dp2px(context, 5), PxUtils.dp2px(context, 10), PxUtils.dp2px(context, 5));
            time.setGravity(Gravity.CENTER);
            ViewGroup.MarginLayoutParams poiLayoutParams = (ViewGroup.MarginLayoutParams) time.getLayoutParams();
            poiLayoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            poiLayoutParams.height = PxUtils.dp2px(context, 25);
            poiLayoutParams.leftMargin = PxUtils.dp2px(context, 5);
            time.setLayoutParams(poiLayoutParams);
            time.setText(DateUtil.smallToBig(Integer.parseInt(split[1])));
            time.setBackgroundResource(R.drawable.travels_district_shape);
        }
        int likesCount = data.get(position).getLikes_count();
        if (likesCount == 0) {
            holder.travelsUserFirstRecyclerItemActivityLikesCount.setText("");
        } else {
            holder.travelsUserFirstRecyclerItemActivityLikesCount.setText(String.valueOf(likesCount));
        }
        int commentsCount = data.get(position).getComments_count();
        if (commentsCount == 0) {
            holder.travelsUserFirstRecyclerItemActivityCommentsCount.setText("");
        } else {
            holder.travelsUserFirstRecyclerItemActivityCommentsCount.setText(String.valueOf(commentsCount));
        }
        int favoritesCount = data.get(position).getFavorites_count();
        if (favoritesCount == 0) {
            holder.travelsUserFirstRecyclerItemActivityFavoritesCount.setText("");
        } else {
            holder.travelsUserFirstRecyclerItemActivityFavoritesCount.setText(String.valueOf(favoritesCount));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.travels_recycler_item_activity_photo_url:
//                userIconClicked.onUserIconClicked(data.get(recyclerView.getChildAdapterPosition((LinearLayout) v.getParent().getParent().getParent())));
                break;
            case R.id.travels_recycler_item_name:
                break;
            case R.id.travels_recycler_item_follow:
                break;
            case R.id.travels_recycler_item_activity_contents_photo_url_one:
                break;
            case R.id.travels_recycler_item_read_all:
                int childAdapterPosition = recyclerView.getChildAdapterPosition((LinearLayout) v.getParent().getParent());
//                readAllClicked.onReadAllClicked(data.get(childAdapterPosition));
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


    public interface OnReadAllClicked {
        void onReadAllClicked(UserActivityModel.DataBean item);
    }

    public interface OnUserIconClicked {
        void onUserIconClicked(UserActivityModel.DataBean item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.travels_user_first_recycler_item_activity_contents_photo_url_one)
        ImageView travelsUserFirstRecyclerItemActivityContentsPhotoUrlOne;
        @BindView(R.id.travels_user_first_recycler_item_activity_contents_photo_url_others)
        LinearLayout travelsUserFirstRecyclerItemActivityContentsPhotoUrlOthers;
        @BindView(R.id.travels_user_first_recycler_item_activity_topic)
        TextView travelsUserFirstRecyclerItemActivityTopic;
        @BindView(R.id.travels_user_first_recycler_item_activity_description)
        TextView travelsUserFirstRecyclerItemActivityDescription;
        @BindView(R.id.travels_user_first_recycler_item_read_all)
        TextView travelsUserFirstRecyclerItemReadAll;
        @BindView(R.id.travels_user_first_recycler_item_activity_districts_to_detail)
        TextView travelsUserFirstRecyclerItemActivityDistrictsToDetail;
        @BindView(R.id.travels_user_first_recycler_item_activity_districts)
        LinearLayout travelsUserFirstRecyclerItemActivityDistricts;
        @BindView(R.id.travels_user_first_recycler_item_activity_likes_count)
        CheckBox travelsUserFirstRecyclerItemActivityLikesCount;
        @BindView(R.id.travels_user_first_recycler_item_activity_comments_count)
        CheckBox travelsUserFirstRecyclerItemActivityCommentsCount;
        @BindView(R.id.travels_user_first_recycler_item_activity_favorites_count)
        CheckBox travelsUserFirstRecyclerItemActivityFavoritesCount;
        @BindView(R.id.travels_user_first_recycler_item_more)
        TextView travelsUserFirstRecyclerItemMore;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
