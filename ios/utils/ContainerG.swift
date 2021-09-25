//
//  ContainerG.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/31/21.
//

import Foundation
import UIKit

class ContainerG : UIView{
    
    init() {
        super.init(frame: .zero)
    }
    
    override var bounds: CGRect{
        didSet{
//            layer.position = CGPoint(x: 0, y: 0)
//            layer.anchorPoint = CGPoint(x: 0, y: 0)
        }
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}
