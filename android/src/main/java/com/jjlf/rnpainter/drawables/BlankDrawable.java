package com.jjlf.rnpainter.drawables;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.Log;


public class BlankDrawable extends Drawable {
    @Override
    public void draw( Canvas canvas) {
        Log.e("ICESOUL","CALLED BlankDrawable draw ");
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
