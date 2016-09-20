package com.xcc.advancedday13.ui.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.lhh.ptrrv.library.PullToRefreshRecyclerView;
import com.xcc.advancedday13.R;
import com.xcc.advancedday13.adapters.TravelsAdapter;
import com.xcc.advancedday13.base.BaseFragment;
import com.xcc.advancedday13.constants.HttpConstant;
import com.xcc.advancedday13.model.TravelRoot;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class TravelsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,Handler.Callback {

    private static final int UP_DATE = 100;
    private static final int DOWN = 200;
    private static final int UP = 300;
    private static final String TAG = TravelsFragment.class.getSimpleName();
    private PullToRefreshRecyclerView mPtrrv;
    private TravelsAdapter adapter;
    private int pageIndex=1;
    private Handler mHandler;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout=inflater.inflate(R.layout.fragment_travels,container,false);
        initView();
        return layout;
    }

    private void initView() {
        mHandler=new Handler(this);
        mPtrrv = ((PullToRefreshRecyclerView) layout.findViewById(R.id.travels_ptrrv));
        mPtrrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPtrrv.setRefreshing(true);
        mPtrrv.setOnRefreshListener(this);
        adapter = new TravelsAdapter(getActivity(), null);
        mPtrrv.setAdapter(adapter);
        setupView(Selecte.DOWN);
    }
    enum Selecte{
        UP,DOWN
    }
    private void setupView(final Selecte state) {
        RequestParams params = new RequestParams(HttpConstant.TRAVELS_URL+pageIndex);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(final String result) {
                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        Gson gson = new Gson();
                        TravelRoot travelRoot = gson.fromJson(result, TravelRoot.class);
                        Message msg = Message.obtain();
                        msg.obj=travelRoot;
                        msg.what=UP_DATE;
                        if (state.equals(Selecte.DOWN)) {
                            msg.arg1=DOWN;
                        }else if (state.equals(Selecte.UP)) {
                            msg.arg1=UP;
                        }
                        mHandler.sendMessage(msg);
                    }
                };
                thread.start();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished: ");
            }
        });
    }

    @Override
    public void onRefresh() {
        pageIndex=1;
        setupView(Selecte.DOWN);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case UP_DATE:
                TravelRoot travelRoot = (TravelRoot) msg.obj;
                if (msg.arg1==DOWN) {
                    adapter.updateRes(travelRoot.getData());
                }else if (msg.arg1==UP){
                    adapter.addRes(travelRoot.getData());
                }
                break;
        }
        return false;
    }
}
