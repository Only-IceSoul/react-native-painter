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
import com.jjlf.rnpainter.shadownodes.PainterShadowNode;
import com.jjlf.rnpainter.utils.ModUtil;
import com.jjlf.rnpainter.views.GViewHardware;
import com.jjlf.rnpainter.views.GViewHardware;


public class GViewManagerHardware extends ViewGroupManager<GViewHardware> {

    @Override
    public String getName() { return "GViewH"; }

    @Override
    protected GViewHardware createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new GViewHardware(reactContext);
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

    @ReactProp(name = "translateZ",defaultFloat = 0f)
    public void setTranslateZ(GViewHardware view ,float v) {
        view.setTranslateZ(v);
    }
    @ReactProp(name = "mask")
    public void setMask(GViewHardware view , String v) {
        view.setMask(v == null ? "" : v);
    }




    @ReactProp(name = "fill")
    public void setFill(GViewHardware view , Dynamic v) {
        view.setFill(ModUtil.getInt(v,Color.BLACK),ModUtil.isNotNull(v));

    }
    @ReactProp(name = "fillRule")
    public void setFillRule(GViewHardware view ,String v) {
        view.setFillRule(v == null ? "none" : v , v != null);

    }
    @ReactProp(name = "fillOpacity")
    public void setFillOpacity(GViewHardware view ,Dynamic v) {
        view.setFillOpacity(ModUtil.getFloat(v,1f), ModUtil.isNotNull(v));

    }

    @ReactProp(name = "stroke")
    public void setStroke(GViewHardware view , Dynamic v) {
        view.setStroke(ModUtil.getInt(v,Color.TRANSPARENT),ModUtil.isNotNull(v));

    }

    @ReactProp(name = "strokeOpacity")
    public void setStrokeOpacity(GViewHardware view ,Dynamic v) {
        view.setStrokeOpacity(ModUtil.getFloat(v,1f), ModUtil.isNotNull(v));

    }

    @ReactProp(name = "strokeWidth")
    public void setStrokeWith(GViewHardware view ,Dynamic v) {
        view.setStrokeWith(ModUtil.getFloat(v,1f),ModUtil.isNotNull(v));

    }


    @ReactProp(name = "strokeCap")
    public void setStrokeCap(GViewHardware view ,String v) {
        view.setStrokeCap(v == null ? "none" : v);

    }

    @ReactProp(name = "strokeJoin")
    public void setStrokeJoin(GViewHardware view ,String v) {
        view.setStrokeJoin(v == null ? "none" : v);

    }
    @ReactProp(name = "strokeMiter")
    public void setStrokeMiter(GViewHardware view ,Dynamic v) {
        view.setStrokeMiter(ModUtil.getFloat(v,4f),ModUtil.isNotNull(v));

    }

    @ReactProp(name = "strokeStart")
    public void setStrokeStart(GViewHardware view ,Dynamic v) {
        view.setStrokeStart(ModUtil.getFloat(v,0f),ModUtil.isNotNull(v));

    }
    @ReactProp(name = "strokeEnd")
    public void setStrokeEnd(GViewHardware view ,Dynamic v) {
        view.setStrokeEnd(ModUtil.getFloat(v,1f),ModUtil.isNotNull(v));

    }

    @ReactProp(name = "shadow")
    public void setShadow(GViewHardware view ,Dynamic v) {
        view.setShadow(ModUtil.getInt(v,Color.BLACK), ModUtil.isNotNull(v));

    }
    @ReactProp(name = "shadowOpacity")
    public void setShadowOpacity(GViewHardware view ,Dynamic v) {
        view.setShadowOpacity(ModUtil.getFloat(v,0f), ModUtil.isNotNull(v));

    }
    @ReactProp(name = "shadowRadius")
    public void setShadowRadius(GViewHardware view ,Dynamic v) {
        view.setShadowRadius(ModUtil.getFloat(v,2f),ModUtil.isNotNull(v));

    }

    //shadow
    @ReactProp(name = "shadowOffset")
    public void setShadowOffset(GViewHardware view , Dynamic v) {
        view.setShadowOffset(ModUtil.getFloat(v,2f),ModUtil.isNotNull(v));
    }
    @ReactProp(name = "shadowOffsetX")
    public void setShadowOffsetX(GViewHardware view , Dynamic v) {
        view.setShadowOffsetX(ModUtil.getFloat(v,2f),ModUtil.isNotNull(v));
    }
    @ReactProp(name = "shadowOffsetY")
    public void setShadowOffsetY(GViewHardware view , Dynamic v) {
        view.setShadowOffsetY(ModUtil.getFloat(v,2f),ModUtil.isNotNull(v));
    }
    @ReactProp(name = "shadowPercentageValue")
    public void setShadowPercentageValue(GViewHardware view , Dynamic v) {
        view.setShadowPercentageValue(ModUtil.getBoolean(v,false),ModUtil.isNotNull(v));
    }


    //MARK: Transform

    @ReactProp(name = "transX",defaultFloat = 0f)
    public void setTransX(GViewHardware view, float v) {
        view.setTransX(v);
    }
    @ReactProp(name = "transY",defaultFloat = 0f)
    public void setTransY(GViewHardware view, float v) {
        view.setTransY(v);
    }
    @ReactProp(name = "transPercentageValue",defaultBoolean = false)
    public void setTransPercentageValue(GViewHardware view, boolean v) {
        view.setTransPercentageValue(v);
    }

    @ReactProp(name = "rot",defaultFloat = 0f)
    public void setRot(GViewHardware view , float v) {
        view.setRot(v);
    }
    @ReactProp(name = "rotO",defaultFloat = 0f)
    public void setRotO(GViewHardware view , float v) {
        view.setRotO(v);
    }
    @ReactProp(name = "rotOx",defaultFloat = 0f)
    public void setRotOx(GViewHardware view , float v) {
        view.setRotOx(v);
    }
    @ReactProp(name = "rotOy",defaultFloat = 0f)
    public void setRotOy(GViewHardware view , float v) {
        view.setRotOy(v);
    }
    @ReactProp(name = "rotPercentageValue",defaultBoolean  = false)
    public void setRotPercentageValue(GViewHardware view , boolean v) {
        view.setRotPercentageValue(v);
    }

    @ReactProp(name = "sc",defaultFloat = 1f)
    public void setSc(GViewHardware view, float v) {
        view.setSc(v);
    }
    @ReactProp(name = "scX",defaultFloat = 1f)
    public void setScX(GViewHardware view, float v) {
        view.setScX(v);
    }
    @ReactProp(name = "scY",defaultFloat = 1f)
    public void setScY(GViewHardware view, float v) {
        view.setScY(v);
    }
    @ReactProp(name = "scO",defaultFloat = 0f)
    public void setScO(GViewHardware view, float v) {
        view.setScO(v);
    }
    @ReactProp(name = "scOx",defaultFloat = 0f)
    public void setScOx(GViewHardware view, float v) {
        view.setScOx(v);
    }
    @ReactProp(name = "scOy",defaultFloat = 0f)
    public void setScOy(GViewHardware view, float v) {
        view.setScOy(v);
    }
    @ReactProp(name = "scPercentageValue",defaultBoolean = false)
    public void setScPercentageValue(GViewHardware view, boolean v) {
        view.setScPercentageValue(v);
    }




}