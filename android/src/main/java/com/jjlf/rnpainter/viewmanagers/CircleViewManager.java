package com.jjlf.rnpainter.viewmanagers;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jjlf.rnpainter.views.CircleView;
import com.jjlf.rnpainter.views.LineView;

public class CircleViewManager extends PaintableViewManager {

    @Override
    public String getName() { return "CircleView"; }


    @Override
    protected CircleView createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new CircleView(reactContext);
    }


    @ReactProp(name = "cx",defaultFloat = 0f)
    public void setCx(CircleView view ,float v) {
        view.setCx(v);
    }

    @ReactProp(name = "cy",defaultFloat = 0f)
    public void setCy(CircleView view ,float v) {
        view.setCy(v);
    }

    @ReactProp(name = "r",defaultFloat = 0f)
    public void setR(CircleView view ,float v) {
        view.setR(v);
    }





}