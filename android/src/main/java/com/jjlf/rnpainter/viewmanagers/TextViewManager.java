package com.jjlf.rnpainter.viewmanagers;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jjlf.rnpainter.views.TextView;

public class TextViewManager extends PaintableViewManager {

    @Override
    public String getName() { return "TextView"; }


    @Override
    protected TextView createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new TextView(reactContext);
    }

    @ReactProp(name = "text")
    public void setText(TextView view ,String v) {
        view.setText(v != null ? v : "");
    }

    @ReactProp(name = "baseline")
    public void setBaseline(TextView view ,String v) {
        view.setBaseline(v != null ? v : "none");
    }
    @ReactProp(name = "verticalOffset",defaultFloat = 0f)
    public void setVerticalOffset(TextView view ,float v) {
        view.setVerticalOffset(v);
    }
    @ReactProp(name = "horizontalOffset",defaultFloat = 0f)
    public void setHorizontalOffset(TextView view ,float v) {
        view.setHorizontalOffset(v);
    }
    @ReactProp(name = "x",defaultFloat = 0f)
    public void setX(TextView view ,float v) {
        view.setX(v);
    }

    @ReactProp(name = "y",defaultFloat = 0f)
    public void setY(TextView view ,float v) {
        view.setY(v);
    }

    @ReactProp(name = "font")
    public void setFont(TextView view ,String v) {
        view.setFont( v != null ? v : "default");
    }
    @ReactProp(name = "fontStyle")
    public void setFontStyle(TextView view ,String v) {
        view.setFontStyle( v != null ? v : "normal");
    }
    @ReactProp(name = "fontSize",defaultFloat = 12f)
    public void setFontSize(TextView view ,float v) {
        view.setFontSize(v);
    }



}
