package com.jjlf.rnpainter.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.react.views.imagehelper.ImageSource;
import com.jjlf.rnpainter.utils.CommonProps;
import com.jjlf.rnpainter.utils.MaskInterface;
import com.jjlf.rnpainter.utils.ModUtil;
import com.jjlf.rnpainter.utils.PaintableInterface;
import com.jjlf.rnpainter.utils.PainterKit;
import com.jjlf.rnpainter.utils.SVGViewBox;
import com.jjlf.rnpainter.utils.TransformProps;

import java.lang.ref.WeakReference;
import java.util.Objects;

public class ImageView extends View implements PaintableInterface {

    PainterKit mPainter;
    private String mMask ="";
    private String mOldMask = "";
    protected boolean mIsMaskChild = false;
    protected float mTranslationZ = 0f;
    TransformProps mTransform = new TransformProps();
    protected RectF mRect = new RectF();

    protected float x = 0f;
    protected float y = 0f;
    protected float w = 0f;
    protected float h = 0f;
    protected String source = "";
    protected String align = "xMidYMid";
    protected int aspect = SVGViewBox.MOS_MEET;
    private int bgColor = Color.TRANSPARENT;
    private boolean clipToBounds = false;

    protected float mOpacity = 1f;
    protected boolean mOpacityStatus = false;

    private final RectF mImageRect = new RectF();
    private Bitmap mBitmapImage ;


    public ImageView(Context context){
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
    public void setSrc(String v){
        source = v;
        handleSource();
    }

    public void setAlign(String v){
        if(!align.equals(v)){
            align = v;
            invalidate();
        }
    }
    public void setAspect(int v){
        if(aspect !=  v){
            aspect = v;
            invalidate();
        }
    }
    public void setBgColor(int v){
        if(bgColor !=  v){
            bgColor = v;
            invalidate();
        }
    }
    public void setClipToBounds(boolean v){
        if(clipToBounds !=  v){
            clipToBounds = v;
            invalidate();
        }
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
            setupPaintBg(mPainter);

            transform(mTransform, mPainter);

            //draw
            int checkpoint = canvas.save();
            canvas.concat(mPainter.matrix);
            try{
                if(mMask.isEmpty()){
                    drawImage(canvas);
                }else{
                    WeakReference<MaskInterface> maskView = mPainter.maskViews.get(mMask);
                    if(maskView != null && maskView.get() != null){
                        drawImage(canvas);
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

    private void handleSource(){
        if(source.isEmpty()){
            mBitmapImage =  null;
            invalidate();
        }else if(source.contains("base64,")) {
             base64String();
        }else if(source.contains("static;")){
            staticImage();
        }else{
            netWork(source);
        }
    }

    private void staticImage(){
        String s = source.split("c;")[1];
        if(s.contains("http")) {
            netWork(s);
        }else{
            int id = getContext().getResources().getIdentifier(s,"drawable", getContext().getPackageName());
            Drawable d = ContextCompat.getDrawable(getContext(),id);
            if (d == null){
                mBitmapImage = null;
                invalidate();
                return;
            }
            if(d.getCurrent() instanceof BitmapDrawable){
                mBitmapImage = ((BitmapDrawable) d.getCurrent()).getBitmap();
                invalidate();
            }
        }
    }

    private void base64String(){
        String s = source.split("4,")[1];
        byte[] bytes = android.util.Base64.decode(s,android.util.Base64.DEFAULT);
         mBitmapImage = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
         invalidate();
    }

    private void netWork(String uri){
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        ImageSource imageSource = new ImageSource(getContext(), uri);
        ImageRequest request = ImageRequest.fromUri(imageSource.getUri());
        boolean inMemoryCache = imagePipeline.isInBitmapMemoryCache(request);

        if (inMemoryCache) {
            final DataSource<CloseableReference<CloseableImage>> dataSource
                    = imagePipeline.fetchImageFromBitmapCache(request, getContext());

            try {
                final CloseableReference<CloseableImage> imageReference = dataSource.getResult();
                if (imageReference == null) {
                    mBitmapImage = null;
                    invalidate();
                    return;
                }

                try {
                    CloseableImage closeableImage = imageReference.get();
                    if (!(closeableImage instanceof CloseableBitmap)) {
                        mBitmapImage = null;
                        invalidate();
                        return;
                    }

                    CloseableBitmap closeableBitmap = (CloseableBitmap) closeableImage;
                    mBitmapImage = closeableBitmap.getUnderlyingBitmap();
                    invalidate();


                } catch (Exception e) {
                    throw new IllegalStateException(e);
                } finally {
                    CloseableReference.closeSafely(imageReference);
                }

            } catch (Exception e) {
                throw new IllegalStateException(e);
            } finally {
                dataSource.close();
            }
        } else {
            final DataSource<CloseableReference<CloseableImage>> dataSource
                    = imagePipeline.fetchDecodedImage(request, getContext());

            BaseBitmapDataSubscriber subscriber = new BaseBitmapDataSubscriber() {
                @Override
                public void onNewResultImpl(Bitmap bitmap) {
                    mBitmapImage = bitmap;
                    invalidate();
                }
                @Override
                public void onFailureImpl(DataSource dataSource) {
                    mBitmapImage = null;
                    invalidate();
                }
            };
            dataSource.subscribe(subscriber, UiThreadImmediateExecutorService.getInstance());
        }
    }

    private void drawImage(Canvas canvas ){
        //bg
        if(bgColor != Color.TRANSPARENT) canvas.drawRect(mRect,mPainter.paint);

        //image
        if(mBitmapImage != null){
            mImageRect.set(0f,0f,mBitmapImage.getWidth(),mBitmapImage.getHeight());
            mPainter.matrix.reset();
            SVGViewBox.transform(mImageRect,mRect,align,aspect,mPainter.matrix,1f);
            canvas.drawBitmap(mBitmapImage,mPainter.matrix,null);
            if(clipToBounds) {
                mPainter.paint.setColor(Color.WHITE);
                mPainter.paintMask.setXfermode(mPainter.dstOut);
                int main = canvas.saveLayer(0f,0f,getWidth(),getHeight(),mPainter.paintMask);
                canvas.drawColor(Color.BLACK);
                mPainter.paintMask.setXfermode(mPainter.dstOut);
                int clip = canvas.saveLayer(0f,0f,getWidth(),getHeight(),mPainter.paintMask);
                canvas.drawRect(mRect,mPainter.paint);
                canvas.restoreToCount(clip);
                canvas.restoreToCount(main);
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

    protected void setupPaintBg(PainterKit p){
        p.paint.reset();
        p.paint.setStyle(Paint.Style.FILL);
        p.paint.setColor(bgColor);
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
