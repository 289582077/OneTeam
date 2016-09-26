package com.xcc.advancedday13.widget;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

/**
 * Created by DaXianSheng on 2016/9/23.
 */
public class CustomImageView extends ImageView implements ViewTreeObserver.OnGlobalLayoutListener,ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener {
    private static final float SCALE_MAX = 4.0f;
    private static final String TAG = CustomImageView.class.getSimpleName();
    private ScaleGestureDetector mScaleGestureDetector;
    //Matrix values
    private final float[] matrixValues=new float[9];
    private final Matrix mScaleMatrix=new Matrix();
    private float initScale=1.0f;
    private boolean once=true;
    private boolean isCheckLeftAndRight;
    private boolean isCheckTopAndBottom;
    private int mTouchSlop;

    public CustomImageView(Context context) {
        this(context,null);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.setScaleType(ScaleType.MATRIX);
        mScaleGestureDetector = new ScaleGestureDetector(context,this);
        this.setOnTouchListener(this);
    }

// ScaleGestureDetector callback,used to detector Gesture,multi-point control
    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        float scale = getScale();
        float scaleFactor = detector.getScaleFactor();
        if (getDrawable()==null) return true;
        if ((scale<SCALE_MAX&&scaleFactor>1.0f)||(scale>initScale&&scaleFactor<1.0f)) {
            //judge max and min values
            if (scaleFactor*scale<initScale) {
                scaleFactor=initScale/scale;
            }
            if (scaleFactor*scale>SCALE_MAX) {
                scaleFactor=SCALE_MAX/scale;
            }
            mScaleMatrix.postScale(scaleFactor,scaleFactor,detector.getFocusX(),detector.getFocusY());
            checkBorderCenter();
            setImageMatrix(mScaleMatrix);
        }
        return true;
    }



    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }
// 点击监听------------------------------------------------------------
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //handle the MotionEvent by ScaleGestureDetector
        mScaleGestureDetector.onTouchEvent(event);
        float x=0,y=0;
        //get 触摸点数
        final int pointerCount=event.getPointerCount();
        int lastPointerCount=pointerCount;
        //get 触摸点的均值
        for (int i = 0; i < pointerCount; i++) {
            x+=event.getX(i);
            y+=event.getY(i);
        }
        x=x/pointerCount;
        y=y/pointerCount;
        float mLastX=0;
        float mLastY=0;
        // reset mLastX，mLastY ；when the touch point changed
        boolean isCanDrag=true;

        if (pointerCount!=lastPointerCount) {
            isCanDrag=false;
            mLastX=x;
            mLastY=y;
            lastPointerCount=pointerCount;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float dx = x - mLastX;
                float dy = y - mLastY;
                if (!isCanDrag) {
                    isCanDrag=isCanDrag(dx,dy);
                }
                if (isCanDrag) {
                    RectF rectF = getMatrixRectF();
                    if (getDrawable()!=null) {
                        isCheckLeftAndRight = isCheckTopAndBottom =true;
                        // if 宽度小于屏幕宽度禁止左右移动
                        if (rectF.width()<getWidth()) {
                            dx=0;
                            isCheckLeftAndRight =false;
                        }
                        //高度小于屏幕高度，禁止上下移动
                        if (rectF.height() < getHeight()) {
                            dy=0;
                            isCheckTopAndBottom =false;
                        }
                        mScaleMatrix.postTranslate(dx,dy);
                        checkMatrixBounds();
                        setImageMatrix(mScaleMatrix);
                    }
                }
                mLastX=x;
                mLastY=y;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                lastPointerCount=0;
                break;
        }
        return true;
    }
//get scale size（float）
    private final float getScale() {
        mScaleMatrix.getValues(matrixValues);
        return matrixValues[Matrix.MSCALE_X];
    }
//----------------------------------------------------------------------

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        if (once) {
            Drawable d = getDrawable();
            if (d==null) return;
            //screen width and height
            int width = getWidth();
            int height = getHeight();
            //get pic width and height
            int dw = d.getIntrinsicWidth();
            int dh = d.getIntrinsicHeight();
            float scale=1.0f;
            if (dw>width&&dh<=height) {
                scale=width*1.0f/dw;
            }
            if (dh>height&&dw<=width) {
                scale=height*1.0f/dh;
            }
            if (dw>width&&dh>height) {
                scale=Math.min(dw*1.0f/width,dh*1.0f/height);
            }
            initScale=scale;

            //move pic to screen center
            mScaleMatrix.postTranslate((width-dw)/2,(height-dh)/2);
            mScaleMatrix.postScale(scale,scale,getWidth()/2,getHeight()/2);
            setImageMatrix(mScaleMatrix);
            once=false;
        }
    }

    private void checkBorderCenter() {
        RectF rect = getMatrixRectF();
        float detalX=0;
        float detalY=0;

        int width = getWidth();
        int height = getHeight();
        //图片超出屏幕
        if (rect.width()>=width) {
            if (rect.left>0) {
                detalX=-rect.left;
            }
            if (rect.right < width) {
                detalX=width-rect.right;
            }
        }
        if (rect.height()>=height) {
            if (rect.top>0) {
                detalY=-rect.top;
            }
            if (rect.bottom < height) {

                detalY=height-rect.bottom;
            }
        }
        //图片小于屏幕
        if (rect.width()<width) {
            detalX=width*0.5f-rect.right+0.5f*rect.width();
        }
        if (rect.height()<height) {
            detalY=height*0.5f-rect.bottom+0.5f*rect.height();
        }
        mScaleMatrix.postTranslate(detalX,detalY);
    }
    private RectF getMatrixRectF(){
        Matrix matrix=mScaleMatrix;
        RectF rectF = new RectF();
        Drawable d = getDrawable();
        if (d!=null) {
            rectF.set(0,0,d.getIntrinsicWidth(),d.getIntrinsicHeight());
            matrix.mapRect(rectF);
        }
        return rectF;
    }

    private void checkMatrixBounds(){
        RectF rect = getMatrixRectF();
        float deltaX=0,deltaY=0;
        final float viewWidth=getWidth();
        final float viewHeight = getHeight();
        //judge 操作之后图片是否超出屏幕的边界
        if (rect.top>0&&isCheckTopAndBottom) {
            deltaY=-rect.top;
        }
        if (rect.bottom < viewHeight && isCheckTopAndBottom) {
            deltaY=viewHeight-rect.bottom;
        }
        if (rect.left > 0 && isCheckLeftAndRight) {
            deltaX=-rect.left;
        }
        if (rect.right < viewWidth && isCheckLeftAndRight) {
            deltaX=viewWidth-rect.right;
        }
        mScaleMatrix.postTranslate(deltaX,deltaY);
    }
    //judge 是否是推动行为
    private boolean isCanDrag(float dx,float dy){
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        return Math.sqrt((dx*dx)+(dy*dy))>=mTouchSlop;
    }
}
