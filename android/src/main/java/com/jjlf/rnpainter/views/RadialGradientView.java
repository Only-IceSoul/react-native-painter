package com.jjlf.rnpainter.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
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
import com.jjlf.rnpainter.utils.TransformProps;

import java.lang.ref.WeakReference;
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

    protected float mOpacity = 1f;
    protected boolean mOpacityStatus = false;


    public RadialGradientView(Context context){
        super(context);
        setLayerType(View.LAYER_TYPE_HARDWARE,null);
    }

    public void setOpacity(float op,boolean status){
        mOpacityStatus = status;
        if(mOpacity != op){
            mOpacity = op;
            setAlpha(mOpacity);
            if(mIsMaskChild) invalidate();
        }
    }

    public void setY(float v) {
        if(y != v){
            y = v;
            invalidate();
        }
    }
    public void setX(float v) {
        if(x != v){
            x = v;
            invalidate();
        }
    }
    public void setW(float v) {
        if(w != v){
            w = v;
            invalidate();
        }
    }
    public void setH(float v) {
        if(h != v){
            h = v;
            invalidate();
        }
    }
    public void setCx(float v) {
        if(cx != v ){
            cx = v;
            invalidate();
        }
    }
    public void setCy(float v) {
        if(cy != v ){
            cy = v;
            invalidate();
        }
    }
    public void setRx(float v) {
        if(rx != v ){
            rx = v;
            invalidate();
        }
    }
    public void setRy(float v) {
        if(ry != v ){
            ry = v;
            invalidate();
        }
    }

    public void  setColors(int[] c) {
        mColors = c;
        invalidate();
    }
    public void  setPositions(float[] p) {
        mPositions = p;
        invalidate();
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
            invalidate();
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

    @SuppressLint("MissingSuperCall")
    @Override
    public void draw(Canvas canvas) {
        if(mPainter != null && w > 0f && h > 0f && rx > 0f && ry > 0f) {

            setupRect(mPainter);
            setupShader(mPainter);

            transform(mTransform, mPainter);

            //draw
            int checkpoint = canvas.save();
            canvas.concat(mPainter.matrix);
            try{
                if(mMask.isEmpty()){
                    canvas.drawRect(mRect,mPainter.paint);
                }else{
                    WeakReference<MaskInterface> maskView = mPainter.maskViews.get(mMask);
                    if(maskView != null && maskView.get() != null){
                        canvas.drawRect(mRect,mPainter.paint);
                        mPainter.paintMask.setXfermode(mPainter.dstIn);
                        canvas.saveLayer(0f,0f,getWidth(),getHeight(),mPainter.paintMask);
                        maskView.get().render(canvas);
                        canvas.restore();


                        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.M && canvas.isHardwareAccelerated()){
                            mPainter.paintMask.setXfermode(mPainter.dstOut);
                            int main = canvas.saveLayer(0f,0f,getWidth(),getHeight(),mPainter.paintMask);
                            canvas.drawColor(Color.BLACK);
                            mPainter.paintMask.setXfermode(mPainter.dstOut);
                            int clip = canvas.saveLayer(0f,0f,getWidth(),getHeight(),mPainter.paintMask);
                            maskView.get().render(canvas);
                            canvas.restoreToCount(clip);
                            canvas.restoreToCount(main);
                        }

                    }else{
                        canvas.drawRect(mRect,mPainter.paint);
                    }
                }
            } finally {
                canvas.restoreToCount(checkpoint);
            }


        }
    }

    protected void setupRect(PainterKit p){
        float l;
        float t;
        float r;
        float b;

        if(p.isViewBoxEnabled){
            l = ModUtil.viewBoxToWidth(x, p.viewBox, p.bounds.width());
            t = ModUtil.viewBoxToHeight(y, p.viewBox, p.bounds.height());
            r = ModUtil.viewBoxToWidth(x + w, p.viewBox, p.bounds.width());
            b = ModUtil.viewBoxToHeight(y + h, p.viewBox, p.bounds.height());
        }else{
            l = toDip(x);
            t = toDip(y);
            r = toDip(x + w);
            b = toDip(y + h);
        }
        mRect.set(l,t,r,b);
    }

    protected void setupShader(PainterKit p){
        p.paint.reset();
        p.paint.setStyle(Paint.Style.FILL);

        float x = (cx * mRect.width()) + mRect.left;
        float y = (cy * mRect.height()) + mRect.top;
        float radius = rx * mRect.width();
        double ratio = ry / rx;
        RadialGradient rg =  new RadialGradient(x,y, radius,mColors,mPositions,Shader.TileMode.CLAMP);
        mMatrix.reset();
        mMatrix.postScale(1f,(float) ratio,x, y);
        rg.setLocalMatrix(mMatrix);
        p.paint.setShader(rg);
    }

    protected void transform(TransformProps transform, PainterKit painter) {
        mPainter.matrix.reset();
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
            mPainter.matrix.postRotate(transform.mRotation,rotX,rotY);
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
            mPainter.matrix.postScale(transform.mScaleX,transform.mScaleY,oX,oY);


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
            mPainter.matrix.postTranslate(transX,transY);
        }

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
            }else if(getParent() instanceof MaskGView){
                for (PaintableInterface c :  ((MaskInterface) ((MaskGView)getParent()).getParent()).getListeners()){
                    c.invalidateMaskCallback();
                }
            }else{
                Log.e("Painter","PaintableView invalidating mask failed ");
            }
        }

    }

    public void invalidateTransform(){
        if(!mIsMaskChild) {
            super.invalidate();
        } else {
            if (getParent() instanceof MaskInterface) {
                for (PaintableInterface c : ((MaskInterface) getParent()).getListeners()) {
                    c.invalidateMaskCallback();
                }
            }else if (getParent() instanceof MaskGView) {
                for (PaintableInterface c : ((MaskInterface) ((MaskGView) getParent()).getParent()).getListeners()) {
                    c.invalidateMaskCallback();
                }
            }else{
                Log.e("Painter","PaintableView invalidating mask failed ");
            }
        }
    }

    public void invalidateReactTransform(){
        if(mIsMaskChild) {
            //transform react style invalidate
            super.invalidate();
            if (getParent() instanceof MaskInterface) {
                for (PaintableInterface c : ((MaskInterface) getParent()).getListeners()) {
                    c.invalidateMaskCallback();
                }
            }else if (getParent() instanceof MaskGView) {
                for (PaintableInterface c : ((MaskInterface) ((MaskGView) getParent()).getParent()).getListeners()) {
                    c.invalidateMaskCallback();
                }
            }else{
                Log.e("Painter","PaintableView invalidating mask failed ");
            }
        }
    }

    @Override
    public void setProps(CommonProps props) {
        if(!mOpacityStatus) mOpacity = props.mOpacity;
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

    @Override
    public void invalidateMaskCallback() {
        invalidate();
    }

    protected float toDip(float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value,getResources().getDisplayMetrics());
    }
}
