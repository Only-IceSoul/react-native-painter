//
//  Drawable.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/29/21.
//

import UIKit
public class Paintable: CAShapeLayer  {
   
    var namePaint = "none"
    
    var mPainterKit : PainterKit!
    var mProps = CommonProps()
    var mTransform = TransformProps()
    var mIgnoreVbTransform = false
    var mIgnoreFill = false
    var mIgnoreStroke = false

    var mRect = CGRect()
    var mPath  = UIBezierPath()
   
    
    public override init() {
        super.init()
        super.fillColor = UIColor.clear.cgColor
        super.shadowOffset = .init(width: 0, height: 0)
        super.lineWidth = 0
        super.strokeColor = UIColor.clear.cgColor
        super.shadowOpacity = 0
        super.shadowRadius = 0
        super.shadowColor = UIColor.clear.cgColor
    }
        
    //MARK: Protocol
    
    func setPainterKit(_ p: PainterKit){
        mPainterKit = p
        invalidateCommonProps()
        invalidatePath()
        invalidateTransform()
    }
    
    func setProps(_ p:CommonProps){
        mProps.set(p)
        invalidateCommonProps()
        invalidatePath()
    }
    
    //MARK: set and get
    @discardableResult
    public func setOpacity(_ v:Float,_ status:Bool) -> Paintable{
        mProps.opacityStatus = status
        if mProps.opacity != v{
            mProps.opacity = v
            invalidateCommonProps()
        }
       return self
    }
    
    @discardableResult
    public func setFill(_ v:Int,_ status:Bool) -> Paintable{
        mProps.fillColorStatus = status
        if mProps.fillColor != v{
            mProps.fillColor = v
            invalidateCommonProps()
        }
       return self
   }
    
    @discardableResult
    public func setFillRule(_ v:String,_ status:Bool) -> Paintable{
        mProps.fillRuleStatus = status
        if mProps.fillRule != v{
            mProps.fillRule = v
            invalidateCommonProps()
            invalidatePath()
        }
       return self
    }
    
    @discardableResult
    public func setFillOpacity(_ v:CGFloat,_ status:Bool) -> Paintable{
        mProps.fillOpacityStatus = status
        if mProps.fillOpacity != v{
            mProps.fillOpacity = v
            invalidateCommonProps()
        }
       return self
    }
    
    @discardableResult
    public func setStroke(_ v:Int,_ status:Bool) -> Paintable{
        mProps.strokeColorStatus = status
        if mProps.strokeColor != v{
            mProps.strokeColor = v
            invalidateCommonProps()
        }
       return self
    }
    
    @discardableResult
    public func setStrokeOpacity(_ v:CGFloat,_ status:Bool) -> Paintable{
        mProps.strokeOpacityStatus = status
        if mProps.strokeOpacity != v{
            mProps.strokeOpacity = v
            invalidateCommonProps()
        }
       return self
    }
    
    @discardableResult
    public func setStrokeWidth(_ v:CGFloat,_ status:Bool) -> Paintable{
        mProps.strokeWidthStatus = status
        if mProps.strokeWidth != v{
            mProps.strokeWidth = v
            invalidateCommonProps()
            invalidatePath()
        }
       return self
    }
    
    @discardableResult
    public func setStrokeCap(_ v:String) -> Paintable{
        if mProps.strokeCap != v{
            mProps.strokeCap = v
            invalidateCommonProps()
            invalidatePath()
        }
       return self
    }
    
    @discardableResult
    public func setStrokeJoin(_ v:String) -> Paintable{
        if mProps.strokeJoin != v{
            mProps.strokeJoin = v
            invalidateCommonProps()
            invalidatePath()
        }
       return self
    }
    @discardableResult
    public func setStrokeMiter(_ v:CGFloat,_ status:Bool) -> Paintable{
        mProps.strokeMiterStatus = status
        if mProps.strokeMiter != v{
            mProps.strokeMiter = v
            invalidateCommonProps()
            invalidatePath()
        }
       return self
    }
    
