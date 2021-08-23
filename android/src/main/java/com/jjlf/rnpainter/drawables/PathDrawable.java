package com.jjlf.rnpainter.drawables;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.Log;


import com.jjlf.rnpainter.PainterView;
import com.jjlf.rnpainter.utils.PainterKit;
import com.jjlf.rnpainter.utils.SVGPathParser;

public class PathDrawable extends Paintable{

    protected String mPath = "";


    public PathDrawable(){
        super();

    }

    public void setPath(String v){
        mPath = v;
    }

    @Override
    protected void setupPath(PainterKit p) {
        p.path.reset();
        try{
            SVGPathParser.mScale = mDensity;
            final Path np = SVGPathParser.parse(mPath);
            p.path.set(np);
            p.path.setFillType(mProps.getFillRule());

        }catch(Error ignored) {

        }
    }
}
