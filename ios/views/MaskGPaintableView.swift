//
//  MaskGPaintableView.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/31/21.
//

import Foundation
import UIKit
@objc(MaskGPaintableView)
public class MaskGPaintableView: PaintableView {
    
     

    private var mProps = CommonProps()
    private var mTransform = TransformProps()

    override init() {
        super.init()
        
    }
    public override var bounds: CGRect{
        didSet{
            layer.anchorPoint = CGPoint(x: 0, y: 0)
            layer.position = CGPoint(x: 0, y: 0)
        }
    }
    override func setMask(_ v: String?) {
        
    }
    
    public override func setProps(_ p : CommonProps){
        mProps.set(p)
        invalidateGroupProps()
    }
    public override func setPainterKit(_ p:PainterKit){
        mPainter = p
        invalidatePainterKit()
    }
    
    override func setTranslateZ(_ v: NSNumber?) {
        
    }
    
    @objc override func setOpacity(_ v:NSNumber?) {
        let ev = Float(truncating: v ?? 1)
        mProps.opacityStatus = v != nil
        if mProps.opacity != ev{
            mProps.opacity = ev
            invalidateGroupProps()
        }
  
    }
    
    
    @objc override func setFill(_ v:NSNumber?) {
        let ev = Int(truncating: v ?? 0)
        mProps.fillColorStatus = v != nil
        if mProps.fillColor != ev{
            mProps.fillColor = ev
            invalidateGroupProps()
        }
       
   }
    
    
    @objc override func setFillRule(_ v:String?) {
        let ev = v == nil ? "none" : v!
        mProps.fillRuleStatus = v != nil
        if mProps.fillRule != ev{
            mProps.fillRule = ev
            invalidateGroupProps()
        }
       
    }
    
    
    @objc override func setFillOpacity(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 1)
        mProps.fillOpacityStatus = v != nil
        if mProps.fillOpacity != ev{
            mProps.fillOpacity = ev
            invalidateGroupProps()
        }
    }
    
    
    @objc override func setStroke(_ v:NSNumber?) {
        let ev = Int(truncating: v ?? 0)
        mProps.strokeColorStatus = v != nil
        if mProps.strokeColor != ev{
            mProps.strokeColor = ev
            invalidateGroupProps()
        }
    }
    
    
    @objc override func setStrokeOpacity(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 1)
        mProps.strokeOpacityStatus = v != nil
        if mProps.strokeOpacity != ev{
            mProps.strokeOpacity = ev
            invalidateGroupProps()
        }
       
    }
    
    
    @objc override func setStrokeWidth(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 1)
        mProps.strokeWidthStatus = v != nil
        if mProps.strokeWidth != ev{
            mProps.strokeWidth = ev
            invalidateGroupProps()
        }
    }
    
    
    @objc override func setStrokeCap(_ v:String?) {
        let ev = v == nil ? "none" : v!
        if mProps.strokeCap != ev{
            mProps.strokeCap = ev
            invalidateGroupProps()
        }
    }
    
    
    @objc override func setStrokeJoin(_ v:String?) {
        let ev = v == nil ? "none" : v!
        if mProps.strokeJoin != ev{
            mProps.strokeJoin = ev
            invalidateGroupProps()
        }
    }
    
    @objc override func setStrokeMiter(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 4)
        mProps.strokeMiterStatus = v != nil
        if mProps.strokeMiter != ev{
            mProps.strokeMiter = ev
            invalidateGroupProps()
        }
    }
    
    
    @objc override func setStrokeStart(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        mProps.strokeStartStatus = v != nil
        if mProps.strokeStart != ev{
            mProps.strokeStart = ev
            invalidateGroupProps()
        }
       
    }
    
    
    @objc override func setStrokeEnd(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 1)
        mProps.strokeEndStatus = v != nil
        if mProps.strokeEnd != ev{
            mProps.strokeEnd = ev
            invalidateGroupProps()
        }
    }
    
    
    
    @objc override func setShadow(_ v:NSNumber?) {
        let ev = Int(truncating: v ?? 0)
        mProps.shadowColorStatus = v != nil
        if mProps.shadowColor != ev{
            mProps.shadowColor = ev
            invalidateGroupProps()
        }
    }
    
    @objc override func setShadowOpacity(_ v:NSNumber?) {
        let ev = Float(truncating: v ?? 0)
        mProps.shadowOpacityStatus = v != nil
        if mProps.shadowOpacity != ev{
            mProps.shadowOpacity = ev
            invalidateGroupProps()
        }
    }
    
    
    @objc override func setShadowRadius(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 2)
        mProps.shadowRadiusStatus = v != nil
        if mProps.shadowRadius != ev{
            mProps.shadowRadius = ev
            invalidateGroupProps()
        }
    }
    
    
    @objc override func setShadowOffset(_ v:[String:Any]?) {
        let x = v?["x"] as? CGFloat ?? 2
        let y = v?["y"] as? CGFloat ?? 2
        let percent = v?["percentageValue"] as? Bool ?? false
        mProps.shadowOffsetStatus = v != nil
        if mProps.shadowOffsetX != x || mProps.shadowOffsetY != y || mProps.shadowOffsetIsPercent != percent {
            mProps.shadowOffsetX = x
            mProps.shadowOffsetY = y
            mProps.shadowOffsetIsPercent = percent
            invalidateGroupProps()
        }
    }
    
 
    //MARK: Transform props

    @objc override func setTransX(v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        if(mTransform.mTranslationX != ev ){
            mTransform.mTranslationX = ev;
            invalidateTransform();
        }
       }
    @objc override func setTransY(v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        if(mTransform.mTranslationY != ev ){
            mTransform.mTranslationY = ev;
            invalidateTransform();
        }
       }
    @objc override func setTransPercentageValue(v:NSNumber?) {
        let n = v == nil ? 0 : Int(truncating: v!)
        let b = n >= 1 ? true : false
        if(mTransform.mTranslationIsPercent != b ){
            mTransform.mTranslationIsPercent = b;
            invalidateTransform();
        }
       }

    @objc override func setRot(v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        if(mTransform.mRotation != ev ){
            mTransform.mRotation = ev;
            invalidateTransform();
        }
       }
    @objc override func setRotO(v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        if(mTransform.mRotationOriginX != ev || mTransform.mRotationOriginY != ev ){
            mTransform.mRotationOriginX = ev;
            mTransform.mRotationOriginY = ev;
            invalidateTransform();
        }
       }
    @objc override func setRotOx(v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        if(mTransform.mRotationOriginX != ev ){
            mTransform.mRotationOriginX = ev;
            invalidateTransform();
        }
       }
    @objc override func setRotOy(v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        if(mTransform.mRotationOriginY != ev ){
            mTransform.mRotationOriginY = ev;
            invalidateTransform();
        }
       }
    @objc override func setRotPercentageValue(v:NSNumber?) {
        let n = v == nil ? 0 : Int(truncating: v!)
        let b = n >= 1 ? true : false
        if(mTransform.mRotationIsPercent != b ){
            mTransform.mRotationIsPercent = b
            invalidateTransform();
        }
       }

    @objc override func setSc(v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 1)
        if(mTransform.mScaleX != ev || mTransform.mScaleY != ev){
            mTransform.mScaleX = ev;
            mTransform.mScaleY = ev;
            invalidateTransform();
        }
       }
    @objc override func setScX(v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 1)
        if(mTransform.mScaleX != ev ){
            mTransform.mScaleX = ev;
            invalidateTransform();
        }
       }

    @objc override func setScY(v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 1)
        if(mTransform.mScaleY != ev ){
            mTransform.mScaleY = ev;
            invalidateTransform();
        }
       }
    @objc override func setScO(v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        if(mTransform.mScaleOriginX != ev || mTransform.mScaleOriginY != ev){
            mTransform.mScaleOriginX = ev;
            mTransform.mScaleOriginY = ev;
            invalidateTransform();
        }
       }
    @objc override func setScOx(v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        if(mTransform.mScaleOriginX != ev ){
            mTransform.mScaleOriginX = ev;
            invalidateTransform();
        }
       }
    @objc override func setScOy(v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        if(mTransform.mScaleOriginY != ev ){
            mTransform.mScaleOriginY = ev;
            invalidateTransform();
        }
       }
    @objc override func setScPercentageValue(v:NSNumber?) {
        let n = v == nil ? 0 : Int(truncating: v!)
        let b = n >= 1 ? true : false
        if(mTransform.mScaleIsPercent != b ){
            mTransform.mScaleIsPercent = b
            invalidateTransform();
        }
       }
    
    
    private var mLazyProps = false
    public func invalidateGroupProps(){
        if reactSubviews() == nil {
            mLazyProps = true
        }else{
            for i in 0..<self.reactSubviews()!.count {
                let v = self.reactSubviews()![i]
                if let p = v as? PaintableView{
                    p.setProps(mProps)
                }
            }
        }
      
    }
    
    private var mLazyPainter = false
    public func invalidatePainterKit(){
        
        if reactSubviews() == nil {
            mLazyPainter = true
        }else{
            for i in 0..<self.reactSubviews()!.count {
                let v = self.reactSubviews()![i]
                if let p = v as? PaintableView{
                    p.setPainterKit(mPainter)
                }
            }
        }
      
    }
    
    public func invalidateTransform(){
        if(bounds.width > 0 && bounds.height > 0 && mPainter != nil){
            var matrix = CATransform3DIdentity
          
            
            if mTransform.mTranslationX != 0 || mTransform.mTranslationY != 0{
                var transX = mTransform.mTranslationX
                var transY = mTransform.mTranslationY
                if mTransform.mTranslationIsPercent{
                    transX = mTransform.mTranslationX * bounds.width
                    transY = mTransform.mTranslationY * bounds.height
                }else if mPainter.mIsViewBoxEnabled {
                    transX = (mTransform.mTranslationX / mPainter.mViewBox.width) * bounds.width
                    transY = (mTransform.mTranslationY / mPainter.mViewBox.height) * bounds.height
                }
                
                matrix = CATransform3DTranslate(matrix, transX, transY, 0)
            }
            
            if mTransform.mRotation != 0{
                var rotX = mTransform.mRotationOriginX
                var rotY = mTransform.mRotationOriginY
                if mTransform.mRotationIsPercent{
                    rotX = mTransform.mRotationOriginX * bounds.width
                    rotY = mTransform.mRotationOriginY * bounds.height
                }else if mPainter.mIsViewBoxEnabled {
                   
                    rotX = mTransform.mRotationOriginX.asViewBoxToWidth(mPainter.mViewBox, bounds.width)
                    rotY = mTransform.mRotationOriginY.asViewBoxToHeight(mPainter.mViewBox, bounds.height)
                }
                matrix = CATransform3DTranslate(matrix, rotX, rotY, 0)
                matrix = CATransform3DRotate(matrix, mTransform.mRotation.toRadians(), 0, 0, 1)
                matrix = CATransform3DTranslate(matrix, -rotX, -rotY, 0)
            }
            
            if mTransform.mScaleX != 1 || mTransform.mScaleY != 1{
                var ox = mTransform.mScaleOriginX
                var oy = mTransform.mScaleOriginY
                if mTransform.mScaleIsPercent {
                    ox = mTransform.mScaleOriginX * bounds.width
                    oy = mTransform.mScaleOriginY * bounds.height
                }else if mPainter.mIsViewBoxEnabled{
                    ox = mTransform.mScaleOriginX.asViewBoxToWidth(mPainter.mViewBox, bounds.width)
                    oy = mTransform.mScaleOriginY.asViewBoxToHeight(mPainter.mViewBox, bounds.height)
                }
                matrix = CATransform3DTranslate(matrix, ox, oy, 0)
                matrix = CATransform3DScale(matrix, mTransform.mScaleX, mTransform.mScaleY, 1)
                matrix = CATransform3DTranslate(matrix, -ox, -oy, 0)
            }

            disableAnimation()
            layer.transform = matrix
            commit()
        }
    }
    public override func didUpdateReactSubviews() {
        if reactSubviews() == nil {
            return
        }

        for i in 0..<self.reactSubviews()!.count {
            let v = self.reactSubviews()![i]
            self.addSubview(v)
        }
    }

    
    public override func layoutSubviews() {
        super.layoutSubviews()
        if(mLazyProps || mLazyPainter && self.reactSubviews() != nil){
            let pr = mLazyProps
            let pp = mLazyPainter
            mLazyProps = false
            mLazyPainter = false
     
            for i in 0..<self.reactSubviews()!.count {
                let v = self.reactSubviews()![i]
                if let p = v as? PaintableView{
                    if pr { p.setProps(mProps) }
                    if pp { p.setPainterKit(mPainter) }
                }
            }
        }
        invalidateTransform()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
        
    }
    
    func disableAnimation(){
       CATransaction.begin()
       CATransaction.setDisableActions(true)
   }
   
    func commit(){
       CATransaction.commit()
   }

}
