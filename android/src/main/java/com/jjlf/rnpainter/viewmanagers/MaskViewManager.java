package com.jjlf.rnpainter.viewmanagers;

import androidx.annotation.NonNull;


import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jjlf.rnpainter.shadownodes.PaintableShadowNode;
import com.jjlf.rnpainter.views.MaskView;

public class MaskViewManager extends ViewGroupManager<MaskView> {

    @Override
    public String getName() { return "MaskView"; }

    @Override
    protected MaskView createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new MaskView(reactContext);
    }

    @Override
    public LayoutShadowNode createShadowNodeInstance() {
        return new PaintableShadowNode();
    }

    @Override
    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        return PaintableShadowNode.class;
    }

    @Override
    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    @ReactProp(name = "name")
    public void setName(MaskView view , String v) {
        view.setName(v == null ? "" : v);
    }





}