//
//  PathView.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/30/21.
//

import UIKit

@objc(PathView)
class PathView: RCTViewManager {
    
    override func view() -> UIView! {
       return PathPaintableView()
     }
   override class func requiresMainQueueSetup() -> Bool {
        return true
    }
}
