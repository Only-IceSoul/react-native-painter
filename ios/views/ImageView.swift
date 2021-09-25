//
//  ImageView.swift
//  react-native-painter
//
//  Created by Juan J LF on 9/7/21.
//

import Foundation

class ImageView :  PaintableView {
    
    
    private var mLayer = ImageLayer()
    
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
    
    
    @objc func setSource(_ v:String?){
        let s = v == nil ? "" : v!
        mLayer.setSrc(s)
    }
   
    @objc func setAlign(_ v:String?){
        let s = v == nil ? "xMidYMid" : v!
        mLayer.setAlign(s) 
    }
    @objc func setAspect(_ v:String?){
        let s = v == nil ? "meet" : v!
        mLayer.setAspect(s)
    }
    @objc func setClipToBounds(_ v:NSNumber?){
        let n = v == nil ? 0 : Int(truncating: v!)
        let b = n >= 1 ? true : false
        mLayer.setClipToBounds(b)
    }
    
     override var bounds: CGRect {
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
    
    
    @objc override func setFill(_ v:NSNumber?) {
        let ev = Int(truncating: v ?? 0xFF000000)
        mLayer.setFill(ev, v != nil)
       
   }
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
    
    @objc override func setShadow(_ v:NSNumber?) {
        let ev = Int(truncating: v ?? 0xFF000000)
        mLayer.setShadow(ev, v != nil)
       
    }
    
    @objc override func setShadowOpacity(_ v:NSNumber?) {
        let ev = Float(truncating: v ?? 0)
        mLayer.setShadowOpacity(ev, v != nil)
    }
    
    
    @objc override func setShadowRadius(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 2)
        mLayer.setShadowRadius(ev, v != nil)
    }
    
    
    @objc override func setShadowOffset(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 2)
        mLayer.setShadowOffset(ev, v != nil)
    }
    @objc override func setShadowOffsetX(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 2)
        mLayer.setShadowOffsetX(ev,v != nil)
    }
    @objc override func setShadowOffsetY(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 2)
        mLayer.setShadowOffsetY(ev,v != nil)
    }
    @objc override func setShadowPercentageValue(_ v:NSNumber?) {
        let n = v == nil ? 0 : Int(truncating: v!)
        let b = n >= 1 ? true : false
        mLayer.setShadowPercentageValue(b,v != nil)
        
    }
    
 
    //MARK: Transform props

    @objc override func setTransX(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setTransX(v: ev)
       }
    @objc override func setTransY(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setTransY(v: ev)
       }
    @objc override func setTransPercentageValue(_ v:NSNumber?) {
        let n = v == nil ? 0 : Int(truncating: v!)
        let b = n >= 1 ? true : false
        mLayer.setTransPercentageValue(v: b)
       }

    @objc override func setRot(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setRot(v: ev)
       }
    @objc override func setRotO(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setRotO(v: ev)
       }
    @objc override func setRotOx(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setRotOx(v: ev)
       }
    @objc override func setRotOy(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setRotOy(v: ev)
       }
    @objc override func setRotPercentageValue(_ v:NSNumber?) {
        let n = v == nil ? 0 : Int(truncating: v!)
        let b = n >= 1 ? true : false
        mLayer.setRotPercentageValue(v: b)
       }

    @objc override func setSc(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 1)
        mLayer.setSc(v: ev)
       }
    @objc override func setScX(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 1)
        mLayer.setScX(v: ev)
       }

    @objc override func setScY(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 1)
        mLayer.setScY(v: ev)
       }
    @objc override func setScO(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setScO(v: ev)
       }
    @objc override func setScOx(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setScOx(v: ev)
       }
    @objc override func setScOy(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setScOy(v: ev)
       }
    @objc override func setScPercentageValue(_ v:NSNumber?) {
        let n = v == nil ? 0 : Int(truncating: v!)
        let b = n >= 1 ? true : false
        mLayer.setScPercentageValue(v: b)
       }
    
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
        
    }

}

