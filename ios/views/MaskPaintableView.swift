//
//  MaskPaintableView.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/31/21.
//

import Foundation
import UIKit


@objc(MaskPaintableView)
class MaskPaintableView: UIView {
    
    var mPainter:PainterKit!
    var mName = ""
    var mOldName = ""
    
    init() {
        super.init(frame: .zero)
    }
    
   
    //not animatable
    @objc func setName(_ v:String?){
        let ev = v == nil ? "" : v!
        if(mName != ev){
            mOldName = mName
            mName = ev
            invalidateName()
        }
    }
   
    open func setPainterKit(_ p:PainterKit){
        mPainter = p
        invalidateName()
        invalidatePainterKit()
    }

    
    func invalidateName(){
        if mPainter != nil{
           setupMaskView()
        }
    }
    
    func setupMaskView(){
        if !mOldName.isEmpty{
            mPainter.mMaskViews.removeValue(forKey: mOldName)
        }
        if !mName.isEmpty{
            mPainter.mMaskViews[mName] = self
        }
    }
    
    private var mLazyPainter = false
     func invalidatePainterKit(){
        
        if reactSubviews() == nil {
            mLazyPainter = true
        }else{
            for i in 0..<self.reactSubviews()!.count {
                let v = self.reactSubviews()![i]
                if let p = v as? PaintableView{
                    p.setPainterKit(mPainter)
                }
            }
        }
      
    }
    
     override func layoutSubviews() {
        super.layoutSubviews()
        if(mLazyPainter &&  self.reactSubviews() != nil){
            mLazyPainter = false
     
            for i in 0..<self.reactSubviews()!.count {
                let v = self.reactSubviews()![i]
                if let p = v as? PaintableView{
                    p.setPainterKit(mPainter)
                }
            }
        }
    }
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
        
    }

}
