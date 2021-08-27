package com.jjlf.rnpainter.utils;

import android.graphics.Canvas;

import java.util.ArrayList;

public interface MaskInterface {
     ArrayList<PaintableInterface> getListeners();
     void addListener(PaintableInterface listener) ;
     void removeListener(PaintableInterface listener);
     String getName();
     void render(Canvas canvas);
}
