//
//  CircleLayer.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/29/21.
//

import Foundation
import UIKit

 class CircleLayer : Paintable{
    
    private var cx:CGFloat = 0
    private var cy:CGFloat = 0
    private var r:CGFloat = 0

    
    
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
     func setR(_ v:CGFloat){
        if r != v{
            r = v
            invalidate()
        }
    }
 
    
     override func setupPath() {
        mPath.removeAllPoints()
       
        let center = CGPoint(x: cx, y: cy)
        let radius = r
        
        mPath.addArc(withCenter: center, radius: radius, startAngle: 0, endAngle: 2 * .pi, clockwise: true)
     
    }
     required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}

