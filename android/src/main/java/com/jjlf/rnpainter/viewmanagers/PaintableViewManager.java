package com.jjlf.rnpainter.viewmanagers;

import android.graphics.Color;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jjlf.rnpainter.utils.ModUtil;
import com.jjlf.rnpainter.views.LineView;
import com.jjlf.rnpainter.views.PaintableView;
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

    @ReactProp(name = "fill")
    public void setFill(PaintableView view , Dynamic v) {
        view.getDrawable().setFill(ModUtil.getInt(v, Color.BLACK),ModUtil.isNotNull(v));
        view.getDrawable().invalidateSelf();
    }
    @ReactProp(name = "fillRule")
    public void setFillRule(PaintableView view ,String v) {
        view.getDrawable().setFillRule(v == null ? "none" : v , v != null);
        view.getDrawable().invalidateSelf();
    }
    @ReactProp(name = "fillOpacity")
    public void setFillOpacity(PaintableView view ,Dynamic v) {
        view.getDrawable().setFillOpacity(ModUtil.getFloat(v,1f), ModUtil.isNotNull(v));
        view.getDrawable().invalidateSelf();
    }

    @ReactProp(name = "stroke")
    public void setStroke(PaintableView view , Dynamic v) {
        view.getDrawable().setStroke(ModUtil.getInt(v,Color.TRANSPARENT),ModUtil.isNotNull(v));
        view.getDrawable().invalidateSelf();
    }

    @ReactProp(name = "strokeWidth")
    public void setStrokeWith(PaintableView view ,Dynamic v) {
        view.getDrawable().setStrokeWith(ModUtil.getFloat(v,1f),ModUtil.isNotNull(v));
        view.getDrawable().invalidateSelf();
    }


    @ReactProp(name = "strokeCap")
    public void setStrokeCap(PaintableView view ,String v) {
        view.getDrawable().setStrokeCap(v == null ? "none" : v);
        view.getDrawable().invalidateSelf();
    }

    @ReactProp(name = "strokeJoin")
    public void setStrokeJoin(PaintableView view ,String v) {
        view.getDrawable().setStrokeJoin(v == null ? "none" : v);
        view.getDrawable().invalidateSelf();
    }
    @ReactProp(name = "strokeMiter")
    public void setStrokeMiter(PaintableView view ,Dynamic v) {
        view.getDrawable().setStrokeMiter(ModUtil.getFloat(v,4f),ModUtil.isNotNull(v));
        view.getDrawable().invalidateSelf();
    }

    @ReactProp(name = "strokeStart")
    public void setStrokeStart(PaintableView view ,Dynamic v) {
        view.getDrawable().setStrokeStart(ModUtil.getFloat(v,0f),ModUtil.isNotNull(v));
        view.getDrawable().invalidateSelf();
    }
    @ReactProp(name = "strokeEnd")
    public void setStrokeEnd(PaintableView view ,Dynamic v) {
        view.getDrawable().setStrokeEnd(ModUtil.getFloat(v,1f),ModUtil.isNotNull(v));
        view.getDrawable().invalidateSelf();
    }

    @ReactProp(name = "shadow")
    public void setShadow(PaintableView view ,Dynamic v) {
        view.getDrawable().setShadow(ModUtil.getInt(v,Color.BLACK), ModUtil.isNotNull(v));
        view.getDrawable().invalidateSelf();
    }
    @ReactProp(name = "shadowOpacity")
    public void setShadowOpacity(PaintableView view ,Dynamic v) {
        view.getDrawable().setShadowOpacity(ModUtil.getFloat(v,0f), ModUtil.isNotNull(v));
        view.getDrawable().invalidateSelf();
    }
    @ReactProp(name = "shadowRadius")
    public void setShadowRadius(PaintableView view ,Dynamic v) {
        view.getDrawable().setShadowRadius(ModUtil.getFloat(v,2f),ModUtil.isNotNull(v));
        view.getDrawable().invalidateSelf();
    }

    @ReactProp(name = "shadowOffset")
    public void setShadowOffset(PaintableView view , ReadableMap v) {
        float x = (float) ModUtil.getDouble(v,"x",2.0);
        float y = (float)ModUtil.getDouble(v,"y",2.0);
        boolean per = ModUtil.getBoolean(v,"percentageValue",false);
        view.getDrawable().setShadowOffset(x,y,per,v != null);
        view.getDrawable().invalidateSelf();
    }


    @ReactProp(name = "rotate")
    public void setRotate(PaintableView view ,ReadableMap v) {
        float a = (float) ModUtil.getDouble(v,"a",0.0);
        float x = (float) ModUtil.getDouble(v,"x",0.0);
        float y = (float) ModUtil.getDouble(v,"y",0.0);
        boolean per = ModUtil.getBoolean(v,"percentageValue",false);

        view.getDrawable().setPathRotation(a,x,y,per);
        view.getDrawable().invalidateSelf();
    }
    @ReactProp(name = "scale")
    public void setScale(PaintableView view , ReadableMap v) {
        float x = (float) ModUtil.getDouble(v,"x",1.0);
        float y = (float) ModUtil.getDouble(v,"y",1.0);
        view.getDrawable().setPathScale(x,y);
        view.getDrawable().invalidateSelf();
    }
    @ReactProp(name = "translate")
    public void setTranslate(PaintableView view ,ReadableMap v) {
        float dx = (float)ModUtil.getDouble(v,"x",0.0);
        float dy = (float)ModUtil.getDouble(v,"y",0.0);
        boolean per = ModUtil.getBoolean(v,"percentageValue",false);
        view.getDrawable().setPathTranslation(dx,dy,per);
        view.getDrawable().invalidateSelf();
    }



}
