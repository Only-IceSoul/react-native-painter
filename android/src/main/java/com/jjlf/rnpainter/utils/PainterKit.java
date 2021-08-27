package com.jjlf.rnpainter.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.text.TextPaint;

public class PainterKit {

    public final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public final Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    public final Path path = new Path();
    public final Path path2 = new Path();
    public final TextPaint textPaint = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
    public final TextPaint textPaint2 = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
    public final RectF bounds = new RectF();
    public String align = "none";
    public int aspect = 2;
    public final RectF viewBox = new RectF();
    public final Matrix matrix = new Matrix();
    public  Bitmap maskBitmap;
    public  Bitmap bitmap;
    public final PorterDuffXfermode porterDuffXferMode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    public final Canvas maskCanvas = new Canvas();
    public final Canvas canvas = new Canvas();
    public boolean isViewBoxEnabled = false;
}
