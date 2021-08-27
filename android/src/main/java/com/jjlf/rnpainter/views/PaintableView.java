package com.jjlf.rnpainter.views;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.jjlf.rnpainter.PainterView;
import com.jjlf.rnpainter.utils.CommonProps;
import com.jjlf.rnpainter.utils.MaskInterface;
import com.jjlf.rnpainter.utils.ModUtil;
import com.jjlf.rnpainter.utils.PaintableInterface;
import com.jjlf.rnpainter.utils.PainterKit;
import com.jjlf.rnpainter.utils.SVGViewBox;
import com.jjlf.rnpainter.utils.TransformProps;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Objects;

public class PaintableView extends View implements PaintableInterface {


    public PaintableView(Context context){
        super(context);
        mTransforms.add(mTransform);
    }



    @Override
    public void setProps(CommonProps props) {
        if(props != null){
            mProps.set(props);
        }
    }

    @Override
    public void setIsMaskChild(boolean v) {
         mIsMaskChild = v;
    }

    @Override
    public void setTransforms(ArrayList<TransformProps> transforms) {
        mTransforms.clear();
        mTransforms.addAll(transforms);
        mTransforms.add(mTransform);
    }
    @Override
    public void setPainterKit(PainterKit painter) {
        mPainter = painter;
    }



    CommonProps mProps = new CommonProps();
    TransformProps mTransform = new TransformProps();
    ArrayList<TransformProps> mTransforms = new ArrayList<>() ;
    protected final PathMeasure mPathMeasure = new PathMeasure();
    protected PainterKit mPainter;

    protected RectF mPathBounds = new RectF();
    protected boolean mIgnoreVbTransform = false;

    protected boolean mIgnoreFill = false;
    protected boolean mIgnoreStroke = false;
    protected boolean mIgnoreShadowFill = false;
    protected boolean mIsMaskChild = false;


    public void setMask(String v) {
        if(!Objects.equals(mProps.mMask, v)) {
            String old = mProps.mMask;
            mProps.mMask = v;
            if(!old.isEmpty()){
               WeakReference<MaskInterface> m = PainterView.MaskViews.get(old);
               if(m.get() != null){
                   m.get().removeListener(this);
               }
            }
            if(!mProps.mMask.isEmpty()){
                WeakReference<MaskInterface> m = PainterView.MaskViews.get(mProps.mMask);
                if(m.get() != null){
                    m.get().addListener(this);
                }
            }
            invalidate();
        }
    }

    public void setOpacity(float v, boolean status) {
        mProps.mOpacityStatus = status;
        if(mProps.mOpacity != v) {
            mProps.mOpacity = v;
            invalidate();
        }
    }

    public void setFill(int v, boolean status) {
        mProps.mFillColorStatus = status;
        if(mProps.mFillColor != v) {
            mProps.mFillColor = v;
            invalidate();
        }
    }

    public void setFillRule(String v, boolean status) {
        mProps.mFillRuleStatus = status;
        if(!Objects.equals(mProps.mFillRule, v)) {
            mProps.mFillRule = v;
            invalidate();
        }
    }

    public void setFillOpacity(float v, boolean status) {
        mProps.mFillOpacityStatus = status;
        if(mProps.mFillOpacity != v) {
            mProps.mFillOpacity = v;
            invalidate();
        }
    }

    public void setStroke(int v, boolean status) {
        mProps.mStrokeColorStatus = status;
        if(mProps.mStrokeColor != v) {
            mProps.mStrokeColor = v;
            invalidate();
        }
    }

    public void setStrokeOpacity(float v, boolean status) {
        mProps.mStrokeOpacityStatus = status;
        if(mProps.mStrokeOpacity != v) {
            mProps.mStrokeOpacity = v;
            invalidate();
        }
    }

    public void setStrokeWith(float v, boolean status) {
        mProps.mStrokeWidthStatus = status;
        if(mProps.mStrokeWidth != v) {
            mProps.mStrokeWidth = v;
            invalidate();
        }

    }

    public void setStrokeCap(String v) {
        if(!Objects.equals(mProps.mStrokeCap, v)) {
            mProps.mStrokeCap = v;
            invalidate();
        }
    }

