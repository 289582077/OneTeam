package com.xcc.advancedday13.ui.fragments;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.os.EnvironmentCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.lhh.ptrrv.library.PullToRefreshRecyclerView;
import com.squareup.picasso.Picasso;
import com.xcc.advancedday13.R;
import com.xcc.advancedday13.adapters.NearByGridViewAdapter;
import com.xcc.advancedday13.adapters.StrategyAdapter;
import com.xcc.advancedday13.adapters.StrategyHeaderAdapter;
import com.xcc.advancedday13.base.BaseFragment;
import com.xcc.advancedday13.constants.HttpConstant;
import com.xcc.advancedday13.model.City;
import com.xcc.advancedday13.model.HeaderMsg;
import com.xcc.advancedday13.model.NearByCity;
import com.xcc.advancedday13.ui.CityDetailActivity;
import com.xcc.advancedday13.ui.SearchResultActivity;
import com.xcc.advancedday13.utils.NetUtils;
import com.xcc.advancedday13.widget.CustomGridView;
import com.xcc.advancedday13.widget.CustomRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


/**
 * Created by bukeyishidecheng on 16/9/20.
 */
public class StrategyFragment extends BaseFragment implements View.OnClickListener, AMapLocationListener ,StrategyAdapter.OnItemClickListener{
    private static final String TAG = StrategyFragment.class.getSimpleName();
    private PullToRefreshRecyclerView mPtrrv;
    private StrategyAdapter adapter;
    private ViewPager mHeaderVp;
    private TextView mNearBy;
    private TextView mTextMore;
    private CustomGridView mNearByCity;

    public AMapLocationClient mLocationClient=null;
    private AMapLocationClientOption mLocationClientOption;
    private NearByGridViewAdapter nearByAdapter;
    private StrategyHeaderAdapter mHeaderAdapter;


