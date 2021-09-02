//
//  EllipsePaintableView.swift
//  react-native-painter
//
//  Created by Juan J LF on 9/1/21.
//


import Foundation
import UIKit


@objc(EllipsePaintableView)
class EllipsePaintableView: PaintableView {
    

    private var mLayer = EllipseLayer()
    
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
    @objc func setRx(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setRx(ev)
    }
    @objc func setRy(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setRy(ev)
    }
    
   

    override func getLayer() -> Paintable? {
        return mLayer
    }


    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
        
    }

}
