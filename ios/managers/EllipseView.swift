//
//  EllipseView.swift
//  react-native-painter
//
//  Created by Juan J LF on 9/1/21.
//

import Foundation
import UIKit

@objc(EllipseView)
class EllipseView: RCTViewManager {
    
    override func view() -> UIView! {
       return EllipsePaintableView()
     }
   override class func requiresMainQueueSetup() -> Bool {
        return true
    }
}
