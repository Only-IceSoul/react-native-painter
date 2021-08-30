package com.jjlf.rnpainter.viewmanagers;

import android.graphics.Color;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jjlf.rnpainter.shadownodes.PainterShadowNode;
import com.jjlf.rnpainter.utils.ModUtil;
import com.jjlf.rnpainter.shadownodes.PaintableShadowNode;
import com.jjlf.rnpainter.views.GView;
import com.jjlf.rnpainter.views.GViewHardware;


public class GViewManager extends ViewGroupManager<GView> {

    @Override
    public String getName() { return "GViewS"; }

    @Override
    protected GView createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new GView(reactContext);
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
    public void setMask(GView view , String v) {
        view.setMask(v == null ? "" : v);
    }

    @ReactProp(name = "opacity")
    public void setOpacity(GView view ,Dynamic v) {
        view.setOpacity(ModUtil.getFloat(v,1f), ModUtil.isNotNull(v));
    }

    @ReactProp(name = "fill")
    public void setFill(GView view , Dynamic v) {
        view.setFill(ModUtil.getInt(v,Color.BLACK),ModUtil.isNotNull(v));
        
    }
    @ReactProp(name = "fillRule")
    public void setFillRule(GView view ,String v) {
        view.setFillRule(v == null ? "none" : v , v != null);
        
    }
    @ReactProp(name = "fillOpacity")
    public void setFillOpacity(GView view ,Dynamic v) {
        view.setFillOpacity(ModUtil.getFloat(v,1f), ModUtil.isNotNull(v));
        
    }

    @ReactProp(name = "stroke")
    public void setStroke(GView view , Dynamic v) {
        view.setStroke(ModUtil.getInt(v,Color.TRANSPARENT),ModUtil.isNotNull(v));
        
    }

    @ReactProp(name = "strokeOpacity")
    public void setStrokeOpacity(GView view ,Dynamic v) {
        view.setStrokeOpacity(ModUtil.getFloat(v,1f), ModUtil.isNotNull(v));

    }
    @ReactProp(name = "strokeWidth")
    public void setStrokeWith(GView view ,Dynamic v) {
        view.setStrokeWith(ModUtil.getFloat(v,1f),ModUtil.isNotNull(v));
        
    }


    @ReactProp(name = "strokeCap")
    public void setStrokeCap(GView view ,String v) {
        view.setStrokeCap(v == null ? "none" : v);
        
    }

    @ReactProp(name = "strokeJoin")
    public void setStrokeJoin(GView view ,String v) {
        view.setStrokeJoin(v == null ? "none" : v);
        
    }
    @ReactProp(name = "strokeMiter")
    public void setStrokeMiter(GView view ,Dynamic v) {
        view.setStrokeMiter(ModUtil.getFloat(v,4f),ModUtil.isNotNull(v));
        
    }

    @ReactProp(name = "strokeStart")
    public void setStrokeStart(GView view ,Dynamic v) {
        view.setStrokeStart(ModUtil.getFloat(v,0f),ModUtil.isNotNull(v));
        
    }
    @ReactProp(name = "strokeEnd")
    public void setStrokeEnd(GView view ,Dynamic v) {
        view.setStrokeEnd(ModUtil.getFloat(v,1f),ModUtil.isNotNull(v));
        
    }

    @ReactProp(name = "shadow")
    public void setShadow(GView view ,Dynamic v) {
        view.setShadow(ModUtil.getInt(v,Color.BLACK), ModUtil.isNotNull(v));
        
    }
    @ReactProp(name = "shadowOpacity")
    public void setShadowOpacity(GView view ,Dynamic v) {
        view.setShadowOpacity(ModUtil.getFloat(v,0f), ModUtil.isNotNull(v));
        
    }
    @ReactProp(name = "shadowRadius")
    public void setShadowRadius(GView view ,Dynamic v) {
        view.setShadowRadius(ModUtil.getFloat(v,2f),ModUtil.isNotNull(v));
        
    }

    @ReactProp(name = "shadowOffset")
    public void setShadowOffset(GView view , ReadableMap v) {
        float x = (float) ModUtil.getDouble(v,"x",2.0);
        float y = (float)ModUtil.getDouble(v,"y",2.0);
        boolean per = ModUtil.getBoolean(v,"percentageValue",false);
        view.setShadowOffset(x,y,per,v != null);
        
    }


    @ReactProp(name = "rotate")
    public void setRotate(GView view ,ReadableMap v) {
        float a = (float) ModUtil.getDouble(v,"a",0.0);
        float x = (float) ModUtil.getDouble(v,"x",0.0);
        float y = (float) ModUtil.getDouble(v,"y",0.0);
        boolean per = ModUtil.getBoolean(v,"percentageValue",false);
        view.setPathRotation(a,x,y,per);
        
    }
    @ReactProp(name = "scale")
    public void setScale(GView view , ReadableMap v) {
        float x = (float) ModUtil.getDouble(v,"x",1.0);
        float y = (float) ModUtil.getDouble(v,"y",1.0);
        float ox = (float) ModUtil.getDouble(v,"ox",0.0);
        float oy = (float) ModUtil.getDouble(v,"oy",0.0);
        boolean per = ModUtil.getBoolean(v,"percentageValue",false);
        view.setPathScale(x,y,ox,oy,per);
        
    }
    @ReactProp(name = "translate")
    public void setTranslate(GView view ,ReadableMap v) {
        float dx = (float)ModUtil.getDouble(v,"x",0.0);
        float dy = (float)ModUtil.getDouble(v,"y",0.0);
        boolean per = ModUtil.getBoolean(v,"percentageValue",false);
        view.setPathTranslation(dx,dy,per);
        
    }




}