package com.jjlf.rnpainter.viewmanagers;

import android.graphics.Color;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jjlf.rnpainter.shadownodes.PaintableShadowNode;
import com.jjlf.rnpainter.utils.ModUtil;
import com.jjlf.rnpainter.views.GViewNone;
import com.jjlf.rnpainter.views.GViewNone;
import com.jjlf.rnpainter.views.GViewNone;


public class GViewManagerNone extends ViewGroupManager<GViewNone> {

    @Override
    public String getName() { return "GView"; }

    @Override
    protected GViewNone createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new GViewNone(reactContext);
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
    public void setTranslateZ(GViewNone view ,float v) {
        view.setTranslateZ(v);
    }
    @ReactProp(name = "mask")
    public void setMask(GViewNone view , String v) {
        view.setMask(v == null ? "" : v);
    }


    @ReactProp(name = "fill")
    public void setFill(GViewNone view , Dynamic v) {
        view.setFill(ModUtil.getInt(v,Color.BLACK),ModUtil.isNotNull(v));

    }
    @ReactProp(name = "fillRule")
    public void setFillRule(GViewNone view ,String v) {
        view.setFillRule(v == null ? "none" : v , v != null);

    }
    @ReactProp(name = "fillOpacity")
    public void setFillOpacity(GViewNone view ,Dynamic v) {
        view.setFillOpacity(ModUtil.getFloat(v,1f), ModUtil.isNotNull(v));

    }

    @ReactProp(name = "stroke")
    public void setStroke(GViewNone view , Dynamic v) {
        view.setStroke(ModUtil.getInt(v,Color.TRANSPARENT),ModUtil.isNotNull(v));

    }

    @ReactProp(name = "strokeOpacity")
    public void setStrokeOpacity(GViewNone view ,Dynamic v) {
        view.setStrokeOpacity(ModUtil.getFloat(v,1f), ModUtil.isNotNull(v));

    }

    @ReactProp(name = "strokeWidth")
    public void setStrokeWith(GViewNone view ,Dynamic v) {
        view.setStrokeWith(ModUtil.getFloat(v,1f),ModUtil.isNotNull(v));

    }


    @ReactProp(name = "strokeCap")
    public void setStrokeCap(GViewNone view ,String v) {
        view.setStrokeCap(v == null ? "none" : v);

    }

    @ReactProp(name = "strokeJoin")
    public void setStrokeJoin(GViewNone view ,String v) {
        view.setStrokeJoin(v == null ? "none" : v);

    }
    @ReactProp(name = "strokeMiter")
    public void setStrokeMiter(GViewNone view ,Dynamic v) {
        view.setStrokeMiter(ModUtil.getFloat(v,4f),ModUtil.isNotNull(v));

    }

    @ReactProp(name = "strokeStart")
    public void setStrokeStart(GViewNone view ,Dynamic v) {
        view.setStrokeStart(ModUtil.getFloat(v,0f),ModUtil.isNotNull(v));

    }
    @ReactProp(name = "strokeEnd")
    public void setStrokeEnd(GViewNone view ,Dynamic v) {
        view.setStrokeEnd(ModUtil.getFloat(v,1f),ModUtil.isNotNull(v));

    }

    @ReactProp(name = "shadow")
    public void setShadow(GViewNone view ,Dynamic v) {
        view.setShadow(ModUtil.getInt(v,Color.BLACK), ModUtil.isNotNull(v));

    }
    @ReactProp(name = "shadowOpacity")
    public void setShadowOpacity(GViewNone view ,Dynamic v) {
        view.setShadowOpacity(ModUtil.getFloat(v,0f), ModUtil.isNotNull(v));

    }
    @ReactProp(name = "shadowRadius")
    public void setShadowRadius(GViewNone view ,Dynamic v) {
        view.setShadowRadius(ModUtil.getFloat(v,2f),ModUtil.isNotNull(v));

    }

    //shadow
    @ReactProp(name = "shadowOffset")
    public void setShadowOffset(GViewNone view , Dynamic v) {
        view.setShadowOffset(ModUtil.getFloat(v,2f),ModUtil.isNotNull(v));
    }
    @ReactProp(name = "shadowOffsetX")
    public void setShadowOffsetX(GViewNone view , Dynamic v) {
        view.setShadowOffsetX(ModUtil.getFloat(v,2f),ModUtil.isNotNull(v));
    }
    @ReactProp(name = "shadowOffsetY")
    public void setShadowOffsetY(GViewNone view , Dynamic v) {
        view.setShadowOffsetY(ModUtil.getFloat(v,2f),ModUtil.isNotNull(v));
    }
    @ReactProp(name = "shadowPercentageValue")
    public void setShadowPercentageValue(GViewNone view , Dynamic v) {
        view.setShadowPercentageValue(ModUtil.getBoolean(v,false),ModUtil.isNotNull(v));
    }


    //MARK: Transform

    @ReactProp(name = "transX",defaultFloat = 0f)
    public void setTransX(GViewNone view, float v) {
        view.setTransX(v);
    }
    @ReactProp(name = "transY",defaultFloat = 0f)
    public void setTransY(GViewNone view, float v) {
        view.setTransY(v);
    }
    @ReactProp(name = "transPercentageValue",defaultBoolean = false)
    public void setTransPercentageValue(GViewNone view, boolean v) {
        view.setTransPercentageValue(v);
    }

    @ReactProp(name = "rot",defaultFloat = 0f)
    public void setRot(GViewNone view , float v) {
        view.setRot(v);
    }
    @ReactProp(name = "rotO",defaultFloat = 0f)
    public void setRotO(GViewNone view , float v) {
        view.setRotO(v);
    }
    @ReactProp(name = "rotOx",defaultFloat = 0f)
    public void setRotOx(GViewNone view , float v) {
        view.setRotOx(v);
    }
    @ReactProp(name = "rotOy",defaultFloat = 0f)
    public void setRotOy(GViewNone view , float v) {
        view.setRotOy(v);
    }
    @ReactProp(name = "rotPercentageValue",defaultBoolean  = false)
    public void setRotPercentageValue(GViewNone view , boolean v) {
        view.setRotPercentageValue(v);
    }

    @ReactProp(name = "sc",defaultFloat = 1f)
    public void setSc(GViewNone view, float v) {
        view.setSc(v);
    }
    @ReactProp(name = "scX",defaultFloat = 1f)
    public void setScX(GViewNone view, float v) {
        view.setScX(v);
    }
    @ReactProp(name = "scY",defaultFloat = 1f)
    public void setScY(GViewNone view, float v) {
        view.setScY(v);
    }
    @ReactProp(name = "scO",defaultFloat = 0f)
    public void setScO(GViewNone view, float v) {
        view.setScO(v);
    }
    @ReactProp(name = "scOx",defaultFloat = 0f)
    public void setScOx(GViewNone view, float v) {
        view.setScOx(v);
    }
    @ReactProp(name = "scOy",defaultFloat = 0f)
    public void setScOy(GViewNone view, float v) {
        view.setScOy(v);
    }
    @ReactProp(name = "scPercentageValue",defaultBoolean = false)
    public void setScPercentageValue(GViewNone view, boolean v) {
        view.setScPercentageValue(v);
    }




}