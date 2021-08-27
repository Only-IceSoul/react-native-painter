package com.jjlf.rnpainter.utils;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class CommonProps {

    public float mOpacity = 1f;
    public boolean mOpacityStatus = false;
    public String mMask = "";

    public int mFillColor = Color.BLACK;
    public boolean mFillColorStatus = false;
    public float mFillOpacity = 1f;
    public boolean mFillOpacityStatus = false;
    public String mFillRule = "none";
    public boolean mFillRuleStatus = false;

    public int mStrokeColor = Color.TRANSPARENT;
    public boolean mStrokeColorStatus = false;
    public float mStrokeOpacity = 1f;
    public boolean mStrokeOpacityStatus = false;
    public float mStrokeWidth = 1f;
    public boolean mStrokeWidthStatus = false;
    public String mStrokeCap = "none";
    public String mStrokeJoin = "none";
    public float mStrokeMiter = 4f;
    public boolean mStrokeMiterStatus = false;
    public float mStrokeStart = 0f;
    public boolean mStrokeStartStatus = false;
    public float mStrokeEnd =  1f;
    public boolean mStrokeEndStatus = false;

    public int mShadowColor = Color.BLACK;
    public boolean mShadowColorStatus = false;
    public float mShadowOpacity = 0f;
    public boolean mShadowOpacityStatus = false;
    public float mShadowRadius = 2f;
    public boolean mShadowRadiusStatus = false;
    public float mShadowOffsetX = 2f;
    public float mShadowOffsetY = 2f;
    public boolean mShadowOffsetIsPercent = false;
    public boolean mShadowOffsetStatus = false;

    public float getOpacity(){
        return ModUtil.clamp(mOpacity);
    }
    public String getMask(){
        return mMask;
    }

    public int getFillColor(){
        return mFillColor;
    }
    public float getFillOpacity(){
        return ModUtil.clamp(mFillOpacity);
    }
    public Path.FillType getFillRule(){
        return mFillRule.equals("evenodd ") ? Path.FillType.EVEN_ODD :  Path.FillType.WINDING;
    }

    public int getStrokeColor(){
        return mStrokeColor;
    }
    public float getStrokeOpacity(){ return  ModUtil.clamp(mStrokeOpacity); }
    public float getStrokeWidth(){
        return ModUtil.uClamp(mStrokeWidth,1f);
    }
    public Paint.Cap getStrokeCap(){
        return mStrokeCap.equals("round") ? Paint.Cap.ROUND : (mStrokeCap.equals("square") ? Paint.Cap.SQUARE : Paint.Cap.BUTT);
    }
    public Paint.Join getStrokeJoin(){
        return mStrokeCap.equals("round") ? Paint.Join.ROUND : (mStrokeCap.equals("bevel") ? Paint.Join.BEVEL : Paint.Join.MITER);
    }
    public float getStrokeMiter(){
        return mStrokeMiter;
    }
    public float getStrokeStart(){
        return ModUtil.clamp(mStrokeStart);
    }
    public float getStrokeEnd(){
        return ModUtil.clamp(mStrokeEnd);
    }

    public int getShadowColor(){
        return mShadowColor;
    }
    public float getShadowOpacity(){
        return ModUtil.clamp(mShadowOpacity);
    }
    public float getShadowRadius(){
        return ModUtil.uClamp(mShadowRadius);
    }
    public float getShadowOffsetX(){
        return mShadowOffsetX;
    }
    public float getShadowOffsetY(){
        return mShadowOffsetY;
    }
    public boolean getShadowOffsetIsPercent(){
        return mShadowOffsetIsPercent;
    }

    public void set(CommonProps props){

        if(!mOpacityStatus) mOpacity = props.mOpacity;

        if(!mFillColorStatus) mFillColor = props.mFillColor;
        if(!mFillOpacityStatus) mFillOpacity = props.mFillOpacity;
        if(!mFillRuleStatus) mFillRule = props.mFillRule;

        if(!mStrokeColorStatus) mStrokeColor = props.mStrokeColor;
        if(!mStrokeOpacityStatus) mStrokeOpacity = props.mStrokeOpacity;
        if(!mStrokeWidthStatus) mStrokeWidth = props.mStrokeWidth;
        if(mStrokeCap.equals("none")) mStrokeCap = props.mStrokeCap;
        if(mStrokeJoin.equals("none")) mStrokeJoin = props.mStrokeJoin;
        if(!mStrokeMiterStatus) mStrokeMiter = props.mStrokeMiter;
        if(!mStrokeStartStatus) mStrokeStart = props.mStrokeStart;
        if(!mStrokeEndStatus) mStrokeEnd = props.mStrokeEnd;

        if(!mShadowColorStatus) mShadowColor = props.mShadowColor;
        if(!mShadowOpacityStatus) mShadowOpacity = props.mShadowOpacity;
        if(!mShadowRadiusStatus) mShadowRadius = props.mShadowRadius;
        if(!mShadowOffsetStatus) {
            mShadowOffsetX = props.mShadowOffsetX;
            mShadowOffsetY = props.mShadowOffsetY;
            mShadowOffsetIsPercent = props.mShadowOffsetIsPercent;
        }
    }



}
