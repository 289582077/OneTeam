package com.xcc.advancedday13.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lhh.ptrrv.library.PullToRefreshRecyclerView;
import com.xcc.advancedday13.R;
import com.xcc.advancedday13.base.BaseFragment;

public class TravelsFragment extends BaseFragment{

    private PullToRefreshRecyclerView mPtrrv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout=inflater.inflate(R.layout.fragment_travels,container,false);
        initView();
        return layout;
    }

    private void initView() {
        mPtrrv = ((PullToRefreshRecyclerView) layout.findViewById(R.id.travels_ptrrv));
        mPtrrv.setLayoutManager(new LinearLayoutManager(getActivity()));

    }
}
