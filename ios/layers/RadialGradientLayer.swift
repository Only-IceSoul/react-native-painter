//
//  RadialGradientLayer.swift
//  react-native-painter
//
//  Created by Juan J LF on 9/5/21.
//

import Foundation

class RadialGradientLayer : CALayer {
    
    
    
    var mLayer = CAGradientLayer()
    var mTransform = TransformProps()
    var mBounds = CGRect()
    var mRectPath = CGRect()
    var mRectVb = CGRect(x: 0, y: 0, width: -1, height: -1)
    var mAlign = "xMidYMid"
    var mAspect = SVGViewBox.AspectRatio.meet
    
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
    
     override init() {
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
        mRectVb.set(rect: p.mViewBox)
        mAspect = p.mAspect
        mAlign = p.mAlign
        invalidate()
    }
    
    func setProps(_ p:CommonProps){}
    
  
    
    //MARK : PROPS
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
    
     func setCx(_ v:CGFloat){
        if cx != v{
            cx = v
            invalidateProps()
        }
    }
     func setCy(_ v:CGFloat){
        if cy != v{
            cy = v
            invalidateProps()
        }
    }
     func setRx(_ v:CGFloat){
        if rx != v{
            rx = v
            invalidateProps()
        }
    }
     func setRy(_ v:CGFloat){
        if ry != v{
            ry = v
            invalidateProps()
        }
    }
     func setColors(_ colors: [CGColor]){
      
        mColors = colors
        invalidateProps()
        
    }
    
     func setPositions(_ pos:[NSNumber]?){
        mPositions = pos
        invalidateProps()
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
        
        viewBoxTransform()
        
        invalidateProps()
        invalidateRect()
        invalidateTransform()
    }
    
     func invalidateProps(){
        disableAnimation()
        mLayer.startPoint = CGPoint(x: cx, y: cy)
        mLayer.endPoint = CGPoint(x: cx + rx, y: cy + ry)
        mLayer.colors = mColors
        mLayer.locations = mPositions
        commit()
    }
    
     func invalidateRect(){
        if !isLayout() {  return }
        
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
        
        disableAnimation()
        mLayer.frame = CGRect(x: 0, y: 0, width:ww, height: hh)
        mLayer.position = CGPoint(x: xx , y: yy)
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

