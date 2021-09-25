package com.jjlf.rnpainter.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;

import com.jjlf.rnpainter.utils.ModUtil;
import com.jjlf.rnpainter.utils.PainterKit;

public class EllipseView extends PaintableView {


    private float cx = 0f;
    private float cy = 0f;
    private float rx = 0f;
    private float ry = 0f;

    private final RectF mRect = new RectF();

    public void setCx(float v) {
        if (cx != v) {
            cx = v;
            invalidate();
        }
    }
    public void setCy(float v) {
        if (cy != v) {
            cy = v;
            invalidate();
        }
    }
    public void setRx(float v) {
        if (rx != v) {
            rx = v;
            invalidate();
        }
    }
    public void setRy(float v) {
        if (ry != v) {
            ry = v;
            invalidate();
        }
    }



    public EllipseView(Context context){
        super(context);
    }

    @Override
    public void draw(Canvas canvas) {
        if( rx != 0f && ry != 0f) {
            super.draw(canvas);
        }
    }

    @Override
    protected void setupPath(PainterKit p) {
        p.path.reset();
        float cxx = toDip(cx);
        float cyy = toDip(cy);
        float rxx = toDip(rx);
        float ryy = toDip(ry);

        mRect.set((cxx - rxx), (cyy - ryy), (cxx + rxx), (cyy + ryy));
        mPainter.path.addOval(mRect, Path.Direction.CW);
    }

}
