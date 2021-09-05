//
//  RadialGradientLayer.swift
//  react-native-painter
//
//  Created by Juan J LF on 9/5/21.
//

import Foundation

class RadialGradientLayer : CALayer {
    
    
    
    var mLayer = CAGradientLayer()
    var mPainterKit : PainterKit!
    var mTransform = TransformProps()
    var mRect = CGRect()
    
    var mOpacityStatus = false
    var mOpacity:Float = 1
    
    private var x:CGFloat = 0
    private var y:CGFloat = 0
    private var w:CGFloat = 0
    private var h:CGFloat = 0
    private var cx :CGFloat = 0.5
    private var cy :CGFloat = 0.5
    private var rx :CGFloat = 0.5
    private var ry :CGFloat = 0.5
    private var mColors : [CGColor] = [CGColor]()
    private var mPositions : [NSNumber]?
    
    public override init() {
        super.init()
        addSublayer(mLayer)

        mColors.append(UIColor.white.cgColor)
        mColors.append(UIColor.black.cgColor)
        mLayer.colors = mColors
        mLayer.startPoint = CGPoint(x: cx, y: cy)
        mLayer.endPoint = CGPoint(x: cx + rx, y: cy + ry)
        mLayer.type = CAGradientLayerType.radial
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
            invalidateGradientPosition()
        }
    
    }
    public func setY(_ v:CGFloat){
        if y != v{
            y = v
            invalidateGradientPosition()
        }
    }
    public func setW(_ v:CGFloat){
        if w != v{
            w = v
            invalidateGradientPosition()
        }
    }
    public func setH(_ v:CGFloat){
        if h != v{
            h = v
            invalidateGradientPosition()
        }
    }
    
    public func setCx(_ v:CGFloat){
        if cx != v{
            cx = v
            invalidateProps()
        }
    }
    public func setCy(_ v:CGFloat){
        if cy != v{
            cy = v
            invalidateProps()
        }
    }
    public func setRx(_ v:CGFloat){
        if rx != v{
            rx = v
            invalidateProps()
        }
    }
    public func setRy(_ v:CGFloat){
        if ry != v{
            ry = v
            invalidateProps()
        }
    }
    public func setColors(_ colors: [CGColor]){
      
        mColors = colors
        invalidateProps()
        
    }
    
    public func setPositions(_ pos:[NSNumber]?){
        mPositions = pos
        invalidateProps()
    }
    
    @discardableResult
    public func setTranslation(_ x:CGFloat,_ y:CGFloat,_ percent:Bool) -> RadialGradientLayer{
        if mTransform.mTranslationX != x || mTransform.mTranslationY != y || mTransform.mTranslationIsPercent != percent {
            mTransform.mTranslationX = x
            mTransform.mTranslationY = y
            mTransform.mTranslationIsPercent = percent
            invalidateTransform()
        }
       return self
    }
    
    @discardableResult
    public func setRotation(_ a:CGFloat,_ x:CGFloat,_ y:CGFloat,_ percent:Bool) -> RadialGradientLayer{
        if mTransform.mRotation != a || mTransform.mRotationOriginX != x || mTransform.mRotationOriginY != y || mTransform.mRotationIsPercent != percent {
            mTransform.mRotation = a
            mTransform.mRotationOriginX = x
            mTransform.mRotationOriginY = y
            mTransform.mRotationIsPercent = percent
            invalidateTransform()
        }
       return self
    }
    
    @discardableResult
    public func setScale(_ x:CGFloat,_ y:CGFloat,_ ox:CGFloat,_ oy:CGFloat,_ percent:Bool) -> RadialGradientLayer{
        if  mTransform.mScaleY != x || mTransform.mScaleY != y  || mTransform.mScaleOriginX != ox || mTransform.mScaleOriginY != oy  ||  mTransform.mScaleIsPercent != percent {
         
            mTransform.mScaleX = x
            mTransform.mScaleY = y
            mTransform.mScaleOriginX = ox
            mTransform.mScaleOriginY = oy
            mTransform.mScaleIsPercent = percent
            invalidateTransform()
        }
       return self
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
        invalidateGradientPosition()
        invalidateProps()
        invalidateTransform()
    }
    
    public func invalidateProps(){
        
     
        
        disableAnimation()
        mLayer.startPoint = CGPoint(x: cx, y: cy)
        mLayer.endPoint = CGPoint(x: cx + rx, y: cy + ry)
        if rx <= 0 || ry <= 0 {
            mLayer.colors = [UIColor.clear.cgColor]
        }else{
            mLayer.colors = mColors
        }
       
        mLayer.locations = mPositions
        commit()
    }
    
    public func invalidateGradientPosition(){
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
        
        
        mLayer.frame = CGRect(x: 0, y: 0, width:ww, height: hh)
        disableAnimation()
        mLayer.position = CGPoint(x: xx , y: yy)
        commit()
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