    public void setStrokeJoin(String v) {
        if(!Objects.equals(mProps.mStrokeJoin, v)) {
            mProps.mStrokeJoin = v;
            invalidate();
        }
    }

    public void setStrokeMiter(float v, boolean status) {
        mProps.mStrokeMiterStatus = status;
        if(mProps.mStrokeMiter != v) {
            mProps.mStrokeMiter = v;
            invalidate();
        }
    }

    public void setStrokeStart(float v, boolean status) {
        mProps.mStrokeStartStatus = status;
        if(mProps.mStrokeStart != v) {
            mProps.mStrokeStart = v;
            invalidate();
        }
    }

    public void setStrokeEnd(float v, boolean status) {
        mProps.mStrokeEndStatus = status;
        if(mProps.mStrokeEnd != v){
            mProps.mStrokeEnd = v;
            invalidate();
        }


    }

    public void setShadow(int v, boolean status) {
        mProps.mShadowColorStatus = status;
        if(mProps.mShadowColor != v){
            mProps.mShadowColor = v;
            invalidate();
        }
    }

    public void setShadowOffset(float x, float y, boolean percent, boolean status) {
        mProps.mShadowOffsetStatus = status;
        if(mProps.mShadowOffsetX != x || mProps.mShadowOffsetY != y || mProps.mShadowOffsetIsPercent != percent){
            mProps.mShadowOffsetX = x;
            mProps.mShadowOffsetY = y;
            mProps.mShadowOffsetIsPercent = percent;
            invalidate();
        }

    }

    public void setShadowOpacity(float v, boolean status) {
        mProps.mShadowOpacityStatus = status;
        if(mProps.mShadowOpacity != v) {
            mProps.mShadowOpacity = v;
            invalidate();
        }
    }

    public void setShadowRadius(float v, boolean status) {
        mProps.mShadowRadiusStatus = status;
        if(mProps.mShadowRadius != v){
            mProps.mShadowRadius = v;
            invalidate();
        }
    }

    public void setPathTranslation(float x, float y, boolean percent) {
        if(mTransform.mPathTranslationX != x || mTransform.mPathTranslationY != y || mTransform.mPathTranslationIsPercent != percent) {
            mTransform.mPathTranslationX = x;
            mTransform.mPathTranslationY = y;
            mTransform.mPathTranslationIsPercent = percent;
            invalidate();
        }
    }

    public void setPathRotation(float a, float x, float y, boolean percent) {
        if(mTransform.mPathRotation != a || mTransform.mPathRotationX != x || mTransform.mPathRotationY != y || mTransform.mPathRotationIsPercent != percent){
            mTransform.mPathRotation = a;
            mTransform.mPathRotationX = x;
            mTransform.mPathRotationY = y;
            mTransform.mPathRotationIsPercent = percent;
            invalidate();
        }
    }

    public void setPathScale(float x, float y,float ox,float oy,boolean percent) {
        if(mTransform.mPathScaleX != x || mTransform.mPathScaleY != y || mTransform.mPathScaleOriginX != ox || mTransform.mPathScaleOriginY != oy || mTransform.mPathScaleIsPercent != percent){
            mTransform.mPathScaleX = x;
            mTransform.mPathScaleY = y;
            mTransform.mPathScaleOriginX = ox;
            mTransform.mPathScaleOriginY = oy;
            mTransform.mPathScaleIsPercent = percent;
            invalidate();
        }

    }





    @SuppressLint("MissingSuperCall")
    @Override
    public void draw(Canvas canvas) {

        if(mPainter != null) {
            setupPath(mPainter);
            if (!mIgnoreVbTransform) transformToViewBox(mPainter);
            if (fill()) {
                setupPaintFill(mPainter);
                if (!mIgnoreShadowFill) setupShadowFill(mPainter);
            }

            if (stroke()){
                setupPaintStroke(mPainter);
                setupPathStroke(mPainter);
                if (!fill()) setupShadowStroke(mPainter);
            }
            mPainter.matrix.reset();
            for (TransformProps t : mTransforms) {
                transform(t, mPainter);
            }

            //draw
            if(mProps.getMask().isEmpty()){
                    drawPaths(canvas);
            }else{
                WeakReference<MaskInterface> maskView = PainterView.MaskViews.get(mProps.getMask());
                if(maskView.get() != null){
                    drawWithMask(canvas,maskView.get());
                }else{
                    drawPaths(canvas);
                }
            }



        }
    }


