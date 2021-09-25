//
//  RectLayer.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/29/21.
//

import Foundation
import UIKit

 class RectLayer : Paintable{
    
    private var x:CGFloat = 0
    private var y:CGFloat = 0
    private var w:CGFloat = 0
    private var h:CGFloat = 0
    private var rtl:CGFloat = 0
    private var rtr:CGFloat = 0
    private var rbl:CGFloat = 0
    private var rbr:CGFloat = 0
    private var mBoundsRect = CGRect()
    private var mRadius: [CGFloat] = [0,0,0,0]
    
    override init() {
        super.init()
    }
    
    
    
     func setX(_ v:CGFloat){
        if x != v{
            x = v
            invalidate()
        }
    
    }
     func setY(_ v:CGFloat){
        if y != v{
            y = v
            invalidate()
        }
    }
     func setW(_ v:CGFloat){
        if w != v{
            w = v
            invalidate()
        }
    }
     func setH(_ v:CGFloat){
        if h != v{
            h = v
            invalidate()
        }
    }
     func setRtl(_ v:CGFloat){
        if rtl != v{
            rtl = v
            invalidate()
        }
    }
     func setRtr(_ v:CGFloat){
        if rtr != v{
            rtr = v
            invalidate()
        }
    }
    
     func setRbl(_ v:CGFloat){
        if rbl != v{
            rbl = v
            invalidate()
        }
    }
     func setRbr(_ v:CGFloat){
        if rbr != v{
            rbr = v
            invalidate()
        }
    }
    
     override func setupPath() {
        mPath.removeAllPoints()
    
        mBoundsRect.origin.x = x
        mBoundsRect.origin.y = y
        mBoundsRect.size.width = w
        mBoundsRect.size.height = h
        mRadius[0] = rtl
        mRadius[1] = rtr
        mRadius[2] = rbl
        mRadius[3] = rbr
      
        mPath.addRoundRect(mBoundsRect, radius: mRadius)
        
     
    }
     required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}

