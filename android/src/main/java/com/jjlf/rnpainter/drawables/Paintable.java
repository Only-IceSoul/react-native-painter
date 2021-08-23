package com.jjlf.rnpainter.drawables;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PathMeasure;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;


import com.jjlf.rnpainter.PainterView;
import com.jjlf.rnpainter.utils.CommonProps;
import com.jjlf.rnpainter.utils.ModUtil;
import com.jjlf.rnpainter.utils.PainterKit;
import com.jjlf.rnpainter.utils.SVGViewBox;
import com.jjlf.rnpainter.utils.TransformProps;

import java.util.ArrayList;

public class Paintable extends Drawable {

    CommonProps mProps = new CommonProps();
    TransformProps mTransform = new TransformProps();
    ArrayList<TransformProps> mTransforms = new ArrayList<>() ;
    protected final PathMeasure mPathMeasure = new PathMeasure();
    protected PainterKit mPainter;

    protected RectF mPathBounds = new RectF();
    protected boolean mIgnoreVbTransform = false;
    protected float mDensity = 1f;
    protected boolean mScaleCentered = false;
    protected boolean mIgnoreFill = false;
    protected boolean mIgnoreStroke = false;
    protected boolean mIgnoreShadow = false;

    public Paintable(){
        mTransforms.add(mTransform);
    }

    public void setDensity(float v) {
        mDensity = v;
    }


    public void setFill(int v, boolean status) {
        mProps.mFillColor = v;
        mProps.mFillColorStatus = status;
    }

    public void setFillRule(String v, boolean status) {
        mProps.mFillRule = v;
        mProps.mFillRuleStatus = status;
    }

    public void setFillOpacity(float v, boolean status) {
        mProps.mFillOpacity = v;
        mProps.mFillOpacityStatus = status;
    }

    public void setStroke(int v, boolean status) {
        mProps.mStrokeColor = v;
        mProps.mStrokeColorStatus = status;
    }

    public void setStrokeWith(float v, boolean status) {
        mProps.mStrokeWidth = v;
        mProps.mStrokeWidthStatus = status;
    }

    public void setStrokeCap(String v) {
        mProps.mStrokeCap = v;
    }

    public void setStrokeJoin(String v) {
        mProps.mStrokeJoin = v;
    }

    public void setStrokeMiter(float v, boolean status) {
        mProps.mStrokeMiter = v;
        mProps.mStrokeMiterStatus = status;
    }

    public void setStrokeStart(float v, boolean status) {
        mProps.mStrokeStart = v;
        mProps.mStrokeStartStatus = status;
    }

    public void setStrokeEnd(float v, boolean status) {
        mProps.mStrokeEnd = v;
        mProps.mStrokeEndStatus = status;
    }

    public void setShadow(int v, boolean status) {
        mProps.mShadowColor = v;
        mProps.mShadowColorStatus = status;
    }

    public void setShadowOffset(float x, float y, boolean percent, boolean status) {
        mProps.mShadowOffsetX = x;
        mProps.mShadowOffsetY = y;
        mProps.mShadowOffsetIsPercent = percent;
        mProps.mShadowOffsetStatus = status;
    }

    public void setShadowOpacity(float v, boolean status) {
        mProps.mShadowOpacity = v;
        mProps.mShadowOpacityStatus = status;
    }

    public void setShadowRadius(float v, boolean status) {
        mProps.mShadowRadius = v;
        mProps.mShadowRadiusStatus = status;
    }

    public void setPathTranslation(float x, float y, boolean percent) {
        mTransform.mPathTranslationX = x;
        mTransform.mPathTranslationY = y;
        mTransform.mPathTranslationIsPercent = percent;
    }

    public void setPathRotation(float a, float x, float y, boolean percent) {
        mTransform.mPathRotation = a;
        mTransform.mPathRotationX = x;
        mTransform.mPathRotationY = y;
        mTransform.mPathRotationIsPercent = percent;
    }

