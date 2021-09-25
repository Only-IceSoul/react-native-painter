//
//  SVGViewBox.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/29/21.
//

import Foundation
import UIKit



class SVGViewBox {
    
    enum AspectRatio {
       case meet,
            slice,
            none
   }
   
    static func transform(vbRect:CGRect,eRect:CGRect,align:String,meetOrSlice:AspectRatio)->CGAffineTransform{
       
       // based on https://svgwg.org/svg2-draft/coords.html#ComputingAViewportsTransform
             // Let vb-x, vb-y, vb-width, vb-height be the min-x, min-y, width and height values of the viewBox attribute respectively.
       let vbX = vbRect.left;
       let vbY = vbRect.top;
       let vbWidth = vbRect.width;
       let vbHeight = vbRect.height;

             // Let e-x, e-y, e-width, e-height be the position and size of the element respectively.
       let eX = eRect.left;
       let eY = eRect.top;
       let eWidth = eRect.width;
       let eHeight = eRect.height;


             // Initialize scale-x to e-width/vb-width.
             var scaleX = eWidth / vbWidth;

             // Initialize scale-y to e-height/vb-height.
             var scaleY = eHeight / vbHeight;

             // Initialize translate-x to e-x - (vb-x * scale-x).
             // Initialize translate-y to e-y - (vb-y * scale-y).
             var translateX = eX - (vbX * scaleX);
             var translateY = eY - (vbY * scaleY);

             // If align is 'none'
           if (meetOrSlice == .none) {
                 // Let scale be set the smaller value of scale-x and scale-y.
                 // Assign scale-x and scale-y to scale.
               
                   scaleX = min(scaleX, scaleY);
                   scaleY = min(scaleX, scaleY);

               translateX += (eWidth - vbWidth * scaleX) / 2.0;
               translateY += (eHeight - vbHeight * scaleY) / 2.0;
             } else {
                 // If align is not 'none' and meetOrSlice is 'meet', set the larger of scale-x and scale-y to the smaller.
                 // Otherwise, if align is not 'none' and meetOrSlice is 'slice', set the smaller of scale-x and scale-y to the larger.
               if (!(align == "none") && meetOrSlice == .meet) {
                   scaleX = min(scaleX, scaleY);
                     scaleY = min(scaleX, scaleY);
               } else if (!(align == "none") && meetOrSlice == .slice) {
                     scaleX = max(scaleX, scaleY);
                     scaleY = max(scaleX, scaleY);
                 }

                 // If align contains 'xMid', add (e-width - vb-width * scale-x) / 2 to translate-x.
                 if (align.contains("xMid")) {
                     translateX += (eWidth - vbWidth * scaleX) / 2.0;
                 }

                 // If align contains 'xMax', add (e-width - vb-width * scale-x) to translate-x.
                 if (align.contains("xMax")) {
                     translateX += (eWidth - vbWidth * scaleX);
                 }

                 // If align contains 'yMid', add (e-height - vb-height * scale-y) / 2 to translate-y.
                 if (align.contains("YMid")) {
                     translateY += (eHeight - vbHeight * scaleY) / 2.0;
                 }

                 // If align contains 'yMax', add (e-height - vb-height * scale-y) to translate-y.
                 if (align.contains("YMax")) {
                     translateY += (eHeight - vbHeight * scaleY);
                 }

             }

             // The transform applied to content contained by the element is given by
             // translate(translate-x, translate-y) scale(scale-x, scale-y).
           
     

       return CGAffineTransform(translationX: translateX, y: translateY).scaledBy(x: scaleX, y: scaleY)
   }
   
