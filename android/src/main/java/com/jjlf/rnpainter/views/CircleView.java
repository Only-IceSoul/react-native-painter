package com.jjlf.rnpainter.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;

import com.jjlf.rnpainter.utils.ModUtil;
import com.jjlf.rnpainter.utils.PainterKit;

public class CircleView extends PaintableView {


    private float cx = 0f;
    private float cy = 0f;
    private float r = 0f;



    public void setCx(float v) {
        if (cx != v) {
            cx = v;
            invalidate();
        }

    }

    public void setR(float v) {
        if (r != v) {
            r = v;
            invalidate();
        }
    }

    public void setCy(float v) {
        if (cy != v) {
            cy = v;
            invalidate();
        }
    }

    public CircleView(Context context) {
        super(context);
        mIgnoreVbTransform = true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    protected void setupPath(PainterKit p) {
        p.path.reset();

        if (p.isViewBoxEnabled) {

            p.path.addCircle(ModUtil.viewBoxToWidth(cx, p.viewBox, p.bounds.width()),
                    ModUtil.viewBoxToHeight(cy, p.viewBox, p.bounds.height()),
                    ModUtil.viewBoxToMax(r,p.viewBox,p.bounds.width(),p.bounds.height())
                    , Path.Direction.CW);

        } else {
            p.path.addCircle(toDip(cx), toDip(cy),toDip(r), Path.Direction.CW);
        }

    }

}