    public void setPathScale(float x, float y) {
        mTransform.mPathScaleX = x;
        mTransform.mPathScaleY = y;
    }

    public void setProps(CommonProps props){
        if(props != null){
            mProps.set(props);
        }
    }

    public void setTransforms(ArrayList<TransformProps> transforms) {
        mTransforms.clear();
        mTransforms.addAll(transforms);
        mTransforms.add(mTransform);
    }

    public void setPainterKit(PainterKit painter) {
        mPainter = painter;
    }

    @Override
    public void draw(Canvas canvas) {
        if(mPainter != null) {
            setupPath(mPainter);
            if (!mIgnoreVbTransform) transformToViewBox(mPainter);
            if (!mIgnoreFill) setupPaintFill(mPainter);
            if (!mIgnoreShadow) setupShadow(mPainter);
            if (!mIgnoreStroke) setupPaintStroke(mPainter);
            for (TransformProps t : mTransforms) {
                transformPath(t, mPainter);
            }
            if (!mIgnoreStroke) setupPathStroke(mPainter);
            if(!mIgnoreFill) canvas.drawPath(mPainter.path,mPainter.paint);
            if (!mIgnoreStroke &&  mProps.getStrokeColor() != Color.TRANSPARENT) canvas.drawPath(mPainter.path2,mPainter.paint2);
        }
    }

    protected void setupPath(PainterKit p) {

    }

    protected void setupPaintFill(PainterKit p) {
        p.paint.reset();
        p.paint.setStyle(Paint.Style.FILL);
        if (mProps.getFillOpacity() < 1f && mProps.getFillColor() != Color.TRANSPARENT) {
            final int alpha = Color.alpha(mProps.getFillColor());
            final int red = Color.red(mProps.getFillColor());
            final int green = Color.green(mProps.getFillColor());
            final int blue = Color.blue(mProps.getFillColor());
            final int c = Color.argb((int) (mProps.getFillOpacity() * alpha), red, green, blue);
            p.paint.setColor(c);
        } else {
            p.paint.setColor(mProps.getFillColor());
        }
    }

    protected void setupPaintStroke(PainterKit p) {
        p.paint2.reset();
        float sw ;
        if (p.isViewBoxEnabled) {
            float size = p.viewBoxRectF.width() > p.viewBoxRectF.height() ? p.bounds.width() : p.bounds.height();
            sw =  (mProps.getStrokeWidth() / Math.max( p.viewBoxRectF.width(), p.viewBoxRectF.height() )) * size;
        }else{
            sw = toDip(mProps.getStrokeWidth());
        }
        p.paint2.setStrokeWidth(sw);
        p.paint2.setStyle(Paint.Style.STROKE);
        p.paint2.setColor(mProps.getStrokeColor());
        p.paint2.setStrokeCap(mProps.getStrokeCap());
        p.paint2.setStrokeMiter(mProps.getStrokeMiter());
        p.paint2.setStrokeJoin(mProps.getStrokeJoin());
    }

    protected void setupPathStroke(PainterKit painter){
        painter.path2.reset();
        if(mProps.getStrokeStart() != 0f || mProps.getStrokeEnd() != 1f) {
            mPathMeasure.setPath(painter.path, false);
            mPathMeasure.getSegment((mPathMeasure.getLength() * mProps.getStrokeStart()), (mPathMeasure.getLength() * mProps.getStrokeEnd()), painter.path2, true);
            painter.path2.rLineTo(0f, 0f);
        }else{
            painter.path2.set(painter.path);
        }
    }

