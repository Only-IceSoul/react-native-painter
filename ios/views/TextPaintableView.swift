//
//  TextPaintableView.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/30/21.
//


import Foundation
import UIKit


@objc(TextPaintableView)
class TextPaintableView: PaintableView {
    

    private var mLayer = TextLayer()
   
    
    override init() {
        super.init()
        layer.addSublayer(mLayer)

    }
    
    @objc func setText(_ v:String?){
        let ev:String =  v != nil ? v! : ""
        mLayer.setText(ev)
    }
    @objc func setBaseline(_ v:String?){
        let ev = v != nil ? v! : "none"
        mLayer.setBaseline(ev)

    }
    @objc func setVerticalOffset(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setVerticalOffset(ev)
    }
    @objc func setHorizontalOffset(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setHorizontalOffset(ev)
    }
    @objc func setX(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setX(ev)
    }
    @objc func setY(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setY(ev)
    }
    
    @objc func setFont(_ v:String?){
        let ev = v != nil ? v! : "default"
        mLayer.setFont(ev)
    }
    @objc func setFontStyle(_ v:String?){
        let ev = v != nil ? v!  : "normal"
        mLayer.setFontStyle(ev)
    }
    @objc func setFontSize(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 16)
        mLayer.setFontSize(ev)

    }
    
    
    
    override func getCALayer() -> CALayer? {
        return mLayer
    }
    

   

    public override var bounds: CGRect {
        didSet{
            mLayer.onBoundsChange(bounds)
        }
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
    
    
    @objc override func setFill(_ v:NSNumber?) {
        let ev = Int(truncating: v ?? 0)
        mLayer.setFill(ev, v != nil)
       
   }
    
    
    @objc override func setFillRule(_ v:String?) {
        
        mLayer.setFillRule(v == nil ? "none" : v!, v != nil)
       
    }
    
    
    @objc override func setFillOpacity(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 1)
        mLayer.setFillOpacity(ev, v != nil)
    }
    
    
    @objc override func setStroke(_ v:NSNumber?) {
        let ev = Int(truncating: v ?? 0)
        mLayer.setStroke(ev, v != nil)
    }
    
    
    @objc override func setStrokeOpacity(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 1)
        mLayer.setStrokeOpacity(ev, v != nil)
       
    }
    
    
    @objc override func setStrokeWidth(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 1)
        mLayer.setStrokeWidth(ev, v != nil)
    }
    
    
    @objc override func setStrokeCap(_ v:String?) {
        mLayer.setStrokeCap(v == nil ? "none" : v!)
       
    }
    
    
    @objc override func setStrokeJoin(_ v:String?) {
        mLayer.setStrokeJoin(v == nil ? "none" : v!)
       
    }
    
    @objc override func setStrokeMiter(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 4)
        mLayer.setStrokeMiter(ev, v != nil)
       
    }
    
    
    @objc override func setStrokeStart(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        mLayer.setStrokeStart(ev, v != nil)
       
    }
    
    
    @objc override func setStrokeEnd(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 1)
        mLayer.setStrokeEnd(ev, v != nil)
    }
    
    
    
    @objc override func setShadow(_ v:NSNumber?) {
        let ev = Int(truncating: v ?? 0)
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
    
    
    @objc override func setShadowOffset(_ v:[String:Any]?) {
        let x = v?["x"] as? CGFloat ?? 2
        let y = v?["y"] as? CGFloat ?? 2
        let percent = v?["percentageValue"] as? Bool ?? false
        mLayer.setShadowOffset(x, y, percent, v != nil)
    }
    
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
