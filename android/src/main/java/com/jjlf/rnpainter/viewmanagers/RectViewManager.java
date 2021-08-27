package com.jjlf.rnpainter.viewmanagers;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jjlf.rnpainter.views.LineView;
import com.jjlf.rnpainter.views.RectView;

public class RectViewManager extends PaintableViewManager {

    @Override
    public String getName() { return "RectView"; }


    @Override
    protected RectView createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new RectView(reactContext);
    }


    @ReactProp(name = "x",defaultFloat = 0f)
    public void setX(RectView view , float v) {
        view.setX(v);
    }

    @ReactProp(name = "y",defaultFloat = 0f)
    public void setY(RectView view ,float v) {
        view.setY(v);
    }

    @ReactProp(name = "width",defaultFloat = 0f)
    public void setWidth(RectView view ,float v) {
        view.setW(v);
    }

    @ReactProp(name = "height",defaultFloat = 0f)
    public void setHeight(RectView view ,float v) {
        view.setH(v);
    }

    @ReactProp(name = "rtl",defaultFloat = 0f)
    public void setRtl(RectView view ,float v) {
        view.setRtl(v);
    }
    @ReactProp(name = "rtr",defaultFloat = 0f)
    public void setRtr(RectView view ,float v) {
        view.setRtr(v);
    }
    @ReactProp(name = "rbl",defaultFloat = 0f)
    public void setRbl(RectView view ,float v) {
        view.setRbl(v);
    }
    @ReactProp(name = "rbr",defaultFloat = 0f)
    public void setRbr(RectView view ,float v) {
        view.setRbr(v);
    }

}
