//
//  ImageContentLayer.swift
//  react-native-painter
//
//  Created by Juan J LF on 9/7/21.
//

import Foundation
import JJGuiso

class ImageContentLayer: CALayer , ViewTarget {
    
    private var mLayer = CALayer()
    private var mRect = CGRect()
    private var mSource = ""
    private var mAlign = "xMidYMid"
    private var mAspect = "meet"
    override init() {
        super.init()
        addSublayer(mLayer)
        mLayer.anchorPoint = CGPoint(x: 0, y: 0)
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
    public func setBgColor(_ v:Int){
        disableAnimation()
        self.backgroundColor = UIColor.parseInt(argb: v).cgColor
        commit()
    }
    public func setClipToBounds(_ v:Bool){
        disableAnimation()
        self.masksToBounds = v
        commit()
    }
    
    func invalidateImageTransform(){
        if self.frame.width > 0 && self.frame.height > 0 && mLayer.frame.width > 0 && mLayer.frame.height > 0 {
             let a = mAspect == "none" ? SVGViewBox.AspectRatio.none : ( mAspect == "slice" ? SVGViewBox.AspectRatio.slice : SVGViewBox.AspectRatio.meet)
            
            let trans =  SVGViewBox.transform3D(vbRect: CGRect(x: 0, y: 0, width: mLayer.frame.width, height: mLayer.frame.height), eRect: CGRect(x: 0, y: 0, width: self.frame.width, height: self.frame.height), align: mAlign, meetOrSlice: a)
            disableAnimation()
            mLayer.transform = trans
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
        mLayer.contents = i
        mLayer.frame = CGRect(x: 0, y: 0, width: i.width,  height: i.height  )
        mLayer.position = CGPoint(x: 0, y: 0)
        commit()
        invalidateImageTransform()
    }
    
    func removeImage(){
        disableAnimation()
        mLayer.contents = nil
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


