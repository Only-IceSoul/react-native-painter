package com.jjlf.rnpainter.viewmanagers;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jjlf.rnpainter.shadownodes.PaintableShadowNode;
import com.jjlf.rnpainter.utils.ModUtil;
import com.jjlf.rnpainter.views.LinearGradientView;
import com.jjlf.rnpainter.views.LinearGradientView;
import com.jjlf.rnpainter.views.PaintableView;
import com.jjlf.rnpainter.views.RectView;

public class LinearGradientViewManager extends SimpleViewManager<LinearGradientView> {

    @Override
    public String getName() { return "JJPainterLinearGradientView"; }


    @Override
    protected LinearGradientView createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new LinearGradientView(reactContext);
    }


    @Override
    public void setBackgroundColor(@NonNull LinearGradientView view, int backgroundColor) { }
    @Override
    public LayoutShadowNode createShadowNodeInstance() {
        return new PaintableShadowNode();
    }

    @Override
    public void setTransform(@NonNull LinearGradientView view, @Nullable ReadableArray matrix) {
        super.setTransform(view, matrix);
        view.invalidateReactTransform();
    }



    @ReactProp(name = "x",defaultFloat = 0f)
    public void setX(LinearGradientView view , float v) {
        view.setX(v);
    }

    @ReactProp(name = "y",defaultFloat = 0f)
    public void setY(LinearGradientView view ,float v) {
        view.setY(v);
    }

    @ReactProp(name = "w",defaultFloat = 0f)
    public void setW(LinearGradientView view ,float v) {
        view.setW(v);
    }

    @ReactProp(name = "h",defaultFloat = 0f)
    public void setH(LinearGradientView view ,float v) {
        view.setH(v);
    }

    @ReactProp(name = "startPoint")
    public void setStartPoint(LinearGradientView view, ReadableMap m){
        float x = (float) ModUtil.getDouble(m,"x",0.5);
        float y = (float) ModUtil.getDouble(m,"y",0.0);
        view.setStartPoint(x,y);

    }

    @ReactProp(name = "endPoint")
    public void setEndPoint(LinearGradientView view, ReadableMap m){
        float x = (float) ModUtil.getDouble(m,"x",0.5);
        float y = (float) ModUtil.getDouble(m,"y",1.0);
        view.setEndPoint(x,y);
    }

    @ReactProp(name = "colors")
    public void setColors(LinearGradientView view, ReadableArray colors)  {
        int[] c = colors != null ? ModUtil.toIntArray(colors) : new int[] { Color.WHITE, Color.BLACK};
        view.setColors(c);
    }
    @ReactProp(name = "positions")
    public void setPositions(LinearGradientView view, ReadableArray positions)  {
        float[] p = positions != null ? ModUtil.toFloatArray(positions) : null;
        view.setPositions(p);
    }

    @ReactProp(name = "translateZ",defaultFloat = 0f)
    public void setTranslateZ(LinearGradientView view ,float v) {
        view.setTranslateZ(v);
    }
    @ReactProp(name = "mask")
    public void setMask(LinearGradientView view ,String v) {
        view.setMask(v == null ? "" : v);
    }
    @ReactProp(name = "opacity")
    public void setOpacity(LinearGradientView view , Dynamic v) {
        view.setOpacity(ModUtil.getFloat(v,1f), ModUtil.isNotNull(v));
    }


    //MARK: Transform

    @ReactProp(name = "transX",defaultFloat = 0f)
    public void setTransX(LinearGradientView view, float v) {
        view.setTransX(v);
    }
    @ReactProp(name = "transY",defaultFloat = 0f)
    public void setTransY(LinearGradientView view, float v) {
        view.setTransY(v);
    }
    @ReactProp(name = "transPercentageValue",defaultBoolean = false)
    public void setTransPercentageValue(LinearGradientView view, boolean v) {
        view.setTransPercentageValue(v);
    }

    @ReactProp(name = "rot",defaultFloat = 0f)
    public void setRot(LinearGradientView view , float v) {
        view.setRot(v);
    }
    @ReactProp(name = "rotO",defaultFloat = 0f)
    public void setRotO(LinearGradientView view , float v) {
        view.setRotO(v);
    }
    @ReactProp(name = "rotOx",defaultFloat = 0f)
    public void setRotOx(LinearGradientView view , float v) {
        view.setRotOx(v);
    }
    @ReactProp(name = "rotOy",defaultFloat = 0f)
    public void setRotOy(LinearGradientView view , float v) {
        view.setRotOy(v);
    }
    @ReactProp(name = "rotPercentageValue",defaultBoolean  = false)
    public void setRotPercentageValue(LinearGradientView view , boolean v) {
        view.setRotPercentageValue(v);
    }

    @ReactProp(name = "sc",defaultFloat = 1f)
    public void setSc(LinearGradientView view, float v) {
        view.setSc(v);
    }
    @ReactProp(name = "scX",defaultFloat = 1f)
    public void setScX(LinearGradientView view, float v) {
        view.setScX(v);
    }
    @ReactProp(name = "scY",defaultFloat = 1f)
    public void setScY(LinearGradientView view, float v) {
        view.setScY(v);
    }
    @ReactProp(name = "scO",defaultFloat = 0f)
    public void setScO(LinearGradientView view, float v) {
        view.setScO(v);
    }
    @ReactProp(name = "scOx",defaultFloat = 0f)
    public void setScOx(LinearGradientView view, float v) {
        view.setScOx(v);
    }
    @ReactProp(name = "scOy",defaultFloat = 0f)
    public void setScOy(LinearGradientView view, float v) {
        view.setScOy(v);
    }
    @ReactProp(name = "scPercentageValue",defaultBoolean = false)
    public void setScPercentageValue(LinearGradientView view, boolean v) {
        view.setScPercentageValue(v);
    }
}
