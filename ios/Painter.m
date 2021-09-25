//
//  Painter.m
//  react-native-painter
//
//  Created by Juan J LF on 8/29/21.
//

#import <Foundation/Foundation.h>
#import "React/RCTViewManager.h"
#import <React/RCTBridgeModule.h>
#import <UIKit/UIKit.h>


@interface
RCT_EXTERN_MODULE(Painter,RCTViewManager)

RCT_EXPORT_VIEW_PROPERTY(viewBox, NSArray)
RCT_EXPORT_VIEW_PROPERTY(align, NSString)
RCT_EXPORT_VIEW_PROPERTY(aspect, NSString)

@end
