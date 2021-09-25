package com.jjlf.rnpainter.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
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
import com.jjlf.rnpainter.utils.SVGViewBox;
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
//        setLayerType(View.LAYER_TYPE_HARDWARE,null);
    }

    protected float mTranslationZ = 0f;
    public void setTranslateZ(float v) {
        if(mTranslationZ != v) {
            mTranslationZ = v;
            setTranslationZ(mTranslationZ);
            invalidateReactTransform();
        }
    }

    protected float mOpacity = 1f;
    public void setOpacity(float v) {
        if(mOpacity != v) {
            mOpacity = v;
            setAlpha(mOpacity);
            invalidateReactTransform();
        }
    }

    public void setFill(int v, boolean status) {
        mProps.mFillColorStatus = status;
        if(mProps.mFillColor != v) {
            mProps.mFillColor = v;
            invalidateWithListener();
        }
    }

    public void setFillRule(String v, boolean status) {
        mProps.mFillRuleStatus = status;
        if(!Objects.equals(mProps.mFillRule, v)) {
            mProps.mFillRule = v;
            invalidateWithListener();
        }
    }

    public void setFillOpacity(float v, boolean status) {
        mProps.mFillOpacityStatus = status;
        if(mProps.mFillOpacity != v) {
            mProps.mFillOpacity = v;
            invalidateWithListener();
        }
    }

    public void setStroke(int v, boolean status) {
        mProps.mStrokeColorStatus = status;
        if(mProps.mStrokeColor != v) {
            mProps.mStrokeColor = v;
            invalidateWithListener();
        }
    }
    public void setStrokeOpacity(float v, boolean status) {
        mProps.mStrokeOpacityStatus = status;
        if(mProps.mStrokeOpacity != v) {
            mProps.mStrokeOpacity = v;
            invalidateWithListener();
        }
    }
    public void setStrokeWith(float v, boolean status) {
        mProps.mStrokeWidthStatus = status;
        if(mProps.mStrokeWidth != v) {
            mProps.mStrokeWidth = v;
            invalidateWithListener();
        }

    }

    public void setStrokeCap(String v) {
        if(!Objects.equals(mProps.mStrokeCap, v)) {
            mProps.mStrokeCap = v;
            invalidateWithListener();
        }
    }

    public void setStrokeJoin(String v) {
        if(!Objects.equals(mProps.mStrokeJoin, v)) {
            mProps.mStrokeJoin = v;
            invalidateWithListener();
        }
    }

    public void setStrokeMiter(float v, boolean status) {
        mProps.mStrokeMiterStatus = status;
        if(mProps.mStrokeMiter != v) {
            mProps.mStrokeMiter = v;
            invalidateWithListener();
        }
    }

    public void setStrokeStart(float v, boolean status) {
        mProps.mStrokeStartStatus = status;
        if(mProps.mStrokeStart != v) {
            mProps.mStrokeStart = v;
            invalidateWithListener();
        }
    }

    public void setStrokeEnd(float v, boolean status) {
        mProps.mStrokeEndStatus = status;
        if(mProps.mStrokeEnd != v){
            mProps.mStrokeEnd = v;
            invalidateWithListener();
        }


    }

    public void setShadow(int v, boolean status) {
        mProps.mShadowColorStatus = status;
        if(mProps.mShadowColor != v){
            mProps.mShadowColor = v;
            invalidateWithListener();
        }
    }

    public void setShadowOpacity(float v, boolean status) {
        mProps.mShadowOpacityStatus = status;
        if(mProps.mShadowOpacity != v) {
            mProps.mShadowOpacity = v;
            invalidateWithListener();
        }
    }

    public void setShadowRadius(float v, boolean status) {
        mProps.mShadowRadiusStatus = status;
        if(mProps.mShadowRadius != v){
            mProps.mShadowRadius = v;
            invalidateWithListener();
        }
    }

    public void setShadowOffset(float v,boolean status) {
        mProps.mShadowOffsetXStatus = status;
        if(mProps.mShadowOffsetX != v || mProps.mShadowOffsetY != v ){
            mProps.mShadowOffsetX = v;
            mProps.mShadowOffsetY = v;
            invalidateWithListener();
        }
    }
    public void setShadowOffsetX(float v,boolean status) {
        mProps.mShadowOffsetXStatus = status;
        if(mProps.mShadowOffsetX != v){
            mProps.mShadowOffsetX = v;
            invalidateWithListener();
        }
    }
    public void setShadowOffsetY(float v,boolean status) {
        mProps.mShadowOffsetYStatus = status;
        if(mProps.mShadowOffsetY != v ){
            mProps.mShadowOffsetY = v;
            invalidateWithListener();
        }
    }
    public void setShadowPercentageValue(boolean v,boolean status) {
        mProps.mShadowOffsetIsPercentStatus = status;
        if(mProps.mShadowOffsetIsPercent != v ){
            mProps.mShadowOffsetIsPercent = v;
            invalidateWithListener();
        }
    }

    //MARK: Transform props

    public void setTransX(float v) {
        if(mTransform.mTranslationX != v ){
            mTransform.mTranslationX = v;
            invalidateWithListener();
        }
    }
    public void setTransY(float v) {
        if(mTransform.mTranslationY != v ){
            mTransform.mTranslationY = v;
            invalidateWithListener();
        }
    }
    public void setTransPercentageValue(boolean v) {
        if(mTransform.mTranslationIsPercent != v ){
            mTransform.mTranslationIsPercent = v;
            invalidateWithListener();
        }
    }

    public void setRot(float v) {
        if(mTransform.mRotation != v ){
            mTransform.mRotation = v;
            invalidateWithListener();
        }
    }
    public void setRotO(float v) {
        if(mTransform.mRotationOx != v || mTransform.mRotationOy != v ){
            mTransform.mRotationOx = v;
            mTransform.mRotationOy = v;
            invalidateWithListener();
        }
    }
    public void setRotOx(float v) {
        if(mTransform.mRotationOx != v ){
            mTransform.mRotationOx = v;
            invalidateWithListener();
        }
    }
    public void setRotOy(float v) {
        if(mTransform.mRotationOy != v ){
            mTransform.mRotationOy = v;
            invalidateWithListener();
        }
    }
    public void setRotPercentageValue(boolean v) {
        if(mTransform.mRotationIsPercent != v ){
            mTransform.mRotationIsPercent = v;
            invalidateWithListener();
        }
    }

    public void setSc(float v){
        if(mTransform.mScaleX != v || mTransform.mScaleY != v){
            mTransform.mScaleX = v;
            mTransform.mScaleY = v;
            invalidateWithListener();
        }
    }
    public void setScX(float v) {
        if(mTransform.mScaleX != v ){
            mTransform.mScaleX = v;
            invalidateWithListener();
        }
    }

    public void setScY(float v) {
        if(mTransform.mScaleY != v ){
            mTransform.mScaleY = v;
            invalidateWithListener();
        }
    }
    public void setScO(float v){
        if(mTransform.mScaleOriginX != v || mTransform.mScaleOriginY != v){
            mTransform.mScaleOriginX = v;
            mTransform.mScaleOriginY = v;
            invalidateWithListener();
        }
    }
    public void setScOx(float v) {
        if(mTransform.mScaleOriginX != v ){
            mTransform.mScaleOriginX = v;
            invalidateWithListener();
        }
    }
    public void setScOy(float v) {
        if(mTransform.mScaleOriginY != v ){
            mTransform.mScaleOriginY = v;
            invalidateWithListener();
        }
    }
    public void setScPercentageValue(boolean v) {
        if(mTransform.mScaleIsPercent != v ){
            mTransform.mScaleIsPercent = v;
            invalidateWithListener();
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

        viewBoxTransform();

        transform();

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


    public void invalidateWithListener() {
        invalidate();
        invalidateMaskListeners();
    }

    public void invalidateReactTransform(){
        //transform react style invalidate
        invalidate();
        invalidateMaskListeners();
    }

    private void invalidateMaskListeners(){
        if(getParent() instanceof MaskInterface){
            ArrayList<PaintableInterface>  listener  =  ((MaskInterface)getParent()).getListeners();
            if(listener != null) {
                for (PaintableInterface c : listener) {
                    c.invalidateMaskCallback();
                }
            }
        }
    }
    

    protected void viewBoxTransform(){
        if (validateViewBox()){
            mPainter.rectPath.set(mPainter.viewBoxDensity);
            SVGViewBox.transform(mPainter.viewBox, mPainter.bounds, mPainter.align, mPainter.aspect, getResources().getDisplayMetrics().density,mPainter.matrix);
            mPainter.matrix.mapRect(mPainter.rectPath);
        }else{
            mPainter.rectPath.set(mPainter.bounds);
        }
    }

    protected void transform() {
        mMatrix.reset();
        if (mTransform.mRotation != 0f) {
            float rotX;
            float rotY;
            if (mTransform.mRotationIsPercent) {
                rotX = (mTransform.mRotationOx * mPainter.bounds.width());
                rotY = (mTransform.mRotationOy * mPainter.bounds.height());
            } else if (validateViewBox()) {
                rotX =  mPainter.rectPath.left +  ModUtil.viewBoxToWidth(mTransform.mRotationOx, mPainter.viewBox,mPainter.rectPath.width());
                rotY =  mPainter.rectPath.top + ModUtil.viewBoxToHeight(mTransform.mRotationOy, mPainter.viewBox, mPainter.rectPath.height());
            } else {
                rotX = toDip(mTransform.mRotationOx);
                rotY = toDip(mTransform.mRotationOy);
            }
            mMatrix.postRotate(mTransform.mRotation,rotX,rotY);
        }

        if (mTransform.mScaleX != 1f || mTransform.mScaleY != 1f) {
            float oX;
            float oY;
            if (mTransform.mScaleIsPercent) {
                oX = (mTransform.mScaleOriginX * mPainter.bounds.width());
                oY = (mTransform.mScaleOriginY * mPainter.bounds.height());
            } else if (validateViewBox()) {
                oX =  mPainter.rectPath.left +  ModUtil.viewBoxToWidth(mTransform.mScaleOriginX, mPainter.viewBox,mPainter.rectPath.width()) ;
                oY =  mPainter.rectPath.top +   ModUtil.viewBoxToHeight(mTransform.mScaleOriginY, mPainter.viewBox,mPainter.rectPath.height()) ;
            } else {
                oX = toDip(mTransform.mScaleOriginX);
                oY = toDip(mTransform.mScaleOriginY);
            }

            mMatrix.postScale(mTransform.mScaleX,mTransform.mScaleY,oX,oY);
        }

        if (mTransform.mTranslationX != 0f || mTransform.mTranslationY != 0f) {
            float transX;
            float transY;
            if (mTransform.mTranslationIsPercent) {
                transX = (mTransform.mTranslationX * mPainter.bounds.width());
                transY = (mTransform.mTranslationY * mPainter.bounds.height());
            } else if (validateViewBox()) {
                transX = (mTransform.mTranslationX / mPainter.viewBox.width()) * mPainter.rectPath.width();
                transY = (mTransform.mTranslationY / mPainter.viewBox.height()) * mPainter.rectPath.height();
            } else {
                transX = toDip(mTransform.mTranslationX);
                transY = toDip(mTransform.mTranslationY);
            }
            mMatrix.postTranslate(transX,transY);
        }

    }
    protected boolean validateViewBox(){
        return mPainter.viewBox.width() >= 0f && mPainter.viewBox.height() >= 0f;
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
