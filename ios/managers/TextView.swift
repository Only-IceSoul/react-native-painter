//
//  TextView.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/30/21.
//

import Foundation
import UIKit

@objc(TextView)
class TextView: RCTViewManager {
    
    override func view() -> UIView! {
       return TextPaintableView()
     }
   override class func requiresMainQueueSetup() -> Bool {
        return true
    }
}
