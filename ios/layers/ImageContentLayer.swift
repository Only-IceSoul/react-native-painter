//
//  ImageContentLayer.swift
//  react-native-painter
//
//  Created by Juan J LF on 9/7/21.
//

import Foundation
import JJGuiso

class ImageContentLayer: CALayer , ViewTarget {
    
    private var mImageLayer = CALayer()
    private var mContainer = CALayer()
    private var mRect = CGRect()
    private var mSource = ""
    private var mAlign = "xMidYMid"
    private var mAspect = "meet"
    override init() {
        super.init()
        addSublayer(mContainer)
        mContainer.anchorPoint = CGPoint(x: 0, y: 0)
        mContainer.addSublayer(mImageLayer)
        mImageLayer.anchorPoint = CGPoint(x: 0, y: 0)
    }
    
    public func setSrc(_ v:String){
        mSource = v
        handleSource()
    }
    
    
    public func setAlign(_ v:String){
        if mAlign != v {
            mAlign = v
            invalidateImageTransform()
        }
    }
    public func setAspect(_ v:String){
        if mAspect != v {
            mAspect = v
            invalidateImageTransform()
        }
    }
    public func setClipToBounds(_ v:Bool){
        disableAnimation()
        mContainer.masksToBounds = v
        commit()
       
    }
    
    public func getContainer()->CALayer{
        return mContainer
    }
    
    public func getImageLayer() -> CALayer{
        return mImageLayer
    }
    
    public func setBounds(_ x:CGFloat,_ y:CGFloat,_ w:CGFloat,_ h:CGFloat){
        disableAnimation()
        let b = CGRect(x: 0, y: 0, width:w, height: h)
        self.frame = b
        self.position = CGPoint(x: x , y: y)
        mContainer.frame = b
        mContainer.position = CGPoint(x: 0 , y: 0)
        commit()
        invalidateImageTransform()
    }
    
    func invalidateImageTransform(){
        if mContainer.frame.width > 0 && mContainer.frame.height > 0 && mImageLayer.frame.width > 0 && mImageLayer.frame.height > 0 {
           
             let a = mAspect == "none" ? SVGViewBox.AspectRatio.none : ( mAspect == "slice" ? SVGViewBox.AspectRatio.slice : SVGViewBox.AspectRatio.meet)
           
            let trans =  SVGViewBox.transformArray(vbRect: CGRect(x: 0, y: 0, width: mImageLayer.frame.width, height: mImageLayer.frame.height), eRect: CGRect(x: 0, y: 0, width: mContainer.frame.width, height: mContainer.frame.height), align: mAlign, meetOrSlice: a)
            disableAnimation()
            mImageLayer.frame.size.width *= trans[2]
            mImageLayer.frame.size.height *= trans[3]
            mImageLayer.position = CGPoint(x:trans[0], y: trans[1])
            commit()
     
        }
    }
    
    private func handleSource(){
        if mSource.isEmpty{
            self.contents = nil
        }else if mSource.contains("base64,"){
            base64String()
        }else if mSource.contains("static;"){
            staticImage()
        }else{
            netWork(mSource)
        }
    }
    
    private func base64String(){
        let s = mSource.split(separator: ",")[1]
        if let data = Data(base64Encoded: String(s)){
            let i = UIImage(data: data)?.cgImage
            if i != nil{
                invalidateImage(i!)
            }else{
                removeImage()
            }
        }else{
            removeImage()
        }
       
    }
    
    private func staticImage(){
        let s = mSource.split(separator: ";")[1]
        let ss = String(s)
        if ss.contains("file://"){
            guard let url = URL(string: ss),
                    let data = try? Data(contentsOf: url),
                    let i = UIImage(data: data)?.cgImage
                            else {
                            removeImage()
                               return
                           }
                invalidateImage(i)
            
        }
        
    }
    private func netWork(_ uri:String){
        mMyRequest?.clear()
        Guiso.load(model: uri).into(self)
        
    }
    
    private var mMyRequest : GuisoRequest?
    func setRequest(_ tag:GuisoRequest?){
        mMyRequest = tag
    }
    func getRequest() -> GuisoRequest?{
        return mMyRequest
    }
    func onResourceReady(_ gif:AnimatedLayer){}
    func onResourceReady(_ img:UIImage){
        let i = img.cgImage
    
        if i != nil{
            invalidateImage(i!)
        }else{
            removeImage()
        }
       
    }
    func invalidateImage(_ i :CGImage){
        disableAnimation()
        mImageLayer.contents = i
        mImageLayer.frame = CGRect(x: 0, y: 0, width: i.width,  height: i.height  )
        commit()
        invalidateImageTransform()
    }
    
    func removeImage(){
        disableAnimation()
        mImageLayer.contents = nil
        commit()
    }
    func onLoadFailed(_ error:String){
        removeImage()
    }
    func getContentMode() -> UIView.ContentMode{
        return UIView.ContentMode.scaleAspectFit
    }
    func onHolder(_ image:UIImage?){
        
    }
    func onFallback(){
        
    }
   
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    
     func disableAnimation(){
        CATransaction.begin()
        CATransaction.setDisableActions(true)
    }
    
     func commit(){
        CATransaction.commit()
    }
}


