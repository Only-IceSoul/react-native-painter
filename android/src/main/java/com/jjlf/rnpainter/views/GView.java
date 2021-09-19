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
import com.jjlf.rnpainter.utils.TransformProps;

import java.lang.ref.WeakReference;
import java.util.Objects;


public class GView extends ViewGroup implements PaintableInterface  {


    CommonProps mProps = new CommonProps();
    TransformProps mTransform = new TransformProps();
    private PainterKit mPainter;

    public GView(Context context){
        super(context);
        setClipChildren(false);
        //shadow layer < 28
        setLayerType(LAYER_TYPE_SOFTWARE,null);

    }

    public void setMask(String v) {
        if(!Objects.equals(mProps.mMask, v)) {
            mProps.mOldMask = mProps.mMask;
            mProps.mMask = v;
            invalidateMask();
        }
    }
    private boolean mLazySetupMask = false;
    private void setupMaskListener(){
        if(! mProps.mOldMask.isEmpty()){
            WeakReference<MaskInterface> m = mPainter.maskViews.get( mProps.mOldMask);
            if(m != null && m.get() != null){
                m.get().removeListener(this);
            }
        }
        if(!mProps.mMask.isEmpty()){
            WeakReference<MaskInterface> m = mPainter.maskViews.get(mProps.mMask);
            if(m != null && m.get() != null){
                m.get().addListener(this);
            }
        }
    }

    public void invalidateMask(){
        if(mPainter != null){
            setupMaskListener();
            invalidate();
        }else{
            mLazySetupMask = true;
        }
    }

