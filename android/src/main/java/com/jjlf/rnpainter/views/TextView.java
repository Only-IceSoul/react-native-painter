package com.jjlf.rnpainter.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import com.jjlf.rnpainter.utils.ModUtil;
import com.jjlf.rnpainter.utils.PainterKit;

public class TextView extends PaintableView {


    private String font = "default";
    private String fontStyle = "normal";
    private String text = "";
    private float x = 0f;
    private float y = 0f;
    private float fontSize = 12f ;
    private String textAnchor = "left";
    private String direction = "ltr";
    private String baseline = "none";
    protected Rect mBoundsText = new Rect();
    protected float baselineOffset = 0f;

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
    public void setBaselineOffset(float v) {
        if(baselineOffset != v){
            baselineOffset = ModUtil.clamp(v);
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
    public void setDirection(String v) {
        if(!direction.equals(v)){
            direction = v;
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

    public void setTextAnchor(String v) {
        if(!textAnchor.equals(v)){
            textAnchor = v;
            invalidate();
        }
    }



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



            if(direction.equals("rtl")){
                px -= mBoundsText.width();
            }

            //baseline
            switch (baseline) {
                case "middle": {
                    setupTextBounds();
                    py -= mBoundsText.exactCenterY();
                    py += getBaselineOffset(false);
                    break;
                }
                case "top": {
                    setupTextBounds();
                    py -= mBoundsText.exactCenterY();
                    py += mBoundsText.height() / 2f;
                    py += getBaselineOffset(false);
                    break;
                }
                case "bottom": {
                    setupTextBounds();
                    py -= mBoundsText.exactCenterY();
                    py -= mBoundsText.height() / 2f;
                    py += getBaselineOffset(false);
                    break;
                }
                case "center": {
                    py = py + ( ((-mPainter.textPaint.getFontMetrics().ascent + mPainter.textPaint.getFontMetrics().descent) / 2) - mPainter.textPaint.getFontMetrics().descent);
                    py += getBaselineOffset(true);
                    break;
                }
            }

            canvas.drawText(text,px,py, mPainter.textPaint);

        }

    }

    protected void setupTextBounds(){
        mBoundsText.setEmpty();
        mPainter.textPaint.getTextBounds(text,0,text.length(),mBoundsText);
    }

    protected float getBaselineOffset(boolean bounds){
        if(baselineOffset != 0f){
            if(bounds) setupTextBounds();
            return baselineOffset * mBoundsText.height();
        }
        return 0f;
    }


    protected void setupTextPaintFill(PainterKit p){
        p.textPaint.reset();
        p.textPaint.setAntiAlias(true);
        p.textPaint.setStyle(Paint.Style.FILL);
        p.textPaint.setColor(mProps.getFillColor());

        float opacity = mProps.getFillOpacity() * mProps.getOpacity();
        p.textPaint.setAlpha((int) (opacity * 255f));

        p.textPaint.setTextAlign(textAnchor.equals("end") ? Paint.Align.RIGHT : (textAnchor.equals("middle") ?Paint.Align.CENTER : Paint.Align.LEFT));
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
        p.textPaint2.setAntiAlias(true);
        p.textPaint2.set(p.textPaint);

        p.textPaint2.setStyle(Paint.Style.STROKE);

        p.textPaint2.setColor(mProps.getStrokeColor());
        float opacity = mProps.getStrokeOpacity() * mProps.getOpacity();
        p.textPaint2.setAlpha((int) (opacity * 255f));


    }


}
