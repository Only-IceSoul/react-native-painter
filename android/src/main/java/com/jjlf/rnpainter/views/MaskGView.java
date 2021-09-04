package com.jjlf.rnpainter.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.jjlf.rnpainter.PainterView;
import com.jjlf.rnpainter.PainterViewHardware;
import com.jjlf.rnpainter.utils.CommonProps;
import com.jjlf.rnpainter.utils.MaskInterface;
import com.jjlf.rnpainter.utils.ModUtil;
import com.jjlf.rnpainter.utils.PaintableInterface;
import com.jjlf.rnpainter.utils.PainterKit;
import com.jjlf.rnpainter.utils.TransformProps;

import java.util.ArrayList;
import java.util.Objects;

public class MaskGView extends ViewGroup implements PaintableInterface {


    CommonProps mProps = new CommonProps();
    TransformProps mTransform = new TransformProps();
    private final Matrix mMatrix = new Matrix();
    private PainterKit mPainter;

    public MaskGView(Context context){
        super(context);
        setClipChildren(false);
        //new
        setLayerType(View.LAYER_TYPE_HARDWARE,null);
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
            invalidateTransform();
        }
    }

    public void setPathRotation(float a, float x, float y, boolean percent) {
        if(mTransform.mPathRotation != a || mTransform.mPathRotationX != x || mTransform.mPathRotationY != y || mTransform.mPathRotationIsPercent != percent){
            mTransform.mPathRotation = a;
            mTransform.mPathRotationX = x;
            mTransform.mPathRotationY = y;
            mTransform.mPathRotationIsPercent = percent;
            invalidateTransform();
        }
    }

    public void setPathScale(float x, float y,float ox,float oy,boolean percent) {
        if(mTransform.mPathScaleX != x || mTransform.mPathScaleY != y || mTransform.mPathScaleOriginX != ox || mTransform.mPathScaleOriginY != oy || mTransform.mPathScaleIsPercent != percent){
            mTransform.mPathScaleX = x;
            mTransform.mPathScaleY = y;
            mTransform.mPathScaleOriginX = ox;
            mTransform.mPathScaleOriginY = oy;
            mTransform.mPathScaleIsPercent = percent;
            invalidateTransform();
        }
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        setupMatrix(mTransform, mPainter);

        int checkpoint = canvas.save();
        canvas.concat(mMatrix);
        try{
            for (int i = 0; i < getChildCount(); i++) {
                final View child = getChildAt(i);
                if(child instanceof PaintableInterface){
                    PaintableInterface c = (PaintableInterface) child;
                    c.setProps(mProps);
                    c.setPainterKit(mPainter);
                }
            }
            super.dispatchDraw(canvas);
        } finally {
            canvas.restoreToCount(checkpoint);
        }
    }


    @Override
    public void invalidate() {
        if(getParent() instanceof MaskInterface){
            for (PaintableInterface c :  ((MaskInterface)getParent()).getListeners()){
                c.invalidateMaskCallback();
            }
        }
    }

    public void invalidateTransform(){
        //transform react style invalidate
        super.invalidate();
        if(getParent() instanceof MaskInterface){
            for (PaintableInterface c :  ((MaskInterface)getParent()).getListeners()){
                c.invalidateMaskCallback();
            }
        }
    }

    protected void setupMatrix(TransformProps transform, PainterKit painter) {
        mMatrix.reset();
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
            mMatrix.postRotate(transform.mPathRotation,rotX,rotY);
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
            mMatrix.postScale(transform.mPathScaleX,transform.mPathScaleY,oX,oY);


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
            mMatrix.postTranslate(transX,transY);
        }

    }
    protected float toDip(float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value,getResources().getDisplayMetrics());
    }

    @Override
    public void onViewAdded(View child) {
        if(child instanceof MaskInterface || child instanceof PainterView || child instanceof PainterViewHardware
                || child instanceof GView || child instanceof GViewHardware || child instanceof MaskGView){
            throw new IllegalArgumentException("MaskG cannot have ViewGroup, G, Painter, Mask, MaskG");
        }
        if(child instanceof PaintableInterface){
            ((PaintableInterface) child).setIsMaskChild(true);
        }else{
            throw new IllegalArgumentException("MaskG : foreignObject cannot be child ");
        }
        super.onViewAdded(child);
    }

    @Override
    public void invalidateMaskCallback() { }
    @Override
    public void setIsMaskChild(boolean v) { }
    @Override
    public void setProps(CommonProps props) { }

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
