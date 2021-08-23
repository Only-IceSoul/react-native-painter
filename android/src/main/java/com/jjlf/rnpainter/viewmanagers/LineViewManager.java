package com.jjlf.rnpainter.viewmanagers;



import android.graphics.Color;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;

import com.jjlf.rnpainter.utils.ModUtil;
import com.jjlf.rnpainter.views.LineView;


public class LineViewManager extends PaintableViewManager {

    @Override
    public String getName() { return "LineView"; }


    @Override
    protected LineView createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new LineView(reactContext);
    }


    @ReactProp(name = "x1",defaultFloat = 0f)
    public void setX1(LineView view ,float v) {
        view.getDrawable().setX1(v);
        view.getDrawable().invalidateSelf();
    }

    @ReactProp(name = "y1",defaultFloat = 0f)
    public void setY1(LineView view ,float v) {
        view.getDrawable().setY1(v);
        view.getDrawable().invalidateSelf();
    }

    @ReactProp(name = "x2",defaultFloat = 0f)
    public void setX2(LineView view ,float v) {
        view.getDrawable().setX2(v);
        view.getDrawable().invalidateSelf();
    }

    @ReactProp(name = "y2",defaultFloat = 0f)
    public void setY2(LineView view ,float v) {
        view.getDrawable().setY2(v);
        view.getDrawable().invalidateSelf();
    }



}
