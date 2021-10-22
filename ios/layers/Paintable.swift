//
//  Paintable.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/29/21.
//

import UIKit
 class Paintable: CAShapeLayer  {
    
 
    
    //MARK: Var
    var mProps = CommonProps()
    var mTransform = TransformProps()
    

    var mBounds = CGRect()
    var mRectPath = CGRect()
    var mRectVb = CGRect(x: 0, y: 0, width: -1, height: -1)
    
    var mPath  = UIBezierPath()
    
    var mAlign = "xMidYMid"
    var mAspect = SVGViewBox.AspectRatio.meet
    
    var mIgnoreFill = false
   
    
     override init() {
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
    
        mRectVb.set(rect: p.mViewBox)
        mAspect = p.mAspect
        mAlign = p.mAlign
        invalidate()
    }
    
    func setProps(_ p:CommonProps){
        mProps.set(p)
        invalidate()
    }
    
    //MARK: Common Props
    
    
    @discardableResult
     func setFill(_ v:Int,_ status:Bool) -> Paintable{
        mProps.fillColorStatus = status
        if mProps.fillColor != v{
            mProps.fillColor = v
            invalidateCommonProps()
        }
       return self
   }
    
    @discardableResult
     func setFillRule(_ v:String,_ status:Bool) -> Paintable{
        mProps.fillRuleStatus = status
        if mProps.fillRule != v{
            mProps.fillRule = v
            invalidate()
        }
       return self
    }
    
    @discardableResult
     func setFillOpacity(_ v:CGFloat,_ status:Bool) -> Paintable{
        mProps.fillOpacityStatus = status
        if mProps.fillOpacity != v{
            mProps.fillOpacity = v
            invalidateCommonProps()
        }
       return self
    }
    
    @discardableResult
     func setStroke(_ v:Int,_ status:Bool) -> Paintable{
        mProps.strokeColorStatus = status
        if mProps.strokeColor != v{
            mProps.strokeColor = v
            invalidateCommonProps()
        }
       return self
    }
    
    @discardableResult
     func setStrokeOpacity(_ v:CGFloat,_ status:Bool) -> Paintable{
        mProps.strokeOpacityStatus = status
        if mProps.strokeOpacity != v{
            mProps.strokeOpacity = v
            invalidateCommonProps()
        }
       return self
    }
    
    @discardableResult
     func setStrokeWidth(_ v:CGFloat,_ status:Bool) -> Paintable{
        mProps.strokeWidthStatus = status
        if mProps.strokeWidth != v{
            mProps.strokeWidth = v
            invalidateShadowPath()
            invalidateCommonProps()
        }
       return self
    }
    
    @discardableResult
     func setStrokeCap(_ v:String) -> Paintable{
        if mProps.strokeCap != v{
            mProps.strokeCap = v
            invalidateShadowPath()
            invalidateCommonProps()
        }
       return self
    }
    
    @discardableResult
     func setStrokeJoin(_ v:String) -> Paintable{
        if mProps.strokeJoin != v{
            mProps.strokeJoin = v
            invalidateShadowPath()
            invalidateCommonProps()
        }
       return self
    }
    @discardableResult
     func setStrokeMiter(_ v:CGFloat,_ status:Bool) -> Paintable{
        mProps.strokeMiterStatus = status
        if mProps.strokeMiter != v{
            mProps.strokeMiter = v
            invalidateShadowPath()
            invalidateCommonProps()
        }
       return self
    }
    
    @discardableResult
     func setStrokeStart(_ v:CGFloat,_ status:Bool) -> Paintable{
        mProps.strokeStartStatus = status
        if mProps.strokeStart != v{
            mProps.strokeStart = v
            invalidateCommonProps()
        }
       return self
    }
    
    @discardableResult
     func setStrokeEnd(_ v:CGFloat,_ status:Bool) -> Paintable{
        mProps.strokeEndStatus = status
        if mProps.strokeEnd != v{
            mProps.strokeEnd = v
            invalidateCommonProps()
        }
       return self
    }
    
    
    @discardableResult
     func setShadow(_ v:Int,_ status:Bool) -> Paintable{
        mProps.shadowColorStatus = status
        if mProps.shadowColor != v{
            mProps.shadowColor = v
            invalidateCommonProps()
        }
       return self
    }
    
    @discardableResult
     func setShadowOpacity(_ v:Float,_ status:Bool) -> Paintable{
        mProps.shadowOpacityStatus = status
        if mProps.shadowOpacity != v{
            mProps.shadowOpacity = v
            invalidateCommonProps()
        }
       return self
    }
    
    @discardableResult
     func setShadowRadius(_ v:CGFloat,_ status:Bool) -> Paintable{
        mProps.shadowRadiusStatus = status
        if mProps.shadowRadius != v{
            mProps.shadowRadius = v
            invalidateCommonProps()
        }
       return self
    }
    
    @discardableResult
     func setShadowOffset(_ v:CGFloat,_ status:Bool) -> Paintable{
        mProps.shadowOffsetXStatus = status
        mProps.shadowOffsetYStatus = status
        if mProps.shadowOffsetX != v || mProps.shadowOffsetY != v  {
            mProps.shadowOffsetX = v
            mProps.shadowOffsetY = v
            invalidateCommonProps()
        }
       return self
    }
    @discardableResult
     func setShadowOffsetX(_ v:CGFloat,_ status:Bool) -> Paintable{
        mProps.shadowOffsetXStatus = status
        if mProps.shadowOffsetX != v {
            mProps.shadowOffsetX = v
            invalidateCommonProps()
        }
       return self
    }
    @discardableResult
     func setShadowOffsetY(_ v:CGFloat,_ status:Bool) -> Paintable{
        mProps.shadowOffsetYStatus = status
        if mProps.shadowOffsetY != v  {
            mProps.shadowOffsetY = v
            invalidateCommonProps()
        }
       return self
    }
    @discardableResult
     func setShadowPercentageValue(_ v:Bool,_ status:Bool) -> Paintable{
        mProps.shadowOffsetIsPerecentStatus = status
        if mProps.shadowOffsetIsPercent != v  {
            mProps.shadowOffsetIsPercent = v
            invalidateCommonProps()
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
        disableAnimation()
        super.frame = mBounds
        super.position = CGPoint(x: 0, y: 0)
        super.anchorPoint = CGPoint(x: 0, y: 0)
        commit()
        invalidate()
        
    }
    

    open func setupPath(){
        
    
        
    }
    
    
    
  
    func invalidate(){
        if(mBounds.width > 0 && mBounds.height > 0){
            
            setupPath()
            
            viewBoxTransform()
            
            mPath.usesEvenOddFillRule = mProps.getFillRule()
            
            super.path = mPath.cgPath
            
          
            //require rectpath , viewboxtransform
            invalidateShadowPath()
            invalidateCommonProps()
            invalidateTransform()
        }
        
    }
    
    func invalidateShadowPath(){
        var sw:CGFloat = validateViewBox() ? mProps.getStrokeWidth().asViewBoxToMax(mRectVb, mRectPath.width, mRectPath.height) : mProps.getStrokeWidth()
       
        super.shadowPath = fill() ? mPath.cgPath : mPath.cgPath.copy(strokingWithWidth: sw, lineCap: mProps.getCGStrokeCap(), lineJoin: mProps.getCGStrokeJoin(), miterLimit: mProps.getStrokeMiter())
        
    }
   
    
     func invalidateCommonProps(){
        
        if(mBounds.width > 0 && mBounds.height > 0 ){
            disableAnimation()
            if !mIgnoreFill { setupFill() }
            setupStroke()
            setupShadow()
            commit()
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
            mPath.apply(trans)
            mRectPath = mRectPath.applying(trans)
         }else{
            mRectPath.set(rect: mBounds)
         }
    }
    
   
    
    private func setupFill(){
        let c = UIColor.parseInt(argb: mProps.getFillColor(), opacity: mProps.getFillOpacity())
        super.fillColor = c.cgColor
    }
    
    private func setupStroke(){
        var sw:CGFloat = validateViewBox() ? mProps.getStrokeWidth().asViewBoxToMax(mRectVb, mRectPath.width, mRectPath.height) : mProps.getStrokeWidth()

        super.lineWidth = sw
        let c = UIColor.parseInt(argb: mProps.getStrokeColor(), opacity: mProps.getStrokeOpacity())
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
            offset.width = mProps.getShadowOffsetX() * mRectPath.width
            offset.height = mProps.getShadowOffsetY() * mRectPath.height
        }else if validateViewBox() {
            offset.width = (mProps.getShadowOffsetX() / mRectVb.size.width) * mRectPath.width
            offset.height = (mProps.getShadowOffsetY() / mRectVb.size.height) * mRectPath.height
        }else{
            offset.width = mProps.getShadowOffsetX()
            offset.height = mProps.getShadowOffsetY()
        }
        super.shadowOffset = offset
        
        var radius = validateViewBox() ? mProps.getShadowRadius().asViewBoxToMax(mRectVb, mRectPath.width, mRectPath.height) : mProps.getShadowRadius()
        super.shadowRadius = radius
        super.shadowOpacity = mProps.getShadowOpacity()
        
    }
 
    private func validateViewBox() -> Bool {
        return mRectVb.size.width >= 0 && mRectVb.size.height >= 0
    }
    private func fill()->Bool{
        return !mIgnoreFill && mProps.getFillColor() != 0
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
