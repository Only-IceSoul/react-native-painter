//
//  Line.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/29/21.
//

import UIKit

@objc(LineView)
class LineView: RCTViewManager {
    
    override func view() -> UIView! {
       return LinePaintableView()
     }
   override class func requiresMainQueueSetup() -> Bool {
        return true
    }
}
