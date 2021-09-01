//
//  new.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/29/21.
//

/*
    RectView -> width height por w h ya que cambia el tamaño de la vista
 */
 

import UIKit


@objc(PainterView)
class PainterView: UIView {
    
    
    var mPainterKit = PainterKit()
    

    init() {
        super.init(frame: .zero)
    }

   
 
    @objc func setViewBox(_ r:NSArray?){
        mPainterKit.mIsViewBoxEnabled = r != nil
        let arr = r as? [CGFloat] ?? [0 , 0 ,0 ,0]
        mPainterKit.mViewBox = CGRect(x: arr[0], y: arr[1], width: arr[0] +
                          arr[2], height: arr[1] + arr[3])
    }
    @objc func setAlign(_ v:NSString?){
        let align = v as String? ?? "none"
        mPainterKit.mAlign = align
            
    }
    @objc func setAspect(_ v:NSString?){
        let aspect = v as String? ?? "none"
        mPainterKit.mAspect = aspect
    }
    
    override func didUpdateReactSubviews() {

     
        if reactSubviews() == nil {
            return
        }

        for i in 0..<self.reactSubviews()!.count {
            let v = self.reactSubviews()![i]
     
            if let p = v as? PaintableView{
                p.setPainterKit(mPainterKit)
                self.addSubview(v)
            }
            if let m = v as? MaskPaintableView{
                m.setPainterKit(mPainterKit)
               
            }

        }
    }

   
    
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
        
    }

}
