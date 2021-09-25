//
//  JJPainterRadialGradientView.swift
//  react-native-painter
//
//  Created by Juan J LF on 9/5/21.
//


import Foundation
@objc(JJPainterRadialGradientView)
class JJPainterRadialGradientView: RCTViewManager {
    
    override func view() -> UIView! {
       return RadialGradientView()
     }
   override class func requiresMainQueueSetup() -> Bool {
        return true
    }
}
