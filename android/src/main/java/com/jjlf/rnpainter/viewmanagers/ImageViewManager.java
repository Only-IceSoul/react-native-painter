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
        view.invalidateReactTransform();
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

    @Override
    public void setOpacity(@NonNull ImageView view, float opacity) {
        view.setOpacity(opacity);
    }

    @ReactProp(name = "fill")
    public void setFill(ImageView view , Dynamic v) {
        view.setFill(ModUtil.getInt(v, Color.BLACK),ModUtil.isNotNull(v));
    }
    @ReactProp(name = "shadow")
    public void setShadow(ImageView view ,Dynamic v) {
        view.setShadow(ModUtil.getInt(v,Color.BLACK), ModUtil.isNotNull(v));

    }
//    @ReactProp(name = "shadowOpacity")
//    public void setShadowOpacity(ImageView view ,Dynamic v) {
//        view.setShadowOpacity(ModUtil.getFloat(v,0f), ModUtil.isNotNull(v));
//
//    }
    @ReactProp(name = "shadowRadius")
    public void setShadowRadius(ImageView view ,Dynamic v) {
        view.setShadowRadius(ModUtil.getFloat(v,2f),ModUtil.isNotNull(v));

    }

    //shadow
    @ReactProp(name = "shadowOffset")
    public void setShadowOffset(ImageView view , Dynamic v) {
        view.setShadowOffset(ModUtil.getFloat(v,2f),ModUtil.isNotNull(v));
    }
    @ReactProp(name = "shadowOffsetX")
    public void setShadowOffsetX(ImageView view , Dynamic v) {
        view.setShadowOffsetX(ModUtil.getFloat(v,2f),ModUtil.isNotNull(v));
    }
    @ReactProp(name = "shadowOffsetY")
    public void setShadowOffsetY(ImageView view , Dynamic v) {
        view.setShadowOffsetY(ModUtil.getFloat(v,2f),ModUtil.isNotNull(v));
    }
    @ReactProp(name = "shadowPercentageValue")
    public void setShadowPercentageValue(ImageView view , Dynamic v) {
        view.setShadowPercentageValue(ModUtil.getBoolean(v,false),ModUtil.isNotNull(v));
    }

    //MARK: Transform

    @ReactProp(name = "transX",defaultFloat = 0f)
    public void setTransX(ImageView view, float v) {
        view.setTransX(v);
    }
    @ReactProp(name = "transY",defaultFloat = 0f)
    public void setTransY(ImageView view, float v) {
        view.setTransY(v);
    }
    @ReactProp(name = "transPercentageValue",defaultBoolean = false)
    public void setTransPercentageValue(ImageView view, boolean v) {
        view.setTransPercentageValue(v);
    }

    @ReactProp(name = "rot",defaultFloat = 0f)
    public void setRot(ImageView view , float v) {
        view.setRot(v);
    }
    @ReactProp(name = "rotO",defaultFloat = 0f)
    public void setRotO(ImageView view , float v) {
        view.setRotO(v);
    }
    @ReactProp(name = "rotOx",defaultFloat = 0f)
    public void setRotOx(ImageView view , float v) {
        view.setRotOx(v);
    }
    @ReactProp(name = "rotOy",defaultFloat = 0f)
    public void setRotOy(ImageView view , float v) {
        view.setRotOy(v);
    }
    @ReactProp(name = "rotPercentageValue",defaultBoolean  = false)
    public void setRotPercentageValue(ImageView view , boolean v) {
        view.setRotPercentageValue(v);
    }

    @ReactProp(name = "sc",defaultFloat = 1f)
    public void setSc(ImageView view, float v) {
        view.setSc(v);
    }
    @ReactProp(name = "scX",defaultFloat = 1f)
    public void setScX(ImageView view, float v) {
        view.setScX(v);
    }
    @ReactProp(name = "scY",defaultFloat = 1f)
    public void setScY(ImageView view, float v) {
        view.setScY(v);
    }
    @ReactProp(name = "scO",defaultFloat = 0f)
    public void setScO(ImageView view, float v) {
        view.setScO(v);
    }
    @ReactProp(name = "scOx",defaultFloat = 0f)
    public void setScOx(ImageView view, float v) {
        view.setScOx(v);
    }
    @ReactProp(name = "scOy",defaultFloat = 0f)
    public void setScOy(ImageView view, float v) {
        view.setScOy(v);
    }
    @ReactProp(name = "scPercentageValue",defaultBoolean = false)
    public void setScPercentageValue(ImageView view, boolean v) {
        view.setScPercentageValue(v);
    }


}
