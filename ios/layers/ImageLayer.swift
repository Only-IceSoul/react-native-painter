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
        mProps.set(p)
        invalidateCommonProps()
        
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
    private var mClipToBounds = false
    public func setClipToBounds(_ v:Bool){
        mLayer.setClipToBounds(v)
        mClipToBounds = v
        invalidateCommonProps()
    }
    
    //MARK: Common props
    
    public func setOpacity(_ v:Float,_ status:Bool){
        mProps.opacityStatus = status
        if mProps.opacity != v{
            mProps.opacity = v
            invalidateCommonProps()
        }
      
    }
    public func setFill(_ v:Int,_ status:Bool){
        mProps.fillColorStatus = status
        if mProps.fillColor != v{
            mProps.fillColor = v
            invalidateCommonProps()
        }
   }
    public func setShadow(_ v:Int,_ status:Bool){
        mProps.shadowColorStatus = status
        if mProps.shadowColor != v{
            mProps.shadowColor = v
            invalidateCommonProps()
        }
    }
    
    public func setShadowOffset(_ x:CGFloat,_ y:CGFloat,_ percent:Bool,_ status:Bool){
        mProps.shadowOffsetStatus = status
        if mProps.shadowOffsetX != x || mProps.shadowOffsetY != y || mProps.shadowOffsetIsPercent != percent {
            mProps.shadowOffsetX = x
            mProps.shadowOffsetY = y
            mProps.shadowOffsetIsPercent = percent
            invalidateCommonProps()
        }
    }
    

    public func setShadowOpacity(_ v:Float,_ status:Bool){
        mProps.shadowOpacityStatus = status
        if mProps.shadowOpacity != v{
            mProps.shadowOpacity = v
            invalidateCommonProps()
        }
    }
    
    public func setShadowRadius(_ v:CGFloat,_ status:Bool){
        mProps.shadowRadiusStatus = status
        if mProps.shadowRadius != v{
            mProps.shadowRadius = v
            invalidateCommonProps()
        }
    }
    
    //MARK: Transform props

    public func setTransX(v:CGFloat) {
            if(mTransform.mTranslationX != v ){
                mTransform.mTranslationX = v;
                invalidateTransform();
            }
        }
        public func setTransY(v:CGFloat) {
            if(mTransform.mTranslationY != v ){
                mTransform.mTranslationY = v;
                invalidateTransform();
            }
        }
        public func setTransPercentageValue(v:Bool) {
            if(mTransform.mTranslationIsPercent != v ){
                mTransform.mTranslationIsPercent = v;
                invalidateTransform();
            }
        }

        public func setRot(v:CGFloat) {
            if(mTransform.mRotation != v ){
                mTransform.mRotation = v;
                invalidateTransform();
            }
        }
        public func setRotO(v:CGFloat) {
            if(mTransform.mRotationOriginX != v || mTransform.mRotationOriginY != v ){
                mTransform.mRotationOriginX = v;
                mTransform.mRotationOriginY = v;
                invalidateTransform();
            }
        }
        public func setRotOx(v:CGFloat) {
            if(mTransform.mRotationOriginX != v ){
                mTransform.mRotationOriginX = v;
                invalidateTransform();
            }
        }
        public func setRotOy(v:CGFloat) {
            if(mTransform.mRotationOriginY != v ){
                mTransform.mRotationOriginY = v;
                invalidateTransform();
            }
        }
        public func setRotPercentageValue(v:Bool) {
            if(mTransform.mRotationIsPercent != v ){
                mTransform.mRotationIsPercent = v;
                invalidateTransform();
            }
        }

        public func setSc(v:CGFloat){
            if(mTransform.mScaleX != v || mTransform.mScaleY != v){
                mTransform.mScaleX = v;
                mTransform.mScaleY = v;
                invalidateTransform();
            }
        }
        public func setScX(v:CGFloat) {
            if(mTransform.mScaleX != v ){
                mTransform.mScaleX = v;
                invalidateTransform();
            }
        }

        public func setScY(v:CGFloat) {
            if(mTransform.mScaleY != v ){
                mTransform.mScaleY = v;
                invalidateTransform();
            }
        }
        public func setScO(v:CGFloat){
            if(mTransform.mScaleOriginX != v || mTransform.mScaleOriginY != v){
                mTransform.mScaleOriginX = v;
                mTransform.mScaleOriginY = v;
                invalidateTransform();
            }
        }
        public func setScOx(v:CGFloat) {
            if(mTransform.mScaleOriginX != v ){
                mTransform.mScaleOriginX = v;
                invalidateTransform();
            }
        }
        public func setScOy(v:CGFloat) {
            if(mTransform.mScaleOriginY != v ){
                mTransform.mScaleOriginY = v;
                invalidateTransform();
            }
        }
        public func setScPercentageValue(v:Bool) {
            if(mTransform.mScaleIsPercent != v ){
                mTransform.mScaleIsPercent = v;
                invalidateTransform();
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
        invalidatePosition()
        invalidateCommonProps()
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
        
       
        mLayer.setBounds(xx,yy,ww,hh)
   
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
    
    
    
    public func invalidateCommonProps(){
        if(mRect.width > 0 && mRect.height > 0 && mPainterKit != nil){
            disableAnimation()
            super.opacity = mProps.getOpacity()
            let c = UIColor.parseInt(argb: mProps.getFillColor())
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
            offset.width = mProps.getShadowOffsetX() * mRect.width
            offset.height = mProps.getShadowOffsetY() * mRect.height
        }else if mPainterKit.mIsViewBoxEnabled {
            offset.width = (mProps.getShadowOffsetX() / mPainterKit.mViewBox.width) * mRect.width
            offset.height = (mProps.getShadowOffsetY() / mPainterKit.mViewBox.height) * mRect.height
        }else{
            offset.width = mProps.getShadowOffsetX()
            offset.height = mProps.getShadowOffsetY()
        }
        
        layer.shadowOffset = offset
        
        var radius = mProps.getShadowRadius()
        if mPainterKit.mIsViewBoxEnabled {
            let size = mPainterKit.mViewBox.width > mPainterKit.mViewBox.height ? mRect.width : mRect.height
            radius = (mProps.getShadowRadius() / max(mPainterKit.mViewBox.width,mPainterKit.mViewBox.height)) * size
        }
        layer.shadowRadius = radius
        layer.shadowOpacity = mProps.getShadowOpacity()
        
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
