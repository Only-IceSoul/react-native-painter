package com.jjlf.rnpainter.views;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;

import com.jjlf.rnpainter.utils.CommonProps;
import com.jjlf.rnpainter.utils.PaintableInterface;
import com.jjlf.rnpainter.utils.PainterKit;
import com.jjlf.rnpainter.utils.TransformProps;

import java.util.ArrayList;

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
    public void setShadowOffset(float x,float y,boolean percent, boolean status) {
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
    public void setPathTranslation(float x,float y,boolean percent) {
        mTransform.mPathTranslationX = x;
        mTransform.mPathTranslationY = y;
        mTransform.mPathTranslationIsPercent = percent;
    }
    public void setPathRotation(float a,float x,float y,boolean percent) {
        mTransform.mPathRotation = a;
        mTransform.mPathRotationX = x;
        mTransform.mPathRotationY = y;
        mTransform.mPathRotationIsPercent = percent;
    }
    public void setPathScale(float x,float y) {
        mTransform.mPathScaleX = x;
        mTransform.mPathScaleY = y;
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
    }


    public void invalidateChildren(){
        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            if(child instanceof PaintableInterface){
                PaintableInterface c = (PaintableInterface) child;
                c.setProps(mProps);
                c.setTransforms(mTransforms);
                c.setPainterKit(mPainter);
            }
            if(child instanceof GViewHardware){
                GViewHardware c = (GViewHardware) child;
                c.invalidateChildren();
            }else if(child instanceof GView){
                GView c = (GView) child;
                c.invalidateChildren();
            }else{
                child.invalidate();
            }
        }
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

    }


}
