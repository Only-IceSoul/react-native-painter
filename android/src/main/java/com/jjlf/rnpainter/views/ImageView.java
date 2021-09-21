package com.jjlf.rnpainter.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
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
import java.util.ArrayList;
import java.util.Objects;

public class ImageView extends View implements PaintableInterface {

    PainterKit mPainter;
    private String mMask ="";
    private String mOldMask = "";
    protected boolean mIsMaskChild = false;
    protected float mTranslationZ = 0f;
    CommonProps mProps = new CommonProps();
    TransformProps mTransform = new TransformProps();
    protected RectF mRect = new RectF();

    protected float x = 0f;
    protected float y = 0f;
    protected float w = 0f;
    protected float h = 0f;
    protected String source = "";
    protected String align = "xMidYMid";
    protected int aspect = SVGViewBox.MOS_MEET;
    private boolean clipToBounds = false;

    private Bitmap mBitmapImage ;


    public ImageView(Context context){
        super(context);
        setLayerType(View.LAYER_TYPE_HARDWARE,null);
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

    public void setClipToBounds(boolean v){
        if(clipToBounds !=  v){
            clipToBounds = v;
            invalidate();
        }
    }


    //MARK: Paintable Props

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
    public void setOpacity(float op,boolean status){
        mProps.mOpacityStatus = status;
        if(mProps.mOpacity != op){
            mProps.mOpacity = op;
            setAlpha(mProps.getOpacity());
            if(mIsMaskChild) invalidate();
        }
    }

    public void setTranslateZ(float v) {
        if(mTranslationZ != v && !mIsMaskChild) {
            mTranslationZ = v;
            setTranslationZ(mTranslationZ);
             invalidateReactTransform();
        }
    }

    public void setFill(int v, boolean status) {
        mProps.mFillColorStatus = status;
        if(mProps.mFillColor != v) {
            mProps.mFillColor = v;
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


    //si es cliptobounds y slice al rect, si es clip false a la imagen, si clip y meet a la imagen.
    @SuppressLint("MissingSuperCall")
    @Override
    public void draw(Canvas canvas) {
        if(mPainter != null && w > 0  && h > 0 ) {

            setupRect(mPainter);
            transform(mTransform, mPainter);

            //draw
            int checkpoint = canvas.save();
            canvas.concat(mPainter.matrix);
            try{
                drawImage(canvas);
                if(!mMask.isEmpty()){
                    WeakReference<MaskInterface> maskView = mPainter.maskViews.get(mMask);
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
        //fill
         setupPaintFill();
         drawFill(canvas);

        //image
        if(mBitmapImage != null){
            mPainter.rect.set(0f,0f,mBitmapImage.getWidth(),mBitmapImage.getHeight());
            mPainter.matrix.reset();
            SVGViewBox.transform(mPainter.rect,mRect,align,aspect,mPainter.matrix,1f);
            mPainter.matrix.mapRect(mPainter.rect);
            if(!clipToBounds){
                drawBitmapShadow(canvas);
                canvas.drawBitmap(mBitmapImage,mPainter.matrix,null);
            }else{
                canvas.saveLayer(0f,0f,getWidth(),getHeight(),null);
                canvas.drawBitmap(mBitmapImage,mPainter.matrix,null);
                if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.M && canvas.isHardwareAccelerated()){
                    mPainter.paintMask.setXfermode(mPainter.dstOut);
                    canvas.saveLayer(0f,0f,getWidth(),getHeight(),mPainter.paintMask);
                    canvas.drawColor(Color.BLACK);
                    mPainter.paintMask.setXfermode(mPainter.dstOut);
                    canvas.saveLayer(0f,0f,getWidth(),getHeight(),mPainter.paintMask);
                    drawClipRect(canvas);
                    canvas.restore();
                }else{
                    mPainter.paintMask.setXfermode(mPainter.dstIn);
                    canvas.saveLayer(0f,0f,getWidth(),getHeight(),mPainter.paintMask);
                    drawClipRect(canvas);
                }
                canvas.restore();
                canvas.restore();

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

    private void setupPaintFill(){
        mPainter.paint.reset();
        mPainter.paint.setAntiAlias(true);
        mPainter.paint.setStyle(Paint.Style.FILL);
        mPainter.paint.setColor(mProps.getFillColor());
        if(clipToBounds) setupShadow(false);
    }

    private void drawClipRect(Canvas canvas){
        mPainter.paint2.reset();
        mPainter.paint2.setAntiAlias(true);
        mPainter.paint2.setStyle(Paint.Style.FILL);
        mPainter.paint2.setColor(Color.WHITE);
        canvas.drawRect(mRect,mPainter.paint2);
    }

    protected void drawFill(Canvas canvas){
        if(mProps.getFillColor() != Color.TRANSPARENT) canvas.drawRect(mRect,mPainter.paint);
    }
    private void drawBitmapShadow(Canvas canvas) {
        if (mProps.getShadowOpacity() > 0f){
            mPainter.paint2.reset();
            mPainter.paint2.setAntiAlias(true);
            mPainter.paint2.setStyle(Paint.Style.FILL);
            mPainter.paint2.setColor(Color.GRAY);
            setupShadow(true);
            canvas.drawRect(mPainter.rect, mPainter.paint2);
        }
    }

    protected void setupShadow(boolean paint2 ) {
        Paint paint = paint2 ? mPainter.paint2 : mPainter.paint;
        if (mProps.getShadowOpacity() > 0f) {
            final int alpha = Color.alpha(mProps.getShadowColor());
            final int red = Color.red(mProps.getShadowColor());
            final int green = Color.green(mProps.getShadowColor());
            final int blue = Color.blue(mProps.getShadowColor());
            final int c = Color.argb((int) (mProps.getShadowOpacity() * alpha), red, green, blue);

            float ox;
            float oy;
            if (mProps.getShadowOffsetIsPercent()) {
                ox = mProps.getShadowOffsetX() * mPainter.bounds.width();
                oy = mProps.getShadowOffsetY() * mPainter.bounds.height();
            } else if (mPainter.isViewBoxEnabled) {
                ox = (mProps.getShadowOffsetX() / mPainter.viewBox.width()) * mPainter.bounds.width();
                oy = (mProps.getShadowOffsetY() / mPainter.viewBox.height()) * mPainter.bounds.height();
            } else {
                ox = toDip(mProps.getShadowOffsetX());
                oy = toDip(mProps.getShadowOffsetY());
            }

            float radius;
            if (mPainter.isViewBoxEnabled) {
                float size = mPainter.viewBox.width() > mPainter.viewBox.height() ? mPainter.bounds.width() : mPainter.bounds.height();
                radius =  (mProps.getShadowRadius() / Math.max( mPainter.viewBox.width(), mPainter.viewBox.height() )) * size;
            }else{
                radius = toDip(mProps.getShadowRadius());
            }

            paint.setShadowLayer(radius, ox, oy, c);

        } else {
            paint.clearShadowLayer();
        }
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
        super.invalidate();
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
            super.invalidate();
            invalidateMaskListeners();
        }
    }

    @Override
    public void setProps(CommonProps props) {
        mProps.set(props);
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
