//
//  GView.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/30/21.
//

import Foundation
import UIKit

@objc(GView)
class GView: RCTViewManager {
    
    override func view() -> UIView! {
       return GPaintableView()
     }
   override class func requiresMainQueueSetup() -> Bool {
        return true
    }
}
