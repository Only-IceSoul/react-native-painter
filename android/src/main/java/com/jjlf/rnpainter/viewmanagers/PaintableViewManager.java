package com.jjlf.rnpainter.viewmanagers;

import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
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

    @Override
    public void setTransform(@NonNull PaintableView view, @Nullable  ReadableArray matrix) {
        super.setTransform(view, matrix);
        view.invalidateReactTransform();
    }
    @ReactProp(name = "translateZ",defaultFloat = 0f)
    public void setTranslateZ(PaintableView view ,float v) {
        view.setTranslateZ(v);
    }
    @ReactProp(name = "mask")
    public void setMask(PaintableView view ,String v) {
        view.setMask(v == null ? "" : v);
    }

    @Override
    public void setOpacity(@NonNull PaintableView view, float opacity) {
        view.setOpacity(opacity);
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
//    @ReactProp(name = "shadowOpacity")
//    public void setShadowOpacity(PaintableView view ,Dynamic v) {
//        view.setShadowOpacity(ModUtil.getFloat(v,0f), ModUtil.isNotNull(v));
//
//    }
    @ReactProp(name = "shadowRadius")
    public void setShadowRadius(PaintableView view ,Dynamic v) {
        view.setShadowRadius(ModUtil.getFloat(v,2f),ModUtil.isNotNull(v));

    }

    //shadow
    @ReactProp(name = "shadowOffset")
    public void setShadowOffset(PaintableView view , Dynamic v) {
        view.setShadowOffset(ModUtil.getFloat(v,2f),ModUtil.isNotNull(v));
    }
    @ReactProp(name = "shadowOffsetX")
    public void setShadowOffsetX(PaintableView view , Dynamic v) {
        view.setShadowOffsetX(ModUtil.getFloat(v,2f),ModUtil.isNotNull(v));
    }
    @ReactProp(name = "shadowOffsetY")
    public void setShadowOffsetY(PaintableView view , Dynamic v) {
        view.setShadowOffsetY(ModUtil.getFloat(v,2f),ModUtil.isNotNull(v));
    }
    @ReactProp(name = "shadowPercentageValue")
    public void setShadowPercentageValue(PaintableView view , Dynamic v) {
        view.setShadowPercentageValue(ModUtil.getBoolean(v,false),ModUtil.isNotNull(v));
    }


    //MARK: Transform

    @ReactProp(name = "transX",defaultFloat = 0f)
    public void setTransX(PaintableView view, float v) {
        view.setTransX(v);
    }
    @ReactProp(name = "transY",defaultFloat = 0f)
    public void setTransY(PaintableView view, float v) {
        view.setTransY(v);
    }
    @ReactProp(name = "transPercentageValue",defaultBoolean = false)
    public void setTransPercentageValue(PaintableView view, boolean v) {
        view.setTransPercentageValue(v);
    }
    
    @ReactProp(name = "rot",defaultFloat = 0f)
    public void setRot(PaintableView view , float v) {
        view.setRot(v);
    }
    @ReactProp(name = "rotO",defaultFloat = 0f)
    public void setRotO(PaintableView view , float v) {
        view.setRotO(v);
    }
    @ReactProp(name = "rotOx",defaultFloat = 0f)
    public void setRotOx(PaintableView view , float v) {
        view.setRotOx(v);
    }
    @ReactProp(name = "rotOy",defaultFloat = 0f)
    public void setRotOy(PaintableView view , float v) {
        view.setRotOy(v);
    }
    @ReactProp(name = "rotPercentageValue",defaultBoolean  = false)
    public void setRotPercentageValue(PaintableView view , boolean v) {
        view.setRotPercentageValue(v);
    }

    @ReactProp(name = "sc",defaultFloat = 1f)
    public void setSc(PaintableView view, float v) {
        view.setSc(v);
    }
    @ReactProp(name = "scX",defaultFloat = 1f)
    public void setScX(PaintableView view, float v) {
        view.setScX(v);
    }
    @ReactProp(name = "scY",defaultFloat = 1f)
    public void setScY(PaintableView view, float v) {
        view.setScY(v);
    }
    @ReactProp(name = "scO",defaultFloat = 0f)
    public void setScO(PaintableView view, float v) {
        view.setScO(v);
    }
    @ReactProp(name = "scOx",defaultFloat = 0f)
    public void setScOx(PaintableView view, float v) {
        view.setScOx(v);
    }
    @ReactProp(name = "scOy",defaultFloat = 0f)
    public void setScOy(PaintableView view, float v) {
        view.setScOy(v);
    }
    @ReactProp(name = "scPercentageValue",defaultBoolean = false)
    public void setScPercentageValue(PaintableView view, boolean v) {
        view.setScPercentageValue(v);
    }
   



}
