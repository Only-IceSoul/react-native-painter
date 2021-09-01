package com.jjlf.rnpainter.viewmanagers;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jjlf.rnpainter.views.CircleView;
import com.jjlf.rnpainter.views.EllipseView;

public class EllipseViewManager extends PaintableViewManager {

    @Override
    public String getName() { return "EllipseView"; }


    @Override
    protected EllipseView createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new EllipseView(reactContext);
    }


    @ReactProp(name = "cx",defaultFloat = 0f)
    public void setCx(EllipseView view ,float v) {
        view.setCx(v);
    }

    @ReactProp(name = "cy",defaultFloat = 0f)
    public void setCy(EllipseView view ,float v) {
        view.setCy(v);
    }

    @ReactProp(name = "rx",defaultFloat = 0f)
    public void setRx(EllipseView view ,float v) {
        view.setRx(v);
    }
    @ReactProp(name = "ry",defaultFloat = 0f)
    public void setRy(EllipseView view ,float v) {
        view.setRy(v);
    }




}