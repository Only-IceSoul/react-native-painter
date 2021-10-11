//
//  TextLayer.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/30/21.
//

import Foundation
import UIKit
 class TextLayer: CALayer  {
   
    
    var mLayer = CATextLayer()
    var mProps = CommonProps()
    var mTransform = TransformProps()
    var mBounds = CGRect()
    var mRectPath = CGRect()
    var mRectVb = CGRect(x: 0, y: 0, width: -1, height: -1)
    var mAlign = "xMidYMid"
    var mAspect = SVGViewBox.AspectRatio.meet
    
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
    
     override init() {
        super.init()
        addSublayer(mLayer)
        mLayer.anchorPoint = CGPoint(x: 0, y: 1)
    }
    
    //MARK: Paintable
    
    func setPainterKit(_ p: PainterKit){
        mRectVb.set(rect: p.mViewBox)
        mAspect = p.mAspect
        mAlign = p.mAlign
        invalidate()
    }
    
    func setProps(_ p:CommonProps){
        mProps.set(p)
        invalidate()
    }
    
    


    //MARK: set and get
    
     func setText(_ v:String){
        mText.mutableString.setString(v)
        disableAnimation()
        mLayer.string = mText
        commit()
        invalidatePosition(false)
    }
     func setBaseline(_ v:String){
        if mBaseline != v{
            mBaseline = v
            invalidatePosition()
        }

    }
     func setVerticalOffset(_ v:CGFloat){
        if mVerticalOffset != v{
            mVerticalOffset = v
            invalidatePosition()
        }

    }
     func setX(_ v:CGFloat){
        if mX != v{
            mX = v
            invalidatePosition()
        }

    }
     func setY(_ v:CGFloat){
        if mY != v{
            mY = v
            invalidatePosition()
        }

    }
     func setHorizontalOffset(_ v:CGFloat){
        if mHorizontalOffset != v{
            mHorizontalOffset = v
            invalidatePosition()
        }

    }
     func setFont(_ v:String){
        if mFont != v{
            mFont = v
            invalidateText()
            invalidatePosition(false)
        }

    }
     func setFontStyle(_ v:String){
        if mFontStyle != v{
            mFontStyle = v
            invalidateText()
            invalidatePosition(false)
        }

    }
     func setFontSize(_ v:CGFloat){
        if mFontSize != v{
            mFontSize = v
            invalidateText()
            invalidatePosition(false)
        }

    }
    
 
  

    @discardableResult
     func setFill(_ v:Int,_ status:Bool) -> TextLayer{
        mProps.fillColorStatus = status
        if mProps.fillColor != v{
            mProps.fillColor = v
            invalidateText()
        }
       return self
   }
    
    @discardableResult
     func setFillRule(_ v:String,_ status:Bool) -> TextLayer{
       return self
    }
    
    @discardableResult
     func setFillOpacity(_ v:CGFloat,_ status:Bool) -> TextLayer{
        mProps.fillOpacityStatus = status
        if mProps.fillOpacity != v{
            mProps.fillOpacity = v
            invalidateText()
        }
       return self
    }
    
    @discardableResult
     func setStroke(_ v:Int,_ status:Bool) -> TextLayer{
        mProps.strokeColorStatus = status
        if mProps.strokeColor != v{
            mProps.strokeColor = v
            invalidateText()
        }
       return self
    }
    
    @discardableResult
     func setStrokeOpacity(_ v:CGFloat,_ status:Bool) -> TextLayer{
        mProps.strokeOpacityStatus = status
        if mProps.strokeOpacity != v{
            mProps.strokeOpacity = v
            invalidateText()
        }
       return self
    }
    
    @discardableResult
     func setStrokeWidth(_ v:CGFloat,_ status:Bool) -> TextLayer{
        mProps.strokeWidthStatus = status
        if mProps.strokeWidth != v{
            mProps.strokeWidth = v
            invalidateText()
        }
       return self
    }
    
    @discardableResult
     func setStrokeCap(_ v:String) -> TextLayer{
       return self
    }
    
    @discardableResult
     func setStrokeJoin(_ v:String) -> TextLayer{
       return self
    }
    @discardableResult
     func setStrokeMiter(_ v:CGFloat,_ status:Bool) -> TextLayer{
       return self
    }
    
    @discardableResult
     func setStrokeStart(_ v:CGFloat,_ status:Bool) -> TextLayer{
       return self
    }
    
    @discardableResult
     func setStrokeEnd(_ v:CGFloat,_ status:Bool) -> TextLayer{
       return self
    }
    
    
    @discardableResult
     func setShadow(_ v:Int,_ status:Bool) -> TextLayer{
        mProps.shadowColorStatus = status
        if mProps.shadowColor != v{
            mProps.shadowColor = v
            invalidateShadow()
        }
       return self
    }
    
    
    @discardableResult
     func setShadowOpacity(_ v:Float,_ status:Bool) -> TextLayer{
        mProps.shadowOpacityStatus = status
        if mProps.shadowOpacity != v{
            mProps.shadowOpacity = v
            invalidateShadow()
        }
       return self
    }
    
    @discardableResult
     func setShadowRadius(_ v:CGFloat,_ status:Bool) -> TextLayer{
        mProps.shadowRadiusStatus = status
        if mProps.shadowRadius != v{
            mProps.shadowRadius = v
            invalidateShadow()
        }
       return self
    }
    
    @discardableResult
     func setShadowOffset(_ v:CGFloat,_ status:Bool) -> TextLayer{
        mProps.shadowOffsetXStatus = status
        mProps.shadowOffsetYStatus = status
        if mProps.shadowOffsetX != v || mProps.shadowOffsetY != v  {
            mProps.shadowOffsetX = v
            mProps.shadowOffsetY = v
            invalidateShadow()
        }
       return self
    }
    @discardableResult
     func setShadowOffsetX(_ v:CGFloat,_ status:Bool) -> TextLayer{
        mProps.shadowOffsetXStatus = status
        if mProps.shadowOffsetX != v {
            mProps.shadowOffsetX = v
            invalidateShadow()
        }
       return self
    }
    @discardableResult
     func setShadowOffsetY(_ v:CGFloat,_ status:Bool) -> TextLayer{
        mProps.shadowOffsetYStatus = status
        if mProps.shadowOffsetY != v  {
            mProps.shadowOffsetY = v
            invalidateShadow()
        }
       return self
    }
    @discardableResult
     func setShadowPercentageValue(_ v:Bool,_ status:Bool) -> TextLayer{
        mProps.shadowOffsetIsPerecentStatus = status
        if mProps.shadowOffsetIsPercent != v  {
            mProps.shadowOffsetIsPercent = v
            invalidateShadow()
        }
       return self
    }
    
    //MARK: Transform props

     func setTransX(v:CGFloat) {
            if(mTransform.mTranslationX != v ){
                mTransform.mTranslationX = v;
                invalidateTransform();
            }
        }
         func setTransY(v:CGFloat) {
            if(mTransform.mTranslationY != v ){
                mTransform.mTranslationY = v;
                invalidateTransform();
            }
        }
         func setTransPercentageValue(v:Bool) {
            if(mTransform.mTranslationIsPercent != v ){
                mTransform.mTranslationIsPercent = v;
                invalidateTransform();
            }
        }

         func setRot(v:CGFloat) {
            if(mTransform.mRotation != v ){
                mTransform.mRotation = v;
                invalidateTransform();
            }
        }
         func setRotO(v:CGFloat) {
            if(mTransform.mRotationOriginX != v || mTransform.mRotationOriginY != v ){
                mTransform.mRotationOriginX = v;
                mTransform.mRotationOriginY = v;
                invalidateTransform();
            }
        }
         func setRotOx(v:CGFloat) {
            if(mTransform.mRotationOriginX != v ){
                mTransform.mRotationOriginX = v;
                invalidateTransform();
            }
        }
         func setRotOy(v:CGFloat) {
            if(mTransform.mRotationOriginY != v ){
                mTransform.mRotationOriginY = v;
                invalidateTransform();
            }
        }
         func setRotPercentageValue(v:Bool) {
            if(mTransform.mRotationIsPercent != v ){
                mTransform.mRotationIsPercent = v;
                invalidateTransform();
            }
        }

         func setSc(v:CGFloat){
            if(mTransform.mScaleX != v || mTransform.mScaleY != v){
                mTransform.mScaleX = v;
                mTransform.mScaleY = v;
                invalidateTransform();
            }
        }
         func setScX(v:CGFloat) {
            if(mTransform.mScaleX != v ){
                mTransform.mScaleX = v;
                invalidateTransform();
            }
        }

         func setScY(v:CGFloat) {
            if(mTransform.mScaleY != v ){
                mTransform.mScaleY = v;
                invalidateTransform();
            }
        }
         func setScO(v:CGFloat){
            if(mTransform.mScaleOriginX != v || mTransform.mScaleOriginY != v){
                mTransform.mScaleOriginX = v;
                mTransform.mScaleOriginY = v;
                invalidateTransform();
            }
        }
         func setScOx(v:CGFloat) {
            if(mTransform.mScaleOriginX != v ){
                mTransform.mScaleOriginX = v;
                invalidateTransform();
            }
        }
         func setScOy(v:CGFloat) {
            if(mTransform.mScaleOriginY != v ){
                mTransform.mScaleOriginY = v;
                invalidateTransform();
            }
        }
         func setScPercentageValue(v:Bool) {
            if(mTransform.mScaleIsPercent != v ){
                mTransform.mScaleIsPercent = v;
                invalidateTransform();
            }
        }
    

    
    //MARK: layer methods
    
     func onBoundsChange(_ frame: CGRect){
        mBounds.set(rect: frame)
        super.frame = mBounds
        super.position = CGPoint(x: 0, y: 0)
        super.anchorPoint = CGPoint(x: 0, y: 0)
        invalidate()
    }
    
     func invalidate(){
        
        viewBoxTransform()
        
        invalidateText()
        invalidatePosition(false)
        invalidateShadow()
        invalidateTransform()
    }
    
     func setupFont(){
        
        let fs = validateViewBox() ? mFontSize.asViewBoxToMax(mRectVb, mRectPath.width, mRectPath.height) : mFontSize
        
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
    
     func invalidatePosition(_ f:Bool = true){
        if !isLayout() { return  }
        
        if f { setupFont() }
        
        var x = mX
        var y = mY
        if validateViewBox() {
            x = mRectPath.left +  mX.asViewBoxToWidth(mRectVb, mRectPath.width)
            y = mRectPath.top + mY.asViewBoxToHeight(mRectVb, mRectPath.height)
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
        disableAnimation()
        mLayer.frame = CGRect(x: 0, y: 0, width: mBoundsText.width, height: mBoundsText.height)
        mLayer.position = CGPoint(x: x , y: y)
        commit()
        
    }
    
 
     func invalidateText(){
   
        if !isLayout() { return }
            
            setupFont()
        
            mTextAttrs[.font ] = mUIFont
   
            let fillColor = UIColor.parseInt(argb: mProps.getFillColor(), opacity: mProps.getFillOpacity())
            mTextAttrs[.foregroundColor ] = fillColor
        
            var strokeWidth:CGFloat = mProps.getStrokeWidth()
            if validateViewBox() {
                let size = max(mRectPath.width,mRectPath.height)
                strokeWidth = ( mProps.getStrokeWidth() / max(mRectVb.size.width,mRectVb.size.height) ) * size
            }
            mTextAttrs[.strokeWidth ] = -strokeWidth
            
            let strokeColor = UIColor.parseInt(argb: mProps.getStrokeColor(), opacity: mProps.getStrokeOpacity())
            mTextAttrs[.strokeColor ] = strokeColor
          
            mText.addAttributes(mTextAttrs, range: NSRange(location: 0, length: self.mText.length))
         
            disableAnimation()
            mLayer.string = mText
            commit()
        
    }
    
  

 
   
    private func invalidateShadow(){
     
        if !isLayout() { return }
        
        let c = UIColor.parseInt(argb: mProps.getShadowColor())
        
        
        var offset = CGSize(width: 0, height: 0)
        if mProps.getShadowOffsetIsPercent(){
            offset.width = mProps.getShadowOffsetX() * mRectPath.width
            offset.height = mProps.getShadowOffsetY() * mRectPath.height
        }else if validateViewBox() {
            offset.width = (mProps.getShadowOffsetX() / mRectVb.size.width) * mRectPath.width
            offset.height = (mProps.getShadowOffsetY() / mRectVb.size.height) * mRectPath.height
        }else{
            offset.width = mProps.getShadowOffsetX()
            offset.height = mProps.getShadowOffsetY()
        }
        
        
        var radius = mProps.getShadowRadius()
        if validateViewBox() {
            let size = max(mRectPath.width,mRectPath.height)
            radius = (mProps.getShadowRadius() / max(mRectVb.size.width,mRectVb.size.height)) * size
        }
        disableAnimation()
        mLayer.shadowColor = c.cgColor
        mLayer.shadowOffset = offset
        mLayer.shadowRadius = radius
        mLayer.shadowOpacity = mProps.getShadowOpacity()
        commit()
    }
 
    
    func invalidateTransform(){
    
        if !isLayout() {  return }
      
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
           super.transform = matrix
           commit()
       
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

    
    private func isLayout() -> Bool {
        return mBounds.width > 0 && mBounds.height > 0
    }
    
     func disableAnimation(){
        CATransaction.begin()
        CATransaction.setDisableActions(true)
    }
    
     func commit(){
        CATransaction.commit()
    }
    

   
     override init(layer: Any) {
        super.init(layer: layer)

    }
     required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
 

      
}
