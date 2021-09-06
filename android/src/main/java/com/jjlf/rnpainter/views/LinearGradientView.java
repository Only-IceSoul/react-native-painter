package com.jjlf.rnpainter.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
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

public class LinearGradientView extends View implements PaintableInterface {

    PainterKit mPainter;

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
    protected float startX = 0.5f;
    protected float startY = 0f;
    protected float endX = 0.5f;
    protected float endY = 1f;

    protected float mOpacity = 1f;
    protected boolean mOpacityStatus = false;


    public LinearGradientView(Context context){
        super(context);
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
    public void setStartPoint(float v,float v2) {
        if(startX != v || startY != v2){
            startX = v;
            startY = v2;
            invalidate();
        }
    }
    public void setEndPoint(float v,float v2) {
        if(endX != v || endY != v2){
            endX = v;
            endY = v2;
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




    public void setTranslateZ(float v) {
        if(mTranslationZ != v && !mIsMaskChild) {
            mTranslationZ = v;
            setTranslationZ(mTranslationZ);
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

    @SuppressLint("MissingSuperCall")
    @Override
    public void draw(Canvas canvas) {
        if(mPainter != null && w > 0  && h > 0 ) {

            setupRect(mPainter);
            setupShader(mPainter);

            transform(mTransform, mPainter);

            //draw
            int checkpoint = canvas.save();
            canvas.concat(mPainter.matrix);
            try{

                canvas.drawRect(mRect,mPainter.paint);

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

        float x1 = (startX * mRect.width()) + mRect.left;
        float y1 = (startY * mRect.height()) + mRect.top;
        float x2 = (endX * mRect.width()) + mRect.left;
        float y2 = (endY * mRect.height()) + mRect.top;
        p.paint.setShader(new LinearGradient(x1,y1,x2,y2,mColors,mPositions, Shader.TileMode.CLAMP));
    }

    protected void transform(TransformProps transform, PainterKit painter) {
        mPainter.matrix.reset();
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
        if(!mOpacityStatus) mOpacity = props.getOpacity();
    }

    @Override
    public void setIsMaskChild(boolean v) {
        mIsMaskChild = v;
    }

    @Override
    public void setPainterKit(PainterKit painter) {
        mPainter = painter;
    }

    @Override
    public void invalidateMaskCallback() {
    }

    protected float toDip(float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value,getResources().getDisplayMetrics());
    }
}
