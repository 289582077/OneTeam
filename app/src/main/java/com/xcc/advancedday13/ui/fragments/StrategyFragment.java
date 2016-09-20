package com.xcc.advancedday13.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.lhh.ptrrv.library.PullToRefreshRecyclerView;
import com.xcc.advancedday13.R;
import com.xcc.advancedday13.adapters.StrategyAdapter;
import com.xcc.advancedday13.base.BaseFragment;
import com.xcc.advancedday13.constants.HttpConstant;
import com.xcc.advancedday13.model.City;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by bukeyishidecheng on 16/9/20.
 */
public class StrategyFragment extends BaseFragment {
    private static final String TAG = StrategyFragment.class.getSimpleName();
    private PullToRefreshRecyclerView mPtrrv;
    private StrategyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout=inflater.inflate(R.layout.fragment_strategy,container,false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setupView();
    }

    private void initView() {
        mPtrrv = (PullToRefreshRecyclerView) layout.findViewById(R.id.fragment_strategy_ptrrv);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mPtrrv.setLayoutManager(layoutManager);
        adapter = new StrategyAdapter(getActivity(),null);
        mPtrrv.setAdapter(adapter);

    }

    private void setupView() {
        RequestParams params=new RequestParams(HttpConstant.STRATEGY_URL);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: ");
                Gson gson = new Gson();
                City city = gson.fromJson(result, City.class);
                adapter.updateRes(city.getData());

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: "+ex.getMessage());

            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled: ");

            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished: ");

            }
        });

    }
}
