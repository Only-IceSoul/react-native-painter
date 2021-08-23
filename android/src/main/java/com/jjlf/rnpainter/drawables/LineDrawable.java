package com.jjlf.rnpainter.drawables;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.util.Log;

import com.jjlf.rnpainter.utils.ModUtil;
import com.jjlf.rnpainter.utils.PainterKit;
import com.jjlf.rnpainter.PainterView;

public class LineDrawable extends Paintable {

    private float x1 = 0f;
    private float x2 = 0f;
    private float y1 = 0f;
    private float y2 = 0f;

    public void setX1(float v) {
        x1 = v;
    }
    public void setY1(float v) {
        y1 = v;
    }
    public void setX2(float v) {
        x2 = v;
    }
    public void setY2(float v) {
        y2 = v;
    }

    public LineDrawable(){
        super();
        mIgnoreVbTransform = true;
        mIgnoreFill = true;
        mIgnoreShadow = true;
    }


    @Override
    protected void setupPath(PainterKit p) {
        p.path.reset();
        if(p.isViewBoxEnabled){

            p.path.moveTo( ModUtil.viewBoxToWidth(x1,p.viewBoxRectF,p.bounds.width()),
                    ModUtil.viewBoxToHeight(y1,p.viewBoxRectF,p.bounds.height())
            );
            p.path.lineTo(ModUtil.viewBoxToWidth(x2,p.viewBoxRectF,p.bounds.width()),
                    ModUtil.viewBoxToHeight(y2,p.viewBoxRectF,p.bounds.height())
            );
        }else{
            p.path.moveTo(toDip(x1),toDip(y1));
            p.path.lineTo(toDip(x2),toDip(y2));
        }

    }


}
