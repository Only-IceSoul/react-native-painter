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
import com.jjlf.rnpainter.views.MaskGView;

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

    @ReactProp(name = "translateZ",defaultFloat = 0f)
    public void setTranslateZ(MaskGView view ,float v) {
        // view.setTranslateZ(v);
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


    @Override
    public void setTransform(@NonNull MaskGView view, @Nullable ReadableArray matrix) {
        super.setTransform(view, matrix);
        view.invalidateTransform();
    }

    //MARK: Transform

    @ReactProp(name = "transX",defaultFloat = 0f)
    public void setTransX(MaskGView view, float v) {
        view.setTransX(v);
    }
    @ReactProp(name = "transY",defaultFloat = 0f)
    public void setTransY(MaskGView view, float v) {
        view.setTransY(v);
    }
    @ReactProp(name = "transPercentageValue",defaultBoolean = false)
    public void setTransPercentageValue(MaskGView view, boolean v) {
        view.setTransPercentageValue(v);
    }

    @ReactProp(name = "rot",defaultFloat = 0f)
    public void setRot(MaskGView view , float v) {
        view.setRot(v);
    }
    @ReactProp(name = "rotO",defaultFloat = 0f)
    public void setRotO(MaskGView view , float v) {
        view.setRotO(v);
    }
    @ReactProp(name = "rotOx",defaultFloat = 0f)
    public void setRotOx(MaskGView view , float v) {
        view.setRotOx(v);
    }
    @ReactProp(name = "rotOy",defaultFloat = 0f)
    public void setRotOy(MaskGView view , float v) {
        view.setRotOy(v);
    }
    @ReactProp(name = "rotPercentageValue",defaultBoolean  = false)
    public void setRotPercentageValue(MaskGView view , boolean v) {
        view.setRotPercentageValue(v);
    }

    @ReactProp(name = "sc",defaultFloat = 1f)
    public void setSc(MaskGView view, float v) {
        view.setSc(v);
    }
    @ReactProp(name = "scX",defaultFloat = 1f)
    public void setScX(MaskGView view, float v) {
        view.setScX(v);
    }
    @ReactProp(name = "scY",defaultFloat = 1f)
    public void setScY(MaskGView view, float v) {
        view.setScY(v);
    }
    @ReactProp(name = "scO",defaultFloat = 0f)
    public void setScO(MaskGView view, float v) {
        view.setScO(v);
    }
    @ReactProp(name = "scOx",defaultFloat = 0f)
    public void setScOx(MaskGView view, float v) {
        view.setScOx(v);
    }
    @ReactProp(name = "scOy",defaultFloat = 0f)
    public void setScOy(MaskGView view, float v) {
        view.setScOy(v);
    }
    @ReactProp(name = "scPercentageValue",defaultBoolean = false)
    public void setScPercentageValue(MaskGView view, boolean v) {
        view.setScPercentageValue(v);
    }





}