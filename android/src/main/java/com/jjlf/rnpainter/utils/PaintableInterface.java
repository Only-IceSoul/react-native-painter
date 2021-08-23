package com.jjlf.rnpainter.utils;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public interface PaintableInterface {
    void setProps(CommonProps props);
    void setTransforms(ArrayList<TransformProps> transforms);
    void setPainterKit(PainterKit painter);
}