    protected void setupShadow(PainterKit p) {
        if (mProps.getShadowOpacity() > 0f) {
            final int alpha = Color.alpha(mProps.getShadowColor());
            final int red = Color.red(mProps.getShadowColor());
            final int green = Color.green(mProps.getShadowColor());
            final int blue = Color.blue(mProps.getShadowColor());
            final int c = Color.argb((int) (mProps.getShadowOpacity() * alpha), red, green, blue);

            float ox;
            float oy;
            if (mProps.getShadowOffsetIsPercent()) {
                ox = mProps.getShadowOffsetX() * p.bounds.width();
                oy = mProps.getShadowOffsetY() * p.bounds.height();
            } else if (p.isViewBoxEnabled) {
                ox = (mProps.getShadowOffsetX() / p.viewBoxRectF.width()) * p.bounds.width();
                oy = (mProps.getShadowOffsetY() / p.viewBoxRectF.height()) * p.bounds.height();
            } else {
                ox = toDip(mProps.getShadowOffsetX());
                oy = toDip(mProps.getShadowOffsetY());
            }

            float radius;
            if (p.isViewBoxEnabled) {
                float size = p.viewBoxRectF.width() > p.viewBoxRectF.height() ? p.bounds.width() : p.bounds.height();
                radius =  (mProps.getShadowRadius() / Math.max( p.viewBoxRectF.width(), p.viewBoxRectF.height() )) * size;
            }else{
                radius = toDip(mProps.getShadowRadius());
            }
            p.paint.setShadowLayer(radius, ox, oy, c);

        } else {
            p.paint.clearShadowLayer();
        }
    }

    protected void transformToViewBox(PainterKit painter) {
        SVGViewBox.transform(painter.viewBoxRectF, painter.bounds, painter.align, painter.aspect, painter.matrix, mDensity);
        painter.path.transform(painter.matrix);
    }


    protected void transformPath(TransformProps transform, PainterKit painter) {
        painter.matrix.reset();

        if (transform.mPathRotation != 0f) {
            float rotX;
            float rotY;
            if (transform.mPathRotationIsPercent) {
                rotX = (transform.mPathRotationX * painter.bounds.width());
                rotY = (transform.mPathRotationY * painter.bounds.height());
            } else if (painter.isViewBoxEnabled) {

                rotX = ModUtil.viewBoxToWidth(transform.mPathRotationX, painter.viewBoxRectF, painter.bounds.width());
                rotY = ModUtil.viewBoxToHeight(transform.mPathRotationY, painter.viewBoxRectF, painter.bounds.height());
            } else {
                rotX = toDip(transform.mPathRotationX);
                rotY = toDip(transform.mPathRotationY);
            }
            painter.matrix.postRotate(transform.mPathRotation, rotX, rotY);
        }

        if (transform.mPathScaleX != 1f || transform.mPathScaleY != 1f) {
            if (mScaleCentered) {
                mPathBounds.set(0f, 0f, 0f, 0f);
                painter.path.computeBounds(mPathBounds, true);
                painter.matrix.postScale(transform.mPathScaleX, transform.mPathScaleY, mPathBounds.centerX(), mPathBounds.centerY());
            } else {
                painter.matrix.postScale(transform.mPathScaleX, transform.mPathScaleY);
            }
        }

        if (transform.mPathTranslationX != 0f || transform.mPathTranslationY != 0f) {
            float transX;
            float transY;
            if (transform.mPathTranslationIsPercent) {
                transX = (transform.mPathTranslationX * painter.bounds.width());
                transY = (transform.mPathTranslationY * painter.bounds.height());
            } else if (painter.isViewBoxEnabled) {
                transX = (transform.mPathTranslationX / painter.viewBoxRectF.width()) * painter.bounds.width();
                transY = (transform.mPathTranslationY / painter.viewBoxRectF.height()) * painter.bounds.height();
            } else {
                transX = toDip(transform.mPathTranslationX);
                transY = toDip(transform.mPathTranslationY);
            }
            painter.matrix.postTranslate(transX, transY);
        }

        painter.path.transform(painter.matrix);

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

    protected float toDip(float value) {
        return value * mDensity;
    }
}