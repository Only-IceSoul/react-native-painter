//
//  PathLayer.swift
//  react-native-painter
//
//  Created by Juan J LF on 8/30/21.
//

import Foundation
import UIKit

public class PathLayer : Paintable{
    
    private var d:String = ""
   
    
    override init() {
        super.init()
    }
    
    
    
    public func setD(_ v:String){
        if d != v{
            d = v
            invalidatePath()
        }
    
    }
   
    public override func setupPath() {
        mPath.removeAllPoints()
       
            do{
                   mPath = try SVGPathParser.parse(d: d)
              
            }catch{
                   print("Exception: ",error)
            }
    }
    public required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}
