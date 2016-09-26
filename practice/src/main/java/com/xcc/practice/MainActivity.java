package com.xcc.practice;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.my_more)
    ImageView myMore;
    @BindView(R.id.ll_community)
    LinearLayout llCommunity;
    @BindView(R.id.ll_setting)
    LinearLayout llSetting;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        popupWindow = new PopupWindow(this);
        View view = LayoutInflater.from(this).inflate(R.layout.popupwindowitem, null);
        popupWindow.setContentView(view);
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        popupWindow.setWidth(widthPixels/2);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
    }

    @OnClick({R.id.my_more, R.id.ll_community, R.id.ll_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_more:

                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }else {
                    popupWindow.showAsDropDown(myMore);
                }
                break;
            case R.id.ll_community:



                break;
//            case R.id.ll_setting:
//                Intent intent = new Intent(this, SettingActivity.class);
//
//                startActivity(intent);




//                break;
        }
    }
}
