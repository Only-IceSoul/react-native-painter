//
//  extension_CGFloat.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/29/21.
//

import Foundation
import UIKit

public extension CGFloat {
    
    func toRadians()-> CGFloat{
        return (self * .pi) / 180
    }
    func toDegrees()-> CGFloat{
        return self * 180 / CGFloat.pi
    }
    
    func clamp(min:CGFloat = 0,max:CGFloat = 1) -> CGFloat{
        return self > max ? max : (self < min ? min : self)
    }
    func clampNotNegative()->CGFloat{
        return self < 0 ? 0 : self
    }
    func uclamp(_ optional:CGFloat = 0)->CGFloat{
        return self < 0 ? optional : self
    }
    func asFraction(_ start:CGFloat,_ end :CGFloat) -> CGFloat{
        return (self - start ) / ( end - start )
    }
    
    func asViewBoxToWidth(_ vb:CGRect,_ w:CGFloat) ->CGFloat{
        return self.asFraction(vb.left, vb.right) * w
    }
    func asViewBoxToHeight(_ vb:CGRect,_ h:CGFloat) ->CGFloat{
        return self.asFraction(vb.top, vb.bottom) * h
    }
    func asViewBoxToMax(_ vb:CGRect,_ w:CGFloat,_ h:CGFloat)->CGFloat{
        let size = vb.width > vb.height ? w : h
        let maxx = vb.width > vb.height ? vb.width : vb.height
        return ( self / maxx) * size
    }
}

public extension Float {
    
    func toRadians()-> Float{
    
        return (self * .pi) / 180
    }
    func toDegrees()-> Float{
        return self * 180 / Float.pi
    }
    
    func clamp(min:Float = 0,max:Float = 1) -> Float{
        return self > max ? max : (self < min ? min : self)
    }
    func clampNotNegative()->Float{
        return self < 0 ? 0 : self
    }
}
