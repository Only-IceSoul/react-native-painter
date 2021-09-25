//
//  MaskGView.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/31/21.
//

import Foundation
import UIKit

@objc(MaskGView)
class MaskGView: RCTViewManager {
    
    override func view() -> UIView! {
       return MaskGPaintableView()
     }
   override class func requiresMainQueueSetup() -> Bool {
        return true
    }
}
