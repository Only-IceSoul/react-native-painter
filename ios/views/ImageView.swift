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
    
    
    @objc override func setFill(_ v:NSNumber?) {
        let ev = Int(truncating: v ?? 0)
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

