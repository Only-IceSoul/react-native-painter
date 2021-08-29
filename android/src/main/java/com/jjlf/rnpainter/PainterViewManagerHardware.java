package com.jjlf.rnpainter;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jjlf.rnpainter.shadownodes.PainterShadowNode;

import java.util.Objects;


public class PainterViewManagerHardware extends ViewGroupManager<PainterViewHardware> {
     static final int VIEW_BOX_MEET = 0;
     static final int VIEW_BOX_SLICE = 1;
     static final int VIEW_BOX_NONE = 2;

    @Override
    public String getName() { return "PainterHardware"; }

    @Override
    protected PainterViewHardware createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new PainterViewHardware(reactContext);
    }

    @Override
    public LayoutShadowNode createShadowNodeInstance() {
        return new PainterShadowNode();
    }

    @Override
    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        return PainterShadowNode.class;
    }

    @Override
    public boolean needsCustomLayoutForChildren() {
        return true;
    }


    @ReactProp(name = "align")
    public void setAlign(PainterViewHardware view , String v) {
        view.setAlign(v != null ? v : "none");
        view.invalidate();
    }
    @ReactProp(name = "aspect")
    public void setAspect(PainterViewHardware view ,String v) {
        view.setAspect(Objects.equals(v, "meet") ? VIEW_BOX_MEET : (Objects.equals(v, "slice") ? VIEW_BOX_SLICE : VIEW_BOX_NONE));
        view.invalidate();
    }

    @ReactProp(name = "viewBox")
    public void setViewBox(PainterViewHardware view , ReadableArray viewBox) {
        float [] v = {0f,0f,0f,0f};
        if(viewBox != null) {
            v[0] = (float) viewBox.getDouble(0);
            v[1] = (float) viewBox.getDouble(1);
            v[2] = (float) viewBox.getDouble(2);
            v[3] = (float)viewBox.getDouble(3);
            view.setViewBox(v);
            view.enableViewBox();
        }else{
            view.disableViewBox();
        }

        view.invalidate();
    }

}
