//
//  extension_CGRect.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/29/21.
//

import UIKit


extension CGRect {
    
    var center : CGPoint {
         return .init(x: midX, y: midY)
    }
    
    var left : CGFloat {
        return self.origin.x
    }
    var top:CGFloat{
        return self.origin.y
    }
    var right : CGFloat {
        return self.origin.x + self.width
    }
    var bottom : CGFloat {
        return self.origin.y + self.height
    }

    mutating func setInset(dx: CGFloat,dy:CGFloat) {
        self.origin.x = self.origin.x + dx
        self.origin.y = self.origin.y + dy
        self.size.width = self.size.width - (dx*2)
        self.size.height = self.size.height - (dy*2)
    }
    
    
    mutating func set(rect: CGRect) {
        self.origin.x = rect.origin.x
        self.origin.y = rect.origin.y
        self.size.width = rect.size.width
        self.size.height = rect.size.height
    }
    mutating func set(left: CGFloat,top:CGFloat,right:CGFloat,bottom:CGFloat) {
        self.origin.x = left
        self.origin.y = top
        
        self.size.width = right - left
        self.size.height = bottom - top
    }
    
    mutating func setOffset(dx: CGFloat,dy:CGFloat) {
           self.origin.x = self.origin.x + dx
           self.origin.y = self.origin.y + dy
    }
    
    
    mutating func setScale(sx: CGFloat,sy:CGFloat) {
        if(sx < 0 && sy < 0 ){ return }
        let nw = self.width * sx
        let nh = self.height * sy
    
   
        let l = self.origin.x - (nw - self.width) / 2
        let t = self.origin.y - (nh - self.height) / 2

        self.origin.x = l
        self.origin.y = t
        self.size.width = nw
        self.size.height = nh
        
    }
    
    
    //MARK: Crop
   

   /**
     Returns true if the rectangle are inside or equal to this rectangle.
    **/
    mutating func contains(rect:CGRect)-> Bool{
        let rr = rect.origin.x + rect.width
        let rb = rect.origin.y + rect.height
        let r =  self.origin.x + self.width
        let b = self.origin.y + self.height
        return self.width > 0 && self.height > 0
            && self.origin.x <= rect.origin.x
            && self.origin.y <= rect.origin.y
            && r >= rr
            && b >= rb
    }
    
    
    func pointsToRotation(_ radian:CGFloat,cx:CGFloat,cy:CGFloat)-> [CGPoint]{
        let xAx = cos(radian)
        let xAy = sin(radian)
        let w = width
        let h = height
        var left = self.origin.x
        var top = self.origin.y
        left -= cx;
        top -= cy;

        let tl = CGPoint(x: left * xAx - top * xAy + cx , y: left * xAy + top * xAx + cy )
        let tr = CGPoint(x: (left + w) * xAx - top * xAy + cx , y: (left + w) * xAy + top * xAx + cy)
        let br = CGPoint(x: (left + w) * xAx - (top + h) * xAy + cx ,y:(left + w) * xAy + (top + h) * xAx + cy)
        let bl = CGPoint(x: left * xAx - (top + h) * xAy + cx, y:left * xAy + (top + h) * xAx + cy)

        return [tl,tr,bl,br]
          
    }
   
}
