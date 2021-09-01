//
//  PathView.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/30/21.
//

import Foundation
import UIKit


@objc(PathPaintableView)
class PathPaintableView: PaintableView {
    

    private var mLayer = PathLayer()
    
    override init() {
        super.init()
        layer.addSublayer(mLayer)

    }

    
 
    @objc func setD(_ v:String?){
        let ev = v != nil ? v! : ""
        mLayer.setD(ev)
    }
    
    
   
    override func getLayer() -> Paintable? {
        return mLayer
    }


    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
        
    }

}
