package com.jjlf.rnpainter.views;

import android.content.Context;
import com.jjlf.rnpainter.drawables.PathDrawable;


public class PathView extends PaintableView {


    PathDrawable mDrawable = new PathDrawable();

    public PathView(Context context){
        super(context);
        mDrawable.setDensity(context.getResources().getDisplayMetrics().density);
        setBackground(mDrawable);

    }
    @Override
    public PathDrawable getDrawable() {
        return mDrawable;
    }




}
