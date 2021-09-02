//
//  Ellipse.swift
//  react-native-painter
//
//  Created by Juan J LF on 9/1/21.
//


import Foundation
import UIKit

public class EllipseLayer : Paintable{
    
    private var cx:CGFloat = 0
    private var cy:CGFloat = 0
    private var rx:CGFloat = 0
    private var ry:CGFloat = 0
    
    
    override init() {
        super.init()
        mIgnoreVbTransform = true
        
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
    public func setRx(_ v:CGFloat){
        if rx != v{
            rx = v
            invalidateCommonProps()
            invalidatePath()
        }
    }
    public func setRy(_ v:CGFloat){
        if ry != v{
            ry = v
            invalidateCommonProps()
            invalidatePath()
        }
    }
    
    public override func setupPath() {
       
        var cxx = cx
        var cyy = cy
        var ryy = ry
        var rxx = rx
        if mPainterKit.mIsViewBoxEnabled{
            cxx = cx.asViewBoxToWidth(mPainterKit.mViewBox, mRect.width)
            cyy = cy.asViewBoxToHeight(mPainterKit.mViewBox, mRect.height)
            rxx = rx.asViewBoxToMax(mPainterKit.mViewBox, mRect.width, mRect.height)
            ryy = ry.asViewBoxToMax(mPainterKit.mViewBox, mRect.width, mRect.height)
            
        }
        
        mPath = UIBezierPath(ovalIn: CGRect(x: cxx - rxx, y: cyy - ryy, width: rxx * 2, height: ryy * 2))
     
    }
    public required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}
