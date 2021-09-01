//
//  extension_UIBezierPath.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/29/21.
//

import UIKit



extension UIBezierPath {
    

    func addRect(_ r:CGRect?){
        guard let rect = r else { return }
        let l = rect.origin.x
        let r = rect.origin.x + rect.width
        let b = rect.origin.y + rect.height
        let t = rect.origin.y
        move(to: CGPoint(x: l, y: t))
        addLine(to: CGPoint(x: r, y: t))
        addLine(to: CGPoint(x: r , y: b))
        addLine(to: CGPoint(x: l, y: b ))
        addLine(to:CGPoint(x: l, y: t))
        close()
    }
    
    func addRoundRect(_ r:CGRect?,radius:[CGFloat]){
        guard let rect = r else { return }
        var rtl = radius[0]
        var rtr = radius[1]
        var rbl = radius[2]
        var rbr = radius[3]
        
        var maxValue = max(rtl + rtr, rbl + rbr)
        maxValue = max(rtl + rbl, rtr + rbr)
        
        if(maxValue > rect.height || maxValue > rect.width){
            let size = min(rect.height, rect.width)
            rtl = (radius[0] / maxValue ) * size
            rtr = (radius[1] / maxValue ) * size
            rbl = (radius[2] / maxValue ) * size
            rbr = (radius[3] / maxValue ) * size
        }
        
        

        let l = rect.origin.x
        let r = rect.origin.x + rect.width
        let b = rect.origin.y + rect.height
        let t = rect.origin.y
        move(to: CGPoint(x: l + rtl, y: t) )
        
        addLine(to: CGPoint(x: r - rtr, y: t))
        addArc(withCenter: CGPoint(x: r - rtr, y: t + rtr), radius: rtr, startAngle: (3 * CGFloat.pi)  / 2, endAngle: 2 * CGFloat.pi, clockwise: true)
        
        addLine(to: CGPoint(x: r , y: b - rbr))
        addArc(withCenter: CGPoint(x: r - rbr, y: b - rbr), radius: rbr, startAngle: 0 , endAngle: CGFloat.pi / 2, clockwise: true)
        
        addLine(to: CGPoint(x: l + rbl, y: b))
        addArc(withCenter: CGPoint(x: l + rbl, y: b - rbl), radius: rbl, startAngle: CGFloat.pi / 2 , endAngle: CGFloat.pi, clockwise: true)
        
        
        addLine(to: CGPoint(x: l, y: t + rtl))
        addArc(withCenter: CGPoint(x: l + rtl, y: t + rtl), radius: rtl, startAngle: CGFloat.pi , endAngle: (3 * CGFloat.pi ) / 2, clockwise: true)
        
        close()
    }
    
}
