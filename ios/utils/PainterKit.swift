//
//  PainterKit.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/29/21.
//

import UIKit


 class PainterKit {
     var mViewBox = CGRect(x: 0, y: 0, width: -1, height: -1)
     var mAlign = "xMidYMid"
     var mAspect = SVGViewBox.AspectRatio.meet
     var mMaskViews = [String: MaskPaintableView]()
}
