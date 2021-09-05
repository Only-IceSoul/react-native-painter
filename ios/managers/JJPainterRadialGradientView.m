//
//  JJPainterRadialGradientView.m
//  react-native-painter
//
//  Created by Juan J LF on 9/5/21.
//


#import <Foundation/Foundation.h>
#import "React/RCTViewManager.h"
#import <React/RCTBridgeModule.h>
#import <UIKit/UIKit.h>


@interface
RCT_EXTERN_MODULE(JJPainterRadialGradientView,RCTViewManager)

RCT_EXPORT_VIEW_PROPERTY(cx, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(cy, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(rx, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(ry, NSNumber)


RCT_EXPORT_VIEW_PROPERTY(x, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(y, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(w, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(h, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(colors, NSArray)
RCT_EXPORT_VIEW_PROPERTY(positions, NSArray)

RCT_EXPORT_VIEW_PROPERTY(translateZ, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(mask, NSString)
RCT_EXPORT_VIEW_PROPERTY(opacity, NSNumber)

RCT_EXPORT_VIEW_PROPERTY(rotate, NSDictionary)
RCT_EXPORT_VIEW_PROPERTY(scale, NSDictionary)
RCT_EXPORT_VIEW_PROPERTY(translate, NSDictionary)


@end
