package com.jjlf.rnpainter.utils;

import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

public class PainterKit {

    public final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public final Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    public final Path path = new Path();
    public final Path path2 = new Path();

    public final RectF bounds = new RectF();
    public String align = "none";
    public int aspect = 2;
    public final RectF viewBox = new RectF();
    public final Matrix matrix = new Matrix();
    public boolean isViewBoxEnabled = false;
}
