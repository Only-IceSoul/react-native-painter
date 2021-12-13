package com.jjlf.rnpainter.views;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PathMeasure;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

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
//        setLayerType(View.LAYER_TYPE_HARDWARE,null);

    }

    CommonProps mProps = new CommonProps();
    TransformProps mTransform = new TransformProps();

    protected final PathMeasure mPathMeasure = new PathMeasure();
    protected PainterKit mPainter;

    protected boolean mIgnoreFill = false;
    protected boolean mIgnoreStroke = false;
    protected boolean mIsMaskChild = false;


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
            invalidateWithChildMask();
        }else{
            mLazySetupMask = true;
        }
    }

    protected float mTranslationZ = 0f;
    public void setTranslateZ(float v) {
        if(mTranslationZ != v && !mIsMaskChild) {
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
            invalidateWithChildMask();
        }
    }

    public void setFillRule(String v, boolean status) {
        mProps.mFillRuleStatus = status;
        if(!Objects.equals(mProps.mFillRule, v)) {
            mProps.mFillRule = v;
            invalidateWithChildMask();
        }
    }

    public void setFillOpacity(float v, boolean status) {
        mProps.mFillOpacityStatus = status;
        if(mProps.mFillOpacity != v) {
            mProps.mFillOpacity = v;
            invalidateWithChildMask();
        }
    }

    public void setStroke(int v, boolean status) {
        mProps.mStrokeColorStatus = status;
        if(mProps.mStrokeColor != v) {
            mProps.mStrokeColor = v;
            invalidateWithChildMask();
        }
    }

    public void setStrokeOpacity(float v, boolean status) {
        mProps.mStrokeOpacityStatus = status;
        if(mProps.mStrokeOpacity != v) {
            mProps.mStrokeOpacity = v;
            invalidateWithChildMask();
        }
    }

    public void setStrokeWith(float v, boolean status) {
        mProps.mStrokeWidthStatus = status;
        if(mProps.mStrokeWidth != v) {
            mProps.mStrokeWidth = v;
            invalidateWithChildMask();
        }

    }

    public void setStrokeCap(String v) {
        if(!Objects.equals(mProps.mStrokeCap, v)) {
            mProps.mStrokeCap = v;
            invalidateWithChildMask();
        }
    }

    public void setStrokeJoin(String v) {
        if(!Objects.equals(mProps.mStrokeJoin, v)) {
            mProps.mStrokeJoin = v;
            invalidateWithChildMask();
        }
    }

    public void setStrokeMiter(float v, boolean status) {
        mProps.mStrokeMiterStatus = status;
        if(mProps.mStrokeMiter != v) {
            mProps.mStrokeMiter = v;
            invalidateWithChildMask();
        }
    }

    public void setStrokeStart(float v, boolean status) {
        mProps.mStrokeStartStatus = status;
        if(mProps.mStrokeStart != v) {
            mProps.mStrokeStart = v;
            invalidateWithChildMask();
        }
    }

    public void setStrokeEnd(float v, boolean status) {
        mProps.mStrokeEndStatus = status;
        if(mProps.mStrokeEnd != v){
            mProps.mStrokeEnd = v;
            invalidateWithChildMask();
        }
    }

    public void setShadow(int v, boolean status) {
        mProps.mShadowColorStatus = status;
        if(mProps.mShadowColor != v){
            mProps.mShadowColor = v;
            invalidateWithChildMask();
        }
    }

    public void setShadowOpacity(float v, boolean status) {
        mProps.mShadowOpacityStatus = status;
        if(mProps.mShadowOpacity != v) {
            mProps.mShadowOpacity = v;
            invalidateWithChildMask();
        }
    }

    public void setShadowRadius(float v, boolean status) {
        mProps.mShadowRadiusStatus = status;
        if(mProps.mShadowRadius != v){
            mProps.mShadowRadius = v;
            invalidateWithChildMask();
        }
    }

    public void setShadowOffset(float v,boolean status) {
        mProps.mShadowOffsetXStatus = status;
        mProps.mShadowOffsetYStatus = status;
        if(mProps.mShadowOffsetX != v || mProps.mShadowOffsetY != v ){
            mProps.mShadowOffsetX = v;
            mProps.mShadowOffsetY = v;
            invalidateWithChildMask();
        }
    }
    public void setShadowOffsetX(float v,boolean status) {
        mProps.mShadowOffsetXStatus = status;
        if(mProps.mShadowOffsetX != v){
            mProps.mShadowOffsetX = v;
            invalidateWithChildMask();
        }
    }
    public void setShadowOffsetY(float v,boolean status) {
        mProps.mShadowOffsetYStatus = status;
        if(mProps.mShadowOffsetY != v ){
            mProps.mShadowOffsetY = v;
            invalidateWithChildMask();
        }
    }
    public void setShadowPercentageValue(boolean v,boolean status) {
        mProps.mShadowOffsetIsPercentStatus = status;
        if(mProps.mShadowOffsetIsPercent != v ){
            mProps.mShadowOffsetIsPercent = v;
            invalidateWithChildMask();
        }
    }

    //MARK: Transform props

    public void setTransX(float v) {
        if(mTransform.mTranslationX != v ){
            mTransform.mTranslationX = v;
            invalidateWithChildMask();
        }
    }
    public void setTransY(float v) {
        if(mTransform.mTranslationY != v ){
            mTransform.mTranslationY = v;
            invalidateWithChildMask();
        }
    }
    public void setTransPercentageValue(boolean v) {
        if(mTransform.mTranslationIsPercent != v ){
            mTransform.mTranslationIsPercent = v;
            invalidateWithChildMask();
        }
    }

    public void setRot(float v) {
        if(mTransform.mRotation != v ){
            mTransform.mRotation = v;
            invalidateWithChildMask();
        }
    }
    public void setRotO(float v) {
        if(mTransform.mRotationOx != v || mTransform.mRotationOy != v ){
            mTransform.mRotationOx = v;
            mTransform.mRotationOy = v;
            invalidateWithChildMask();
        }
    }
    public void setRotOx(float v) {
        if(mTransform.mRotationOx != v ){
            mTransform.mRotationOx = v;
            invalidateWithChildMask();
        }
    }
    public void setRotOy(float v) {
        if(mTransform.mRotationOy != v ){
            mTransform.mRotationOy = v;
            invalidateWithChildMask();
        }
    }
    public void setRotPercentageValue(boolean v) {
        if(mTransform.mRotationIsPercent != v ){
            mTransform.mRotationIsPercent = v;
            invalidateWithChildMask();
        }
    }

    public void setSc(float v){
        if(mTransform.mScaleX != v || mTransform.mScaleY != v){
            mTransform.mScaleX = v;
            mTransform.mScaleY = v;
            invalidateWithChildMask();
        }
    }
    public void setScX(float v) {
        if(mTransform.mScaleX != v ){
            mTransform.mScaleX = v;
            invalidateWithChildMask();
        }
    }

    public void setScY(float v) {
        if(mTransform.mScaleY != v ){
            mTransform.mScaleY = v;
            invalidateWithChildMask();
        }
    }
    public void setScO(float v){
        if(mTransform.mScaleOriginX != v || mTransform.mScaleOriginY != v){
            mTransform.mScaleOriginX = v;
            mTransform.mScaleOriginY = v;
            invalidateWithChildMask();
        }
    }
    public void setScOx(float v) {
        if(mTransform.mScaleOriginX != v ){
            mTransform.mScaleOriginX = v;
            invalidateWithChildMask();
        }
    }
    public void setScOy(float v) {
        if(mTransform.mScaleOriginY != v ){
            mTransform.mScaleOriginY = v;
            invalidateWithChildMask();
        }
    }
    public void setScPercentageValue(boolean v) {
        if(mTransform.mScaleIsPercent != v ){
            mTransform.mScaleIsPercent = v;
            invalidateWithChildMask();
        }
    }



    @SuppressLint({"MissingSuperCall", "WrongCall"})
    @Override
    public void draw(Canvas canvas) {
        if(mPainter != null) {

            setupPath(mPainter);

            viewBoxTransform();

            props();

            transform();

            drawContent(canvas);

        }
    }


    protected void drawContent(Canvas canvas) {
        int checkpoint = canvas.save();
        canvas.concat(mPainter.matrix);
        try{
            drawPath(canvas);
            drawMask(canvas);
        } finally {
            canvas.restoreToCount(checkpoint);
        }
    }

    protected void drawPath(Canvas canvas){
        if(fill()) { canvas.drawPath(mPainter.path,mPainter.paint); }
        if (stroke()) canvas.drawPath(mPainter.path2,mPainter.paint2);
    }

    protected void drawMask(Canvas canvas){
        if(!mProps.mMask.isEmpty()){
            WeakReference<MaskInterface> maskView = mPainter.maskViews.get(mProps.mMask);
            if(maskView != null && maskView.get() != null){
                if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1){
                    mPainter.paintMask.setXfermode(mPainter.dstOut);
                    canvas.saveLayer(0f,0f,getWidth(),getHeight(),mPainter.paintMask);
                    canvas.drawColor(Color.BLACK);
                    mPainter.paintMask.setXfermode(mPainter.dstOut);
                    canvas.saveLayer(0f,0f,getWidth(),getHeight(),mPainter.paintMask);
                    maskView.get().render(canvas);
                    canvas.restore();
                }else{
                    mPainter.paintMask.setXfermode(mPainter.dstIn);
                    canvas.saveLayer(0f,0f,getWidth(),getHeight(),mPainter.paintMask);
                    maskView.get().render(canvas);
                }
                canvas.restore();
            }
        }
    }

    protected void viewBoxTransform(){
        if (validateViewBox()){
            mPainter.rectPath.set(mPainter.viewBoxDensity);
            SVGViewBox.transform(mPainter.viewBox, mPainter.bounds, mPainter.align, mPainter.aspect, getResources().getDisplayMetrics().density,mPainter.matrix);
            mPainter.path.transform(mPainter.matrix);
            mPainter.matrix.mapRect(mPainter.rectPath);
        }else{
            mPainter.rectPath.set(mPainter.bounds);
        }
    }

    protected void props(){

        mPainter.path.setFillType(mProps.getFillRule());

        if (fill()) {
            setupPaintFill();
            setupShadow(false);
        }

        if (stroke()){
            setupPaintStroke();
            setupPathStroke();
            if (!fill()) setupShadow(true);
        }

    }

    protected void setupPaintFill() {
        mPainter.paint.reset();
        mPainter.paint.setAntiAlias(true);
        mPainter.paint.setStyle(Paint.Style.FILL);
        mPainter.paint.setColor(mProps.getFillColor());
        mPainter.paint.setAlpha((int) (mProps.getFillOpacity() * 255f));
    }

    protected void setupPaintStroke() {
        mPainter.paint2.reset();
        mPainter.paint2.setStyle(Paint.Style.STROKE);
        mPainter.paint2.setAntiAlias(true);

        float sw =  validateViewBox() ?  ModUtil.viewBoxToMax(mProps.getStrokeWidth(),mPainter.viewBox,mPainter.rectPath.width(),mPainter.rectPath.height()) : toDip(mProps.getStrokeWidth());
        mPainter.paint2.setStrokeWidth(sw);
        mPainter.paint2.setColor(mProps.getStrokeColor());

        mPainter.paint2.setAlpha((int)(mProps.getStrokeOpacity() * 255f));

        mPainter.paint2.setStrokeCap(mProps.getStrokeCap());
        mPainter.paint2.setStrokeMiter(mProps.getStrokeMiter());
        mPainter.paint2.setStrokeJoin(mProps.getStrokeJoin());
    }

    protected void setupPathStroke(){
        mPainter.path2.reset();
        if(mProps.getStrokeStart() != 0f || mProps.getStrokeEnd() != 1f) {
            mPathMeasure.setPath(mPainter.path, false);
            mPathMeasure.getSegment((mPathMeasure.getLength() * mProps.getStrokeStart()), (mPathMeasure.getLength() * mProps.getStrokeEnd()), mPainter.path2, true);
            mPainter.path2.rLineTo(0f, 0f);
        }else{
            mPainter.path2.set(mPainter.path);
        }
    }


    protected void setupShadow(boolean stroke) {
        mPainter.paint.clearShadowLayer();
        mPainter.paint2.clearShadowLayer();
        Paint paint = stroke ?  mPainter.paint2 :  mPainter.paint;
        if (mProps.getShadowColor() != Color.TRANSPARENT) {

            float ox;
            float oy;
            if (mProps.getShadowOffsetIsPercent()) {
                ox = mProps.getShadowOffsetX() * getWidth();
                oy = mProps.getShadowOffsetY() * getHeight();
            } else if (validateViewBox()) {
                ox = (mProps.getShadowOffsetX() / mPainter.viewBox.width()) * mPainter.rectPath.width();
                oy = (mProps.getShadowOffsetY() / mPainter.viewBox.height()) * mPainter.rectPath.height();
            } else {
                ox = toDip(mProps.getShadowOffsetX());
                oy = toDip(mProps.getShadowOffsetY());
            }

            float radius = validateViewBox() ?  ModUtil.viewBoxToMax(mProps.getShadowRadius(),mPainter.viewBox,mPainter.rectPath.width(),mPainter.rectPath.height()) : toDip(mProps.getShadowRadius());
            paint.setShadowLayer(radius, ox, oy, mProps.getShadowColor());

        }
    }



    protected void transform() {
        mPainter.matrix.reset();
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
            mPainter.matrix.postRotate(mTransform.mRotation,rotX,rotY);
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

            mPainter.matrix.postScale(mTransform.mScaleX,mTransform.mScaleY,oX,oY);
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
            mPainter.matrix.postTranslate(transX,transY);
        }

    }


    protected boolean stroke(){
        return !mIgnoreStroke &&  mProps.getStrokeColor() != Color.TRANSPARENT && mProps.getStrokeWidth() > 0f;
    }
    protected boolean fill(){
        return !mIgnoreFill && mProps.getFillColor() != Color.TRANSPARENT;
    }
    protected boolean validateViewBox(){
        return mPainter.viewBox.width() >= 0f && mPainter.viewBox.height() >= 0f;
    }


    protected void setupPath(PainterKit p) {

    }

    protected float toDip(float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value,getResources().getDisplayMetrics());
    }
    @Override
    public void invalidateMaskCallback() {
        invalidateWithChildMask();
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
    public void setPainterKit(PainterKit painter) {
        mPainter = painter;
        if (mLazySetupMask){
            mLazySetupMask= false;
            setupMaskListener();
        }
    }




    public void invalidateWithChildMask(){
        invalidate();
        if(mIsMaskChild) {
            invalidateMaskListeners();
        }
    }
    

    public void invalidateMaskListeners(){
        if (getParent() instanceof MaskInterface) {
            ArrayList<PaintableInterface> listener = ((MaskInterface) getParent()).getListeners();
            if(listener != null){
                for (PaintableInterface c : listener ) {
                    c.invalidateMaskCallback();
                }
            }
        }else if (getParent() instanceof MaskGView) {
            ArrayList<PaintableInterface> listener = ((MaskInterface) ((MaskGView) getParent()).getParent()).getListeners();
            if(listener != null){
                for (PaintableInterface c : listener) {
                    c.invalidateMaskCallback();
                }
            }

        }else{
            Log.e("Painter","PaintableView invalidating mask failed ");
        }
    }

    public void invalidateReactTransform(){
        if(mIsMaskChild) {
            //transform react style invalidate
            invalidate();
            invalidateMaskListeners();
        }
    }
}