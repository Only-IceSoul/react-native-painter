package com.jjlf.rnpainter.viewmanagers;

import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jjlf.rnpainter.utils.ModUtil;
import com.jjlf.rnpainter.views.PaintableView;
import com.jjlf.rnpainter.shadownodes.PaintableShadowNode;

public class PaintableViewManager extends SimpleViewManager<PaintableView> {

    @Override
    public String getName() {
        return null;
    }


    @Override
    protected PaintableView createViewInstance(@NonNull  ThemedReactContext reactContext) {
        return null;
    }

    @Override
    public void setBackgroundColor(@NonNull PaintableView view, int backgroundColor) { }

    @Override
    public LayoutShadowNode createShadowNodeInstance() {
        return new PaintableShadowNode();
    }

    @ReactProp(name = "mask")
    public void setMask(PaintableView view ,String v) {
        view.setMask(v == null ? "" : v);
    }
    @ReactProp(name = "opacity")
    public void setOpacity(PaintableView view ,Dynamic v) {
        view.setOpacity(ModUtil.getFloat(v,1f), ModUtil.isNotNull(v));
    }

    @ReactProp(name = "fill")
    public void setFill(PaintableView view , Dynamic v) {
        view.setFill(ModUtil.getInt(v, Color.BLACK),ModUtil.isNotNull(v));
    }
    @ReactProp(name = "fillRule")
    public void setFillRule(PaintableView view ,String v) {
        view.setFillRule(v == null ? "none" : v , v != null);
        
    }
    @ReactProp(name = "fillOpacity")
    public void setFillOpacity(PaintableView view ,Dynamic v) {
        view.setFillOpacity(ModUtil.getFloat(v,1f), ModUtil.isNotNull(v));
        
    }

    @ReactProp(name = "stroke")
    public void setStroke(PaintableView view , Dynamic v) {
        view.setStroke(ModUtil.getInt(v,Color.TRANSPARENT),ModUtil.isNotNull(v));
        
    }
    @ReactProp(name = "strokeOpacity")
    public void setStrokeOpacity(PaintableView view ,Dynamic v) {
        view.setStrokeOpacity(ModUtil.getFloat(v,1f), ModUtil.isNotNull(v));

    }

    @ReactProp(name = "strokeWidth")
    public void setStrokeWith(PaintableView view ,Dynamic v) {
        view.setStrokeWith(ModUtil.getFloat(v,1f),ModUtil.isNotNull(v));
        
    }


    @ReactProp(name = "strokeCap")
    public void setStrokeCap(PaintableView view ,String v) {
        view.setStrokeCap(v == null ? "none" : v);
        
    }

    @ReactProp(name = "strokeJoin")
    public void setStrokeJoin(PaintableView view ,String v) {
        view.setStrokeJoin(v == null ? "none" : v);
        
    }
    @ReactProp(name = "strokeMiter")
    public void setStrokeMiter(PaintableView view ,Dynamic v) {
        view.setStrokeMiter(ModUtil.getFloat(v,4f),ModUtil.isNotNull(v));
        
    }

    @ReactProp(name = "strokeStart")
    public void setStrokeStart(PaintableView view ,Dynamic v) {
        view.setStrokeStart(ModUtil.getFloat(v,0f),ModUtil.isNotNull(v));
        
    }
    @ReactProp(name = "strokeEnd")
    public void setStrokeEnd(PaintableView view ,Dynamic v) {
        view.setStrokeEnd(ModUtil.getFloat(v,1f),ModUtil.isNotNull(v));
        
    }

    @ReactProp(name = "shadow")
    public void setShadow(PaintableView view ,Dynamic v) {
        view.setShadow(ModUtil.getInt(v,Color.BLACK), ModUtil.isNotNull(v));

    }
    @ReactProp(name = "shadowOpacity")
    public void setShadowOpacity(PaintableView view ,Dynamic v) {
        view.setShadowOpacity(ModUtil.getFloat(v,0f), ModUtil.isNotNull(v));

    }
    @ReactProp(name = "shadowRadius")
    public void setShadowRadius(PaintableView view ,Dynamic v) {
        view.setShadowRadius(ModUtil.getFloat(v,2f),ModUtil.isNotNull(v));

    }

    @ReactProp(name = "shadowOffset")
    public void setShadowOffset(PaintableView view , ReadableMap v) {
        float x = (float) ModUtil.getDouble(v,"x",2.0);
        float y = (float)ModUtil.getDouble(v,"y",2.0);
        boolean per = ModUtil.getBoolean(v,"percentageValue",false);
        view.setShadowOffset(x,y,per,v != null);

    }


    @ReactProp(name = "rotate")
    public void setRotate(PaintableView view ,ReadableMap v) {
        float a = (float) ModUtil.getDouble(v,"a",0.0);
        float x = (float) ModUtil.getDouble(v,"x",0.0);
        float y = (float) ModUtil.getDouble(v,"y",0.0);
        boolean per = ModUtil.getBoolean(v,"percentageValue",false);

        view.setPathRotation(a,x,y,per);

    }
    @ReactProp(name = "scale")
    public void setScale(PaintableView view , ReadableMap v) {
        float x = (float) ModUtil.getDouble(v,"x",1.0);
        float y = (float) ModUtil.getDouble(v,"y",1.0);
        float ox = (float) ModUtil.getDouble(v,"ox",0.0);
        float oy = (float) ModUtil.getDouble(v,"oy",0.0);
        boolean per = ModUtil.getBoolean(v,"percentageValue",false);
        view.setPathScale(x,y,ox,oy,per);

    }
    @ReactProp(name = "translate")
    public void setTranslate(PaintableView view ,ReadableMap v) {
        float dx = (float)ModUtil.getDouble(v,"x",0.0);
        float dy = (float)ModUtil.getDouble(v,"y",0.0);
        boolean per = ModUtil.getBoolean(v,"percentageValue",false);
        view.setPathTranslation(dx,dy,per);

    }



}
