package com.jjlf.rnpainter.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.SystemClock;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import com.facebook.react.views.view.ReactViewGroup;
import com.jjlf.rnpainter.PainterView;

import com.jjlf.rnpainter.PainterViewHardware;
import com.jjlf.rnpainter.utils.CommonProps;
import com.jjlf.rnpainter.utils.MaskInterface;
import com.jjlf.rnpainter.utils.ModUtil;
import com.jjlf.rnpainter.utils.PaintableInterface;
import com.jjlf.rnpainter.utils.PainterKit;
import com.jjlf.rnpainter.utils.TransformProps;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Objects;


public class MaskView extends RelativeLayout implements PaintableInterface, MaskInterface {

    private  ArrayList<PaintableInterface> mListeners;
    private PainterKit mPainter;

    protected String name = "";

    public MaskView(Context context){
        super(context);
        setClipChildren(false);
        setLayerType(View.LAYER_TYPE_HARDWARE,null);
    }

    public void setName(String n) {
       if(!name.equals(n)){
           String old = name;
           name = n;
           if(name.isEmpty()){
               PainterView.MaskViews.remove(old);
           }else{
               PainterView.MaskViews.put(name, new WeakReference<MaskInterface>(this));
           }
       }
    }

    @Override
    public void render(Canvas canvas) {
//        setupMatrix(mTransform, mPainter);
//
//        int checkpoint = canvas.save();
//        canvas.concat(mMatrix);
//        try{
//            for (int i = 0; i < getChildCount(); i++) {
//                final View child = getChildAt(i);
//                if(child instanceof PaintableInterface){
//                    PaintableInterface c = (PaintableInterface) child;
//                    c.setProps(mProps);
//                    c.setPainterKit(mPainter);
//                }
//                child.draw(canvas);
//            }
//
//        } finally {
//            canvas.restoreToCount(checkpoint);
//        }
        draw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
            for (int i = 0; i < getChildCount(); i++) {
                final View child = getChildAt(i);
                if(child instanceof PaintableInterface){
                    PaintableInterface c = (PaintableInterface) child;
                    c.setPainterKit(mPainter);
                }
            }
            super.dispatchDraw(canvas);
    }



    @Override
    public String getName() {
        return name;
    }
    @Override
    public ArrayList<PaintableInterface> getListeners() {
        return mListeners;
    }

    @Override
    public void addListener(PaintableInterface listener) {
        if (mListeners == null) {
            mListeners = new ArrayList<PaintableInterface>();
        }
        mListeners.add(listener);
    }

    @Override
    public void removeListener(PaintableInterface listener) {
        if (mListeners == null) {
            return;
        }
        mListeners.remove(listener);
        if (mListeners.size() == 0) {
            mListeners = null;
        }
    }

    @Override
    public void onViewAdded(View child) {
        if(child instanceof MaskInterface || child instanceof PainterView || child instanceof PainterViewHardware
         || child instanceof GView || child instanceof GViewHardware){
            throw new IllegalArgumentException("Mask cannot have unpaintable children, G, Painter, Mask.");
        }
        if(child instanceof PaintableInterface){
            ((PaintableInterface) child).setIsMaskChild(true);
        }else{
            throw new IllegalArgumentException("foreignObject cannot be child ");
        }
        super.onViewAdded(child);
    }


    @Override
    public void setPainterKit(PainterKit painter) {
        mPainter = painter;
    }
    @Override
    public PainterKit getPainter() {
        return mPainter;
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).layout(0, 0, getWidth(), getHeight());
        }
    }
    @Override
    public void setIsMaskChild(boolean v) { }
    @Override
    public void invalidateMaskCallback() { }
    @Override
    public void setProps(CommonProps props) { }
    @Override
    public void setTransforms(ArrayList<TransformProps> transforms) { }
}
