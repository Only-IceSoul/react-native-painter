//
//  Ellipse.swift
//  react-native-painter
//
//  Created by Juan J LF on 9/1/21.
//


import Foundation
import UIKit

 class EllipseLayer : Paintable{
    
    private var cx:CGFloat = 0
    private var cy:CGFloat = 0
    private var rx:CGFloat = 0
    private var ry:CGFloat = 0
    
    
    override init() {
        super.init()
        
    }
    
    
    
     func setCx(_ v:CGFloat){
        if cx != v{
            cx = v
            invalidate()
        }
    
    }
     func setCy(_ v:CGFloat){
        if cy != v{
            cy = v
            invalidate()
        }
    }
     func setRx(_ v:CGFloat){
        if rx != v{
            rx = v
            invalidate()
        }
    }
     func setRy(_ v:CGFloat){
        if ry != v{
            ry = v
            invalidate()
        }
    }
    
     override func setupPath() {
       
        mPath.removeAllPoints()
        mPath = UIBezierPath(ovalIn: CGRect(x: cx - rx, y: cy - ry, width: rx * 2, height: ry * 2))
     
    }
     required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}
