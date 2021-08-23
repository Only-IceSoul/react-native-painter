package com.jjlf.rnpainter.viewmanagers;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jjlf.rnpainter.views.PathView;

public class PathViewManager  extends PaintableViewManager {

    @Override
    public String getName() { return "PathView"; }


    @Override
    protected PathView createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new PathView(reactContext);
    }

    @ReactProp(name = "d")
    public void setD(PathView view ,String v) {
        view.getDrawable().setPath(v);
        view.getDrawable().invalidateSelf();
    }

}