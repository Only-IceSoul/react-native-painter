//
//  TextLayer.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/30/21.
//

import Foundation
import UIKit
public class TextLayer: CALayer  {
   
    
    var mLayer = CATextLayer()
    var mPainterKit : PainterKit!
    var mProps = CommonProps()
    var mTransform = TransformProps()
    var mRect = CGRect()
    var mTextAttrs = [NSAttributedString.Key : Any]()
   
    var mText = NSMutableAttributedString(string: "")
    var mFontStyle = "normal"
    var mFont = "default"
    var mUIFont = UIFont.systemFont(ofSize: 12)
    var mFontSize:CGFloat = 12
    var mX:CGFloat = 0
    var mY:CGFloat = 0
    var mBaseline = "none"
    var mVerticalOffset :CGFloat = 0
    var mBoundsText = CGSize()
    var mHorizontalOffset :CGFloat = 0
    
    public override init() {
        super.init()
        addSublayer(mLayer)
        mLayer.anchorPoint = CGPoint(x: 0, y: 1)
    }
    
    
     func setText(_ v:String){
        mText.mutableString.setString(v)
        invalidateText()
    }
     func setBaseline(_ v:String){
        if mBaseline != v{
            mBaseline = v
            invalidateTextPosition()
        }

    }
     func setVerticalOffset(_ v:CGFloat){
        if mVerticalOffset != v{
            mVerticalOffset = v
            invalidateTextPosition()
        }

    }
     func setX(_ v:CGFloat){
        if mX != v{
            mX = v
            invalidateTextPosition()
        }

    }
     func setY(_ v:CGFloat){
        if mY != v{
            mY = v
            invalidateTextPosition()
        }

    }
     func setHorizontalOffset(_ v:CGFloat){
        if mHorizontalOffset != v{
            mHorizontalOffset = v
            invalidateTextPosition()
        }

    }
     func setFont(_ v:String){
        if mFont != v{
            mFont = v
            invalidateText()
            invalidateTextPosition(false)
        }

    }
     func setFontStyle(_ v:String){
        if mFontStyle != v{
            mFontStyle = v
            invalidateText()
            invalidateTextPosition(false)
        }

    }
     func setFontSize(_ v:CGFloat){
        if mFontSize != v{
            mFontSize = v
            invalidateText()
            invalidateTextPosition(false)
        }

    }
    
        
    //MARK: Paintable
    
    func setPainterKit(_ p: PainterKit){
        mPainterKit = p
        invalidateSelf()
    }
    
    func setProps(_ p:CommonProps){
        mProps.set(p)
        invalidateText()
        invalidateShadow()
        invalidateOpacity()
        invalidateTextPosition(false)
    }
    
    //MARK: set and get
    @discardableResult
    public func setOpacity(_ v:Float,_ status:Bool) -> TextLayer{
        mProps.opacityStatus = status
        if mProps.opacity != v{
            mProps.opacity = v
            invalidateOpacity()
        }
       return self
    }
    
    @discardableResult
    public func setFill(_ v:Int,_ status:Bool) -> TextLayer{
        mProps.fillColorStatus = status
        if mProps.fillColor != v{
            mProps.fillColor = v
            invalidateText()
        }
       return self
   }
    
    @discardableResult
    public func setFillRule(_ v:String,_ status:Bool) -> TextLayer{
       return self
    }
    
    @discardableResult
    public func setFillOpacity(_ v:CGFloat,_ status:Bool) -> TextLayer{
        mProps.fillOpacityStatus = status
        if mProps.fillOpacity != v{
            mProps.fillOpacity = v
            invalidateText()
        }
       return self
    }
    
    @discardableResult
    public func setStroke(_ v:Int,_ status:Bool) -> TextLayer{
        mProps.strokeColorStatus = status
        if mProps.strokeColor != v{
            mProps.strokeColor = v
            invalidateText()
        }
       return self
    }
    
