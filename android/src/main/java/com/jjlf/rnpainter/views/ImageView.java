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
    public void setSrc(String v){
        source = v;
        handleSource();
    }

    public void setAlign(String v){
        if(!align.equals(v)){
            align = v;
            invalidateWithChildMask();
        }
    }
    public void setAspect(int v){
        if(aspect !=  v){
            aspect = v;
            invalidateWithChildMask();
        }
    }

    public void setClipToBounds(boolean v){
        if(clipToBounds !=  v){
            clipToBounds = v;
            invalidateWithChildMask();
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
            invalidateWithChildMask();
        }else{
            mLazySetupMask = true;
        }
    }

    //MARK: COMMON PROPS
    protected float mOpacity = 1f;
    public void setOpacity(float v) {
        if(mOpacity != v) {
            mOpacity = v;
            setAlpha(mOpacity);
            invalidateReactTransform();
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

    @SuppressLint("MissingSuperCall")
    @Override
    public void draw(Canvas canvas) {
        if(mPainter != null && w > 0  && h > 0 ) {

            setupRect();

            viewBoxTransform();

            props();

            transform();

            drawContent(canvas);

        }
    }

    protected void props(){
        setupMatrixImage();

        setupPaintRect();

        setupPaintRectImage();

        setupShadow(!clipToBounds);
    }

    protected void drawContent(Canvas canvas) {
        int checkpoint = canvas.save();
        canvas.concat(mPainter.matrix);
        try{
            drawImage(canvas);
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

    private void drawImage(Canvas canvas ){
        //fill
         drawFill(canvas);

        //image
        if(mBitmapImage != null){
            if(!clipToBounds){
                drawImageShadow(canvas);
                canvas.drawBitmap(mBitmapImage,mPainter.matrix2,null);

            }else{
                canvas.saveLayer(0f,0f,getWidth(),getHeight(),null);
                canvas.drawBitmap(mBitmapImage,mPainter.matrix2,null);
                if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1){
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

    protected void setupMatrixImage(){
        if(mBitmapImage != null) {
            mPainter.rectHelper.set(0f, 0f, mBitmapImage.getWidth(), mBitmapImage.getHeight());
            SVGViewBox.transform(mPainter.rectHelper, mRect, align, aspect, 1f, mPainter.matrix2);
        }
    }

    private void setupPaintRect(){
        mPainter.paint.reset();
        mPainter.paint.setAntiAlias(true);
        mPainter.paint.setStyle(Paint.Style.FILL);
        mPainter.paint.setColor(mProps.getFillColor());

    }

    private void setupPaintRectImage(){
        mPainter.paint2.reset();
        mPainter.paint2.setAntiAlias(true);
        mPainter.paint2.setStyle(Paint.Style.FILL);
        mPainter.paint2.setColor(Color.GRAY);

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
    private void drawImageShadow(Canvas canvas) {
        if (mProps.getShadowOpacity() > 0f){
            canvas.drawRect(mPainter.rectHelper, mPainter.paint2);
        }
    }

    protected void setupShadow(boolean stroke) {
        mPainter.paint.clearShadowLayer();
        mPainter.paint2.clearShadowLayer();
        Paint paint = stroke ?  mPainter.paint2 :  mPainter.paint;
        if (mProps.getShadowOpacity() > 0f) {
            final int alpha = Color.alpha(mProps.getShadowColor());
            final int red = Color.red(mProps.getShadowColor());
            final int green = Color.green(mProps.getShadowColor());
            final int blue = Color.blue(mProps.getShadowColor());
            final int c = Color.argb((int) (mProps.getShadowOpacity() * alpha), red, green, blue);

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

            float radius;
            if (validateViewBox()) {
                float size = Math.max( mPainter.rectPath.width(), mPainter.rectPath.height() );
                radius =  (mProps.getShadowRadius() /  Math.max( mPainter.viewBox.width(), mPainter.viewBox.height() ) ) * size;
            }else{
                radius = toDip(mProps.getShadowRadius());
            }
            paint.setShadowLayer(radius, ox, oy, c);

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

    private void handleSource(){
        if(source.isEmpty()){
            mBitmapImage =  null;
            invalidateWithChildMask();
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
                invalidateWithChildMask();
                return;
            }
            if(d.getCurrent() instanceof BitmapDrawable){
                mBitmapImage = ((BitmapDrawable) d.getCurrent()).getBitmap();
                invalidateWithChildMask();
            }
        }
    }

    private void base64String(){
        String s = source.split("4,")[1];
        byte[] bytes = android.util.Base64.decode(s,android.util.Base64.DEFAULT);
        mBitmapImage = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        invalidateWithChildMask();
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
                    invalidateWithChildMask();
                    return;
                }

                try {
                    CloseableImage closeableImage = imageReference.get();
                    if (!(closeableImage instanceof CloseableBitmap)) {
                        mBitmapImage = null;
                        invalidateWithChildMask();
                        return;
                    }

                    CloseableBitmap closeableBitmap = (CloseableBitmap) closeableImage;
                    mBitmapImage = closeableBitmap.getUnderlyingBitmap();
                    invalidateWithChildMask();


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
                    invalidateWithChildMask();
                }
                @Override
                public void onFailureImpl(DataSource dataSource) {
                    mBitmapImage = null;
                    invalidateWithChildMask();
                }
            };
            dataSource.subscribe(subscriber, UiThreadImmediateExecutorService.getInstance());
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

    protected boolean validateViewBox(){
        return mPainter.viewBox.width() >= 0f && mPainter.viewBox.height() >= 0f;
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
        invalidateWithChildMask();
    }

    protected float toDip(float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value,getResources().getDisplayMetrics());
    }
}
