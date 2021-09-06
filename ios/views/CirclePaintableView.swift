//
//  CirclePaintableView.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/30/21.
//


import Foundation
import UIKit


@objc(CirclePaintableView)
class CirclePaintableView: PaintableView {
    

    private var mLayer = CircleLayer()
    
    override init() {
        super.init()
        layer.addSublayer(mLayer)
    
    }

    
 
    @objc func setCx(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setCx(ev)
    }
    @objc func setCy(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setCy(ev)
    }
    @objc func setR(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setR(ev)
    }
  
    
   

    override func getLayer() -> Paintable? {
        return mLayer
    }


    override func getCALayer() -> CALayer? {
        return mLayer
    }
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
        
    }

}
