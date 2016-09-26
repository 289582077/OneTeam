package com.xcc.advancedday13.ui;

import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xcc.advancedday13.R;
import com.xcc.advancedday13.adapters.PicShowVpAdapter;
import com.xcc.advancedday13.base.BaseActivity;
import com.xcc.advancedday13.widget.CustomImageView;

import java.util.ArrayList;
import java.util.List;

public class PicActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private static final String TAG = PicActivity.class.getSimpleName();
    private String[] pics;
    private ViewPager mPIc;
    private PicShowVpAdapter adapter;
    private TextView mNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        pics = getIntent().getStringArrayExtra("pic");
        init();
    }

    private void init() {
        mPIc = (ViewPager) findViewById(R.id.vp_picShow);
        mNum = (TextView) findViewById(R.id.tv_numTotal);

        adapter = new PicShowVpAdapter(getPicData());
        mPIc.setAdapter(adapter);
        mPIc.addOnPageChangeListener(this);

    }

    private List<ImageView> getPicData() {
        List<ImageView> data=new ArrayList<>();
        for (int i = 0; i < pics.length; i++) {
            CustomImageView iv =new CustomImageView(this);
            iv.setLayoutParams(new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT));
            iv.setScaleType(ImageView.ScaleType.MATRIX);
            Picasso.with(this).load(pics[i]).into(iv);
            iv.setOnClickListener(this);


            data.add(iv);
        }
        return data;
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mNum.setText(position+1+"/"+mPIc.getAdapter().getCount());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
