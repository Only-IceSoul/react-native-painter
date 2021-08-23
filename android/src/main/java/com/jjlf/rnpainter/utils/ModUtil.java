package com.jjlf.rnpainter.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

public class ModUtil {

    public static float clamp(float v){
        return v > 1 ? 1 : (v < 0 ? 0 : v);
    }
    public static float uClamp(float v){
        return v < 0 ? 0 : v;
    }

    public static float uClamp(float v,float optional){
        return v < 0 ? optional : v;
    }

    public static float viewBoxEvaluator(float value, float start,float end){
        return (value - start) / (end - start);
    }
    public static float viewBoxToWidth(float value, RectF viewBox, float w){
        return viewBoxEvaluator(value,viewBox.left,viewBox.right) * w;
    }
    public static float viewBoxToHeight(float value, RectF viewBox, float h){
        return viewBoxEvaluator(value,viewBox.top,viewBox.bottom) * h;
    }

    public static boolean isNull(Dynamic d){
        return d == null || d.isNull();
    }
    public static boolean isNotNull(Dynamic d){
        return d != null && !d.isNull();
    }
    public static float getFloat(Dynamic d, float optional){
        if(isNotNull(d)){
            return (float) d.asDouble();
        }
        return optional;
    }
    public static int getInt(Dynamic d, int optional){
        if(isNotNull(d)){
            return d.asInt();
        }
        return optional;
    }
    public static ReadableMap getMap(ReadableMap m, String name, ReadableMap optional){
        if(m != null){

            try{
                ReadableMap v = m.getMap(name);
                if(v != null){
                    return v;
                }
            }catch (Exception ignored){
                return optional;
            }
        }
        return optional;
    }
    public static String getString(ReadableMap m, String name, String optional){
        if(m != null){
            try{
                String v = m.getString(name);
                if(v != null){
                    return v;
                }
            }catch (Exception ignored){
                return optional;
            }

        }
        return optional;
    }
    public static double getDouble(ReadableMap m, String name, double optional){
        if(m != null){
            try{
                return m.getDouble(name);
            }catch (Exception ignored){
                return optional;
            }
        }
        return optional;
    }
    public static int getInt(ReadableMap m, String name, int optional){
        if(m != null){
            try{
                return m.getInt(name);
            }catch (Exception ignored){
                return optional;
            }
        }
        return optional;
    }
    public static boolean getBoolean(ReadableMap m, String name, boolean optional){
        if(m != null){
            try{
                return m.getBoolean(name);
            }catch (Exception ignored){
                return optional;
            }

        }
        return optional;
    }
    public static ReadableArray getArray(ReadableMap m, String name, ReadableArray optional){
        if(m != null){
            try{
                ReadableArray v = m.getArray(name);
                if(v != null){
                    return v;
                }
            }catch (Exception ignored){
                return optional;
            }
        }
        return optional;
    }

    public static int[] toIntArray(ReadableArray colors){
        int[] list = new int [colors.size()];
        for(int i = 0 ;   i < colors.size() ; i++){
            list[i] = colors.getInt(i);
        }
        return list;
    }
    public static float[] toFloatArray(ReadableArray colors){
        float[] list = new float [colors.size()];
        for(int i = 0 ;   i < colors.size() ; i++){
            list[i] = (float) colors.getDouble(i);
        }
        return list;
    }

    public static Bitmap toBitmap(Drawable drawable){
        if(drawable == null){
            return null;
        }
        if (drawable.getClass() == BitmapDrawable.class) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();

        Bitmap bitmap = Bitmap.createBitmap(width,height,  Bitmap.Config.ARGB_8888);
        drawable.draw(new Canvas(bitmap));
        return bitmap;
    }
}