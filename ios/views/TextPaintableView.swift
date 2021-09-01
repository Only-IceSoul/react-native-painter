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
    
    
    
    
    

   

    public override var bounds: CGRect {
        didSet{
            mLayer.onBoundsChange(bounds)
        }
    }


  
    
    open override func setProps(_ p : CommonProps){
        mLayer.setProps(p)
    }
    open override func setPainterKit(_ p:PainterKit){
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
    
 
    @objc override func setRotate(_ v:[String:Any]?) {
        let a = v?["a"] as? CGFloat ?? 0
        let y = v?["y"] as? CGFloat ?? 0
        let x = v?["x"] as? CGFloat ?? 0
        let percent = v?["percentageValue"] as? Bool ?? false
        mLayer.setRotation(a, x, y, percent)
    }
    
    @objc override func setScale(_ v:[String:Any]?) {
        let y = v?["y"] as? CGFloat ?? 1
        let x = v?["x"] as? CGFloat ?? 1
        let oy = v?["oy"] as? CGFloat ?? 0
        let ox = v?["ox"] as? CGFloat ?? 0
        let percent = v?["percentageValue"] as? Bool ?? false
        mLayer.setScale(x, y, ox, oy, percent)
    }
    
    @objc override func setTranslate(_ v:[String:Any]?) {
        let y = v?["y"] as? CGFloat ?? 0
        let x = v?["x"] as? CGFloat ?? 0
        let percent = v?["percentageValue"] as? Bool ?? false
        mLayer.setTranslation(x, y, percent)
       
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
        
    }

}
