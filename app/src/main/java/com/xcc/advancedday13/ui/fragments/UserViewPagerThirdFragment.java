package com.xcc.advancedday13.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.lhh.ptrrv.library.PullToRefreshRecyclerView;
import com.xcc.advancedday13.R;
import com.xcc.advancedday13.adapters.TravelsUserThirdAdapter;
import com.xcc.advancedday13.base.BaseFragment;
import com.xcc.advancedday13.constants.HttpConstant;
import com.xcc.advancedday13.model.UserGrouped;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


public class UserViewPagerThirdFragment extends BaseFragment implements Handler.Callback{

    private static final String TAG = UserViewPagerThirdFragment.class.getSimpleName();
    private TravelsUserThirdAdapter adapter;
    private static final int UP_DATE = 100;
    private static final int DOWN = 200;
    private static final int UP = 300;
    private int id;
    private int pageIndex=1;
    private Handler mHandler;
    private PullToRefreshRecyclerView mUserPtrrv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout=inflater.inflate(R.layout.fragment_travels_user,container,false);
        Bundle bundle = getArguments();
        id = bundle.getInt("id");
        initView();
        return layout;
    }

    private void initView() {
        mHandler=new Handler(this);
        mUserPtrrv = (PullToRefreshRecyclerView) layout.findViewById(R.id.travels_user_ptrrv);
        mUserPtrrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TravelsUserThirdAdapter(getActivity(), null);
        mUserPtrrv.setAdapter(adapter);
        setupView(Selected.DOWN);
    }



    enum Selected{
        UP,DOWN

    }
    private void setupView(final Selected state) {
        RequestParams params = new RequestParams(HttpConstant.TRAVEL_USER_FIRST_URL+id+"/user_activities.json?grouped="+pageIndex);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(final String result) {
                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        Gson gson = new Gson();
                        UserGrouped userGrouped = gson.fromJson(result, UserGrouped.class);
                        Message msg = Message.obtain();
                        msg.obj=userGrouped;
                        msg.what=UP_DATE;
                        if (state.equals(Selected.DOWN)) {
                            msg.arg1=DOWN;
                        }else if (state.equals(Selected.UP)) {
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
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case UP_DATE:
                UserGrouped userGrouped = (UserGrouped) msg.obj;
                if (msg.arg1==DOWN) {
                    adapter.updateRes(userGrouped.getData());
                }else if (msg.arg1==UP){
                    adapter.addRes(userGrouped.getData());
                }
                break;
        }
        return false;
    }

}
