package com.jjlf.rnpainter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.facebook.react.views.view.ReactViewGroup;
import com.jjlf.rnpainter.utils.MaskInterface;
import com.jjlf.rnpainter.utils.PaintableInterface;
import com.jjlf.rnpainter.utils.PainterKit;
import com.jjlf.rnpainter.views.GView;
import com.jjlf.rnpainter.views.GViewHardware;
import com.jjlf.rnpainter.views.MaskGView;


public class PainterViewHardware extends ReactViewGroup {



    private final PainterKit mPainter = new PainterKit();


    PainterViewHardware(Context context){
        super(context);
        setClipChildren(false);
        setLayerType(LAYER_TYPE_HARDWARE,null);
    }

    public void setViewBox(float[] viewBox){
        float density = getResources().getDisplayMetrics().density;
        mPainter.viewBox.set(viewBox[0] ,viewBox[1] ,(viewBox[0] + viewBox[2])  ,(viewBox[1] + viewBox[3]));
        mPainter.viewBoxDensity.set(viewBox[0] * density ,viewBox[1] * density ,(viewBox[0] + viewBox[2]) * density ,(viewBox[1] + viewBox[3]) * density);
        invalidateChild();
    }

    public void setAlign(String v){
        mPainter.align = v;
        invalidateChild();
    }
    public void setAspect(int v){
        mPainter.aspect = v;
        invalidateChild();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mPainter.bounds.set(0f,0f,(float)w,(float)h);
        super.onSizeChanged(w, h, oldw, oldh);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).layout(0, 0, getWidth(), getHeight());
        }
    }


    //new
    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        if(child instanceof MaskInterface){
            child.setVisibility(View.INVISIBLE);
        }
        if(child instanceof PaintableInterface){
            PaintableInterface c = (PaintableInterface) child;
            c.setPainterKit(mPainter);
        }
        if(child instanceof PainterView || child instanceof PainterViewHardware
                || child instanceof MaskGView){
            throw new IllegalArgumentException("Painter cannot have MaskG,Painter.");
        }
    }


    public void invalidateChild() {
        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            child.invalidate();
        }
    }
}