    static func transform3D(vbRect:CGRect,eRect:CGRect,align:String,meetOrSlice:AspectRatio)->CATransform3D{
       
       // based on https://svgwg.org/svg2-draft/coords.html#ComputingAViewportsTransform
             // Let vb-x, vb-y, vb-width, vb-height be the min-x, min-y, width and height values of the viewBox attribute respectively.
       let vbX = vbRect.left;
       let vbY = vbRect.top;
       let vbWidth = vbRect.width;
       let vbHeight = vbRect.height;

             // Let e-x, e-y, e-width, e-height be the position and size of the element respectively.
       let eX = eRect.left;
       let eY = eRect.top;
       let eWidth = eRect.width;
       let eHeight = eRect.height;


             // Initialize scale-x to e-width/vb-width.
             var scaleX = eWidth / vbWidth;

             // Initialize scale-y to e-height/vb-height.
             var scaleY = eHeight / vbHeight;

             // Initialize translate-x to e-x - (vb-x * scale-x).
             // Initialize translate-y to e-y - (vb-y * scale-y).
             var translateX = eX - (vbX * scaleX);
             var translateY = eY - (vbY * scaleY);

             // If align is 'none'
           if (meetOrSlice == .none) {
                 // Let scale be set the smaller value of scale-x and scale-y.
                 // Assign scale-x and scale-y to scale.
                
                   scaleX = min(scaleX, scaleY);
                   scaleY = min(scaleX, scaleY);

               translateX += (eWidth - vbWidth * scaleX) / 2.0;
               translateY += (eHeight - vbHeight * scaleY) / 2.0;
             } else {
                 // If align is not 'none' and meetOrSlice is 'meet', set the larger of scale-x and scale-y to the smaller.
                 // Otherwise, if align is not 'none' and meetOrSlice is 'slice', set the smaller of scale-x and scale-y to the larger.
               if (!(align == "none") && meetOrSlice == .meet) {
                   scaleX = min(scaleX, scaleY);
                     scaleY = min(scaleX, scaleY);
               } else if (!(align == "none") && meetOrSlice == .slice) {
                     scaleX = max(scaleX, scaleY);
                     scaleY = max(scaleX, scaleY);
                 }

                 // If align contains 'xMid', add (e-width - vb-width * scale-x) / 2 to translate-x.
                 if (align.contains("xMid")) {
                     translateX += (eWidth - vbWidth * scaleX) / 2.0;
                 }

                 // If align contains 'xMax', add (e-width - vb-width * scale-x) to translate-x.
                 if (align.contains("xMax")) {
                     translateX += (eWidth - vbWidth * scaleX);
                 }

                 // If align contains 'yMid', add (e-height - vb-height * scale-y) / 2 to translate-y.
                 if (align.contains("YMid")) {
                     translateY += (eHeight - vbHeight * scaleY) / 2.0;
                 }

                 // If align contains 'yMax', add (e-height - vb-height * scale-y) to translate-y.
                 if (align.contains("YMax")) {
                     translateY += (eHeight - vbHeight * scaleY);
                 }

             }

             // The transform applied to content contained by the element is given by
             // translate(translate-x, translate-y) scale(scale-x, scale-y).
           
       
       var matrix = CATransform3DTranslate(CATransform3DIdentity, translateX, translateY, 0)
        matrix = CATransform3DScale(matrix, scaleX, scaleY, 1)
       return matrix
   }
    static func transformArray(vbRect:CGRect,eRect:CGRect,align:String,meetOrSlice:AspectRatio)->[CGFloat]{
       
       // based on https://svgwg.org/svg2-draft/coords.html#ComputingAViewportsTransform
             // Let vb-x, vb-y, vb-width, vb-height be the min-x, min-y, width and height values of the viewBox attribute respectively.
       let vbX = vbRect.left;
       let vbY = vbRect.top;
       let vbWidth = vbRect.width;
       let vbHeight = vbRect.height;

             // Let e-x, e-y, e-width, e-height be the position and size of the element respectively.
       let eX = eRect.left;
       let eY = eRect.top;
       let eWidth = eRect.width;
       let eHeight = eRect.height;


             // Initialize scale-x to e-width/vb-width.
             var scaleX = eWidth / vbWidth;

             // Initialize scale-y to e-height/vb-height.
             var scaleY = eHeight / vbHeight;

             // Initialize translate-x to e-x - (vb-x * scale-x).
             // Initialize translate-y to e-y - (vb-y * scale-y).
             var translateX = eX - (vbX * scaleX);
             var translateY = eY - (vbY * scaleY);

             // If align is 'none'
           if (meetOrSlice == .none) {
                 // Let scale be set the smaller value of scale-x and scale-y.
                 // Assign scale-x and scale-y to scale.
                 
                   scaleX = min(scaleX, scaleY);
                   scaleY = min(scaleX, scaleY);

               translateX += (eWidth - vbWidth * scaleX) / 2.0;
               translateY += (eHeight - vbHeight * scaleY) / 2.0;
             } else {
                 // If align is not 'none' and meetOrSlice is 'meet', set the larger of scale-x and scale-y to the smaller.
                 // Otherwise, if align is not 'none' and meetOrSlice is 'slice', set the smaller of scale-x and scale-y to the larger.
               if (!(align == "none") && meetOrSlice == .meet) {
                   scaleX = min(scaleX, scaleY);
                     scaleY = min(scaleX, scaleY);
               } else if (!(align == "none") && meetOrSlice == .slice) {
                     scaleX = max(scaleX, scaleY);
                     scaleY = max(scaleX, scaleY);
                 }

                 // If align contains 'xMid', add (e-width - vb-width * scale-x) / 2 to translate-x.
                 if (align.contains("xMid")) {
                     translateX += (eWidth - vbWidth * scaleX) / 2.0;
                 }

                 // If align contains 'xMax', add (e-width - vb-width * scale-x) to translate-x.
                 if (align.contains("xMax")) {
                     translateX += (eWidth - vbWidth * scaleX);
                 }

                 // If align contains 'yMid', add (e-height - vb-height * scale-y) / 2 to translate-y.
                 if (align.contains("YMid")) {
                     translateY += (eHeight - vbHeight * scaleY) / 2.0;
                 }

                 // If align contains 'yMax', add (e-height - vb-height * scale-y) to translate-y.
                 if (align.contains("YMax")) {
                     translateY += (eHeight - vbHeight * scaleY);
                 }

             }

             // The transform applied to content contained by the element is given by
             // translate(translate-x, translate-y) scale(scale-x, scale-y).
           
       
   
       return [translateX,translateY,scaleX,scaleY]
   }
   
}
