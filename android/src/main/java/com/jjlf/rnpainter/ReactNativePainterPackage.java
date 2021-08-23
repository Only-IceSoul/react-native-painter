// ReactNativePainterPackage.java

package com.jjlf.rnpainter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.jjlf.rnpainter.viewmanagers.GViewManager;
import com.jjlf.rnpainter.viewmanagers.GViewManagerHardware;
import com.jjlf.rnpainter.viewmanagers.LineViewManager;
import com.jjlf.rnpainter.viewmanagers.PathViewManager;

public class ReactNativePainterPackage implements ReactPackage {
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Arrays.<ViewManager>asList(
                new LineViewManager(),
                new PainterViewManager(),
                new PainterViewManagerHardware(),
                new PathViewManager(),
                new GViewManager(),
                new GViewManagerHardware());
    }
}
