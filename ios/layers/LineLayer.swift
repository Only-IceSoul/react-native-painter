//
//  LineLayer.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/29/21.
//

import Foundation
import UIKit

public class LineLayer : Paintable{
    
    private var x1:CGFloat = 0
    private var y1:CGFloat = 0
    private var x2:CGFloat = 0
    private var y2:CGFloat = 0
    
    
    override init() {
        super.init()
        mIgnoreVbTransform = true
        mIgnoreFill = true
        
    }
    
    
    
    public func setX1(_ v:CGFloat){
        if x1 != v{
            x1 = v
            invalidateCommonProps()
            invalidatePath()
        }
    
    }
    public func setY1(_ v:CGFloat){
        if y1 != v{
            y1 = v
            invalidateCommonProps()
            invalidatePath()
        }
    }
    public func setX2(_ v:CGFloat){
        if x2 != v{
            x2 = v
            invalidateCommonProps()
            invalidatePath()
        }
    }
    public func setY2(_ v:CGFloat){
        if y2 != v{
            y2 = v
            invalidateCommonProps()
            invalidatePath()
        }
    }
    
    public override func setupPath() {
        mPath.removeAllPoints()
       
        if mPainterKit.mIsViewBoxEnabled{
            mPath.move(to: .init(x: x1.asViewBoxToWidth(mPainterKit.mViewBox, mRect.width), y: y1.asViewBoxToHeight(mPainterKit.mViewBox,mRect.height)))
            mPath.addLine(to: .init(x: x2.asViewBoxToWidth(mPainterKit.mViewBox, mRect.width), y: y2.asViewBoxToHeight(mPainterKit.mViewBox,mRect.height)))
        }else{
            mPath.move(to: .init(x: x1, y: y1))
            mPath.addLine(to: .init(x: x2, y: y2))
        }
     
    }
    public required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}
