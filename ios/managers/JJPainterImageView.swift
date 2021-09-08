//
//  JJPainterImageView.swift
//  react-native-painter
//
//  Created by Juan J LF on 9/7/21.
//

import UIKit
import Foundation
@objc(JJPainterImageView)
class JJPainterImageView: RCTViewManager {
    
    override func view() -> UIView! {
        return ImageView()
     }
   override class func requiresMainQueueSetup() -> Bool {
        return true
    }
}
