//
//  enums.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/29/21.
//


import Foundation
import UIKit

enum PathParserError: Error {
    case runtimeError(String)
    case IllegalStateException(String)
    case Exception(String)
    case IllegalArgumentException(String)
    case IndexOutOfBoundsException(String)
    case NullPointerException(String)
    case OperationCanceledException(String)
    case ParseException(String)
  
}

public enum DrawableAxis {
    case x, y , z
}

public enum DrawableLineCap : String{
    case butt,
         round,
         square
    
    internal func toTarget() -> CAShapeLayerLineCap
    {
        return CAShapeLayerLineCap.init(rawValue: rawValue)
    }
}

public enum GradientDrawableType : String{
    case axial,
         radial,
         conic
    internal func toTarget() -> CAGradientLayerType
    {
        return CAGradientLayerType(rawValue: rawValue)
    }
}

public enum DrawableLineJoin : String{
    case bevel,
         miter,
         round
    
    internal func toTarget() -> CAShapeLayerLineJoin
    {
        return CAShapeLayerLineJoin.init(rawValue: rawValue)
    }
}
