package com.jjlf.rnpainter.viewmanagers;

import android.graphics.Color;
import android.util.Log;

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
import com.jjlf.rnpainter.views.GViewHardware;
import com.jjlf.rnpainter.views.MaskGView;
import com.jjlf.rnpainter.views.PaintableView;

public class MaskGViewManager extends ViewGroupManager<MaskGView> {

    @Override
    public String getName() { return "MaskGView"; }

    @Override
    protected MaskGView createViewInstance(@NonNull ThemedReactContext reactContext) {
    return new MaskGView(reactContext);
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
    @ReactProp(name = "mask")
    public void setMask(MaskGView view , String v) {

    }

    @ReactProp(name = "opacity")
    public void setOpacity(MaskGView view , Dynamic v) {
        view.setOpacity(ModUtil.getFloat(v,1f), ModUtil.isNotNull(v));
    }


    @ReactProp(name = "fill")
    public void setFill(MaskGView view , Dynamic v) {
        view.setFill(ModUtil.getInt(v, Color.BLACK),ModUtil.isNotNull(v));

    }
    @ReactProp(name = "fillRule")
    public void setFillRule(MaskGView view ,String v) {
        view.setFillRule(v == null ? "none" : v , v != null);

    }
    @ReactProp(name = "fillOpacity")
    public void setFillOpacity(MaskGView view ,Dynamic v) {
        view.setFillOpacity(ModUtil.getFloat(v,1f), ModUtil.isNotNull(v));

    }

    @ReactProp(name = "stroke")
    public void setStroke(MaskGView view , Dynamic v) {
        view.setStroke(ModUtil.getInt(v,Color.TRANSPARENT),ModUtil.isNotNull(v));

    }

    @ReactProp(name = "strokeOpacity")
    public void setStrokeOpacity(MaskGView view ,Dynamic v) {
        view.setStrokeOpacity(ModUtil.getFloat(v,1f), ModUtil.isNotNull(v));

    }

    @ReactProp(name = "strokeWidth")
    public void setStrokeWith(MaskGView view ,Dynamic v) {
        view.setStrokeWith(ModUtil.getFloat(v,1f),ModUtil.isNotNull(v));

    }


    @ReactProp(name = "strokeCap")
    public void setStrokeCap(MaskGView view ,String v) {
        view.setStrokeCap(v == null ? "none" : v);

    }

    @ReactProp(name = "strokeJoin")
    public void setStrokeJoin(MaskGView view ,String v) {
        view.setStrokeJoin(v == null ? "none" : v);

    }
    @ReactProp(name = "strokeMiter")
    public void setStrokeMiter(MaskGView view ,Dynamic v) {
        view.setStrokeMiter(ModUtil.getFloat(v,4f),ModUtil.isNotNull(v));

    }

    @ReactProp(name = "strokeStart")
    public void setStrokeStart(MaskGView view ,Dynamic v) {
        view.setStrokeStart(ModUtil.getFloat(v,0f),ModUtil.isNotNull(v));

    }
    @ReactProp(name = "strokeEnd")
    public void setStrokeEnd(MaskGView view ,Dynamic v) {
        view.setStrokeEnd(ModUtil.getFloat(v,1f),ModUtil.isNotNull(v));

    }

    @ReactProp(name = "shadow")
    public void setShadow(MaskGView view ,Dynamic v) {
        view.setShadow(ModUtil.getInt(v,Color.BLACK), ModUtil.isNotNull(v));

    }
    @ReactProp(name = "shadowOpacity")
    public void setShadowOpacity(MaskGView view ,Dynamic v) {
        view.setShadowOpacity(ModUtil.getFloat(v,0f), ModUtil.isNotNull(v));

    }
    @ReactProp(name = "shadowRadius")
    public void setShadowRadius(MaskGView view ,Dynamic v) {
         view.setShadowRadius(ModUtil.getFloat(v,2f),ModUtil.isNotNull(v));

    }

    @ReactProp(name = "shadowOffset")
    public void setShadowOffset(MaskGView view , ReadableMap v) {
        float x = (float) ModUtil.getDouble(v,"x",2.0);
        float y = (float)ModUtil.getDouble(v,"y",2.0);
        boolean per = ModUtil.getBoolean(v,"percentageValue",false);
        view.setShadowOffset(x,y,per,v != null);
    }


    @ReactProp(name = "rotate")
    public void setRotate(MaskGView view ,ReadableMap v) {
        float a = (float) ModUtil.getDouble(v,"a",0.0);
        float x = (float) ModUtil.getDouble(v,"x",0.0);
        float y = (float) ModUtil.getDouble(v,"y",0.0);
        boolean per = ModUtil.getBoolean(v,"percentageValue",false);
        view.setPathRotation(a,x,y,per);

    }
    @ReactProp(name = "scale")
    public void setScale(MaskGView view , ReadableMap v) {
        float x = (float) ModUtil.getDouble(v,"x",1.0);
        float y = (float) ModUtil.getDouble(v,"y",1.0);
        float ox = (float) ModUtil.getDouble(v,"ox",0.0);
        float oy = (float) ModUtil.getDouble(v,"oy",0.0);
        boolean per = ModUtil.getBoolean(v,"percentageValue",false);
        view.setPathScale(x,y,ox,oy,per);

    }
    @ReactProp(name = "translate")
    public void setTranslate(MaskGView view ,ReadableMap v) {
        float dx = (float)ModUtil.getDouble(v,"x",0.0);
        float dy = (float)ModUtil.getDouble(v,"y",0.0);
        boolean per = ModUtil.getBoolean(v,"percentageValue",false);
        view.setPathTranslation(dx,dy,per);

    }

    @Override
    public void setTransform(@NonNull MaskGView view, @Nullable ReadableArray matrix) {
        super.setTransform(view, matrix);
        view.invalidate();
    }


}