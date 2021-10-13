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
    var mProps = CommonProps()
    var mTransform = TransformProps()
    
    var mBounds = CGRect()
    var mRectPath = CGRect()
    var mRectVb = CGRect(x: 0, y: 0, width: -1, height: -1)
    var mAlign = "xMidYMid"
    var mAspect = SVGViewBox.AspectRatio.meet
    private var mClipToBounds = false
    
    private var x:CGFloat = 0
    private var y:CGFloat = 0
    private var w:CGFloat = 0
    private var h:CGFloat = 0
 
    
     override init() {
        super.init()
        addSublayer(mLayer)
        mLayer.anchorPoint = CGPoint(x: 0, y: 0)
        
    }
    
    //MARK: Protocol
    
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
    
    //MARK: Props
     func setX(_ v:CGFloat){
        if x != v{
            x = v
            invalidateRect()
        }
    
    }
     func setY(_ v:CGFloat){
        if y != v{
            y = v
            invalidateRect()
        }
    }
     func setW(_ v:CGFloat){
        if w != v{
            w = v
            invalidateRect()
        }
    }
     func setH(_ v:CGFloat){
        if h != v{
            h = v
            invalidateRect()
        }
    }
    
     func setSrc(_ v:String){
        mLayer.setSrc(v)
    }
     func setAlign(_ v:String){
        mLayer.setAlign(v)
    }
     func setAspect(_ v:String){
        mLayer.setAspect(v)
    }
  
     func setClipToBounds(_ v:Bool){
        mLayer.setClipToBounds(v)
        mClipToBounds = v
        invalidateCommonProps()
    }
    
    //MARK: Common props
    
     func setFill(_ v:Int,_ status:Bool){
        mProps.fillColorStatus = status
        if mProps.fillColor != v{
            mProps.fillColor = v
            invalidateCommonProps()
        }
   }
     func setShadow(_ v:Int,_ status:Bool){
        mProps.shadowColorStatus = status
        if mProps.shadowColor != v{
            mProps.shadowColor = v
            invalidateCommonProps()
        }
    }
    

     func setShadowOpacity(_ v:Float,_ status:Bool){
        mProps.shadowOpacityStatus = status
        if mProps.shadowOpacity != v{
            mProps.shadowOpacity = v
            invalidateCommonProps()
        }
    }
    
     func setShadowRadius(_ v:CGFloat,_ status:Bool){
        mProps.shadowRadiusStatus = status
        if mProps.shadowRadius != v{
            mProps.shadowRadius = v
            invalidateCommonProps()
        }
    }
    
     func setShadowOffset(_ v:CGFloat,_ status:Bool){
        mProps.shadowOffsetXStatus = status
        mProps.shadowOffsetYStatus = status
        if mProps.shadowOffsetX != v || mProps.shadowOffsetY != v  {
            mProps.shadowOffsetX = v
            mProps.shadowOffsetY = v
            invalidateCommonProps()
        }
    }

     func setShadowOffsetX(_ v:CGFloat,_ status:Bool){
        mProps.shadowOffsetXStatus = status
        if mProps.shadowOffsetX != v {
            mProps.shadowOffsetX = v
            invalidateCommonProps()
        }
   
    }

     func setShadowOffsetY(_ v:CGFloat,_ status:Bool){
        mProps.shadowOffsetYStatus = status
        if mProps.shadowOffsetY != v  {
            mProps.shadowOffsetY = v
            invalidateCommonProps()
        }
   
    }
     func setShadowPercentageValue(_ v:Bool,_ status:Bool){
        mProps.shadowOffsetIsPerecentStatus = status
        if mProps.shadowOffsetIsPercent != v  {
            mProps.shadowOffsetIsPercent = v
            invalidateCommonProps()
        }
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
        disableAnimation()
        super.frame = mBounds
        super.position = CGPoint(x: 0, y: 0)
        super.anchorPoint = CGPoint(x: 0, y: 0)
        commit()
        invalidate()
    }
    
      func invalidate(){
          if(mBounds.width > 0 && mBounds.height > 0){
              
              
              viewBoxTransform()
            
              //require rectpath , viewboxtransform
              invalidateRect()
              invalidateCommonProps()
              invalidateTransform()
          }
          
      }
    
     func invalidateRect(){
        if(mBounds.width > 0 && mBounds.height > 0){
        var xx = x
        var yy = y
        var ww = w
        var hh = h
        
        if validateViewBox(){
            
            xx = mRectPath.left + x.asViewBoxToWidth(mRectVb, mRectPath.width)
            yy = mRectPath.top +  y.asViewBoxToHeight(mRectVb, mRectPath.height)
            ww = w.asViewBoxToWidth(mRectVb, mRectPath.width)
            hh = h.asViewBoxToHeight(mRectVb, mRectPath.height)
        }
        
       
        mLayer.setBounds(xx,yy,ww,hh)
   
        }
    }
    
    func invalidateTransform(){
    
       if(mBounds.width > 0 && mBounds.height > 0 ){
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
    
     func invalidateCommonProps(){
        if(mBounds.width > 0 && mBounds.height > 0){
            let c = UIColor.parseInt(argb: mProps.getFillColor())
            disableAnimation()
            mLayer.backgroundColor = c.cgColor
            if mClipToBounds  {
                mLayer.getImageLayer().shadowOpacity = 0
                setupShadow(mLayer)
            }else{
                mLayer.shadowOpacity = 0
                setupShadow(mLayer.getImageLayer())
            }
            commit()
        }
    }
    
    private func setupShadow(_ layer:CALayer){
        let c = UIColor.parseInt(argb: mProps.getShadowColor())
        layer.shadowColor = c.cgColor
        
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
        layer.shadowOffset = offset
        
        var radius = mProps.getShadowRadius()
        if validateViewBox() {
            let size = max(mRectPath.width,mRectPath.height)
            radius = (mProps.getShadowRadius() / max(mRectVb.size.width,mRectVb.size.height)) * size
        }
        layer.shadowRadius = radius
        layer.shadowOpacity = mProps.getShadowOpacity()
        
    }
 
    private func validateViewBox() -> Bool {
        return mRectVb.size.width >= 0 && mRectVb.size.height >= 0
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
