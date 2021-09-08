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
import com.jjlf.rnpainter.utils.SVGViewBox;
import com.jjlf.rnpainter.views.ImageView;
import com.jjlf.rnpainter.views.PaintableView;

import java.util.Objects;

public class ImageViewManager extends SimpleViewManager<ImageView> {

    @Override
    public String getName() { return "JJPainterImageView"; }


    @Override
    protected ImageView createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new ImageView(reactContext);
    }


    @Override
    public LayoutShadowNode createShadowNodeInstance() {
        return new PaintableShadowNode();
    }

    @Override
    public void setTransform(@NonNull ImageView view, @Nullable ReadableArray matrix) {
        super.setTransform(view, matrix);
        view.invalidateTransform();
    }

    @ReactProp(name = "source")
    public void setSource(ImageView view , String v) {
        view.setSrc(v != null ? v : "");
    }

    @ReactProp(name = "x",defaultFloat = 0f)
    public void setX(ImageView view , float v) {
        view.setX(v);
    }

    @ReactProp(name = "y",defaultFloat = 0f)
    public void setY(ImageView view ,float v) {
        view.setY(v);
    }

    @ReactProp(name = "w",defaultFloat = 0f)
    public void setW(ImageView view ,float v) {
        view.setW(v);
    }

    @ReactProp(name = "h",defaultFloat = 0f)
    public void setH(ImageView view ,float v) {
        view.setH(v);
    }

    @ReactProp(name = "align")
    public void setAlign(ImageView view , String v) {
        view.setAlign(v != null ? v : "xMidYMid");
    }
    @ReactProp(name = "aspect")
    public void setAspect(ImageView view ,String v) {
        view.setAspect(Objects.equals(v, "none") ? SVGViewBox.MOS_NONE : (Objects.equals(v, "slice") ? SVGViewBox.MOS_SLICE : SVGViewBox.MOS_MEET));
    }
    @ReactProp(name = "clipToBounds",defaultBoolean = false)
    public void setClipToBounds(ImageView view ,boolean v) {
        view.setClipToBounds(v);
    }


    @ReactProp(name = "translateZ",defaultFloat = 0f)
    public void setTranslateZ(ImageView view ,float v) {
        view.setTranslateZ(v);
    }
    @ReactProp(name = "mask")
    public void setMask(ImageView view ,String v) {
        view.setMask(v == null ? "" : v);
    }
    @ReactProp(name = "opacity")
    public void setOpacity(ImageView view , Dynamic v) {
        view.setOpacity(ModUtil.getFloat(v,1f), ModUtil.isNotNull(v));
    }

    @ReactProp(name = "fill")
    public void setFill(ImageView view , Dynamic v) {
        view.setFill(ModUtil.getInt(v, Color.BLACK),ModUtil.isNotNull(v));
    }
    @ReactProp(name = "shadow")
    public void setShadow(ImageView view ,Dynamic v) {
        view.setShadow(ModUtil.getInt(v,Color.BLACK), ModUtil.isNotNull(v));

    }
    @ReactProp(name = "shadowOpacity")
    public void setShadowOpacity(ImageView view ,Dynamic v) {
        view.setShadowOpacity(ModUtil.getFloat(v,0f), ModUtil.isNotNull(v));

    }
    @ReactProp(name = "shadowRadius")
    public void setShadowRadius(ImageView view ,Dynamic v) {
        view.setShadowRadius(ModUtil.getFloat(v,2f),ModUtil.isNotNull(v));

    }
    @ReactProp(name = "shadowOffset")
    public void setShadowOffset(ImageView view , ReadableMap v) {
        float x = (float) ModUtil.getDouble(v,"x",2.0);
        float y = (float)ModUtil.getDouble(v,"y",2.0);
        boolean per = ModUtil.getBoolean(v,"percentageValue",false);
        view.setShadowOffset(x,y,per,v != null);

    }

    @ReactProp(name = "rotate")
    public void setRotate(ImageView view , ReadableMap v) {
        float a = (float) ModUtil.getDouble(v,"a",0.0);
        float x = (float) ModUtil.getDouble(v,"x",0.0);
        float y = (float) ModUtil.getDouble(v,"y",0.0);
        boolean per = ModUtil.getBoolean(v,"percentageValue",false);

        view.setPathRotation(a,x,y,per);

    }
    @ReactProp(name = "scale")
    public void setScale(ImageView view , ReadableMap v) {
        float x = (float) ModUtil.getDouble(v,"x",1.0);
        float y = (float) ModUtil.getDouble(v,"y",1.0);
        float ox = (float) ModUtil.getDouble(v,"ox",0.0);
        float oy = (float) ModUtil.getDouble(v,"oy",0.0);
        boolean per = ModUtil.getBoolean(v,"percentageValue",false);
        view.setPathScale(x,y,ox,oy,per);

    }
    @ReactProp(name = "translate")
    public void setTranslate(ImageView view , ReadableMap v) {
        float dx = (float)ModUtil.getDouble(v,"x",0.0);
        float dy = (float)ModUtil.getDouble(v,"y",0.0);
        boolean per = ModUtil.getBoolean(v,"percentageValue",false);
        view.setPathTranslation(dx,dy,per);

    }

}
