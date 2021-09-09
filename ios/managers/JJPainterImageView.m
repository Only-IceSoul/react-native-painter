//
//  JJPainterImageView.m
//  react-native-painter
//
//  Created by Juan J LF on 9/7/21.
//

#import <Foundation/Foundation.h>
#import "React/RCTViewManager.h"
#import <React/RCTBridgeModule.h>
#import <UIKit/UIKit.h>


@interface
RCT_EXTERN_MODULE(JJPainterImageView,RCTViewManager)

RCT_EXPORT_VIEW_PROPERTY(source, NSString)
RCT_EXPORT_VIEW_PROPERTY(align, NSString)
RCT_EXPORT_VIEW_PROPERTY(aspect, NSString)
RCT_EXPORT_VIEW_PROPERTY(clipToBounds, NSNumber)


RCT_EXPORT_VIEW_PROPERTY(x, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(y, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(w, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(h, NSNumber)

RCT_EXPORT_VIEW_PROPERTY(translateZ, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(mask, NSString)
RCT_EXPORT_VIEW_PROPERTY(opacity, NSNumber)

RCT_EXPORT_VIEW_PROPERTY(fill, NSNumber)

RCT_EXPORT_VIEW_PROPERTY(shadow, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(shadowOpacity, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(shadowRadius, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(shadowOffset, NSDictionary)

RCT_EXPORT_VIEW_PROPERTY(rotate, NSDictionary)
RCT_EXPORT_VIEW_PROPERTY(scale, NSDictionary)
RCT_EXPORT_VIEW_PROPERTY(translate, NSDictionary)


@end
