//
//  CircleLayer.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/29/21.
//

import Foundation
import UIKit

public class CircleLayer : Paintable{
    
    private var cx:CGFloat = 0
    private var cy:CGFloat = 0
    private var r:CGFloat = 0

    
    
    override init() {
        super.init()
        mIgnoreVbTransform = true
        namePaint = "circle"
    }
    
    
    
    public func setCx(_ v:CGFloat){
        if cx != v{
            cx = v
            invalidateCommonProps()
            invalidatePath()
        }
    
    }
    public func setCy(_ v:CGFloat){
        if cy != v{
            cy = v
            invalidateCommonProps()
            invalidatePath()
        }
    }
    public func setR(_ v:CGFloat){
        if r != v{
            r = v
            invalidateCommonProps()
            invalidatePath()
        }
    }
 
    
    public override func setupPath() {
        mPath.removeAllPoints()
       
        var center = CGPoint(x: cx, y: cy)
        var radius = r
        
        
        if mPainterKit.mIsViewBoxEnabled{
            center.x = cx.asViewBoxToWidth(mPainterKit.mViewBox, mRect.width)
            center.y = cy.asViewBoxToHeight(mPainterKit.mViewBox, mRect.height)
            radius = r.asViewBoxToMax(mPainterKit.mViewBox, mRect.width, mRect.height)
        }
        
        mPath.addArc(withCenter: center, radius: radius, startAngle: 0, endAngle: 2 * .pi, clockwise: true)
     
    }
    public required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}

