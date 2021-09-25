package com.jjlf.rnpainter.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Build;
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

import java.lang.ref.WeakReference;
import java.util.Objects;

public class GViewNone extends ViewGroup implements PaintableInterface  {


    CommonProps mProps = new CommonProps();
    TransformProps mTransform = new TransformProps();

    private PainterKit mPainter;

    public GViewNone(Context context){
        super(context);
        setClipChildren(false);

    }

    //new
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
            super.invalidate();
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

    public void setFill(int v, boolean status) {
        mProps.mFillColorStatus = status;
        if(mProps.mFillColor != v) {
            mProps.mFillColor = v;
            invalidateChild();
        }
    }

    public void setFillRule(String v, boolean status) {
        mProps.mFillRuleStatus = status;
        if(!Objects.equals(mProps.mFillRule, v)) {
            mProps.mFillRule = v;
            invalidateChild();
        }
    }

    public void setFillOpacity(float v, boolean status) {
        mProps.mFillOpacityStatus = status;
        if(mProps.mFillOpacity != v) {
            mProps.mFillOpacity = v;
            invalidateChild();
        }
    }

    public void setStroke(int v, boolean status) {
        mProps.mStrokeColorStatus = status;
        if(mProps.mStrokeColor != v) {
            mProps.mStrokeColor = v;
            invalidateChild();
        }
    }
    public void setStrokeOpacity(float v, boolean status) {
        mProps.mStrokeOpacityStatus = status;
        if(mProps.mStrokeOpacity != v) {
            mProps.mStrokeOpacity = v;
            invalidateChild();
        }
    }
    public void setStrokeWith(float v, boolean status) {
        mProps.mStrokeWidthStatus = status;
        if(mProps.mStrokeWidth != v) {
            mProps.mStrokeWidth = v;
            invalidateChild();
        }

    }

    public void setStrokeCap(String v) {
        if(!Objects.equals(mProps.mStrokeCap, v)) {
            mProps.mStrokeCap = v;
            invalidateChild();
        }
    }

    public void setStrokeJoin(String v) {
        if(!Objects.equals(mProps.mStrokeJoin, v)) {
            mProps.mStrokeJoin = v;
            invalidateChild();
        }
    }

    public void setStrokeMiter(float v, boolean status) {
        mProps.mStrokeMiterStatus = status;
        if(mProps.mStrokeMiter != v) {
            mProps.mStrokeMiter = v;
            invalidateChild();
        }
    }

    public void setStrokeStart(float v, boolean status) {
        mProps.mStrokeStartStatus = status;
        if(mProps.mStrokeStart != v) {
            mProps.mStrokeStart = v;
            invalidateChild();
        }
    }

    public void setStrokeEnd(float v, boolean status) {
        mProps.mStrokeEndStatus = status;
        if(mProps.mStrokeEnd != v){
            mProps.mStrokeEnd = v;
            invalidateChild();
        }


    }

    public void setShadow(int v, boolean status) {
        mProps.mShadowColorStatus = status;
        if(mProps.mShadowColor != v){
            mProps.mShadowColor = v;
            invalidateChild();
        }
    }

    public void setShadowOpacity(float v, boolean status) {
        mProps.mShadowOpacityStatus = status;
        if(mProps.mShadowOpacity != v) {
            mProps.mShadowOpacity = v;
            invalidateChild();
        }
    }

    public void setShadowRadius(float v, boolean status) {
        mProps.mShadowRadiusStatus = status;
        if(mProps.mShadowRadius != v){
            mProps.mShadowRadius = v;
            invalidateChild();
        }
    }

    public void setShadowOffset(float v,boolean status) {
        mProps.mShadowOffsetXStatus = status;
        mProps.mShadowOffsetYStatus = status;
        if(mProps.mShadowOffsetX != v || mProps.mShadowOffsetY != v ){
            mProps.mShadowOffsetX = v;
            mProps.mShadowOffsetY = v;
            invalidateChild();
        }
    }
    public void setShadowOffsetX(float v,boolean status) {
        mProps.mShadowOffsetXStatus = status;
        if(mProps.mShadowOffsetX != v){
            mProps.mShadowOffsetX = v;
            invalidateChild();
        }
    }
    public void setShadowOffsetY(float v,boolean status) {
        mProps.mShadowOffsetYStatus = status;
        if(mProps.mShadowOffsetY != v ){
            mProps.mShadowOffsetY = v;
            invalidateChild();
        }
    }
    public void setShadowPercentageValue(boolean v,boolean status) {
        mProps.mShadowOffsetIsPercentStatus = status;
        if(mProps.mShadowOffsetIsPercent != v ){
            mProps.mShadowOffsetIsPercent = v;
            invalidateChild();
        }
    }

    //MARK: Transform props