    DbManager.DaoConfig daoConfig=new DbManager.DaoConfig()
            .setDbVersion(1)
            .setDbName("travel")
            .setAllowTransaction(true)
            //.setDbDir(new File("/sdcard"));
            .setDbDir(new File(Environment.getExternalStorageDirectory()+File.separator+"team"));



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout=inflater.inflate(R.layout.fragment_strategy,container,false);
        Log.e(TAG, "onCreateView: "+Environment.getExternalStorageDirectory()+File.separator+"team");
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setupHeaderView();
        initLocation();
        setupView();
    }

    private void initView() {
        mPtrrv = (PullToRefreshRecyclerView) layout.findViewById(R.id.fragment_strategy_ptrrv);
        //给RecyclerView设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mPtrrv.setLayoutManager(layoutManager);
        //给RecyclerView添加头布局
        View mHeaderView = LayoutInflater.from(getActivity()).inflate(R.layout.strategy_header, null);
        //实例化头布局的轮播页
        mHeaderVp = (ViewPager) mHeaderView.findViewById(R.id.strategy_header_vp);
//        mHeaderAdapter = new StrategyHeaderAdapter(null);
//        mHeaderVp.setAdapter(mHeaderAdapter);

        //
        //实例化头布局附近的地方布局
        mNearBy = ((TextView) mHeaderView.findViewById(R.id.strategy_item_title));
        mNearBy.setText("附近的目的地");
        mNearByCity = (CustomGridView) mHeaderView.findViewById(R.id.strategy_item_nearby_city);

        nearByAdapter = new NearByGridViewAdapter(getActivity(),null);
        mNearByCity.setAdapter(nearByAdapter);


        mTextMore = ((TextView) mHeaderView.findViewById(R.id.strategy_item_more));
        mTextMore.setOnClickListener(this);


        mPtrrv.addHeaderView(mHeaderView);
        adapter = new StrategyAdapter(getActivity(),null);
       adapter.setOnItemClick(this);
        mPtrrv.setAdapter(adapter);

    }

    private void setupHeaderView() {
        OkHttpUtils.get().url(HttpConstant.STRATEGY_HEAD_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }
            @Override
            public void onResponse(String response, int id) {
                //Log.e(TAG, "onResponse: "+response);
                Gson gson = new Gson();
                HeaderMsg headerMsg = gson.fromJson(response, HeaderMsg.class);
                int size = headerMsg.getData().size();
               // Log.e(TAG, "onResponse: "+size);
                List<View> data=new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    Picasso.with(getActivity()).load(headerMsg.getData().get(i).getPhoto().getPhoto_url()).into(imageView);
                    data.add(imageView);
                }
                mHeaderAdapter = new StrategyHeaderAdapter(data);
                mHeaderVp.setAdapter(mHeaderAdapter);
                //mHeaderAdapter.updateRes(data);

            }
        });
    }

    private void initLocation() {
        //实例化一个定位服务
        mLocationClient=new AMapLocationClient(getActivity());
        //设置监听
        mLocationClient.setLocationListener(this);
        //实例化一个设置对象
        mLocationClientOption = new AMapLocationClientOption();
        //设置定位精度模式
        mLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位次数方式
        mLocationClientOption.setOnceLocation(true);
        //获取三秒内最精确的一次,设置setOnceLocationLatest为true,setOnceLocation就默认为true
        mLocationClientOption.setOnceLocationLatest(true);
        mLocationClient.setLocationOption(mLocationClientOption);
        //启动定位
        mLocationClient.startLocation();


    }

    private void setupView() {

        if (NetUtils.isConnected(getActivity())) {

            RequestParams params=new RequestParams(HttpConstant.STRATEGY_URL);

            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Log.e(TAG, "onSuccess: ");
                    Gson gson = new Gson();
                    City city = gson.fromJson(result, City.class);
                    List<City.DataBean> data = city.getData();
                    adapter.updateRes(data);

                    //往数据库存数据
                    DbManager db = x.getDb(daoConfig);
                    for (City.DataBean cd:data){
                        try {
                            Log.e(TAG, "onSuccess: 往数据库存储数据"+cd);
                            db.saveOrUpdate(cd);
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.e(TAG, "onSuccess: "+data.size());
                    for (int i = 0; i < data.size(); i++) {
                        for (City.DataBean.DestinationsBean cddb : data.get(i).getDestinations()) {
                            try {
                                Log.e(TAG, "onSuccess: ParentId: "+data.get(i).getParentId());
                                cddb.setParentId(data.get(i).getParentId());
                                db.saveOrUpdate(cddb);
                            } catch (DbException e) {
                                e.printStackTrace();
                            }
                        }

                    }
//                    for (City.DataBean.DestinationsBean cddb :data.get ) {
//
//                    }

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

        }else {
            //Toast.makeText(getActivity(), "没有网络", Toast.LENGTH_SHORT).show();


            //从数据库查找数据
            DbManager db = x.getDb(daoConfig);

            //City.DataBean dataBean = new City.DataBean();
//        dataBean.setParentId(11);
//        City.DataBean.DestinationsBean destinationsBean = new City.DataBean.DestinationsBean();
//        destinationsBean.setParentId(dataBean.getParentId());

            try {
                Log.e(TAG, "setupView: 从数据库获取数据");
                List<City.DataBean> dataBean = db.selector(City.DataBean.class).offset(0).limit(5).findAll();
                if (dataBean != null && dataBean.size() != 0) {
                    for (int i = 0; i < dataBean.size(); i++) {
                        //Log.e(TAG, "setupView: 获取字表");
                        List<City.DataBean.DestinationsBean> destinationsBeen = dataBean.get(i).getCityDataBeanDestinationsBean(db);
                        //Log.e(TAG, "setupView: "+ destinationsBeen.get(i).getPhoto_url());
                        dataBean.get(i).setDestinations(destinationsBeen);
                    }
                    adapter.updateRes(dataBean);
                } else {
                    getDataForNet();
                }
                //List<City.DataBean.DestinationsBean> destinationsBeen = db.selector(City.DataBean.DestinationsBean.class).findAll();

            } catch (DbException e) {
                e.printStackTrace();
            }


        }
    }

    private void getDataForNet() {
        Log.e(TAG, "getDataForNet: 联网下载数据");
        //判断是否有网
        if (NetUtils.isConnected(getActivity())) {

            RequestParams params=new RequestParams(HttpConstant.STRATEGY_URL);

            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Log.e(TAG, "onSuccess: ");
                    Gson gson = new Gson();
                    City city = gson.fromJson(result, City.class);
                    List<City.DataBean> data = city.getData();
                    adapter.updateRes(data);

                    //往数据库存数据
                    DbManager db = x.getDb(daoConfig);
                    for (City.DataBean cd:data){
                        try {
                            Log.e(TAG, "onSuccess: 往数据库存储数据"+cd);
                            db.saveOrUpdate(cd);
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                    }
                    for (int i = 0; i < data.size(); i++) {
                        for (City.DataBean.DestinationsBean cddb : data.get(i).getDestinations()) {
                            try {
                                Log.e(TAG, "onSuccess: "+data.get(i).getParentId());
                                cddb.setParentId(data.get(i).getParentId());
                                db.saveOrUpdate(cddb);
                            } catch (DbException e) {
                                e.printStackTrace();
                            }
                        }

                    }
//                    for (City.DataBean.DestinationsBean cddb :data.get ) {
//
//                    }

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

        }else {
            Toast.makeText(getActivity(), "没有网络", Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.strategy_item_more:

                break;
        }
    }

    public List<View> getData() {
        List<View> data=new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(R.mipmap.ic_launcher);
            data.add(imageView);
        }

        return data;
    }
//-----------------定位服务的监听回调----------------
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation!=null) {
            if (aMapLocation.getErrorCode()==AMapLocation.LOCATION_SUCCESS) {

                double longitude = aMapLocation.getLongitude();
                double latitude = aMapLocation.getLatitude();
                //Log.e(TAG, "onLocationChanged: 纬度:"+ latitude +"经度:"+ longitude);
                OkHttpUtils.get()
                        .url(HttpConstant.STRATEGY_NEARBY_URL)
                        .addParams("lat", String.valueOf(latitude))
                        .addParams("lng",String.valueOf(longitude))
                        //.addParams("recommend",null)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                //Log.e(TAG, "onResponse: "+response);
                                Gson gson = new Gson();
                                NearByCity nearByCity = gson.fromJson(response, NearByCity.class);
                                //Log.e(TAG, "onResponse: "+nearByCity.getData().size());
                                nearByAdapter.updateRes(nearByCity.getData());

                            }
                        });


            }
            else {
                Log.e(TAG, "onLocationChanged: "+aMapLocation.getErrorCode()+"<--->"+aMapLocation.getErrorInfo());
            }
        }

    }


    @Override
    public void onItemClick(City.DataBean.DestinationsBean data) {

        Intent intent = new Intent(getActivity(), SearchResultActivity.class);

        int id = data.getId();
        intent.putExtra("id",id);
        startActivity(intent);

    }
}
