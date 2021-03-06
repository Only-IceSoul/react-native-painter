// ReactNativePainterPackage.java

package com.jjlf.rnpainter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.jjlf.rnpainter.viewmanagers.CircleViewManager;
import com.jjlf.rnpainter.viewmanagers.EllipseViewManager;
import com.jjlf.rnpainter.viewmanagers.GViewManager;
import com.jjlf.rnpainter.viewmanagers.GViewManagerHardware;
import com.jjlf.rnpainter.viewmanagers.GViewManagerNone;
import com.jjlf.rnpainter.viewmanagers.LineViewManager;
import com.jjlf.rnpainter.viewmanagers.MaskGViewManager;
import com.jjlf.rnpainter.viewmanagers.MaskViewManager;
import com.jjlf.rnpainter.viewmanagers.PathViewManager;
import com.jjlf.rnpainter.viewmanagers.RectViewManager;
import com.jjlf.rnpainter.viewmanagers.TextViewManager;
import com.jjlf.rnpainter.viewmanagers.RadialGradientViewManager;
import com.jjlf.rnpainter.viewmanagers.LinearGradientViewManager;
import com.jjlf.rnpainter.viewmanagers.ImageViewManager;

public class ReactNativePainterPackage implements ReactPackage {
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Arrays.<ViewManager>asList(
                new GViewManagerNone(),
            new PainterViewManagerNone(),
            new ImageViewManager(),
              new RadialGradientViewManager(),
                new LinearGradientViewManager(),
                new EllipseViewManager(),
                new MaskGViewManager(),
                new RectViewManager(),
                new MaskViewManager(),
                new CircleViewManager(),
                new TextViewManager(),
                new LineViewManager(),
                new PainterViewManager(),
                new PainterViewManagerHardware(),
                new PathViewManager(),
                new GViewManager(),
                new GViewManagerHardware());
    }
}