    public void setTransX(float v) {
        if(mTransform.mTranslationX != v ){
            mTransform.mTranslationX = v;
            invalidateTransform();
        }
    }
    public void setTransY(float v) {
        if(mTransform.mTranslationY != v ){
            mTransform.mTranslationY = v;
            invalidateTransform();
        }
    }
    public void setTransPercentageValue(boolean v) {
        if(mTransform.mTranslationIsPercent != v ){
            mTransform.mTranslationIsPercent = v;
            invalidateTransform();
        }
    }

    public void setRot(float v) {
        if(mTransform.mRotation != v ){
            mTransform.mRotation = v;
            invalidateTransform();
        }
    }
    public void setRotO(float v) {
        if(mTransform.mRotationOx != v || mTransform.mRotationOy != v ){
            mTransform.mRotationOx = v;
            mTransform.mRotationOy = v;
            invalidateTransform();
        }
    }
    public void setRotOx(float v) {
        if(mTransform.mRotationOx != v ){
            mTransform.mRotationOx = v;
            invalidateTransform();
        }
    }
    public void setRotOy(float v) {
        if(mTransform.mRotationOy != v ){
            mTransform.mRotationOy = v;
            invalidateTransform();
        }
    }
    public void setRotPercentageValue(boolean v) {
        if(mTransform.mRotationIsPercent != v ){
            mTransform.mRotationIsPercent = v;
            invalidateTransform();
        }
    }

    public void setSc(float v){
        if(mTransform.mScaleX != v || mTransform.mScaleY != v){
            mTransform.mScaleX = v;
            mTransform.mScaleY = v;
            invalidateTransform();
        }
    }
    public void setScX(float v) {
        if(mTransform.mScaleX != v ){
            mTransform.mScaleX = v;
            invalidateTransform();
        }
    }

    public void setScY(float v) {
        if(mTransform.mScaleY != v ){
            mTransform.mScaleY = v;
            invalidateTransform();
        }
    }
    public void setScO(float v){
        if(mTransform.mScaleOriginX != v || mTransform.mScaleOriginY != v){
            mTransform.mScaleOriginX = v;
            mTransform.mScaleOriginY = v;
            invalidateTransform();
        }
    }
    public void setScOx(float v) {
        if(mTransform.mScaleOriginX != v ){
            mTransform.mScaleOriginX = v;
            invalidateTransform();
        }
    }
    public void setScOy(float v) {
        if(mTransform.mScaleOriginY != v ){
            mTransform.mScaleOriginY = v;
            invalidateTransform();
        }
    }
    public void setScPercentageValue(boolean v) {
        if(mTransform.mScaleIsPercent != v ){
            mTransform.mScaleIsPercent = v;
            invalidateTransform();
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

        viewBoxTransform();
        transform();

        int checkpoint = canvas.save();
        canvas.concat(mMatrix);
        try{
            canvas.drawColor(Color.TRANSPARENT);
            canvas.saveLayer(0f,0f,getWidth(),getHeight(),null);
            super.dispatchDraw(canvas);
            if(!mProps.getMask().isEmpty()) {
                WeakReference<MaskInterface> maskView = mPainter.maskViews.get(mProps.getMask());
                if(maskView != null && maskView.get() != null) {
                    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1) {
                        mPainter.paintMask.setXfermode(mPainter.dstOut);
                        canvas.saveLayer(0f, 0f, getWidth(), getHeight(), mPainter.paintMask);
                        canvas.drawColor(Color.BLACK);
                        mPainter.paintMask.setXfermode(mPainter.dstOut);
                        int clip = canvas.saveLayer(0f, 0f, getWidth(), getHeight(), mPainter.paintMask);
                        maskView.get().render(canvas);
                        canvas.restoreToCount(clip);
                    } else {
                        mPainter.paintMask.setXfermode(mPainter.dstIn);
                        canvas.saveLayer(0f, 0f, getWidth(), getHeight(), mPainter.paintMask);
                        maskView.get().render(canvas);
                    }
                    canvas.restore();
                }
            }
            canvas.restore();
        } finally {
            canvas.restoreToCount(checkpoint);
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

    private final Matrix mMatrix = new Matrix();
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

    //Painter Props change, full invalidate
    @Override
    public void invalidate() {
        super.invalidate();
        invalidateChild();
    }

    public void invalidateChild() {
        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            if(child instanceof PaintableInterface){
                PaintableInterface c = (PaintableInterface) child;
                c.setProps(mProps);
                c.setPainterKit(mPainter);
            }
            child.invalidate();
        }
    }

    public void invalidateTransform(){
        super.invalidate();
    }


    @Override
    public void invalidateMaskCallback() {
        super.invalidate();
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
    public void setPainterKit(PainterKit painter) {
        mPainter = painter;
        if (mLazySetupMask){
            mLazySetupMask= false;
            setupMaskListener();
        }
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
