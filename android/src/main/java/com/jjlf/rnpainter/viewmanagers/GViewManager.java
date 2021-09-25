package com.jjlf.rnpainter.viewmanagers;

import android.graphics.Color;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jjlf.rnpainter.utils.ModUtil;
import com.jjlf.rnpainter.shadownodes.PaintableShadowNode;
import com.jjlf.rnpainter.views.GView;


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


    @ReactProp(name = "translateZ",defaultFloat = 0f)
    public void setTranslateZ(GView view ,float v) {
        view.setTranslateZ(v);
    }
    @ReactProp(name = "mask")
    public void setMask(GView view , String v) {
        view.setMask(v == null ? "" : v);
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

    //shadow
    @ReactProp(name = "shadowOffset")
    public void setShadowOffset(GView view , Dynamic v) {
        view.setShadowOffset(ModUtil.getFloat(v,2f),ModUtil.isNotNull(v));
    }
    @ReactProp(name = "shadowOffsetX")
    public void setShadowOffsetX(GView view , Dynamic v) {
        view.setShadowOffsetX(ModUtil.getFloat(v,2f),ModUtil.isNotNull(v));
    }
    @ReactProp(name = "shadowOffsetY")
    public void setShadowOffsetY(GView view , Dynamic v) {
        view.setShadowOffsetY(ModUtil.getFloat(v,2f),ModUtil.isNotNull(v));
    }
    @ReactProp(name = "shadowPercentageValue")
    public void setShadowPercentageValue(GView view , Dynamic v) {
        view.setShadowPercentageValue(ModUtil.getBoolean(v,false),ModUtil.isNotNull(v));
    }

    //MARK: Transform

    @ReactProp(name = "transX",defaultFloat = 0f)
    public void setTransX(GView view, float v) {
        view.setTransX(v);
    }
    @ReactProp(name = "transY",defaultFloat = 0f)
    public void setTransY(GView view, float v) {
        view.setTransY(v);
    }
    @ReactProp(name = "transPercentageValue",defaultBoolean = false)
    public void setTransPercentageValue(GView view, boolean v) {
        view.setTransPercentageValue(v);
    }

    @ReactProp(name = "rot",defaultFloat = 0f)
    public void setRot(GView view , float v) {
        view.setRot(v);
    }
    @ReactProp(name = "rotO",defaultFloat = 0f)
    public void setRotO(GView view , float v) {
        view.setRotO(v);
    }
    @ReactProp(name = "rotOx",defaultFloat = 0f)
    public void setRotOx(GView view , float v) {
        view.setRotOx(v);
    }
    @ReactProp(name = "rotOy",defaultFloat = 0f)
    public void setRotOy(GView view , float v) {
        view.setRotOy(v);
    }
    @ReactProp(name = "rotPercentageValue",defaultBoolean  = false)
    public void setRotPercentageValue(GView view , boolean v) {
        view.setRotPercentageValue(v);
    }

    @ReactProp(name = "sc",defaultFloat = 1f)
    public void setSc(GView view, float v) {
        view.setSc(v);
    }
    @ReactProp(name = "scX",defaultFloat = 1f)
    public void setScX(GView view, float v) {
        view.setScX(v);
    }
    @ReactProp(name = "scY",defaultFloat = 1f)
    public void setScY(GView view, float v) {
        view.setScY(v);
    }
    @ReactProp(name = "scO",defaultFloat = 0f)
    public void setScO(GView view, float v) {
        view.setScO(v);
    }
    @ReactProp(name = "scOx",defaultFloat = 0f)
    public void setScOx(GView view, float v) {
        view.setScOx(v);
    }
    @ReactProp(name = "scOy",defaultFloat = 0f)
    public void setScOy(GView view, float v) {
        view.setScOy(v);
    }
    @ReactProp(name = "scPercentageValue",defaultBoolean = false)
    public void setScPercentageValue(GView view, boolean v) {
        view.setScPercentageValue(v);
    }



}