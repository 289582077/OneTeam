package com.xcc.advancedday13.ui.fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.xcc.advancedday13.R;
import com.xcc.advancedday13.ui.SettingActivity;
import com.xcc.advancedday13.base.BaseFragment;
import com.xcc.advancedday13.ui.FeedBackActivity;
import com.xcc.advancedday13.ui.LoginActivity;

/**
 * Created by DaXianSheng on 2016/9/20.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {
    private View mNotifi;
    private View mMore;
    private View mLogin;
    private View mCollect;
    private View mOrder;
    private View mFeedBack;
    private View mSet;
    private PopupWindow pw;
    private int widthPixels;
    private int heightPixels;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout=inflater.inflate(R.layout.fragment_my,container,false);
        pw = new PopupWindow(getActivity());
        widthPixels = getActivity().getResources().getDisplayMetrics().widthPixels;
        heightPixels = getActivity().getResources().getDisplayMetrics().heightPixels;
        View popView = LayoutInflater.from(getActivity()).inflate(R.layout.mypopupitem, null);
        Drawable drawable = getResources().getDrawable(R.drawable.pop_bg);
        pw.setBackgroundDrawable(drawable);
        pw.setWidth(widthPixels/2);
        pw.setHeight(heightPixels/5);
        pw.setFocusable(true);
        pw.setContentView(popView);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mNotifi = layout.findViewById(R.id.ib_my_notification);
        mMore = layout.findViewById(R.id.ib_my_more);
        mLogin = layout.findViewById(R.id.ll_my_login);
        mCollect = layout.findViewById(R.id.ll_my_collect);
        mOrder = layout.findViewById(R.id.ll_my_order);
        mFeedBack = layout.findViewById(R.id.ll_my_feedback);
        mSet = layout.findViewById(R.id.ll_my_set);

        mNotifi.setOnClickListener(this);
        mMore.setOnClickListener(this);
        mLogin.setOnClickListener(this);
        mCollect.setOnClickListener(this);
        mOrder.setOnClickListener(this);
        mFeedBack.setOnClickListener(this);
        mSet.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_my_notification:
                break;
            case R.id.ib_my_more:
                if (pw.isShowing()) {
                    pw.dismiss();
                }else {
                    pw.showAsDropDown(mMore);
                }
                break;
            case R.id.ll_my_login:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_my_collect:

                break;
            case R.id.ll_my_feedback:
                Intent intent1 = new Intent(getActivity(), FeedBackActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll_my_set:
                Intent intent2 = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent2);
                break;
            case R.id.ll_my_order:
                break;
        }
    }
}
