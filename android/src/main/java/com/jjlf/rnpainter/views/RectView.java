package com.jjlf.rnpainter.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import com.jjlf.rnpainter.utils.ModUtil;
import com.jjlf.rnpainter.utils.PainterKit;

public class RectView extends PaintableView {


    private float x = 0f;
    private float y = 0f;
    private float w = 0f;
    private float h = 0f;
    private float rtl = 0f;
    private float rtr = 0f;
    private float rbl = 0f;
    private float rbr = 0f;
    private final RectF mRect = new RectF();
    private final float[] mRadius = new float[8];

    public void setY(float v) {
        if(y != v){
            y = v;
            invalidate();
        }
    }
    public void setX(float v) {
        if(x != v){
            x = v;
            invalidate();
        }
    }
    public void setW(float v) {
        if(w != v){
            w = v;
            invalidate();
        }
    }
    public void setH(float v) {
        if(h != v){
            h = v;
            invalidate();
        }
    }
    public void setRtl(float v) {
        if(rtl != v){
            rtl = v;
            invalidate();
        }
    }
    public void setRtr(float v) {
        if(rtr != v){
            rtr = v;
            invalidate();
        }
    }
    public void setRbl(float v) {
        if(rbl != v){
            rbl = v;
            invalidate();
        }
    }
    public void setRbr(float v) {
        if(rbr != v){
            rbr = v;
            invalidate();
        }
    }
    public RectView(Context context){
        super(context);
    }

    @Override
    public void draw(Canvas canvas) {
        if(w > 0f && h > 0f ) {
            super.draw(canvas);
        }
    }

    @Override
    protected void setupPath(PainterKit p) {
        p.path.reset();

        float  l = toDip(x);
        float  t = toDip(y);
        float  r = toDip(x + w);
        float  b = toDip(y + h);
        float  rrtl = toDip(rtl);
        float  rrtr = toDip(rtr);
        float  rrbl = toDip(rbl);
        float  rrbr = toDip(rtr);

        mRect.set(l,t,r,b);
        mRadius[0] = rrtl;
        mRadius[1] = rrtl;
        mRadius[2] = rrtr;
        mRadius[3] = rrtr;
        mRadius[4] = rrbr;
        mRadius[5] = rrbr;
        mRadius[6] = rrbl;
        mRadius[7] = rrbl;
        p.path.addRoundRect(mRect,mRadius, Path.Direction.CW);

    }

}