    @discardableResult
    public func setStrokeStart(_ v:CGFloat,_ status:Bool) -> Paintable{
        mProps.strokeStartStatus = status
        if mProps.strokeStart != v{
            mProps.strokeStart = v
            invalidateCommonProps()
        }
       return self
    }
    
    @discardableResult
    public func setStrokeEnd(_ v:CGFloat,_ status:Bool) -> Paintable{
        mProps.strokeEndStatus = status
        if mProps.strokeEnd != v{
            mProps.strokeEnd = v
            invalidateCommonProps()
        }
       return self
    }
    
    
    @discardableResult
    public func setShadow(_ v:Int,_ status:Bool) -> Paintable{
        mProps.shadowColorStatus = status
        if mProps.shadowColor != v{
            mProps.shadowColor = v
            invalidateCommonProps()
        }
       return self
    }
    
    @discardableResult
    public func setShadowOffset(_ x:CGFloat,_ y:CGFloat,_ percent:Bool,_ status:Bool) -> Paintable{
        mProps.shadowOffsetStatus = status
        if mProps.shadowOffsetX != x || mProps.shadowOffsetY != y || mProps.shadowOffsetIsPercent != percent {
            mProps.shadowOffsetX = x
            mProps.shadowOffsetY = y
            mProps.shadowOffsetIsPercent = percent
            invalidateCommonProps()
        }
       return self
    }
    
    @discardableResult
    public func setShadowOpacity(_ v:Float,_ status:Bool) -> Paintable{
        mProps.shadowOpacityStatus = status
        if mProps.shadowOpacity != v{
            mProps.shadowOpacity = v
            invalidateCommonProps()
        }
       return self
    }
    
    @discardableResult
    public func setShadowRadius(_ v:CGFloat,_ status:Bool) -> Paintable{
        mProps.shadowRadiusStatus = status
        if mProps.shadowRadius != v{
            mProps.shadowRadius = v
            invalidateCommonProps()
        }
       return self
    }
    
    
    
    @discardableResult
    public func setTranslation(_ x:CGFloat,_ y:CGFloat,_ percent:Bool) -> Paintable{
        if mTransform.mTranslationX != x || mTransform.mTranslationY != y || mTransform.mTranslationIsPercent != percent {
            mTransform.mTranslationX = x
            mTransform.mTranslationY = y
            mTransform.mTranslationIsPercent = percent
            invalidateTransform()
        }
       return self
    }
    
