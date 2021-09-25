//
//  MaskView.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/31/21.
//

import Foundation
import UIKit

@objc(MaskView)
class MaskView: RCTViewManager {
    
    override func view() -> UIView! {
       return MaskPaintableView()
     }
   override class func requiresMainQueueSetup() -> Bool {
        return true
    }
}
