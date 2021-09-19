//
//  LinearGradientView.swift
//  react-native-painter
//
//  Created by Juan J LF on 9/5/21.
//

import Foundation
import UIKit

class LinearGradientView :  PaintableView {
    
    
    private var mLayer = LinearGradientLayer()
   
    
    override init() {
        super.init()
        layer.addSublayer(mLayer)

    }
    
    @objc func setX(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setX(ev)
    }
    @objc func setY(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setY(ev)
    }

    @objc func setW(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setW(ev)
    }
    @objc func setH(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setH(ev)
    }
    
    
    @objc func setStartPoint(_ v:[String:Any]?){
        let x = v?["x"] as? CGFloat ?? 0.5
        let y = v?["y"] as? CGFloat ?? 0
        mLayer.setStartPoint(x, y)
    }
    @objc func setEndPoint(_ v:[String:Any]?){
        let x = v?["x"] as? CGFloat ?? 0.5
        let y = v?["y"] as? CGFloat ?? 1
        mLayer.setEndPoint(x, y)
    }
    
    @objc func setColors(_ v:[NSNumber]?){
        
        if v == nil{
            mLayer.setColors([UIColor.white.cgColor,UIColor.black.cgColor])
        }else{
            var arr = [CGColor]()
            v!.forEach { (c) in
                let col = UIColor.parseInt(argb: Int(truncating: c) )
                arr.append(col.cgColor)
            }
            mLayer.setColors(arr)
        }
    }
    
    @objc func setPositions(_ p:[NSNumber]?){
        mLayer.setPositions(p)
    }

   

    public override var bounds: CGRect {
        didSet{
            mLayer.onBoundsChange(bounds)
        }
    }

    override func getCALayer() -> CALayer? {
        return mLayer
    }
  
    
    open override func setProps(_ p : CommonProps){
        mLayer.setProps(p)
    }
    open override func setPainterKit(_ p:PainterKit){
        mPainter = p
        mLayer.setPainterKit(p)
    }

    //MARK: set and get
    
    @objc override func setOpacity(_ v:NSNumber?) {
        let ev = Float(truncating: v ?? 1)
        
        mLayer.setOpacity(ev, v != nil)
    }
    
    
    @objc override func setFill(_ v:NSNumber?) {}
    @objc override func setFillRule(_ v:String?) {}
    @objc override func setFillOpacity(_ v:NSNumber?) {}
    @objc override func setStroke(_ v:NSNumber?) {}
    @objc override func setStrokeOpacity(_ v:NSNumber?) {}
    @objc override func setStrokeWidth(_ v:NSNumber?) {}
    @objc override func setStrokeCap(_ v:String?) {}
    @objc override func setStrokeJoin(_ v:String?) {}
    @objc override func setStrokeMiter(_ v:NSNumber?) {}
    @objc override func setStrokeStart(_ v:NSNumber?) {}
    @objc override func setStrokeEnd(_ v:NSNumber?) {}
    @objc override func setShadow(_ v:NSNumber?) {}
    @objc override func setShadowOpacity(_ v:NSNumber?) {}
    @objc override func setShadowRadius(_ v:NSNumber?) {}
    @objc override func setShadowOffset(_ v:[String:Any]?) {}
    
 
    //MARK: Transform props

    @objc override func setTransX(v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setTransX(v: ev)
       }
    @objc override func setTransY(v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setTransY(v: ev)
       }
    @objc override func setTransPercentageValue(v:NSNumber?) {
        let n = v == nil ? 0 : Int(truncating: v!)
        let b = n >= 1 ? true : false
        mLayer.setTransPercentageValue(v: b)
       }

    @objc override func setRot(v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setRot(v: ev)
       }
    @objc override func setRotO(v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setRotO(v: ev)
       }
    @objc override func setRotOx(v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setRotOx(v: ev)
       }
    @objc override func setRotOy(v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setRotOy(v: ev)
       }
    @objc override func setRotPercentageValue(v:NSNumber?) {
        let n = v == nil ? 0 : Int(truncating: v!)
        let b = n >= 1 ? true : false
        mLayer.setRotPercentageValue(v: b)
       }

    @objc override func setSc(v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 1)
        mLayer.setSc(v: ev)
       }
    @objc override func setScX(v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 1)
        mLayer.setScX(v: ev)
       }

    @objc override func setScY(v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 1)
        mLayer.setScY(v: ev)
       }
    @objc override func setScO(v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setScO(v: ev)
       }
    @objc override func setScOx(v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setScOx(v: ev)
       }
    @objc override func setScOy(v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setScOy(v: ev)
       }
    @objc override func setScPercentageValue(v:NSNumber?) {
        let n = v == nil ? 0 : Int(truncating: v!)
        let b = n >= 1 ? true : false
        mLayer.setScPercentageValue(v: b)
       }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
        
    }

}
