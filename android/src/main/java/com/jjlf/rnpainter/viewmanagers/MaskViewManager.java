package com.jjlf.rnpainter.viewmanagers;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jjlf.rnpainter.shadownodes.PaintableShadowNode;
import com.jjlf.rnpainter.utils.ModUtil;
import com.jjlf.rnpainter.views.MaskView;
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