package com.jjlf.rnpainter.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;



import com.facebook.react.views.text.ReactFontManager;
import com.jjlf.rnpainter.utils.ModUtil;
import com.jjlf.rnpainter.utils.SVGViewBox;

public class TextView extends PaintableView {

    private String text = "";
    private String font = "default";
    private float fontSize = 12f ;
    private String fontStyle = "normal";
    private String baseline = "none";
    private float horizontalOffset = 0f;
    protected float verticalOffset = 0f;
    private float x = 0f;
    private float y = 0f;
    private Typeface mTypeFace = Typeface.DEFAULT;

    protected Rect mBoundsText = new Rect();


    public void setText(String v) {
        if(!text.equals(v)){
            text = v;
            invalidateWithChildMask();
        }

    }
    public void setBaseline(String v) {
        if(!baseline.equals(v)){
            baseline = v;
            invalidateWithChildMask();
        }
    }
    public void setVerticalOffset(float v) {
        if(verticalOffset != v){
            verticalOffset = v;
            invalidateWithChildMask();
        }
    }
    public void setHorizontalOffset(float v) {
        if(verticalOffset != v){
            horizontalOffset = v;
            invalidateWithChildMask();
        }
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

    public void setFont(String v) {
        if(!font.equals(v)){
            font = v;
            invalidateFont();
        }
    }

    public void setFontStyle(String v) {
        if(!fontStyle.equals(v)){
            fontStyle = v;
            invalidateFont();
        }
    }
    public void setFontSize(float v) {
        if(fontSize != v){
            fontSize = v;
            invalidateWithChildMask();
        }
    }

    public void invalidateFont(){
        try{
            mTypeFace = font.equals("default") ? Typeface.DEFAULT : ReactFontManager.getInstance().getTypeface(font,Typeface.NORMAL,getResources().getAssets());
//                Typeface.createFromAsset(getContext().getAssets(), "fonts/"+font);
            if(font.equals("default") && (fontStyle.equals("bold") || fontStyle.equals("italic"))){
                mTypeFace =  Typeface.create(mTypeFace,fontStyle.equals("bold") ? Typeface.BOLD : Typeface.ITALIC);
            }
        }catch (Exception ignored){
            mTypeFace = Typeface.DEFAULT;
        }finally {
            invalidateWithChildMask();
        }
    }

    //Mark: Paintable


    @Override
    public void setFillRule(String v, boolean status) { }
    @Override
    public void setStrokeJoin(String v) { }
    @Override
    public void setStrokeCap(String v) { }
    @Override
    public void setStrokeMiter(float v, boolean status) { }
    @Override
    public void setStrokeEnd(float v, boolean status) { }
    @Override
    public void setStrokeStart(float v, boolean status) { }

    public TextView(Context context){
        super(context);
    }

    float mPx = 0f;
    float mPy = 0f;
    @SuppressLint("MissingSuperCall")
    @Override
    public void draw(Canvas canvas) {
        if(mPainter != null){

            viewBoxTransform();

            setupCoordinates();

            props();

            transform();

            super.drawContent(canvas);

        }

    }

    @Override
    protected void drawPath(Canvas canvas) {
        if(validateViewBox()){
            float fontPixelH = ModUtil.viewBoxToMin(fontSize,mPainter.viewBox,mPainter.rectPath.width(),mPainter.rectPath.height());
            float fontPixelW = ModUtil.viewBoxToWidth(fontSize,mPainter.viewBox,mPainter.rectPath.width());

            float scx = fontPixelW / fontPixelH;

            mPainter.matrix2.reset();
            mPainter.matrix2.postScale(scx,1f);

            int checkpoint = canvas.save();
            canvas.concat(mPainter.matrix2);

            try{
                if(fill()) canvas.drawText(text,mPx,mPy, mPainter.textPaint);
                if(stroke()) canvas.drawText(text,mPx,mPy,mPainter.textPaint2);
            } finally {
                canvas.restoreToCount(checkpoint);
            }
        }else{
            if(fill()) canvas.drawText(text,mPx,mPy, mPainter.textPaint);
            if(stroke()) canvas.drawText(text,mPx,mPy,mPainter.textPaint2);
        }

    }

    protected void setupCoordinates(){
        if(validateViewBox()){
            mPx = mPainter.rectPath.left + ModUtil.viewBoxToWidth(x,mPainter.viewBox,mPainter.rectPath.width());
            mPy = mPainter.rectPath.top + ModUtil.viewBoxToHeight(y,mPainter.viewBox,mPainter.rectPath.height());
        }else{
            mPx = toDip(x);
            mPy = toDip(y);
        }
    }

    @Override
    protected void viewBoxTransform() {
        if (validateViewBox()){

            mPainter.rectPath.set(mPainter.viewBoxDensity);
            SVGViewBox.transform(mPainter.viewBox, mPainter.bounds, mPainter.align, mPainter.aspect, getResources().getDisplayMetrics().density,mPainter.matrix);
            mPainter.matrix.mapRect(mPainter.rectPath);

        }else{
            mPainter.rectPath.set(mPainter.bounds);
        }
    }


    @Override
    protected void props() {
        if(fill()){
            setupTextPaintFill();
            setupShadow(false);
        }


        if(stroke()){
            setupTextPaintStroke();
            if (!fill()) setupShadow(true);
        }


        //baseline
        switch (baseline) {
            case "middle": {
                setupTextBoundsWithDescender();
                float offsetY = mBoundsText.exactCenterY();
                mPy -= offsetY;
                break;
            }
            case "capHeight": {
                setupTextBounds();
                mPy -= mBoundsText.exactCenterY();
                mPy += mBoundsText.height() / 2f;
                break;
            }
            case "descender": {
                mPy -=  mPainter.textPaint.getFontMetrics().descent;
                break;
            }
            case "center": {
                mPy = mPy + ( ((-mPainter.textPaint.getFontMetrics().ascent + mPainter.textPaint.getFontMetrics().descent) / 2) - mPainter.textPaint.getFontMetrics().descent);
                break;
            }
            case "ascender":{
                mPy -= mPainter.textPaint.getFontMetrics().ascent;
            }
        }

        if( verticalOffset != 0f){
            mPy +=  (-mPainter.textPaint.getFontMetrics().ascent + mPainter.textPaint.getFontMetrics().descent) * verticalOffset;
        }


        if(horizontalOffset != 0f){
            setupTextBounds();
            mPx += mBoundsText.width() * horizontalOffset;
        }
    }

    @Override
    protected void setupShadow(boolean stroke) {
        mPainter.textPaint.clearShadowLayer();
        mPainter.textPaint2.clearShadowLayer();
        Paint paint = stroke ?  mPainter.textPaint2 :  mPainter.textPaint;
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



    protected void setupTextBounds(){
        mBoundsText.setEmpty();
        mPainter.textPaint.getTextBounds(text,0,text.length(),mBoundsText);
    }

    protected void setupTextBoundsWithDescender(){
        mBoundsText.setEmpty();
        String t = text + "Hj";
        mPainter.textPaint.getTextBounds(t,0,t.length(),mBoundsText);
    }


    protected void setupTextPaintFill(){
        mPainter.textPaint.reset();
        mPainter.textPaint.setAntiAlias(true);
        mPainter.textPaint.setStyle(Paint.Style.FILL);

        mPainter.textPaint.setColor(mProps.getFillColor());
        mPainter.textPaint.setAlpha((int) (mProps.getFillOpacity() * 255f));

        mPainter.textPaint.setTextAlign(Paint.Align.LEFT);
       mPainter.textPaint.setTextSize(validateViewBox() ?  ModUtil.viewBoxToMin(fontSize,mPainter.viewBox,mPainter.rectPath.width(),mPainter.rectPath.height()) : toDip(fontSize));


      
        mPainter.textPaint.setTypeface(mTypeFace);

    }


    protected void setupTextPaintStroke(){
        mPainter.textPaint2.reset();
        mPainter.textPaint2.set(mPainter.textPaint);
        mPainter.textPaint2.setAntiAlias(true);
        mPainter.textPaint2.setStyle(Paint.Style.STROKE);
        mPainter.textPaint2.setColor(mProps.getStrokeColor());
        mPainter.textPaint2.setAlpha((int) (mProps.getStrokeOpacity() * 255f));

        float sw = validateViewBox() ?  ModUtil.viewBoxToMax(mProps.getStrokeWidth(),mPainter.viewBox,mPainter.rectPath.width(),mPainter.rectPath.height()) : toDip(mProps.getStrokeWidth());
        mPainter.textPaint2.setStrokeWidth(sw);

        mPainter.textPaint2.clearShadowLayer();

    }


}
