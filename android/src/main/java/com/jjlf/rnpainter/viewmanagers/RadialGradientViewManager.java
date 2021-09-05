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
        view.invalidateTransform();
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
    @ReactProp(name = "opacity")
    public void setOpacity(RadialGradientView view , Dynamic v) {
        view.setOpacity(ModUtil.getFloat(v,1f), ModUtil.isNotNull(v));
    }

    @ReactProp(name = "rotate")
    public void setRotate(RadialGradientView view , ReadableMap v) {
        float a = (float) ModUtil.getDouble(v,"a",0.0);
        float x = (float) ModUtil.getDouble(v,"x",0.0);
        float y = (float) ModUtil.getDouble(v,"y",0.0);
        boolean per = ModUtil.getBoolean(v,"percentageValue",false);

        view.setPathRotation(a,x,y,per);

    }
    @ReactProp(name = "scale")
    public void setScale(RadialGradientView view , ReadableMap v) {
        float x = (float) ModUtil.getDouble(v,"x",1.0);
        float y = (float) ModUtil.getDouble(v,"y",1.0);
        float ox = (float) ModUtil.getDouble(v,"ox",0.0);
        float oy = (float) ModUtil.getDouble(v,"oy",0.0);
        boolean per = ModUtil.getBoolean(v,"percentageValue",false);
        view.setPathScale(x,y,ox,oy,per);

    }
    @ReactProp(name = "translate")
    public void setTranslate(RadialGradientView view ,ReadableMap v) {
        float dx = (float)ModUtil.getDouble(v,"x",0.0);
        float dy = (float)ModUtil.getDouble(v,"y",0.0);
        boolean per = ModUtil.getBoolean(v,"percentageValue",false);
        view.setPathTranslation(dx,dy,per);

    }

}