    @discardableResult
    public func setStrokeOpacity(_ v:CGFloat,_ status:Bool) -> TextLayer{
        mProps.strokeOpacityStatus = status
        if mProps.strokeOpacity != v{
            mProps.strokeOpacity = v
            invalidateText()
        }
       return self
    }
    
    @discardableResult
    public func setStrokeWidth(_ v:CGFloat,_ status:Bool) -> TextLayer{
        mProps.strokeWidthStatus = status
        if mProps.strokeWidth != v{
            mProps.strokeWidth = v
            invalidateText()
        }
       return self
    }
    
    @discardableResult
    public func setStrokeCap(_ v:String) -> TextLayer{
       return self
    }
    
    @discardableResult
    public func setStrokeJoin(_ v:String) -> TextLayer{
       return self
    }
    @discardableResult
    public func setStrokeMiter(_ v:CGFloat,_ status:Bool) -> TextLayer{
       return self
    }
    
    @discardableResult
    public func setStrokeStart(_ v:CGFloat,_ status:Bool) -> TextLayer{
       return self
    }
    
    @discardableResult
    public func setStrokeEnd(_ v:CGFloat,_ status:Bool) -> TextLayer{
       return self
    }
    
    
    @discardableResult
    public func setShadow(_ v:Int,_ status:Bool) -> TextLayer{
        mProps.shadowColorStatus = status
        if mProps.shadowColor != v{
            mProps.shadowColor = v
            invalidateShadow()
        }
       return self
    }
    
    @discardableResult
    public func setShadowOffset(_ x:CGFloat,_ y:CGFloat,_ percent:Bool,_ status:Bool) -> TextLayer{
        mProps.shadowOffsetStatus = status
        if mProps.shadowOffsetX != x || mProps.shadowOffsetY != y || mProps.shadowOffsetIsPercent != percent {
            mProps.shadowOffsetX = x
            mProps.shadowOffsetY = y
            mProps.shadowOffsetIsPercent = percent
            invalidateShadow()
        }
       return self
    }
    
    @discardableResult
    public func setShadowOpacity(_ v:Float,_ status:Bool) -> TextLayer{
        mProps.shadowOpacityStatus = status
        if mProps.shadowOpacity != v{
            mProps.shadowOpacity = v
            invalidateShadow()
        }
       return self
    }
    
    @discardableResult
    public func setShadowRadius(_ v:CGFloat,_ status:Bool) -> TextLayer{
        mProps.shadowRadiusStatus = status
        if mProps.shadowRadius != v{
            mProps.shadowRadius = v
            invalidateShadow()
        }
       return self
    }
    
    
    
    @discardableResult
    public func setTranslation(_ x:CGFloat,_ y:CGFloat,_ percent:Bool) -> TextLayer{
        if mTransform.mTranslationX != x || mTransform.mTranslationY != y || mTransform.mTranslationIsPercent != percent {
            mTransform.mTranslationX = x
            mTransform.mTranslationY = y
            mTransform.mTranslationIsPercent = percent
            invalidateTransform()
        }
       return self
    }
    
