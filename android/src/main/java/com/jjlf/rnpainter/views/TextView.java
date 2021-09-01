package com.jjlf.rnpainter.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.SystemClock;
import android.util.Log;

import com.jjlf.rnpainter.utils.ModUtil;
import com.jjlf.rnpainter.utils.PainterKit;

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

    protected Rect mBoundsText = new Rect();


    public void setText(String v) {
        if(!text.equals(v)){
            text = v;
            invalidate();
        }

    }
    public void setBaseline(String v) {
        if(!baseline.equals(v)){
            baseline = v;
            invalidate();
        }
    }
    public void setVerticalOffset(float v) {
        if(verticalOffset != v){
            verticalOffset = ModUtil.clamp(v);
            invalidate();
        }
    }
    public void setHorizontalOffset(float v) {
        if(verticalOffset != v){
            verticalOffset = ModUtil.clamp(v);
            invalidate();
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

    public void setFont(String v) {
        if(!font.equals(v)){
            font = v;
            invalidate();
        }
    }

    public void setFontStyle(String v) {
        if(!fontStyle.equals(v)){
            fontStyle = v;
            invalidate();
        }
    }
    public void setFontSize(float v) {
        if(fontSize != v){
            fontSize = v;
            invalidate();
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

    @SuppressLint("MissingSuperCall")
    @Override
    public void draw(Canvas canvas) {
        if(mPainter != null){
            float px;
            float py ;

            if(mPainter.isViewBoxEnabled){
                 px = ModUtil.viewBoxToWidth(x,mPainter.viewBox,mPainter.bounds.width());
                 py = ModUtil.viewBoxToHeight(y,mPainter.viewBox,mPainter.bounds.height());
            }else{
                px = toDip(x);
                py = toDip(y);
            }


            setupTextPaintFill(mPainter);
            if(fill()) setupShadowFill(mPainter);

            if(stroke()){
                setupTextPaintStroke(mPainter);
                if(!fill()){
                    setupShadowStroke(mPainter);
                }
            }


            //baseline
            switch (baseline) {
                case "middle": {
                    setupTextBounds();
                    py -= mBoundsText.exactCenterY();
                    break;
                }
                case "capHeight": {
                    setupTextBounds();
                    py -= mBoundsText.exactCenterY();
                    py += mBoundsText.height() / 2f;
                    break;
                }
                case "descender": {
                    py +=  mPainter.textPaint.getFontMetrics().descent;
                    break;
                }
                case "center": {
                    py = py + ( ((-mPainter.textPaint.getFontMetrics().ascent + mPainter.textPaint.getFontMetrics().descent) / 2) - mPainter.textPaint.getFontMetrics().descent);
                    break;
                }
                case "ascender":{
                    py -= mPainter.textPaint.getFontMetrics().ascent;
                }
            }

            if( verticalOffset != 0f){
               py +=  (-mPainter.textPaint.getFontMetrics().ascent + mPainter.textPaint.getFontMetrics().descent) * verticalOffset;
            }


            if(horizontalOffset != 0f){
                setupTextBounds();
                px += mBoundsText.width() * horizontalOffset;
            }

            if(fill()) canvas.drawText(text,px,py, mPainter.textPaint);
            if(stroke()) canvas.drawText(text,px,py,mPainter.textPaint2);


        }

    }

    @Override
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
            p.textPaint.setShadowLayer(radius, ox, oy, c);

        } else {
            p.textPaint.clearShadowLayer();
        }
    }

    @Override
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
            p.textPaint2.setShadowLayer(radius, ox, oy, c);

        } else {
            p.textPaint2.clearShadowLayer();
        }
    }

    protected void setupTextBounds(){
        mBoundsText.setEmpty();
        mPainter.textPaint.getTextBounds(text,0,text.length(),mBoundsText);
    }



    protected void setupTextPaintFill(PainterKit p){
        p.textPaint.reset();
        p.textPaint.setAntiAlias(true);
        p.textPaint.setStyle(Paint.Style.FILL);
        p.textPaint.setColor(mProps.getFillColor());

        float opacity = mProps.getFillOpacity() * mProps.getOpacity();
        p.textPaint.setAlpha((int) (opacity * 255f));

        p.textPaint.setTextAlign(Paint.Align.LEFT);
        p.textPaint.setTextSize(mPainter.isViewBoxEnabled ?  ModUtil.viewBoxToMax(fontSize,p.viewBox,p.bounds.width(),p.bounds.height()) : toDip(fontSize));

        //font
        Typeface f = font.equals("default") ? Typeface.DEFAULT : Typeface.createFromAsset(getContext().getAssets(), "fonts/"+font);

        if(font.equals("default") && (fontStyle.equals("bold") || fontStyle.equals("italic"))){
            f =  Typeface.create(f,fontStyle.equals("bold") ? Typeface.BOLD : Typeface.ITALIC);
        }
        p.textPaint.setTypeface(f);



    }


    protected void setupTextPaintStroke(PainterKit p){
        p.textPaint2.reset();
        p.textPaint2.set(p.textPaint);
        p.textPaint2.setAntiAlias(true);
        p.textPaint2.setStyle(Paint.Style.STROKE);

        p.textPaint2.setColor(mProps.getStrokeColor());

        float opacity = mProps.getStrokeOpacity() * mProps.getOpacity();
        p.textPaint2.setAlpha((int) (opacity * 255f));

        float sw ;
        if (p.isViewBoxEnabled) {
            float size = p.viewBox.width() > p.viewBox.height() ? p.bounds.width() : p.bounds.height();
            sw =  (mProps.getStrokeWidth() / Math.max( p.viewBox.width(), p.viewBox.height() )) * size;
        }else{
            sw = toDip(mProps.getStrokeWidth());
        }
        p.textPaint2.setStrokeWidth(sw);

        p.textPaint2.clearShadowLayer();


    }


}
