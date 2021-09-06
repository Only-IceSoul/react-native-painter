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
        view.invalidateTransform();
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

    @ReactProp(name = "opacity")
    public void setOpacity(LinearGradientView view , Dynamic v) {
        view.setOpacity(ModUtil.getFloat(v,1f), ModUtil.isNotNull(v));
    }

    @ReactProp(name = "rotate")
    public void setRotate(LinearGradientView view , ReadableMap v) {
        float a = (float) ModUtil.getDouble(v,"a",0.0);
        float x = (float) ModUtil.getDouble(v,"x",0.0);
        float y = (float) ModUtil.getDouble(v,"y",0.0);
        boolean per = ModUtil.getBoolean(v,"percentageValue",false);

        view.setPathRotation(a,x,y,per);

    }
    @ReactProp(name = "scale")
    public void setScale(LinearGradientView view , ReadableMap v) {
        float x = (float) ModUtil.getDouble(v,"x",1.0);
        float y = (float) ModUtil.getDouble(v,"y",1.0);
        float ox = (float) ModUtil.getDouble(v,"ox",0.0);
        float oy = (float) ModUtil.getDouble(v,"oy",0.0);
        boolean per = ModUtil.getBoolean(v,"percentageValue",false);
        view.setPathScale(x,y,ox,oy,per);

    }
    @ReactProp(name = "translate")
    public void setTranslate(LinearGradientView view ,ReadableMap v) {
        float dx = (float)ModUtil.getDouble(v,"x",0.0);
        float dy = (float)ModUtil.getDouble(v,"y",0.0);
        boolean per = ModUtil.getBoolean(v,"percentageValue",false);
        view.setPathTranslation(dx,dy,per);

    }

}
