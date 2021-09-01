//
//  RectView.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/30/21.
//

import Foundation
import UIKit

@objc(RectView)
class RectView: RCTViewManager {
    
    override func view() -> UIView! {
       return RectPaintableView()
     }
   override class func requiresMainQueueSetup() -> Bool {
        return true
    }
    
 
}
