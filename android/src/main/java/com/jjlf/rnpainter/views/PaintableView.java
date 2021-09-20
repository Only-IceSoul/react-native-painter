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
import java.util.Objects;

public class PaintableView extends View implements PaintableInterface {


    public PaintableView(Context context){
        super(context);
        setLayerType(View.LAYER_TYPE_HARDWARE,null);

    }

    CommonProps mProps = new CommonProps();
    TransformProps mTransform = new TransformProps();

    protected final PathMeasure mPathMeasure = new PathMeasure();
    protected PainterKit mPainter;


    protected boolean mIgnoreVbTransform = false;

    protected boolean mIgnoreFill = false;
    protected boolean mIgnoreStroke = false;
    protected boolean mIgnoreShadowFill = false;
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
            invalidate();
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
        if(mPainter != null) {
            setupPath(mPainter);
            if (!mIgnoreVbTransform && mPainter.viewBox.width() > 0f && mPainter.viewBox.height() > 0f) transformToViewBox(mPainter);
            if (fill()) {
                setupPaintFill(mPainter);
                if (!mIgnoreShadowFill) setupShadowFill(mPainter);
            }

            if (stroke()){
                setupPaintStroke(mPainter);
                setupPathStroke(mPainter);
                if (!fill()) setupShadowStroke(mPainter);
            }

            transform(mTransform, mPainter);

            //draw
            int checkpoint = canvas.save();
            canvas.concat(mPainter.matrix);
            try{

                drawPaths(canvas);
                if(!mProps.mMask.isEmpty()){
                    WeakReference<MaskInterface> maskView = mPainter.maskViews.get(mProps.mMask);
                    if(maskView != null && maskView.get() != null){
                        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1 && canvas.isHardwareAccelerated()){
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
            } finally {
                canvas.restoreToCount(checkpoint);
            }

        }
    }


    protected void drawPaths(Canvas canvas){

        if(fill()) { canvas.drawPath(mPainter.path,mPainter.paint); }
        if (stroke()) canvas.drawPath(mPainter.path2,mPainter.paint2);


    }

    protected boolean stroke(){
        return !mIgnoreStroke &&  mProps.getStrokeColor() != Color.TRANSPARENT && mProps.getStrokeWidth() > 0f;
    }
    protected boolean fill(){
        return !mIgnoreFill && mProps.getFillColor() != Color.TRANSPARENT;
    }


    protected void setupPath(PainterKit p) {

    }

    protected void setupPaintFill(PainterKit p) {
        p.paint.reset();
        p.paint.setAntiAlias(true);
        p.paint.setStyle(Paint.Style.FILL);
        p.paint.setColor(mProps.getFillColor());

        float opacity = mProps.getFillOpacity() * mProps.getOpacity();
        p.paint.setAlpha((int) (opacity * 255f));


    }

    protected void setupPaintStroke(PainterKit p) {
        p.paint2.reset();
        p.paint2.setStyle(Paint.Style.STROKE);
        p.paint2.setAntiAlias(true);

        float sw ;
        if (p.isViewBoxEnabled) {
            float size = p.viewBox.width() > p.viewBox.height() ? p.bounds.width() : p.bounds.height();
            sw =  (mProps.getStrokeWidth() / Math.max( p.viewBox.width(), p.viewBox.height() )) * size;
        }else{
            sw = toDip(mProps.getStrokeWidth());
        }
        p.paint2.setStrokeWidth(sw);
        p.paint2.setColor(mProps.getStrokeColor());

        float opacity = mProps.getStrokeOpacity() * mProps.getOpacity();
        p.paint2.setAlpha((int)(opacity * 255f));

        p.paint2.setStrokeCap(mProps.getStrokeCap());
        p.paint2.setStrokeMiter(mProps.getStrokeMiter());
        p.paint2.setStrokeJoin(mProps.getStrokeJoin());
    }

    protected void setupPathStroke(PainterKit painter){
        painter.path2.reset();
        if(mProps.getStrokeStart() != 0f || mProps.getStrokeEnd() != 1f) {
            mPathMeasure.setPath(painter.path, false);
            mPathMeasure.getSegment((mPathMeasure.getLength() * mProps.getStrokeStart()), (mPathMeasure.getLength() * mProps.getStrokeEnd()), painter.path2, true);
            painter.path2.rLineTo(0f, 0f);
        }else{
            painter.path2.set(painter.path);
        }
    }

    protected void setupShadowFill(PainterKit p) {
        if (mProps.getShadowOpacity() > 0f) {
            final int alpha = Color.alpha(mProps.getShadowColor());
            final int red = Color.red(mProps.getShadowColor());
            final int green = Color.green(mProps.getShadowColor());
            final int blue = Color.blue(mProps.getShadowColor());
            final int c = Color.argb((int) (mProps.getShadowOpacity() * alpha), red, green, blue);

            float ox;
            float oy;
            if (mProps.getShadowOffsetIsPercent()) {
                ox = mProps.getShadowOffsetX() * p.bounds.width();
                oy = mProps.getShadowOffsetY() * p.bounds.height();
            } else if (p.isViewBoxEnabled) {
                ox = (mProps.getShadowOffsetX() / p.viewBox.width()) * p.bounds.width();
                oy = (mProps.getShadowOffsetY() / p.viewBox.height()) * p.bounds.height();
            } else {
                ox = toDip(mProps.getShadowOffsetX());
                oy = toDip(mProps.getShadowOffsetY());
            }

            float radius;
            if (p.isViewBoxEnabled) {
                float size = p.viewBox.width() > p.viewBox.height() ? p.bounds.width() : p.bounds.height();
                radius =  (mProps.getShadowRadius() / Math.max( p.viewBox.width(), p.viewBox.height() )) * size;
            }else{
                radius = toDip(mProps.getShadowRadius());
            }
            p.paint.setShadowLayer(radius, ox, oy, c);

        } else {
            p.paint.clearShadowLayer();
        }
    }
    protected void setupShadowStroke(PainterKit p) {
        if (mProps.getShadowOpacity() > 0f) {
            final int alpha = Color.alpha(mProps.getShadowColor());
            final int red = Color.red(mProps.getShadowColor());
            final int green = Color.green(mProps.getShadowColor());
            final int blue = Color.blue(mProps.getShadowColor());
            final int c = Color.argb((int) (mProps.getShadowOpacity() * alpha), red, green, blue);

            float ox;
            float oy;
            if (mProps.getShadowOffsetIsPercent()) {
                ox = mProps.getShadowOffsetX() * p.bounds.width();
                oy = mProps.getShadowOffsetY() * p.bounds.height();
            } else if (p.isViewBoxEnabled) {
                ox = (mProps.getShadowOffsetX() / p.viewBox.width()) * p.bounds.width();
                oy = (mProps.getShadowOffsetY() / p.viewBox.height()) * p.bounds.height();
            } else {
                ox = toDip(mProps.getShadowOffsetX());
                oy = toDip(mProps.getShadowOffsetY());
            }

            float radius;
            if (p.isViewBoxEnabled) {
                float size = p.viewBox.width() > p.viewBox.height() ? p.bounds.width() : p.bounds.height();
                radius =  (mProps.getShadowRadius() / Math.max( p.viewBox.width(), p.viewBox.height() )) * size;
            }else{
                radius = toDip(mProps.getShadowRadius());
            }
            p.paint2.setShadowLayer(radius, ox, oy, c);

        } else {
            p.paint2.clearShadowLayer();
        }
    }


    protected void transformToViewBox(PainterKit painter) {
        SVGViewBox.transform(painter.viewBox, painter.bounds, painter.align, painter.aspect, painter.matrix, getResources().getDisplayMetrics().density);
        painter.path.transform(painter.matrix);
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



    protected float toDip(float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value,getResources().getDisplayMetrics());
    }
    @Override
    public void invalidateMaskCallback() {
        invalidate();
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


    @Override
    public void invalidate() {
        if(!mIsMaskChild) {
            super.invalidate();
        } else{
            invalidateMaskListeners();
        }

    }

    public void invalidateTransform(){
        if(!mIsMaskChild) {
            super.invalidate();
        } else {
            invalidateMaskListeners();
        }
    }

    public void invalidateMaskListeners(){
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

    public void invalidateReactTransform(){
        if(mIsMaskChild) {
            //transform react style invalidate
            super.invalidate();
            invalidateMaskListeners();
        }
    }
}