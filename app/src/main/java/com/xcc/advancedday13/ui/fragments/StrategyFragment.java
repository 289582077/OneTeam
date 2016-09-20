package com.xcc.advancedday13.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xcc.advancedday13.R;
import com.xcc.advancedday13.base.BaseFragment;

/**
 * Created by bukeyishidecheng on 16/9/20.
 */
public class StrategyFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout=inflater.inflate(R.layout.fragment_strategy,container,false);
        return layout;
    }
}
