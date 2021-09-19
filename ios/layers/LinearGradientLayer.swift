//
//  LinearGradientLayer.swift
//  react-native-painter
//
//  Created by Juan J LF on 9/5/21.
//

import Foundation

class LinearGradientLayer: CALayer {
    
    
    
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
    private var startX :CGFloat = 0.5
    private var startY :CGFloat = 0
    private var endX :CGFloat = 0.5
    private var endY :CGFloat = 1
    private var mColors : [CGColor] = [CGColor]()
    private var mPositions : [NSNumber]?
    
    public override init() {
        super.init()
        addSublayer(mLayer)
        mColors.append(UIColor.white.cgColor)
        mColors.append(UIColor.black.cgColor)
        mLayer.colors = mColors
      
        mLayer.type = CAGradientLayerType.axial
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
    public func setStartPoint(_ v:CGFloat,_ v2:CGFloat){
        if startX != v || startY != v2{
            startX = v
            startY = v2
            invalidateProps()
        }
    }
    public func setEndPoint(_ v:CGFloat,_ v2:CGFloat){
        if endX != v || endY != v2{
            endX = v
            endY = v2
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
        invalidateOpacity()
        invalidateGradientPosition()
        invalidateProps()
        invalidateTransform()
    }
    
    public func invalidateProps(){
        disableAnimation()
        mLayer.startPoint = CGPoint(x: startX, y: startY)
        mLayer.endPoint = CGPoint(x: endX, y: endY)
        mLayer.colors = mColors
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
