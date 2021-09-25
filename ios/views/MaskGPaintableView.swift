//
//  MaskGPaintableView.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/31/21.
//

import Foundation
import UIKit
@objc(MaskGPaintableView)
 class MaskGPaintableView: PaintableView {
    
     

    private var mProps = CommonProps()
    private var mTransform = TransformProps()
    private var mBounds = CGRect()
    var mRectPath = CGRect()
    var mRectVb = CGRect(x: 0, y: 0, width: -1, height: -1)
    var mAlign = "xMidYMid"
    var mAspect = SVGViewBox.AspectRatio.meet
    
    override init() {
        super.init()
        
    }
     override var bounds: CGRect{
        didSet{
            mBounds.set(rect: bounds)
            layer.anchorPoint = CGPoint(x: 0, y: 0)
            layer.position = CGPoint(x: 0, y: 0)
        }
    }
    override func setMask(_ v: String?) {
        
    }
    
     override func setProps(_ p : CommonProps){
        mProps.set(p)
        invalidateGroupProps()
    }
     override func setPainterKit(_ p:PainterKit){
        mPainter = p
        mRectVb.set(rect: p.mViewBox)
        mAspect = p.mAspect
        mAlign = p.mAlign
        invalidatePainterKit()
        invalidateTransform()
    }
    
      
    
    @objc override func setFill(_ v:NSNumber?) {
        let ev = Int(truncating: v ?? 0xFF000000)
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
        let ev = Int(truncating: v ?? 0xFF000000)
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
    func setShadowOffset(_ v:CGFloat,_ status:Bool){
       mProps.shadowOffsetXStatus = status
       mProps.shadowOffsetYStatus = status
       if mProps.shadowOffsetX != v || mProps.shadowOffsetY != v  {
           mProps.shadowOffsetX = v
           mProps.shadowOffsetY = v
           invalidateGroupProps()
       }
 
   }
   
    func setShadowOffsetX(_ v:CGFloat,_ status:Bool){
       mProps.shadowOffsetXStatus = status
       if mProps.shadowOffsetX != v {
           mProps.shadowOffsetX = v
           invalidateGroupProps()
       }

   }
   
    func setShadowOffsetY(_ v:CGFloat,_ status:Bool){
       mProps.shadowOffsetYStatus = status
       if mProps.shadowOffsetY != v  {
           mProps.shadowOffsetY = v
           invalidateGroupProps()
       }
   }

    func setShadowPercentageValue(_ v:Bool,_ status:Bool){
       mProps.shadowOffsetIsPerecentStatus = status
       if mProps.shadowOffsetIsPercent != v  {
           mProps.shadowOffsetIsPercent = v
           invalidateGroupProps()
       }
   }

  
    
 
    //MARK: Transform props

    @objc override func setTransX(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        if(mTransform.mTranslationX != ev ){
            mTransform.mTranslationX = ev;
            invalidateTransform();
        }
       }
    @objc override func setTransY(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        if(mTransform.mTranslationY != ev ){
            mTransform.mTranslationY = ev;
            invalidateTransform();
        }
       }
    @objc override func setTransPercentageValue(_ v:NSNumber?) {
        let n = v == nil ? 0 : Int(truncating: v!)
        let b = n >= 1 ? true : false
        if(mTransform.mTranslationIsPercent != b ){
            mTransform.mTranslationIsPercent = b;
            invalidateTransform();
        }
       }

    @objc override func setRot(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        if(mTransform.mRotation != ev ){
            mTransform.mRotation = ev;
            invalidateTransform();
        }
       }
    @objc override func setRotO(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        if(mTransform.mRotationOriginX != ev || mTransform.mRotationOriginY != ev ){
            mTransform.mRotationOriginX = ev;
            mTransform.mRotationOriginY = ev;
            invalidateTransform();
        }
       }
    @objc override func setRotOx(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        if(mTransform.mRotationOriginX != ev ){
            mTransform.mRotationOriginX = ev;
            invalidateTransform();
        }
       }
    @objc override func setRotOy(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        if(mTransform.mRotationOriginY != ev ){
            mTransform.mRotationOriginY = ev;
            invalidateTransform();
        }
       }
    @objc override func setRotPercentageValue(_ v:NSNumber?) {
        let n = v == nil ? 0 : Int(truncating: v!)
        let b = n >= 1 ? true : false
        if(mTransform.mRotationIsPercent != b ){
            mTransform.mRotationIsPercent = b
            invalidateTransform();
        }
       }

    @objc override func setSc(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 1)
        if(mTransform.mScaleX != ev || mTransform.mScaleY != ev){
            mTransform.mScaleX = ev;
            mTransform.mScaleY = ev;
            invalidateTransform();
        }
       }
    @objc override func setScX(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 1)
        if(mTransform.mScaleX != ev ){
            mTransform.mScaleX = ev;
            invalidateTransform();
        }
       }

    @objc override func setScY(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 1)
        if(mTransform.mScaleY != ev ){
            mTransform.mScaleY = ev;
            invalidateTransform();
        }
       }
    @objc override func setScO(_ v:NSNumber?){
        let ev = CGFloat(truncating: v ?? 0)
        if(mTransform.mScaleOriginX != ev || mTransform.mScaleOriginY != ev){
            mTransform.mScaleOriginX = ev;
            mTransform.mScaleOriginY = ev;
            invalidateTransform();
        }
       }
    @objc override func setScOx(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        if(mTransform.mScaleOriginX != ev ){
            mTransform.mScaleOriginX = ev;
            invalidateTransform();
        }
       }
    @objc override func setScOy(_ v:NSNumber?) {
        let ev = CGFloat(truncating: v ?? 0)
        if(mTransform.mScaleOriginY != ev ){
            mTransform.mScaleOriginY = ev;
            invalidateTransform();
        }
       }
    @objc override func setScPercentageValue(_ v:NSNumber?) {
        let n = v == nil ? 0 : Int(truncating: v!)
        let b = n >= 1 ? true : false
        if(mTransform.mScaleIsPercent != b ){
            mTransform.mScaleIsPercent = b
            invalidateTransform();
        }
       }
    
    
    private var mLazyProps = false
     func invalidateGroupProps(){
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
     func invalidatePainterKit(){
        
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
    
    func invalidateTransform(){
    
       if(mBounds.width > 0 && mBounds.height > 0 ){
        
          viewBoxTransform()
        
           var matrix = CATransform3DIdentity
         
           
           if mTransform.mTranslationX != 0 || mTransform.mTranslationY != 0{
               var transX = mTransform.mTranslationX
               var transY = mTransform.mTranslationY
               if mTransform.mTranslationIsPercent{
                   transX = mTransform.mTranslationX * mRectPath.width
                   transY = mTransform.mTranslationY * mRectPath.height
               }else if validateViewBox() {
                   transX = (mTransform.mTranslationX / mRectVb.size.width) * mRectPath.width
                   transY = (mTransform.mTranslationY / mRectVb.size.height) * mRectPath.height
               }
               
               matrix = CATransform3DTranslate(matrix, transX, transY, 0)
           }
           
           if mTransform.mRotation != 0{
               var rotX = mTransform.mRotationOriginX
               var rotY = mTransform.mRotationOriginY
               if mTransform.mRotationIsPercent{
                   rotX = mTransform.mRotationOriginX * mRectPath.width
                   rotY = mTransform.mRotationOriginY * mRectPath.height
               }else if validateViewBox(){
                  
                   rotX = mRectPath.left + mTransform.mRotationOriginX.asViewBoxToWidth(mRectVb, mRectPath.width)
                   rotY = mRectPath.top + mTransform.mRotationOriginY.asViewBoxToHeight(mRectVb, mRectPath.height)
               }
               matrix = CATransform3DTranslate(matrix, rotX, rotY, 0)
               matrix = CATransform3DRotate(matrix, mTransform.mRotation.toRadians(), 0, 0, 1)
               matrix = CATransform3DTranslate(matrix, -rotX, -rotY, 0)
           }
      
           if mTransform.mScaleX != 1 || mTransform.mScaleY != 1{
           
               var ox = mTransform.mScaleOriginX
               var oy = mTransform.mScaleOriginY
               if mTransform.mScaleIsPercent {
                   ox = mTransform.mScaleOriginX * mRectPath.width
                   oy = mTransform.mScaleOriginY * mRectPath.height
               }else if validateViewBox() {
                   ox = mRectPath.left + mTransform.mScaleOriginX.asViewBoxToWidth(mRectVb, mRectPath.width)
                   oy = mRectPath.top +  mTransform.mScaleOriginY.asViewBoxToHeight(mRectVb, mRectPath.height)
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
   
    func viewBoxTransform(){
        if validateViewBox() {
           mRectPath.set(rect: mRectVb)
           let trans = SVGViewBox.transform(vbRect: mRectVb, eRect: mBounds, align: mAlign, meetOrSlice: mAspect )
           mRectPath = mRectPath.applying(trans)
        }else{
           mRectPath.set(rect: mBounds)
        }
   }
    private func validateViewBox() -> Bool {
        return mRectVb.size.width >= 0 && mRectVb.size.height >= 0
    }
   
  
     override func didUpdateReactSubviews() {
        if reactSubviews() == nil {
            return
        }

        for i in 0..<self.reactSubviews()!.count {
            let v = self.reactSubviews()![i]
            self.addSubview(v)
        }
    }

    
     override func layoutSubviews() {
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
