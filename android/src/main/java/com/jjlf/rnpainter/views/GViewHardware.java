package com.jjlf.rnpainter.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.jjlf.rnpainter.PainterView;
import com.jjlf.rnpainter.utils.CommonProps;
import com.jjlf.rnpainter.utils.MaskInterface;
import com.jjlf.rnpainter.utils.PaintableInterface;
import com.jjlf.rnpainter.utils.PainterKit;
import com.jjlf.rnpainter.utils.TransformProps;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Objects;

public class GViewHardware extends ViewGroup implements PaintableInterface  {


    CommonProps mProps = new CommonProps();
    TransformProps mTransform = new TransformProps();
    ArrayList<TransformProps> mTransforms = new ArrayList<>();
    private PainterKit mPainter;

    public GViewHardware(Context context){
        super(context);
        setClipChildren(false);
        mTransforms.add(mTransform);
        setLayerType(LAYER_TYPE_HARDWARE,null);
    }

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

            mIsInvalidate = true;
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

    @Override
    protected void dispatchDraw(Canvas canvas) {
            for (int i = 0; i < getChildCount(); i++) {
                final View child = getChildAt(i);
                if(child instanceof PaintableInterface){
                    PaintableInterface c = (PaintableInterface) child;
                    c.setProps(mProps);
                    c.setTransforms(mTransforms);
                    c.setPainterKit(mPainter);
                }
            }
            super.dispatchDraw(canvas);
            if(!mProps.getMask().isEmpty()){
                WeakReference<MaskInterface> maskView = PainterView.MaskViews.get(mProps.getMask());
                if(maskView.get() != null){
                    drawMask(canvas,maskView.get());
                }
            }

    }



    private boolean mIsInvalidate = false;
    @Override
    public void invalidate() {
        if(mIsInvalidate){
            mIsInvalidate = false;
            super.invalidate();

        }else{
            for (int i = 0; i < getChildCount(); i++) {
                final View child = getChildAt(i);
                if(child instanceof PaintableInterface){
                    PaintableInterface c = (PaintableInterface) child;
                    c.setProps(mProps);
                    c.setTransforms(mTransforms);
                    c.setPainterKit(mPainter);
                }
                child.invalidate();
            }
        }

    }

    protected void drawMask(Canvas canvas, MaskInterface mask){
        if(mPainter.maskBitmap != null){
            mPainter.maskBitmap.eraseColor(Color.TRANSPARENT);
            mask.render(mPainter.maskCanvas);
            mPainter.paint.reset();
            mPainter.paint.setAntiAlias(true);
            mPainter.paint.setXfermode(mPainter.porterDuffXferMode);
            canvas.drawBitmap(mPainter.maskBitmap,0f,0f,mPainter.paint);
            mPainter.paint.setXfermode(null);
        }
    }

    @Override
    public void invalidateMaskCallback() {
        mIsInvalidate = true;
        invalidate();
    }
    @Override
    public void setIsMaskChild(boolean v) {
    }
    @Override
    public void setProps(CommonProps props) {
        if(props != null){
            mProps.set(props);
        }
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



    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).layout(0, 0, getWidth(), getHeight());
        }
    }

}
