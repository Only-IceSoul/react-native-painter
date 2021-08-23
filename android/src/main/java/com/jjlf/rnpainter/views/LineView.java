package com.jjlf.rnpainter.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

import com.jjlf.rnpainter.drawables.LineDrawable;
import com.jjlf.rnpainter.drawables.Paintable;


public class LineView extends PaintableView {

    LineDrawable mDrawable = new LineDrawable();

    public LineView(Context context){
        super(context);
        mDrawable.setDensity(context.getResources().getDisplayMetrics().density);
        setBackground(mDrawable);
    }

    @Override
    public LineDrawable getDrawable() {
        return mDrawable;
    }


}
