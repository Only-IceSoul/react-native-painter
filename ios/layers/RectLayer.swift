//
//  RectLayer.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/29/21.
//

import Foundation
import UIKit

public class RectLayer : Paintable{
    
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
        mIgnoreVbTransform = true
    
    }
    
    
    
    public func setX(_ v:CGFloat){
        if x != v{
            x = v
            invalidateCommonProps()
            invalidatePath()
        }
    
    }
    public func setY(_ v:CGFloat){
        if y != v{
            y = v
            invalidateCommonProps()
            invalidatePath()
        }
    }
    public func setW(_ v:CGFloat){
        if w != v{
            w = v
            invalidateCommonProps()
            invalidatePath()
        }
    }
    public func setH(_ v:CGFloat){
        if h != v{
            h = v
            invalidateCommonProps()
            invalidatePath()
        }
    }
    public func setRtl(_ v:CGFloat){
        if rtl != v{
            rtl = v
            invalidateCommonProps()
            invalidatePath()
        }
    }
    public func setRtr(_ v:CGFloat){
        if rtr != v{
            rtr = v
            invalidateCommonProps()
            invalidatePath()
        }
    }
    
    public func setRbl(_ v:CGFloat){
        if rbl != v{
            rbl = v
            invalidateCommonProps()
            invalidatePath()
        }
    }
    public func setRbr(_ v:CGFloat){
        if rbr != v{
            rbr = v
            invalidateCommonProps()
            invalidatePath()
        }
    }
    
    public override func setupPath() {
        mPath.removeAllPoints()
        
        var xx = x
        var yy = y
        var ww = w
        var hh = h
        var rtll:CGFloat = rtl
        var rtrr:CGFloat = rtr
        var rbll:CGFloat = rbl
        var rbrr:CGFloat = rbr
       
        if mPainterKit.mIsViewBoxEnabled{
            xx = x.asViewBoxToWidth(mPainterKit.mViewBox, mRect.width)
            yy = y.asViewBoxToHeight(mPainterKit.mViewBox, mRect.height)
            ww = w.asViewBoxToWidth(mPainterKit.mViewBox, mRect.width)
            hh = h.asViewBoxToHeight(mPainterKit.mViewBox, mRect.height)
            rtll = rtl.asViewBoxToMax(mPainterKit.mViewBox, mRect.width, mRect.height)
            rtrr = rtr.asViewBoxToMax(mPainterKit.mViewBox, mRect.width, mRect.height)
            rbll = rbl.asViewBoxToMax(mPainterKit.mViewBox, mRect.width, mRect.height)
            rbrr = rbr.asViewBoxToMax(mPainterKit.mViewBox, mRect.width, mRect.height)
          
        }
        mBoundsRect.origin.x = xx
        mBoundsRect.origin.y = yy
        mBoundsRect.size.width = ww
        mBoundsRect.size.height = hh
        mRadius[0] = rtll
        mRadius[1] = rtrr
        mRadius[2] = rbll
        mRadius[3] = rbrr
        if rtll <= 0 && rtrr <= 0 && rbll <= 0 && rbrr <= 0 {
            mPath.addRect(mBoundsRect)
        }else{
            mPath.addRoundRect(mBoundsRect, radius: mRadius)
        }
        
       
      
        
    
     
    }
    public required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}