    protected void drawPaths(Canvas canvas){
        int checkpoint = canvas.save();
        canvas.concat(mPainter.matrix);
        try{
            if(fill()) { canvas.drawPath(mPainter.path,mPainter.paint); }
            if (stroke()) canvas.drawPath(mPainter.path2,mPainter.paint2);
        } finally {
            canvas.restoreToCount(checkpoint);
        }
    }

    protected boolean stroke(){
        return !mIgnoreStroke &&  mProps.getStrokeColor() != Color.TRANSPARENT && mProps.getStrokeWidth() > 0f;
    }
    protected boolean fill(){
        return !mIgnoreFill && mProps.getFillColor() != Color.TRANSPARENT;
    }

    protected void drawWithMask(Canvas canvas, MaskInterface mask){
        if( mPainter.bitmap != null && ( stroke() || fill() ) ){
            mPainter.bitmap.eraseColor(Color.TRANSPARENT);
            drawPaths(mPainter.canvas);
            mPainter.paint.reset();
            mPainter.paint.setAntiAlias(true);
            if(mPainter.maskBitmap != null){
                mPainter.maskBitmap.eraseColor(Color.TRANSPARENT);
                mask.render(mPainter.maskCanvas);
                mPainter.paint.setXfermode(mPainter.porterDuffXferMode);;
                mPainter.canvas.drawBitmap(mPainter.maskBitmap,0f,0f,mPainter.paint);
                mPainter.paint.setXfermode(null);
            }
            canvas.drawBitmap(mPainter.bitmap,0f,0f, mPainter.paint);
        }

    }
    protected void setupPath(PainterKit p) {

    }

    protected void setupPaintFill(PainterKit p) {
        p.paint.reset();
        p.paint.setAntiAlias(true);
        p.paint.setStyle(Paint.Style.FILL);
        p.paint.setColor(mProps.getFillColor());

        float opacity = mProps.getFillOpacity() * mProps.getOpacity();
        p.paint.setAlpha((int) (opacity * 255f));


    }

