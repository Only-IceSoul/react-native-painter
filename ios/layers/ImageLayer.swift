//
//  ImageLayer.swift
//  react-native-painter
//
//  Created by Juan J LF on 9/7/21.
//

import Foundation
import UIKit

class ImageLayer: CALayer {
    
    
    
    var mLayer = ImageContentLayer()
    
    var mPainterKit : PainterKit!
    var mTransform = TransformProps()
    var mRect = CGRect()
    
    var mOpacityStatus = false
    var mOpacity:Float = 1
    
    private var x:CGFloat = 0
    private var y:CGFloat = 0
    private var w:CGFloat = 0
    private var h:CGFloat = 0
 
    
    public override init() {
        super.init()
        addSublayer(mLayer)
        mLayer.anchorPoint = CGPoint(x: 0, y: 0)
        
    }
    
    func setPainterKit(_ p: PainterKit){
        mPainterKit = p
        invalidateSelf()
    }
    
    func setProps(_ p:CommonProps){
        if !mOpacityStatus { mOpacity = p.getOpacity() }
        invalidateOpacity()
        
    }
    public func setOpacity(_ v:Float,_ status:Bool){
        mOpacityStatus = status
        if mOpacity != v{
            mOpacity = v
            invalidateOpacity()
        }
      
    }
    
    //MARK : PROPS
    public func setX(_ v:CGFloat){
        if x != v{
            x = v
            invalidatePosition()
        }
    
    }
    public func setY(_ v:CGFloat){
        if y != v{
            y = v
            invalidatePosition()
        }
    }
    public func setW(_ v:CGFloat){
        if w != v{
            w = v
            invalidatePosition()
        }
    }
    public func setH(_ v:CGFloat){
        if h != v{
            h = v
            invalidatePosition()
        }
    }
    
    public func setSrc(_ v:String){
        mLayer.setSrc(v)
    }
    public func setAlign(_ v:String){
        mLayer.setAlign(v)
    }
    public func setAspect(_ v:String){
        mLayer.setAspect(v)
    }
    public func setBgColor(_ v:Int){
        mLayer.setBgColor(v)
    }
    public func setClipToBounds(_ v:Bool){
        mLayer.setClipToBounds(v)
    }
    
    
    public func setTranslation(_ x:CGFloat,_ y:CGFloat,_ percent:Bool){
        if mTransform.mTranslationX != x || mTransform.mTranslationY != y || mTransform.mTranslationIsPercent != percent {
            mTransform.mTranslationX = x
            mTransform.mTranslationY = y
            mTransform.mTranslationIsPercent = percent
            invalidateTransform()
        }
    
    }
    

    public func setRotation(_ a:CGFloat,_ x:CGFloat,_ y:CGFloat,_ percent:Bool){
        if mTransform.mRotation != a || mTransform.mRotationOriginX != x || mTransform.mRotationOriginY != y || mTransform.mRotationIsPercent != percent {
            mTransform.mRotation = a
            mTransform.mRotationOriginX = x
            mTransform.mRotationOriginY = y
            mTransform.mRotationIsPercent = percent
            invalidateTransform()
        }
       
    }
    
    public func setScale(_ x:CGFloat,_ y:CGFloat,_ ox:CGFloat,_ oy:CGFloat,_ percent:Bool){
        if  mTransform.mScaleY != x || mTransform.mScaleY != y  || mTransform.mScaleOriginX != ox || mTransform.mScaleOriginY != oy  ||  mTransform.mScaleIsPercent != percent {
         
            mTransform.mScaleX = x
            mTransform.mScaleY = y
            mTransform.mScaleOriginX = ox
            mTransform.mScaleOriginY = oy
            mTransform.mScaleIsPercent = percent
            invalidateTransform()
        }
     
    }
    
    //MARK: layer methods
    
    public func onBoundsChange(_ frame: CGRect){
        mRect.set(rect: frame)
        super.frame = mRect
        super.position = CGPoint(x: 0, y: 0)
        super.anchorPoint = CGPoint(x: 0, y: 0)
        invalidateSelf()
    }
    
