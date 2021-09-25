//
//  extension_NSAttrString.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/31/21.
//

import Foundation
import UIKit

extension NSAttributedString {
    
    func sizeOneLine( cgSize: inout CGSize) {
         
         let size = CGSize(width: CGFloat.greatestFiniteMagnitude, height: CGFloat.greatestFiniteMagnitude)
   
        let boundingBox = self.boundingRect(with: size, options: [.usesLineFragmentOrigin, .usesFontLeading], context: nil)
         
         //redondeo float
         cgSize.width =  boundingBox.width
         cgSize.height = boundingBox.height
     }
    
    

}
