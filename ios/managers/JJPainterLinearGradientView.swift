//
//  JJPainterLinearGradientView.swift
//  react-native-painter
//
//  Created by Juan J LF on 9/5/21.
//

import Foundation
@objc(JJPainterLinearGradientView)
class JJPainterLinearGradientView: RCTViewManager {
    
    override func view() -> UIView! {
       return LinearGradientView()
     }
   override class func requiresMainQueueSetup() -> Bool {
        return true
    }
}
