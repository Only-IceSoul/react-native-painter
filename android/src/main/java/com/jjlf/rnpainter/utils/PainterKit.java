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

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class PainterKit {

    public final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public final Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    public final Path path = new Path();
    public final Path path2 = new Path();
    public final TextPaint textPaint = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
    public final TextPaint textPaint2 = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
    public String align = "xMidYMid";
    public int aspect = SVGViewBox.MOS_MEET;
    public final RectF bounds = new RectF();
    public final RectF rectPath = new RectF();
    public final RectF rectHelper = new RectF();
    public final RectF viewBox = new RectF(0f,0f,-1f,-1f);
    public final RectF viewBoxDensity = new RectF();
    public final Matrix matrix = new Matrix();
    public final Matrix matrix2 = new Matrix();
    public final PorterDuffXfermode dstIn = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    public final PorterDuffXfermode srcIn = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    public final PorterDuffXfermode dstOut = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
    public final Paint paintMask = new Paint(Paint.ANTI_ALIAS_FLAG);

    public final Map<String, WeakReference<MaskInterface>> maskViews = new HashMap<>();
}
