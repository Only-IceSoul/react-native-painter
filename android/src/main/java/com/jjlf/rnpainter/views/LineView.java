package com.jjlf.rnpainter.views;

import android.content.Context;
import android.graphics.Canvas;

import com.jjlf.rnpainter.utils.ModUtil;
import com.jjlf.rnpainter.utils.PainterKit;


public class LineView extends PaintableView {


    private float x1 = 0f;
    private float x2 = 0f;
    private float y1 = 0f;
    private float y2 = 0f;



    public void setX1(float v) {
        if(x1 != v){
            x1 = v;
            invalidate();
        }

    }
    public void setY1(float v) {
        if(y1 != v){
            y1 = v;
            invalidate();
        }
    }
    public void setX2(float v) {
        if(x2 != v){
            x2 = v;
            invalidate();
        }
    }
    public void setY2(float v) {
        if(y2 != v){
            y2 = v;
            invalidate();
        }
    }



    public LineView(Context context){
        super(context);
        mIgnoreFill = true;
    }

    @Override
    public void draw(Canvas canvas) {
        if(x1 != 0f || x2 != 0f || y1 != 0f || y2 != 0f) {
            super.draw(canvas);
        }
    }

    @Override
    protected void setupPath(PainterKit p) {
        p.path.reset();
        p.path.moveTo(toDip(x1),toDip(y1));
        p.path.lineTo(toDip(x2),toDip(y2));
    }

}
