package com.xcc.advancedday13.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by bukeyishidecheng on 16/9/20.
 */
public class CustomRecyclerView extends RecyclerView {

    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int width=MeasureSpec.getSize(widthSpec);
        int height=MeasureSpec.getSize(heightSpec);
        super.onMeasure(width, height);
    }
}
