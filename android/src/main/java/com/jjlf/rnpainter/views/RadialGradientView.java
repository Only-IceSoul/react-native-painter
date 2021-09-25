package com.jjlf.rnpainter.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
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

public class RadialGradientView  extends View implements PaintableInterface {

    PainterKit mPainter;
    private String mMask ="";
    private String mOldMask = "";
    protected boolean mIsMaskChild = false;
    protected float mTranslationZ = 0f;
    TransformProps mTransform = new TransformProps();
    protected RectF mRect = new RectF();

    private float[] mPositions = null;
    private int[] mColors = {Color.WHITE,Color.BLACK};
    protected float x = 0f;
    protected float y = 0f;
    protected float w = 0f;
    protected float h = 0f;
    protected float cx = 0.5f;
    protected float cy = 0.5f;
    protected float rx = 0.5f;
    protected float ry = 0.5f;

    protected Matrix mMatrix = new Matrix();



    public RadialGradientView(Context context){
        super(context);
        setLayerType(View.LAYER_TYPE_HARDWARE,null);
    }



    public void setY(float v) {
        if(y != v){
            y = v;
            invalidateWithChildMask();
        }
    }
    public void setX(float v) {
        if(x != v){
            x = v;
            invalidateWithChildMask();
        }
    }
    public void setW(float v) {
        if(w != v){
            w = v;
            invalidateWithChildMask();
        }
    }
    public void setH(float v) {
        if(h != v){
            h = v;
            invalidateWithChildMask();
        }
    }
    public void setCx(float v) {
        if(cx != v ){
            cx = v;
            invalidateWithChildMask();
        }
    }
    public void setCy(float v) {
        if(cy != v ){
            cy = v;
            invalidateWithChildMask();
        }
    }
    public void setRx(float v) {
        if(rx != v ){
            rx = v;
            invalidateWithChildMask();
        }
    }
    public void setRy(float v) {
        if(ry != v ){
            ry = v;
            invalidateWithChildMask();
        }
    }

    public void  setColors(int[] c) {
        mColors = c;
        invalidateWithChildMask();
    }
    public void  setPositions(float[] p) {
        mPositions = p;
        invalidateWithChildMask();
    }


    public void setMask(String v) {
        if(!Objects.equals(mMask, v)) {
            mOldMask = mMask;
            mMask = v;
            invalidateMask();
        }
    }

    private boolean mLazySetupMask = false;
    private void setupMaskListener(){
        if(!mOldMask.isEmpty()){
            WeakReference<MaskInterface> m = mPainter.maskViews.get( mOldMask);
            if(m != null && m.get() != null){
                m.get().removeListener(this);
            }
        }
        if(!mMask.isEmpty()){
            WeakReference<MaskInterface> m = mPainter.maskViews.get(mMask);
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

    @SuppressLint("MissingSuperCall")
    @Override
    public void draw(Canvas canvas) {
        if(mPainter != null && w > 0f && h > 0f && rx > 0f && ry > 0f) {

            setupRect();

            viewBoxTransform();

            setupShader();

            transform();

            drawContent(canvas);



        }
    }

    protected void drawContent(Canvas canvas) {
        int checkpoint = canvas.save();
        canvas.concat(mPainter.matrix);
        try{
            canvas.drawRect(mRect,mPainter.paint);
            drawMask(canvas);
        } finally {
            canvas.restoreToCount(checkpoint);
        }
    }

    protected void drawMask(Canvas canvas){
        if(!mMask.isEmpty()){
            WeakReference<MaskInterface> maskView = mPainter.maskViews.get(mMask);
            if(maskView != null && maskView.get() != null){
                if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1){
                    mPainter.paintMask.setXfermode(mPainter.dstOut);
                    canvas.saveLayer(0f,0f,getWidth(),getHeight(),mPainter.paintMask);
                    canvas.drawColor(Color.BLACK);
                    mPainter.paintMask.setXfermode(mPainter.dstOut);
                    int clip = canvas.saveLayer(0f,0f,getWidth(),getHeight(),mPainter.paintMask);
                    maskView.get().render(canvas);
                    canvas.restoreToCount(clip);
                }else{
                    mPainter.paintMask.setXfermode(mPainter.dstIn);
                    canvas.saveLayer(0f,0f,getWidth(),getHeight(),mPainter.paintMask);
                    maskView.get().render(canvas);
                }
                canvas.restore();
            }
        }
    }

    protected void setupRect(){
        float  l = toDip(x);
        float t = toDip(y);
        float r = toDip(x + w);
        float b = toDip(y + h);
        mRect.set(l,t,r,b);
    }

    protected void viewBoxTransform(){
        if (validateViewBox()){
            mPainter.rectPath.set(mPainter.viewBoxDensity);
            SVGViewBox.transform(mPainter.viewBox, mPainter.bounds, mPainter.align, mPainter.aspect, getResources().getDisplayMetrics().density,mPainter.matrix);
            mPainter.matrix.mapRect(mRect);
            mPainter.matrix.mapRect(mPainter.rectPath);
        }else{
            mPainter.rectPath.set(mPainter.bounds);
        }
    }

    protected void setupShader(){
        mPainter.paint.reset();
        mPainter.paint.setStyle(Paint.Style.FILL);

        float x = (cx * mRect.width()) + mRect.left;
        float y = (cy * mRect.height()) + mRect.top;
        float radius = rx * mRect.width();
        double ratio = ry / rx;
        RadialGradient rg =  new RadialGradient(x,y, radius,mColors,mPositions,Shader.TileMode.CLAMP);
        mMatrix.reset();
        mMatrix.postScale(1f,(float) ratio,x, y);
        rg.setLocalMatrix(mMatrix);
        mPainter.paint.setShader(rg);
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
    @Override
    public void invalidateMaskCallback() {
        invalidateWithChildMask();
    }
    
   
    
    @Override
    public void setProps(CommonProps props) { }

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

   

    protected boolean validateViewBox(){
        return mPainter.viewBox.width() >= 0f && mPainter.viewBox.height() >= 0f;
    }
    protected float toDip(float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value,getResources().getDisplayMetrics());
    }
}
