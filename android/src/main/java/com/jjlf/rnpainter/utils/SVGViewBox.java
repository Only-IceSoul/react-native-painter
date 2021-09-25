package com.jjlf.rnpainter.utils;


import android.graphics.Matrix;
import android.graphics.RectF;

public class SVGViewBox {

    public static final int MOS_MEET = 0;
    public static final int MOS_SLICE = 1;
    public static final int MOS_NONE = 2;
    public static void transform(RectF vbRect,RectF eRect, String align, int meetOrSlice,float density, Matrix result) {
        // based on https://svgwg.org/svg2-draft/coords.html#ComputingAViewportsTransform

        // Let vb-x, vb-y, vb-width, vb-height be the min-x, min-y, width and height values of the viewBox attribute respectively.
        double vbX = vbRect.left * density;
        double vbY = vbRect.top * density;
        double vbX2 = vbRect.right * density;
        double vbY2 = vbRect.bottom * density;
        double vbWidth = vbX2 - vbX;
        double vbHeight = vbY2 - vbY;

        // Let e-x, e-y, e-width, e-height be the position and size of the element respectively.
        double eX = eRect.left;
        double eY = eRect.top;
        double eWidth = eRect.width();
        double eHeight = eRect.height();


        // Initialize scale-x to e-width/vb-width.
        double scaleX = eWidth / vbWidth;

        // Initialize scale-y to e-height/vb-height.
        double scaleY = eHeight / vbHeight;

        // Initialize translate-x to e-x - (vb-x * scale-x).
        // Initialize translate-y to e-y - (vb-y * scale-y).
        double translateX = eX - (vbX * scaleX);
        double translateY = eY - (vbY * scaleY);



        //If align is 'none'
        if (meetOrSlice == MOS_NONE) {
            // Let scale be set the smaller value of scale-x and scale-y.
            // Assign scale-x and scale-y to scale.
            double scale = scaleX = scaleY = Math.min(scaleX, scaleY);

            translateX += (eWidth - vbWidth * scaleX) / 2.0d;
            translateY += (eHeight - vbHeight * scaleY) / 2.0d;

        } else {
            // If align is not 'none' and meetOrSlice is 'meet', set the larger of scale-x and scale-y to the smaller.
            // Otherwise, if align is not 'none' and meetOrSlice is 'slice', set the smaller of scale-x and scale-y to the larger.

            if (!align.equals("none") && meetOrSlice == MOS_MEET) {
                scaleX = scaleY = Math.min(scaleX, scaleY);
            } else if (!align.equals("none") && meetOrSlice == MOS_SLICE) {
                scaleX = scaleY = Math.max(scaleX, scaleY);
            }

            // If align contains 'xMid', add (e-width - vb-width * scale-x) / 2 to translate-x.
            if (align.contains("xMid")) {
                translateX += (eWidth - vbWidth * scaleX) / 2.0d;
            }

            // If align contains 'xMax', add (e-width - vb-width * scale-x) to translate-x.
            if (align.contains("xMax")) {
                translateX += (eWidth - vbWidth * scaleX);
            }

            // If align contains 'yMid', add (e-height - vb-height * scale-y) / 2 to translate-y.
            if (align.contains("YMid")) {
                translateY += (eHeight - vbHeight * scaleY) / 2.0d;
            }

            // If align contains 'yMax', add (e-height - vb-height * scale-y) to translate-y.
            if (align.contains("YMax")) {
                translateY += (eHeight - vbHeight * scaleY);
            }
        }


        // The transform applied to content contained by the element is given by
        // translate(translate-x, translate-y) scale(scale-x, scale-y).
        result.reset();
        result.postScale((float) scaleX,(float) scaleY);
        result.postTranslate((float) translateX,(float) translateY);

    }

    public static void transform(RectF vbRect,RectF eRect, String align, int meetOrSlice,float density, float[] result) {
        // based on https://svgwg.org/svg2-draft/coords.html#ComputingAViewportsTransform

        // Let vb-x, vb-y, vb-width, vb-height be the min-x, min-y, width and height values of the viewBox attribute respectively.
        double vbX = vbRect.left * density;
        double vbY = vbRect.top * density;
        double vbX2 = vbRect.right * density;
        double vbY2 = vbRect.bottom * density;
        double vbWidth = vbX2 - vbX;
        double vbHeight = vbY2 - vbY;

        // Let e-x, e-y, e-width, e-height be the position and size of the element respectively.
        double eX = eRect.left;
        double eY = eRect.top;
        double eWidth = eRect.width();
        double eHeight = eRect.height();


        // Initialize scale-x to e-width/vb-width.
        double scaleX = eWidth / vbWidth;

        // Initialize scale-y to e-height/vb-height.
        double scaleY = eHeight / vbHeight;

        // Initialize translate-x to e-x - (vb-x * scale-x).
        // Initialize translate-y to e-y - (vb-y * scale-y).
        double translateX = eX - (vbX * scaleX);
        double translateY = eY - (vbY * scaleY);



         //If align is 'none'
        if (meetOrSlice == MOS_NONE) {
            // Let scale be set the smaller value of scale-x and scale-y.
            // Assign scale-x and scale-y to scale.
            double scale = scaleX = scaleY = Math.min(scaleX, scaleY);
            translateX += (eWidth - vbWidth * scaleX) / 2.0d;
            translateY += (eHeight - vbHeight * scaleY) / 2.0d;


        } else {
            // If align is not 'none' and meetOrSlice is 'meet', set the larger of scale-x and scale-y to the smaller.
            // Otherwise, if align is not 'none' and meetOrSlice is 'slice', set the smaller of scale-x and scale-y to the larger.

            if (!align.equals("none") && meetOrSlice == MOS_MEET) {
                scaleX = scaleY = Math.min(scaleX, scaleY);
            } else if (!align.equals("none") && meetOrSlice == MOS_SLICE) {
                scaleX = scaleY = Math.max(scaleX, scaleY);
            }

            // If align contains 'xMid', add (e-width - vb-width * scale-x) / 2 to translate-x.
            if (align.contains("xMid")) {
                translateX += (eWidth - vbWidth * scaleX) / 2.0d;
            }

            // If align contains 'xMax', add (e-width - vb-width * scale-x) to translate-x.
            if (align.contains("xMax")) {
                translateX += (eWidth - vbWidth * scaleX);
            }

            // If align contains 'yMid', add (e-height - vb-height * scale-y) / 2 to translate-y.
            if (align.contains("YMid")) {
                translateY += (eHeight - vbHeight * scaleY) / 2.0d;
            }

            // If align contains 'yMax', add (e-height - vb-height * scale-y) to translate-y.
            if (align.contains("YMax")) {
                translateY += (eHeight - vbHeight * scaleY);
            }
        }


        // The transform applied to content contained by the element is given by
        // translate(translate-x, translate-y) scale(scale-x, scale-y).

        result[0] = (float) scaleX;
        result[1] = (float) scaleY;
        result[2] = (float) translateX;
        result[3] = (float) translateY;

    }
}