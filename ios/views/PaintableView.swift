//
//  PaintableView.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/29/21.
//


import Foundation
import UIKit


@objc(PaintableView)
 class PaintableView: UIView {
    

     init() {
        super.init(frame: .zero)
    }
    
     override var bounds: CGRect {
        didSet{
            getLayer()?.onBoundsChange(bounds)
        }
    }

    var mMask = ""
    var mOldMask = ""
    var mLazyInvalidateMask = false
    @objc func setMask(_ v:String?){
        let ev = v != nil ? v! : ""
        if mMask != ev{
            mOldMask = mMask
            mMask = ev
            invalidateMask()
        }
    }

    @objc func setupMaskListener(){
        //perfomance
//        if !mOldMask.isEmpty{
//            if let m = mPainter.mMaskViews[mOldMask] {
//
//            }
//        }
//        if !mMask.isEmpty{
//            if let m = mPainter.mMaskViews[mMask] {
//
//            }
//        }
    }
    
    var mPainter : PainterKit!
    @objc func invalidateMask(){
        if  mPainter != nil  {
            if !mMask.isEmpty{
                if let m = mPainter.mMaskViews[mMask]{
                    layer.mask = m.layer
                }else{
                    self.mask = nil
                }
            }else{
                self.mask = nil
            }
            
        }else{
            mLazyInvalidateMask = true
        }
    }
    
    open func getLayer() -> Paintable?{
        return nil
    }
    open func getCALayer() -> CALayer?{
        return nil
    }
    
    open func setProps(_ p : CommonProps){
        getLayer()?.setProps(p)
    }
    open func setPainterKit(_ p:PainterKit){
        mPainter = p
        getLayer()?.setPainterKit(p)
    }

    //MARK: set and get
    
    @objc func setTranslateZ(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        layer.zPosition = ev
 
    }
    
    @objc func setOpacity(_ v:NSNumber?) {
        let ev = Float(truncating: v ?? 1)
        layer.opacity = ev
    }
    
    
    @objc func setFill(_ v:NSNumber?) {
        let ev = Int(truncating: v ?? 0xFF000000)
        getLayer()?.setFill(ev, v != nil)
       
   }
    
    
    @objc func setFillRule(_ v:String?) {
        
        getLayer()?.setFillRule(v == nil ? "none" : v!, v != nil)
       
    }
    
    
    @objc func setFillOpacity(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 1)
        getLayer()?.setFillOpacity(ev, v != nil)
    }
    
    
    @objc func setStroke(_ v:NSNumber?) {
        let ev = Int(truncating: v ?? 0)
        getLayer()?.setStroke(ev, v != nil)
    }
    
    
    @objc func setStrokeOpacity(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 1)
        getLayer()?.setStrokeOpacity(ev, v != nil)
       
    }
    
    
    @objc func setStrokeWidth(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 1)
        getLayer()?.setStrokeWidth(ev, v != nil)
    }
    
    
    @objc func setStrokeCap(_ v:String?) {
        getLayer()?.setStrokeCap(v == nil ? "none" : v!)
       
    }
    
    
    @objc func setStrokeJoin(_ v:String?) {
        getLayer()?.setStrokeJoin(v == nil ? "none" : v!)
       
    }
    
    @objc func setStrokeMiter(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 4)
        getLayer()?.setStrokeMiter(ev, v != nil)
       
    }
    
    
    @objc func setStrokeStart(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        getLayer()?.setStrokeStart(ev, v != nil)
       
    }
    
    
    @objc func setStrokeEnd(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 1)
        getLayer()?.setStrokeEnd(ev, v != nil)
    }
    
    
    
    @objc func setShadow(_ v:NSNumber?) {
        let ev = Int(truncating: v ?? 0xFF000000)
        getLayer()?.setShadow(ev, v != nil)
       
    }
    
    @objc func setShadowOpacity(_ v:NSNumber?) {
        let ev = Float(truncating: v ?? 0)
        getLayer()?.setShadowOpacity(ev, v != nil)
    }
    
    
    @objc func setShadowRadius(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 2)
        getLayer()?.setShadowRadius(ev, v != nil)
    }
    
    

    @objc func setShadowOffset(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 2)
        getLayer()?.setShadowOffset(ev, v != nil)
    }
    @objc func setShadowOffsetX(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 2)
        getLayer()?.setShadowOffsetX(ev,v != nil)
    }
    @objc func setShadowOffsetY(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 2)
        getLayer()?.setShadowOffsetY(ev,v != nil)
    }
    @objc func setShadowPercentageValue(_ v:NSNumber?) {
        let n = v == nil ? 0 : Int(truncating: v!)
        let b = n >= 1 ? true : false
        getLayer()?.setShadowPercentageValue(b,v != nil)
        
    }
 
    //MARK: Transform props

    @objc func setTransX(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        getLayer()?.setTransX(v: ev)
       }
    @objc func setTransY(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        getLayer()?.setTransY(v: ev)
       }
    @objc func setTransPercentageValue(_ v:NSNumber?) {
        let n = v == nil ? 0 : Int(truncating: v!)
        let b = n >= 1 ? true : false
        getLayer()?.setTransPercentageValue(v: b)
       }

    @objc func setRot(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        getLayer()?.setRot(v: ev)
       }
    @objc func setRotO(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        getLayer()?.setRotO(v: ev)
       }
    @objc func setRotOx(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        getLayer()?.setRotOx(v: ev)
       }
    @objc func setRotOy(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        getLayer()?.setRotOy(v: ev)
       }
    @objc func setRotPercentageValue(_ v:NSNumber?) {
        let n = v == nil ? 0 : Int(truncating: v!)
        let b = n >= 1 ? true : false
        getLayer()?.setRotPercentageValue(v: b)
       }

    @objc func setSc(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 1)
        getLayer()?.setSc(v: ev)
       }
    @objc func setScX(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 1)
        getLayer()?.setScX(v: ev)
       }

    @objc func setScY(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 1)
        getLayer()?.setScY(v: ev)
       }
    @objc func setScO(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        getLayer()?.setScO(v: ev)
       }
    @objc func setScOx(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        getLayer()?.setScOx(v: ev)
       }
    @objc func setScOy(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        getLayer()?.setScOy(v: ev)
       }
    @objc func setScPercentageValue(_ v:NSNumber?) {
        let n = v == nil ? 0 : Int(truncating: v!)
        let b = n >= 1 ? true : false
        getLayer()?.setScPercentageValue(v: b)
       }
    
    
  
    
     override func layoutSubviews() {
        super.layoutSubviews()
        if(mLazyInvalidateMask){
            mLazyInvalidateMask = false
            invalidateMask()
        }
    }
   
  



    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
        
    }

}
