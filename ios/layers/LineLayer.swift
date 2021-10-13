//
//  LineLayer.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/29/21.
//

import Foundation
import UIKit

 class LineLayer : Paintable{
    
    private var x1:CGFloat = 0
    private var y1:CGFloat = 0
    private var x2:CGFloat = 0
    private var y2:CGFloat = 0
    
    
    override init() {
        super.init()
        mIgnoreFill = true
        
    }
    
    override init(layer: Any) {
        super.init(layer: layer)
    }
    
     func setX1(_ v:CGFloat){
        if x1 != v{
            x1 = v
            invalidate()
        }
    
    }
     func setY1(_ v:CGFloat){
        if y1 != v{
            y1 = v
            invalidate()
        }
    }
     func setX2(_ v:CGFloat){
        if x2 != v{
            x2 = v
            invalidate()
        }
    }
     func setY2(_ v:CGFloat){
        if y2 != v{
            y2 = v
            invalidate()
        }
    }
    
     override func setupPath() {
        mPath.removeAllPoints()
        mPath.move(to: .init(x: x1, y: y1))
        mPath.addLine(to: .init(x: x2, y: y2))
     
    }
     required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}
