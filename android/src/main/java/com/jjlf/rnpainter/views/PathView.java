package com.jjlf.rnpainter.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;

import com.jjlf.rnpainter.utils.PainterKit;
import com.jjlf.rnpainter.utils.SVGPathParser;

import java.util.Objects;


public class PathView extends PaintableView {


    public PathView(Context context){
        super(context);
    }

    protected String mPath = "";
    public void setPath(String v){
        if(!Objects.equals(mPath, v)){
            mPath = v;
            invalidate();
        }

    }

    @Override
    public void draw(Canvas canvas) {
        if(mPath != null && !mPath.isEmpty()){
            super.draw(canvas);
        }

    }

    @Override
    protected void setupPath(PainterKit p) {
        p.path.reset();
        try{
            SVGPathParser.mScale = getResources().getDisplayMetrics().density;
            final Path np = SVGPathParser.parse(mPath);
            p.path.set(np);
        }catch(Error ignored) {

        }
    }


}
