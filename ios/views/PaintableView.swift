//
//  PaintableView.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/29/21.
//


import Foundation
import UIKit


@objc(PaintableView)
public class PaintableView: UIView {
    

    public init() {
        super.init(frame: .zero)
    }
    
    public override var bounds: CGRect {
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
                    getCALayer()?.mask = m.layer
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
        
        getLayer()?.setOpacity(ev, v != nil)
    }
    
    
    @objc func setFill(_ v:NSNumber?) {
        let ev = Int(truncating: v ?? 0)
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
        let ev = Int(truncating: v ?? 0)
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
    
    
    @objc func setShadowOffset(_ v:[String:Any]?) {
        let x = v?["x"] as? CGFloat ?? 2
        let y = v?["y"] as? CGFloat ?? 2
        let percent = v?["percentageValue"] as? Bool ?? false
        getLayer()?.setShadowOffset(x, y, percent, v != nil)
    }
    
 
    @objc func setRotate(_ v:[String:Any]?) {
        let a = v?["a"] as? CGFloat ?? 0
        let y = v?["y"] as? CGFloat ?? 0
        let x = v?["x"] as? CGFloat ?? 0
        let percent = v?["percentageValue"] as? Bool ?? false
        getLayer()?.setRotation(a, x, y, percent)
    }
    
    @objc func setScale(_ v:[String:Any]?) {
        let y = v?["y"] as? CGFloat ?? 1
        let x = v?["x"] as? CGFloat ?? 1
        let oy = v?["oy"] as? CGFloat ?? 0
        let ox = v?["ox"] as? CGFloat ?? 0
        let percent = v?["percentageValue"] as? Bool ?? false
        getLayer()?.setScale(x, y, ox, oy, percent)
    }
    
    @objc func setTranslate(_ v:[String:Any]?) {
        let y = v?["y"] as? CGFloat ?? 0
        let x = v?["x"] as? CGFloat ?? 0
        let percent = v?["percentageValue"] as? Bool ?? false
        getLayer()?.setTranslation(x, y, percent)
       
    }
    
    
  
    
    public override func layoutSubviews() {
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
