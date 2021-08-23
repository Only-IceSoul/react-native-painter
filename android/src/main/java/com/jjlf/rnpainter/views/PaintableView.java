package com.jjlf.rnpainter.views;


import android.content.Context;
import android.view.View;

import com.jjlf.rnpainter.drawables.Paintable;
import com.jjlf.rnpainter.utils.CommonProps;
import com.jjlf.rnpainter.utils.PaintableInterface;
import com.jjlf.rnpainter.utils.PainterKit;
import com.jjlf.rnpainter.utils.TransformProps;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class PaintableView extends View implements PaintableInterface {


    public PaintableView(Context context){
        super(context);
    }
    public Paintable getDrawable(){
        return null;
    }


    @Override
    public void setProps(CommonProps props) {
        if(getDrawable() != null){
            getDrawable().setProps(props);
        }
    }

    @Override
    public void setTransforms(ArrayList<TransformProps> transforms) {
        if(getDrawable() != null){
            getDrawable().setTransforms(transforms);
        }
    }
    @Override
    public void setPainterKit(PainterKit painter) {
        if(getDrawable() != null){
            getDrawable().setPainterKit(painter);
        }
    }


}