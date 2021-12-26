//
//  CommonProps.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/29/21.
//

import UIKit

 class CommonProps {
  
    
    var fillColor : Int = 0xFF000000
    var fillColorStatus : Bool = false
    var fillOpacity :CGFloat = 1
    var fillOpacityStatus :Bool = false
    var fillRule = "none"
    var fillRuleStatus = false
    
    var strokeColor : Int = 0
    var strokeColorStatus = false
    var strokeOpacity :CGFloat = 1
    var strokeOpacityStatus = false
    var strokeWidth:CGFloat = 1
    var strokeWidthStatus = false
    var strokeCap = "none"
    var strokeJoin = "none"
    var strokeMiter :CGFloat = 4
    var strokeMiterStatus = false
    var strokeStart:CGFloat = 0
    var strokeStartStatus = false
    var strokeEnd :CGFloat = 1
    var strokeEndStatus = false
    
    var shadowColor = 0
    var shadowColorStatus = false
    var shadowOpacity:Float = 1
    var shadowOpacityStatus  = false
    var shadowRadius:CGFloat = 2
    var shadowRadiusStatus = false
    var shadowOffsetX :CGFloat = 2
    var shadowOffsetY :CGFloat = 2
    var shadowOffsetIsPercent = false
    var shadowOffsetXStatus = false
    var shadowOffsetYStatus = false
    var shadowOffsetIsPerecentStatus = false
    
 
    
    func getFillColor()->Int{
        return fillColor
    }
    func getFillOpacity()->CGFloat{
        return fillOpacity.clamp()
    }
    func getFillRule()->Bool{
        return fillRule == "evenodd"
    }
    
    func getStrokeColor()->Int{
        return strokeColor
    }
    func getStrokeOpacity()->CGFloat{
        return strokeOpacity.clamp()
    }
    func getStrokeWidth()->CGFloat{
        return strokeWidth.uclamp(1)
    }
    func getStrokeCap()->DrawableLineCap{
        return strokeCap == "round" ? .round : ( strokeCap == "square"  ? .square : .butt)
    }
    func getCGStrokeCap()->CGLineCap{
        return strokeCap == "round" ? .round : ( strokeCap == "square"  ? .square : .butt)
    }
    func getStrokeJoin()->DrawableLineJoin{
        return strokeJoin == "round" ? .round : ( strokeCap == "bevel"  ? .bevel : .miter)
    }
    func getCGStrokeJoin()->CGLineJoin{
        return strokeJoin == "round" ? .round : ( strokeCap == "bevel"  ? .bevel : .miter)
    }
    func getStrokeMiter()->CGFloat{
        return strokeMiter
    }
    func getStrokeStart()->CGFloat{
        return strokeStart.clamp()
    }
    func getStrokeEnd()->CGFloat{
        return strokeEnd.clamp()
    }
    
    func getShadowColor()->Int{
        return shadowColor
    }
    func getShadowOpacity()->Float{
        return shadowOpacity.clamp()
    }
    func getShadowRadius()->CGFloat{
        return shadowRadius.uclamp()
    }
    func getShadowOffsetY()->CGFloat{
        return shadowOffsetY
    }
    func getShadowOffsetX()->CGFloat{
        return shadowOffsetX
    }
    func getShadowOffsetIsPercent()->Bool{
        return shadowOffsetIsPercent
    }
    
    
     func set(_ props:CommonProps){
    

        if(!fillColorStatus){ fillColor = props.fillColor;}
        if(!fillOpacityStatus) {fillOpacity = props.fillOpacity;}
        if(!fillRuleStatus) {fillRule = props.fillRule;}

        if(!strokeColorStatus) {strokeColor = props.strokeColor;}
        if(!strokeOpacityStatus){ strokeOpacity = props.strokeOpacity;}
        if(!strokeWidthStatus) {strokeWidth = props.strokeWidth;}
        if(strokeCap == "none"){ strokeCap = props.strokeCap;}
        if(strokeJoin == "none"){ strokeJoin = props.strokeJoin;}
        if(!strokeMiterStatus) {strokeMiter = props.strokeMiter;}
        if(!strokeStartStatus) {strokeStart = props.strokeStart;}
        if(!strokeEndStatus) {strokeEnd = props.strokeEnd;}

        if(!shadowColorStatus) {shadowColor = props.shadowColor;}
        if(!shadowOpacityStatus) {shadowOpacity = props.shadowOpacity;}
        if(!shadowRadiusStatus) {shadowRadius = props.shadowRadius;}
        if(!shadowOffsetXStatus) {shadowOffsetX = props.shadowOffsetX;}
        if(!shadowOffsetYStatus) {shadowOffsetY = props.shadowOffsetY;}
        if(!shadowOffsetIsPerecentStatus) {shadowOffsetIsPercent = props.shadowOffsetIsPercent;}
       
    }
    
}