    @discardableResult
    public func setRotation(_ a:CGFloat,_ x:CGFloat,_ y:CGFloat,_ percent:Bool) -> Paintable{
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
    public func setScale(_ x:CGFloat,_ y:CGFloat,_ ox:CGFloat,_ oy:CGFloat,_ percent:Bool) -> Paintable{
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
        
        invalidateCommonProps()
        invalidatePath()
        invalidateTransform()
    }
    
    
 
    
  
    
    open func setupPath(){
        
          
         
        
        
    }
    
    
    
  
   public func invalidatePath(){
        if(mRect.width > 0 && mRect.height > 0 && mPainterKit != nil){
            setupPath()
            if !mIgnoreVbTransform{ transformPathToViewbox() }
            
            mPath.usesEvenOddFillRule = mProps.getFillRule()
            super.path = mPath.cgPath
            var sw:CGFloat = mProps.getStrokeWidth()
            if mPainterKit.mIsViewBoxEnabled {
                let size = mPainterKit.mViewBox.width > mPainterKit.mViewBox.height ? mRect.width : mRect.height
                sw = (mProps.getStrokeWidth() / max(mPainterKit.mViewBox.width,mPainterKit.mViewBox.height)) * size
            }
            super.shadowPath = fill() ? mPath.cgPath : mPath.cgPath.copy(strokingWithWidth: sw, lineCap: mProps.getCGStrokeCap(), lineJoin: mProps.getCGStrokeJoin(), miterLimit: mProps.getStrokeMiter())
        }
        
    }
   
    
    public func invalidateCommonProps(){
        
        if(mRect.width > 0 && mRect.height > 0 && mPainterKit != nil){
    
            disableAnimation()
            if !mIgnoreFill { setupFill()}
            if !mIgnoreStroke { setupStroke() }
            setupShadow()
            super.opacity = mProps.getOpacity()
            commit()
     
          
        }
    }
    
    
    
    private func fill()->Bool{
        return !mIgnoreFill && mProps.getFillColor() != 0
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
    
    private func transformPathToViewbox(){
         if mPainterKit.mViewBox.width > 0 && mPainterKit.mViewBox.height > 0{
            let aspect : SVGViewBox.AspectRatio = mPainterKit.mAspect == "meet" ? .meet : ( mPainterKit.mAspect == "slice" ? .slice : .none )
            let trans = SVGViewBox.transform(vbRect: mPainterKit.mViewBox, eRect: mRect, align: mPainterKit.mAlign, meetOrSlice: aspect )
            mPath.apply(trans)
        }
    }
    
    private func setupFill(){
        let c = UIColor.parseInt(argb: mProps.getFillColor(), opacity: mProps.getFillOpacity()
//                                    * CGFloat(mProps.getOpacity())
        )
        super.fillColor = c.cgColor
    }
    
    private func setupStroke(){
        var sw:CGFloat = mProps.getStrokeWidth()
        if mPainterKit.mIsViewBoxEnabled {
            let size = mPainterKit.mViewBox.width > mPainterKit.mViewBox.height ? mRect.width : mRect.height
            sw = (mProps.getStrokeWidth() / max(mPainterKit.mViewBox.width,mPainterKit.mViewBox.height)) * size
        }
        super.lineWidth = sw
        let c = UIColor.parseInt(argb: mProps.getStrokeColor(), opacity: mProps.getStrokeOpacity()
//                                    * CGFloat(mProps.getOpacity())
        )
        super.strokeColor = c.cgColor
        
        super.lineCap = mProps.getStrokeCap().toTarget()
        super.miterLimit = mProps.getStrokeMiter()
        super.lineJoin = mProps.getStrokeJoin().toTarget()
        super.strokeStart = mProps.getStrokeStart()
        super.strokeEnd = mProps.getStrokeEnd()
        
    }
    private func setupShadow(){
        let c = UIColor.parseInt(argb: mProps.getShadowColor())
        super.shadowColor = c.cgColor
        
        var offset = CGSize(width: 0, height: 0)
        if mProps.getShadowOffsetIsPercent(){
            offset.width = mProps.getShadowOffsetX() * mRect.width
            offset.height = mProps.getShadowOffsetY() * mRect.height
        }else if mPainterKit.mIsViewBoxEnabled {
            offset.width = (mProps.getShadowOffsetX() / mPainterKit.mViewBox.width) * mRect.width
            offset.height = (mProps.getShadowOffsetY() / mPainterKit.mViewBox.height) * mRect.height
        }else{
            offset.width = mProps.getShadowOffsetX()
            offset.height = mProps.getShadowOffsetY()
        }
        super.shadowOffset = offset
        
        var radius = mProps.getShadowRadius()
        if mPainterKit.mIsViewBoxEnabled {
            let size = mPainterKit.mViewBox.width > mPainterKit.mViewBox.height ? mRect.width : mRect.height
            radius = (mProps.getShadowRadius() / max(mPainterKit.mViewBox.width,mPainterKit.mViewBox.height)) * size
        }
        super.shadowRadius = radius
        super.shadowOpacity = mProps.getShadowOpacity()
        
    }
 
    
     func disableAnimation(){
        CATransaction.begin()
        CATransaction.setDisableActions(true)
    }
    
     func commit(){
        CATransaction.commit()
    }
    

   
    public override init(layer: Any) {
        super.init(layer: layer)

    }
    public required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
 

      
}
