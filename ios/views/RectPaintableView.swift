//
//  RectPaintableView.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/30/21.
//

import Foundation
import UIKit


@objc(RectPaintableView)
class RectPaintableView: PaintableView {
    

    private var mLayer = RectLayer()
    
    override init() {
        super.init()
        layer.addSublayer(mLayer)

    }

    
 
    @objc func setX(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setX(ev)
    }
    @objc func setY(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setY(ev)
    }
    @objc func setW(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setW(ev)
    }
    @objc func setH(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setH(ev)
    }
    @objc func setRtl(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setRtl(ev)
    }
    @objc func setRtr(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setRtr(ev)
    }
    @objc func setRbl(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setRbl(ev)
    }
    @objc func setRbr(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setRbr(ev)
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
