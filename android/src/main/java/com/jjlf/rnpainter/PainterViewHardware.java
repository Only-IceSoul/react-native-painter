package com.jjlf.rnpainter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

import com.facebook.react.views.view.ReactViewGroup;
import com.jjlf.rnpainter.utils.MaskInterface;
import com.jjlf.rnpainter.utils.PaintableInterface;
import com.jjlf.rnpainter.utils.PainterKit;


public class PainterViewHardware extends ReactViewGroup {



    private final PainterKit mPainter = new PainterKit();


    PainterViewHardware(Context context){
        super(context);
        setClipChildren(false);
        setLayerType(LAYER_TYPE_HARDWARE,null);

    }

    public void setViewBox(float[] viewBox){
        mPainter.viewBox.set(viewBox[0] ,viewBox[1] ,(viewBox[0] + viewBox[2])  ,(viewBox[1] + viewBox[3]));
    }
    public void enableViewBox(){
        mPainter.isViewBoxEnabled = true;
    }
    public void disableViewBox(){
        mPainter.isViewBoxEnabled = false;
    }
    public void setAlign(String v){
        mPainter.align = v;
    }
    public void setAspect(int v){
        mPainter.aspect = v;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mPainter.bounds.set(0f,0f,(float)w,(float)h);
        if(mPainter.maskBitmap == null){
            mPainter.maskBitmap  = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
            mPainter.maskCanvas.setBitmap(mPainter.maskBitmap );

        }else{
            if(mPainter.maskBitmap .getWidth() != w || mPainter.maskBitmap .getHeight() != h){
                mPainter.maskBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
                mPainter.maskCanvas.setBitmap(mPainter.maskBitmap );

            }
        }
        if(mPainter.bitmap == null){
            mPainter.bitmap  = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
            mPainter.canvas.setBitmap(mPainter.bitmap );

        }else{
            if(mPainter.bitmap .getWidth() != w || mPainter.bitmap .getHeight() != h){
                mPainter.bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
                mPainter.canvas.setBitmap(mPainter.bitmap );

            }
        }
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            if(child instanceof PaintableInterface){
                PaintableInterface c = (PaintableInterface) child;
                c.setPainterKit(mPainter);
            }
        }
        super.dispatchDraw(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).layout(0, 0, getWidth(), getHeight());
        }
    }


    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        if(child instanceof MaskInterface){
            child.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void invalidate() {
        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            child.invalidate();
        }
    }
}
