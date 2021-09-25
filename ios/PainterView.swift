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
        let arr = r as? [CGFloat] ?? [0 , 0 ,-1 ,-1]
        mPainterKit.mViewBox = CGRect(x: arr[0], y: arr[1], width:
                          arr[2], height: arr[3])
        invalidatePainterKit()
    }
    @objc func setAlign(_ v:NSString?){
        let align = v as String? ?? "xMidYMid"
        mPainterKit.mAlign = align
        invalidatePainterKit()
    }
    @objc func setAspect(_ v:NSString?){
        let aspect = v as String? ?? "meet"
        let a = aspect == "none" ? SVGViewBox.AspectRatio.none : ( aspect == "slice" ? SVGViewBox.AspectRatio.slice : SVGViewBox.AspectRatio.meet)
        mPainterKit.mAspect = a
        invalidatePainterKit()
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
    
    
     func invalidatePainterKit(){
        
        if reactSubviews() == nil {
            return
        }
            for i in 0..<self.reactSubviews()!.count {
                let v = self.reactSubviews()![i]
                if let p = v as? PaintableView{
                    p.setPainterKit(mPainterKit)
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
