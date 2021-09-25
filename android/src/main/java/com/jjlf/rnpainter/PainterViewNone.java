package com.jjlf.rnpainter;


import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.view.View;

import com.facebook.react.views.view.ReactViewGroup;
import com.jjlf.rnpainter.utils.PaintableInterface;
import com.jjlf.rnpainter.utils.PainterKit;
import com.jjlf.rnpainter.views.MaskGView;
import com.jjlf.rnpainter.views.MaskView;


public class PainterViewNone extends ReactViewGroup {


    private final PainterKit mPainter = new PainterKit();

    PainterViewNone(Context context){
        super(context);
        setClipChildren(false);
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
        if(child instanceof MaskView){
            child.setVisibility(View.INVISIBLE);
        }
        if(child instanceof PainterViewNone || child instanceof PainterViewHardware
                || child instanceof MaskGView){
            throw new IllegalArgumentException("Painter cannot have child MaskG,Painter.");
        }
    }


    public void invalidateChild() {
        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            child.invalidate();
        }
    }
}
