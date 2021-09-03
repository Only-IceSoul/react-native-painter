//
//  CircleView.m
//  react-native-painter
//
//  Created by Juan J LF on 8/30/21.
//


#import <Foundation/Foundation.h>
#import "React/RCTViewManager.h"
#import <React/RCTBridgeModule.h>
#import <UIKit/UIKit.h>


@interface
RCT_EXTERN_MODULE(CircleView,RCTViewManager)

RCT_EXPORT_VIEW_PROPERTY(cx, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(cy, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(r, NSNumber)

RCT_EXPORT_VIEW_PROPERTY(translateZ, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(mask, NSString)
RCT_EXPORT_VIEW_PROPERTY(opacity, NSNumber)

RCT_EXPORT_VIEW_PROPERTY(fill, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(fillRule, NSString)
RCT_EXPORT_VIEW_PROPERTY(fillOpacity, NSNumber)

RCT_EXPORT_VIEW_PROPERTY(stroke, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(strokeOpacity, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(strokeWidth, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(strokeCap, NSString)
RCT_EXPORT_VIEW_PROPERTY(strokeJoin, NSString)
RCT_EXPORT_VIEW_PROPERTY(strokeMiter, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(strokeStart, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(strokeEnd, NSNumber)

RCT_EXPORT_VIEW_PROPERTY(shadow, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(shadowOpacity, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(shadowRadius, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(shadowOffset, NSDictionary)


RCT_EXPORT_VIEW_PROPERTY(rotate, NSDictionary)
RCT_EXPORT_VIEW_PROPERTY(scale, NSDictionary)
RCT_EXPORT_VIEW_PROPERTY(translate, NSDictionary)


@end