    protected void setupPaintStroke(PainterKit p) {
        p.paint2.reset();
        p.paint2.setStyle(Paint.Style.STROKE);
        p.paint2.setAntiAlias(true);

        float sw ;
        if (p.isViewBoxEnabled) {
            float size = p.viewBox.width() > p.viewBox.height() ? p.bounds.width() : p.bounds.height();
            sw =  (mProps.getStrokeWidth() / Math.max( p.viewBox.width(), p.viewBox.height() )) * size;
        }else{
            sw = toDip(mProps.getStrokeWidth());
        }
        p.paint2.setStrokeWidth(sw);
        p.paint2.setColor(mProps.getStrokeColor());

        float opacity = mProps.getStrokeOpacity() * mProps.getOpacity();
        p.paint2.setAlpha((int)(opacity * 255f));

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

    protected void setupShadowFill(PainterKit p) {
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
                ox = (mProps.getShadowOffsetX() / p.viewBox.width()) * p.bounds.width();
                oy = (mProps.getShadowOffsetY() / p.viewBox.height()) * p.bounds.height();
            } else {
                ox = toDip(mProps.getShadowOffsetX());
                oy = toDip(mProps.getShadowOffsetY());
            }

            float radius;
            if (p.isViewBoxEnabled) {
                float size = p.viewBox.width() > p.viewBox.height() ? p.bounds.width() : p.bounds.height();
                radius =  (mProps.getShadowRadius() / Math.max( p.viewBox.width(), p.viewBox.height() )) * size;
            }else{
                radius = toDip(mProps.getShadowRadius());
            }
            p.paint.setShadowLayer(radius, ox, oy, c);

        } else {
            p.paint.clearShadowLayer();
        }
    }
    protected void setupShadowStroke(PainterKit p) {
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
                ox = (mProps.getShadowOffsetX() / p.viewBox.width()) * p.bounds.width();
                oy = (mProps.getShadowOffsetY() / p.viewBox.height()) * p.bounds.height();
            } else {
                ox = toDip(mProps.getShadowOffsetX());
                oy = toDip(mProps.getShadowOffsetY());
            }

            float radius;
            if (p.isViewBoxEnabled) {
                float size = p.viewBox.width() > p.viewBox.height() ? p.bounds.width() : p.bounds.height();
                radius =  (mProps.getShadowRadius() / Math.max( p.viewBox.width(), p.viewBox.height() )) * size;
            }else{
                radius = toDip(mProps.getShadowRadius());
            }
            p.paint2.setShadowLayer(radius, ox, oy, c);

        } else {
            p.paint2.clearShadowLayer();
        }
    }


    protected void transformToViewBox(PainterKit painter) {
        SVGViewBox.transform(painter.viewBox, painter.bounds, painter.align, painter.aspect, painter.matrix, getResources().getDisplayMetrics().density);
        painter.path.transform(painter.matrix);
    }


    protected void transform(TransformProps transform, PainterKit painter) {

        if (transform.mPathRotation != 0f) {
            float rotX;
            float rotY;
            if (transform.mPathRotationIsPercent) {
                rotX = (transform.mPathRotationX * painter.bounds.width());
                rotY = (transform.mPathRotationY * painter.bounds.height());
            } else if (painter.isViewBoxEnabled) {

                rotX = ModUtil.viewBoxToWidth(transform.mPathRotationX, painter.viewBox, painter.bounds.width());
                rotY = ModUtil.viewBoxToHeight(transform.mPathRotationY, painter.viewBox, painter.bounds.height());
            } else {
                rotX = toDip(transform.mPathRotationX);
                rotY = toDip(transform.mPathRotationY);
            }
            mPainter.matrix.postRotate(transform.mPathRotation,rotX,rotY);
        }

        if (transform.mPathScaleX != 1f || transform.mPathScaleY != 1f) {
                float oX;
                float oY;
            if (transform.mPathScaleIsPercent) {
                oX = (transform.mPathScaleOriginX * painter.bounds.width());
                oY = (transform.mPathScaleOriginY * painter.bounds.height());
            } else if (painter.isViewBoxEnabled) {
                    oX = ModUtil.viewBoxToWidth(transform.mPathScaleOriginX, painter.viewBox, painter.bounds.width());
                    oY = ModUtil.viewBoxToHeight(transform.mPathScaleOriginY, painter.viewBox, painter.bounds.height());
                } else {
                    oX = toDip(transform.mPathScaleOriginX);
                    oY = toDip(transform.mPathScaleOriginY);
                }
                mPainter.matrix.postScale(transform.mPathScaleX,transform.mPathScaleY,oX,oY);


        }

        if (transform.mPathTranslationX != 0f || transform.mPathTranslationY != 0f) {
            float transX;
            float transY;
            if (transform.mPathTranslationIsPercent) {
                transX = (transform.mPathTranslationX * painter.bounds.width());
                transY = (transform.mPathTranslationY * painter.bounds.height());
            } else if (painter.isViewBoxEnabled) {
                transX = (transform.mPathTranslationX / painter.viewBox.width()) * painter.bounds.width();
                transY = (transform.mPathTranslationY / painter.viewBox.height()) * painter.bounds.height();
            } else {
                transX = toDip(transform.mPathTranslationX);
                transY = toDip(transform.mPathTranslationY);
            }
            mPainter.matrix.postTranslate(transX,transY);
        }

    }


    protected float toDip(float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value,getResources().getDisplayMetrics());
    }
    @Override
    public void invalidateMaskCallback() {
        invalidate();
    }

    @Override
    public void invalidate() {
        if(!mIsMaskChild) {
            super.invalidate();
        } else{
            if(getParent() instanceof MaskInterface){
                for (PaintableInterface c :  ((MaskInterface)getParent()).getListeners()){
                    c.invalidateMaskCallback();
                }
            }
        }

    }
}