    @discardableResult
    public func setRotation(_ a:CGFloat,_ x:CGFloat,_ y:CGFloat,_ percent:Bool) -> TextLayer{
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
    public func setScale(_ x:CGFloat,_ y:CGFloat,_ ox:CGFloat,_ oy:CGFloat,_ percent:Bool) -> TextLayer{
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
        invalidateText()
        invalidateShadow()
        invalidateOpacity()
        invalidateTextPosition(false)
        invalidateTransform()
    }
    
    public func setupFont(){
        
        let fs = mPainterKit.mIsViewBoxEnabled ? mFontSize.asViewBoxToMax(mPainterKit.mViewBox, mRect.width, mRect.height) : mFontSize
        
        if mFont == "default"{
            if mFontStyle == "bold"{
                mUIFont = UIFont.systemFont(ofSize: fs, weight: .bold)
            }else if mFontStyle == "italic"{
                mUIFont = UIFont.italicSystemFont(ofSize: fs)
            }else{
                mUIFont = UIFont.systemFont(ofSize: fs)
            }
        }else{
            mUIFont = UIFont(name: mFont, size: fs) ?? UIFont.systemFont(ofSize: fs)
        }
        
    }
    
    public func invalidateTextPosition(_ f:Bool = true){
        if(mRect.width > 0 && mRect.height > 0 && mPainterKit != nil){
        if f { setupFont() }
        var x = mX
        var y = mY
        if mPainterKit.mIsViewBoxEnabled{
            
            x = mX.asViewBoxToWidth(mPainterKit.mViewBox, mRect.width)
            y = mY.asViewBoxToHeight(mPainterKit.mViewBox, mRect.height)
        }
        mText.sizeOneLine(cgSize: &mBoundsText)
     
        switch mBaseline {
        case "middle":
            y -= mUIFont.descender
            y += mUIFont.xHeight / 2
            break;
        case "capHeight":
            y -= mUIFont.descender
            y += mUIFont.capHeight
            break;
        case "center":
            y += mBoundsText.height / 2
            break;
        case "none":
            y -= mUIFont.descender
            break;
        case "ascender":
            y += mUIFont.ascender - mUIFont.descender
        default:
            break;
            
        }
        
        if mVerticalOffset != 0{
            y += mVerticalOffset * mBoundsText.height
        }
        
        if mHorizontalOffset != 0{
            x += mHorizontalOffset * mBoundsText.width
        }
     
        mLayer.frame = CGRect(x: 0, y: 0, width: mBoundsText.width, height: mBoundsText.height)
        mLayer.position = CGPoint(x: x , y: y)
        }
    }
    
    public func invalidateOpacity(){
        disableAnimation()
        super.opacity = mProps.getOpacity()
        commit()
    }
    
    public func invalidateText(){
   
        if(mRect.width > 0 && mRect.height > 0 && mPainterKit != nil){
            
            setupFont()
            mTextAttrs[.font ] = mUIFont
   
            let fillColor = UIColor.parseInt(argb: mProps.getFillColor(), opacity: mProps.getFillOpacity())
            mTextAttrs[.foregroundColor ] = fillColor
            var strokeWidth:CGFloat = mProps.getStrokeWidth()
            if mPainterKit.mIsViewBoxEnabled {
                let size = mPainterKit.mViewBox.width > mPainterKit.mViewBox.height ? mRect.width : mRect.height
                strokeWidth = (mProps.getStrokeWidth() / max(mPainterKit.mViewBox.width,mPainterKit.mViewBox.height)) * size
            }
            mTextAttrs[.strokeWidth ] = -strokeWidth
            
            let strokeColor = UIColor.parseInt(argb: mProps.getStrokeColor(), opacity: mProps.getStrokeOpacity())
            mTextAttrs[.strokeColor ] = strokeColor
          
            mText.addAttributes(mTextAttrs, range: NSRange(location: 0, length: self.mText.length))
         
            disableAnimation()
            mLayer.string = mText
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
    
 
   
    private func invalidateShadow(){
        if(mRect.width > 0 && mRect.height > 0 && mPainterKit != nil){
        let c = UIColor.parseInt(argb: mProps.getShadowColor())
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
        var radius = mProps.getShadowRadius()
        if mPainterKit.mIsViewBoxEnabled {
            let size = mPainterKit.mViewBox.width > mPainterKit.mViewBox.height ? mRect.width : mRect.height
            radius = (mProps.getShadowRadius() / max(mPainterKit.mViewBox.width,mPainterKit.mViewBox.height)) * size
        }
        disableAnimation()
        mLayer.shadowColor = c.cgColor
        mLayer.shadowOffset = offset
        mLayer.shadowRadius = radius
        mLayer.shadowOpacity = mProps.getShadowOpacity()
        commit()
        }
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