    public func invalidateSelf(){
        invalidateOpacity()
        invalidatePosition()
        invalidateTransform()
    }
    
    
    public func invalidatePosition(){
        if(mRect.width > 0 && mRect.height > 0 && mPainterKit != nil){
        var xx = x
        var yy = y
        var ww = w
        var hh = h
        
        if mPainterKit.mIsViewBoxEnabled{
            
            xx = x.asViewBoxToWidth(mPainterKit.mViewBox, mRect.width)
            yy = y.asViewBoxToHeight(mPainterKit.mViewBox, mRect.height)
            ww = w.asViewBoxToWidth(mPainterKit.mViewBox, mRect.width)
            hh = h.asViewBoxToHeight(mPainterKit.mViewBox, mRect.height)
        }
        
        disableAnimation()
        mLayer.frame = CGRect(x: 0, y: 0, width:ww, height: hh)
        mLayer.position = CGPoint(x: xx , y: yy)
        commit()
        mLayer.invalidateImageTransform()
        }
    }
    
    public func invalidateTransform(){
        
        if(mRect.width > 0 && mRect.height > 0 && mPainterKit != nil){
            var matrix = CATransform3DIdentity
          
            
            if mTransform.mTranslationX != 0 || mTransform.mTranslationY != 0{
                var transX = mTransform.mTranslationX
                var transY = mTransform.mTranslationY
                if mTransform.mTranslationIsPercent{
                    transX = mTransform.mTranslationX * mRect.width
                    transY = mTransform.mTranslationY * mRect.height
                }else if mPainterKit.mIsViewBoxEnabled {
                    transX = (mTransform.mTranslationX / mPainterKit.mViewBox.width) * mRect.width
                    transY = (mTransform.mTranslationY / mPainterKit.mViewBox.height) * mRect.height
                }
                
                matrix = CATransform3DTranslate(matrix, transX, transY, 0)
            }
            
            if mTransform.mRotation != 0{
                var rotX = mTransform.mRotationOriginX
                var rotY = mTransform.mRotationOriginY
                if mTransform.mRotationIsPercent{
                    rotX = mTransform.mRotationOriginX * mRect.width
                    rotY = mTransform.mRotationOriginY * mRect.height
                }else if mPainterKit.mIsViewBoxEnabled {
                   
                    rotX = mTransform.mRotationOriginX.asViewBoxToWidth(mPainterKit.mViewBox, mRect.width)
                    rotY = mTransform.mRotationOriginY.asViewBoxToHeight(mPainterKit.mViewBox, mRect.height)
                }
                matrix = CATransform3DTranslate(matrix, rotX, rotY, 0)
                matrix = CATransform3DRotate(matrix, mTransform.mRotation.toRadians(), 0, 0, 1)
                matrix = CATransform3DTranslate(matrix, -rotX, -rotY, 0)
            }
            
            if mTransform.mScaleX != 1 || mTransform.mScaleY != 1{
                var ox = mTransform.mScaleOriginX
                var oy = mTransform.mScaleOriginY
                if mTransform.mScaleIsPercent {
                    ox = mTransform.mScaleOriginX * mRect.width
                    oy = mTransform.mScaleOriginY * mRect.height
                }else if mPainterKit.mIsViewBoxEnabled{
                    ox = mTransform.mScaleOriginX.asViewBoxToWidth(mPainterKit.mViewBox, mRect.width)
                    oy = mTransform.mScaleOriginY.asViewBoxToHeight(mPainterKit.mViewBox, mRect.height)
                }
                matrix = CATransform3DTranslate(matrix, ox, oy, 0)
                matrix = CATransform3DScale(matrix, mTransform.mScaleX, mTransform.mScaleY, 1)
                matrix = CATransform3DTranslate(matrix, -ox, -oy, 0)
            }

            disableAnimation()
            super.transform = matrix
            commit()
        }
    }
    
    
    
    public func invalidateOpacity(){
        disableAnimation()
        super.opacity = mOpacity
        commit()
    }
    
    
     func disableAnimation(){
        CATransaction.begin()
        CATransaction.setDisableActions(true)
    }
    
     func commit(){
        CATransaction.commit()
    }
    

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}
