//
//  LineView.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/29/21.
//

import Foundation
import UIKit


@objc(LinePaintableView)
class LinePaintableView: PaintableView {
    

    private var mLayer = LineLayer()
    
    override init() {
        super.init()
        layer.addSublayer(mLayer)

    }

    
 
    @objc func setX1(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setX1(ev)
    }
    @objc func setY1(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setY1(ev)
    }
    @objc func setX2(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setX2(ev)
    }
    @objc func setY2(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setY2(ev)
    }
    
   

    override func getLayer() -> Paintable? {
        return mLayer
    }


    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
        
    }

}
