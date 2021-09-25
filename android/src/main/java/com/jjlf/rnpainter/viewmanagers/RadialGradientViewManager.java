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
import com.jjlf.rnpainter.views.MaskGView;
import com.jjlf.rnpainter.views.RadialGradientView;
import com.jjlf.rnpainter.views.RadialGradientView;

public class RadialGradientViewManager extends SimpleViewManager<RadialGradientView> {

    @Override
    public String getName() { return "JJPainterRadialGradientView"; }


    @Override
    protected RadialGradientView createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new RadialGradientView(reactContext);
    }


    @Override
    public void setBackgroundColor(@NonNull RadialGradientView view, int backgroundColor) { }
    @Override
    public LayoutShadowNode createShadowNodeInstance() {
        return new PaintableShadowNode();
    }

    @Override
    public void setTransform(@NonNull RadialGradientView view, @Nullable ReadableArray matrix) {
        super.setTransform(view, matrix);
        view.invalidateReactTransform();
    }



    @ReactProp(name = "x",defaultFloat = 0f)
    public void setX(RadialGradientView view , float v) {
        view.setX(v);
    }

    @ReactProp(name = "y",defaultFloat = 0f)
    public void setY(RadialGradientView view ,float v) {
        view.setY(v);
    }

    @ReactProp(name = "w",defaultFloat = 0f)
    public void setW(RadialGradientView view ,float v) {
        view.setW(v);
    }

    @ReactProp(name = "h",defaultFloat = 0f)
    public void setH(RadialGradientView view ,float v) {
        view.setH(v);
    }

    @ReactProp(name = "cx",defaultFloat = 0.5f)
    public void setCx(RadialGradientView view , float v) {
        view.setCx(v);
    }

    @ReactProp(name = "cy",defaultFloat = 0.5f)
    public void setCY(RadialGradientView view ,float v) {
        view.setCy(v);
    }

    @ReactProp(name = "rx",defaultFloat = 0.5f)
    public void setRx(RadialGradientView view , float v) {
        view.setRx(v);
    }

    @ReactProp(name = "ry",defaultFloat = 0.5f)
    public void setRy(RadialGradientView view ,float v) {
        view.setRy(v);
    }
    
    @ReactProp(name = "colors")
    public void setColors(RadialGradientView view, ReadableArray colors)  {
        int[] c = colors != null ? ModUtil.toIntArray(colors) : new int[] { Color.WHITE, Color.BLACK};
        view.setColors(c);
    }
    @ReactProp(name = "positions")
    public void setPositions(RadialGradientView view, ReadableArray positions)  {
        float[] p = positions != null ? ModUtil.toFloatArray(positions) : null;
        view.setPositions(p);
    }

    @ReactProp(name = "translateZ",defaultFloat = 0f)
    public void setTranslateZ(RadialGradientView view ,float v) {
        view.setTranslateZ(v);
    }
    @ReactProp(name = "mask")
    public void setMask(RadialGradientView view ,String v) {
        view.setMask(v == null ? "" : v);
    }
    @Override
    public void setOpacity(@NonNull RadialGradientView view, float opacity) {
        view.setOpacity(opacity);
    }

    //MARK: Transform

    @ReactProp(name = "transX",defaultFloat = 0f)
    public void setTransX(RadialGradientView view, float v) {
        view.setTransX(v);
    }
    @ReactProp(name = "transY",defaultFloat = 0f)
    public void setTransY(RadialGradientView view, float v) {
        view.setTransY(v);
    }
    @ReactProp(name = "transPercentageValue",defaultBoolean = false)
    public void setTransPercentageValue(RadialGradientView view, boolean v) {
        view.setTransPercentageValue(v);
    }

    @ReactProp(name = "rot",defaultFloat = 0f)
    public void setRot(RadialGradientView view , float v) {
        view.setRot(v);
    }
    @ReactProp(name = "rotO",defaultFloat = 0f)
    public void setRotO(RadialGradientView view , float v) {
        view.setRotO(v);
    }
    @ReactProp(name = "rotOx",defaultFloat = 0f)
    public void setRotOx(RadialGradientView view , float v) {
        view.setRotOx(v);
    }
    @ReactProp(name = "rotOy",defaultFloat = 0f)
    public void setRotOy(RadialGradientView view , float v) {
        view.setRotOy(v);
    }
    @ReactProp(name = "rotPercentageValue",defaultBoolean  = false)
    public void setRotPercentageValue(RadialGradientView view , boolean v) {
        view.setRotPercentageValue(v);
    }

    @ReactProp(name = "sc",defaultFloat = 1f)
    public void setSc(RadialGradientView view, float v) {
        view.setSc(v);
    }
    @ReactProp(name = "scX",defaultFloat = 1f)
    public void setScX(RadialGradientView view, float v) {
        view.setScX(v);
    }
    @ReactProp(name = "scY",defaultFloat = 1f)
    public void setScY(RadialGradientView view, float v) {
        view.setScY(v);
    }
    @ReactProp(name = "scO",defaultFloat = 0f)
    public void setScO(RadialGradientView view, float v) {
        view.setScO(v);
    }
    @ReactProp(name = "scOx",defaultFloat = 0f)
    public void setScOx(RadialGradientView view, float v) {
        view.setScOx(v);
    }
    @ReactProp(name = "scOy",defaultFloat = 0f)
    public void setScOy(RadialGradientView view, float v) {
        view.setScOy(v);
    }
    @ReactProp(name = "scPercentageValue",defaultBoolean = false)
    public void setScPercentageValue(RadialGradientView view, boolean v) {
        view.setScPercentageValue(v);
    }

}