    protected float mTranslationZ = 0f;
    public void setTranslateZ(float v) {
        if(mTranslationZ != v) {
            mTranslationZ = v;
            setTranslationZ(mTranslationZ);
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


    //MARK: Transform props

    public void setTransX(float v) {
        if(mTransform.mTranslationX != v ){
            mTransform.mTranslationX = v;
            invalidate();
        }
    }
    public void setTransY(float v) {
        if(mTransform.mTranslationY != v ){
            mTransform.mTranslationY = v;
            invalidate();
        }
    }
    public void setTransPercentageValue(boolean v) {
        if(mTransform.mTranslationIsPercent != v ){
            mTransform.mTranslationIsPercent = v;
            invalidate();
        }
    }

    public void setRot(float v) {
        if(mTransform.mRotation != v ){
            mTransform.mRotation = v;
            invalidate();
        }
    }
    public void setRotO(float v) {
        if(mTransform.mRotationOx != v || mTransform.mRotationOy != v ){
            mTransform.mRotationOx = v;
            mTransform.mRotationOy = v;
            invalidate();
        }
    }
    public void setRotOx(float v) {
        if(mTransform.mRotationOx != v ){
            mTransform.mRotationOx = v;
            invalidate();
        }
    }
    public void setRotOy(float v) {
        if(mTransform.mRotationOy != v ){
            mTransform.mRotationOy = v;
            invalidate();
        }
    }
    public void setRotPercentageValue(boolean v) {
        if(mTransform.mRotationIsPercent != v ){
            mTransform.mRotationIsPercent = v;
            invalidate();
        }
    }

    public void setSc(float v){
        if(mTransform.mScaleX != v || mTransform.mScaleY != v){
            mTransform.mScaleX = v;
            mTransform.mScaleY = v;
            invalidate();
        }
    }
    public void setScX(float v) {
        if(mTransform.mScaleX != v ){
            mTransform.mScaleX = v;
            invalidate();
        }
    }

    public void setScY(float v) {
        if(mTransform.mScaleY != v ){
            mTransform.mScaleY = v;
            invalidate();
        }
    }
    public void setScO(float v){
        if(mTransform.mScaleOriginX != v || mTransform.mScaleOriginY != v){
            mTransform.mScaleOriginX = v;
            mTransform.mScaleOriginY = v;
            invalidate();
        }
    }
    public void setScOx(float v) {
        if(mTransform.mScaleOriginX != v ){
            mTransform.mScaleOriginX = v;
            invalidate();
        }
    }
    public void setScOy(float v) {
        if(mTransform.mScaleOriginY != v ){
            mTransform.mScaleOriginY = v;
            invalidate();
        }
    }
    public void setScPercentageValue(boolean v) {
        if(mTransform.mScaleIsPercent != v ){
            mTransform.mScaleIsPercent = v;
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
                c.setPainterKit(mPainter);
            }
        }
        setupMatrix(mTransform, mPainter);
        int checkpoint = canvas.save();
        canvas.concat(mMatrix);
        try{
            if(!mProps.getMask().isEmpty()){
                WeakReference<MaskInterface> maskView = mPainter.maskViews.get(mProps.getMask());
                if(maskView != null && maskView.get() != null){
                    maskView.get().render(canvas);
                    mPainter.paintMask.setXfermode(mPainter.srcIn);
                    canvas.saveLayer(0f,0f,getWidth(),getHeight(),mPainter.paintMask);
                    super.dispatchDraw(canvas);
                    canvas.restore();
                }
            }else{
                super.dispatchDraw(canvas);
            }
        } finally {
            canvas.restoreToCount(checkpoint);
        }

    }


    private final Matrix mMatrix = new Matrix();
    protected void setupMatrix(TransformProps transform, PainterKit painter) {
        mMatrix.reset();
        if (transform.mRotation != 0f) {
            float rotX;
            float rotY;
            if (transform.mRotationIsPercent) {
                rotX = (transform.mRotationOx * painter.bounds.width());
                rotY = (transform.mRotationOy * painter.bounds.height());
            } else if (painter.isViewBoxEnabled) {

                rotX = ModUtil.viewBoxToWidth(transform.mRotationOx, painter.viewBox, painter.bounds.width());
                rotY = ModUtil.viewBoxToHeight(transform.mRotationOy, painter.viewBox, painter.bounds.height());
            } else {
                rotX = toDip(transform.mRotationOx);
                rotY = toDip(transform.mRotationOy);
            }
            mMatrix.postRotate(transform.mRotation,rotX,rotY);
        }

        if (transform.mScaleX != 1f || transform.mScaleY != 1f) {
            float oX;
            float oY;
            if (transform.mScaleIsPercent) {
                oX = (transform.mScaleOriginX * painter.bounds.width());
                oY = (transform.mScaleOriginY * painter.bounds.height());
            } else if (painter.isViewBoxEnabled) {
                oX = ModUtil.viewBoxToWidth(transform.mScaleOriginX, painter.viewBox, painter.bounds.width());
                oY = ModUtil.viewBoxToHeight(transform.mScaleOriginY, painter.viewBox, painter.bounds.height());
            } else {
                oX = toDip(transform.mScaleOriginX);
                oY = toDip(transform.mScaleOriginY);
            }
            mMatrix.postScale(transform.mScaleX,transform.mScaleY,oX,oY);


        }

        if (transform.mTranslationX != 0f || transform.mTranslationY != 0f) {
            float transX;
            float transY;
            if (transform.mTranslationIsPercent) {
                transX = (transform.mTranslationX * painter.bounds.width());
                transY = (transform.mTranslationY * painter.bounds.height());
            } else if (painter.isViewBoxEnabled) {
                transX = (transform.mTranslationX / painter.viewBox.width()) * painter.bounds.width();
                transY = (transform.mTranslationY / painter.viewBox.height()) * painter.bounds.height();
            } else {
                transX = toDip(transform.mTranslationX);
                transY = toDip(transform.mTranslationY);
            }
            mMatrix.postTranslate(transX,transY);
        }

    }
    protected float toDip(float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value,getResources().getDisplayMetrics());
    }

    @Override
    public void setProps(CommonProps props) {
        if(props != null){
            mProps.set(props);
        }
    }

    @Override
    public void setIsMaskChild(boolean v) { }

    @Override
    public void setPainterKit(PainterKit painter) {
        mPainter = painter;
        if (mLazySetupMask){
            mLazySetupMask= false;
            setupMaskListener();
        }
    }


    @Override
    public void invalidateMaskCallback() {
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).layout(0, 0, getWidth(), getHeight());
        }
    }
    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        if(child instanceof PainterView || child instanceof PainterViewHardware
                || child instanceof MaskGView || child instanceof MaskView){
            throw new IllegalArgumentException("G cannot have MaskG,Painter,Mask.");
        }
    }

}
