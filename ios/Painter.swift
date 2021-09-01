//
//  Painter.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/29/21.
//

import Foundation
import UIKit

@objc(Painter)
class Painter: RCTViewManager {
    
    static var MaskViews = [Int]()

    override func view() -> UIView! {
       return PainterView()
     }
   override class func requiresMainQueueSetup() -> Bool {
        return true
    }
}
