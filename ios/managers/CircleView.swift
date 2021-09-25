//
//  CircleView.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/30/21.
//

import Foundation
import UIKit

@objc(CircleView)
class CircleView: RCTViewManager {
    
    override func view() -> UIView! {
       return CirclePaintableView()
     }
   override class func requiresMainQueueSetup() -> Bool {
        return true
    }
}
