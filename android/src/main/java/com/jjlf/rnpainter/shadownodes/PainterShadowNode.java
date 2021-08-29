package com.jjlf.rnpainter.shadownodes;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.NativeViewHierarchyOptimizer;
import com.facebook.react.uimanager.annotations.ReactPropGroup;

import static com.facebook.react.uimanager.ViewProps.ALIGN_CONTENT;
import static com.facebook.react.uimanager.ViewProps.ALIGN_ITEMS;
import static com.facebook.react.uimanager.ViewProps.ALIGN_SELF;
import static com.facebook.react.uimanager.ViewProps.BACKGROUND_COLOR;
import static com.facebook.react.uimanager.ViewProps.BORDER_BOTTOM_WIDTH;
import static com.facebook.react.uimanager.ViewProps.BORDER_END_WIDTH;
import static com.facebook.react.uimanager.ViewProps.BORDER_LEFT_WIDTH;
import static com.facebook.react.uimanager.ViewProps.BORDER_RIGHT_WIDTH;
import static com.facebook.react.uimanager.ViewProps.BORDER_START_WIDTH;
import static com.facebook.react.uimanager.ViewProps.BORDER_TOP_WIDTH;
import static com.facebook.react.uimanager.ViewProps.BORDER_WIDTH;
import static com.facebook.react.uimanager.ViewProps.BOTTOM;
import static com.facebook.react.uimanager.ViewProps.COLLAPSABLE;
import static com.facebook.react.uimanager.ViewProps.DISPLAY;
import static com.facebook.react.uimanager.ViewProps.ELEVATION;
import static com.facebook.react.uimanager.ViewProps.END;
import static com.facebook.react.uimanager.ViewProps.FLEX;
import static com.facebook.react.uimanager.ViewProps.FLEX_BASIS;
import static com.facebook.react.uimanager.ViewProps.FLEX_DIRECTION;
import static com.facebook.react.uimanager.ViewProps.FLEX_GROW;
import static com.facebook.react.uimanager.ViewProps.FLEX_SHRINK;
import static com.facebook.react.uimanager.ViewProps.FLEX_WRAP;
import static com.facebook.react.uimanager.ViewProps.HEIGHT;
import static com.facebook.react.uimanager.ViewProps.JUSTIFY_CONTENT;
import static com.facebook.react.uimanager.ViewProps.LEFT;
import static com.facebook.react.uimanager.ViewProps.MARGIN;
import static com.facebook.react.uimanager.ViewProps.MARGIN_BOTTOM;
import static com.facebook.react.uimanager.ViewProps.MARGIN_END;
import static com.facebook.react.uimanager.ViewProps.MARGIN_HORIZONTAL;
import static com.facebook.react.uimanager.ViewProps.MARGIN_LEFT;
import static com.facebook.react.uimanager.ViewProps.MARGIN_RIGHT;
import static com.facebook.react.uimanager.ViewProps.MARGIN_START;
import static com.facebook.react.uimanager.ViewProps.MARGIN_TOP;
import static com.facebook.react.uimanager.ViewProps.MARGIN_VERTICAL;
import static com.facebook.react.uimanager.ViewProps.MAX_HEIGHT;
import static com.facebook.react.uimanager.ViewProps.MAX_WIDTH;
import static com.facebook.react.uimanager.ViewProps.MIN_HEIGHT;
import static com.facebook.react.uimanager.ViewProps.MIN_WIDTH;
import static com.facebook.react.uimanager.ViewProps.OVERFLOW;
import static com.facebook.react.uimanager.ViewProps.PADDING;
import static com.facebook.react.uimanager.ViewProps.PADDING_BOTTOM;
import static com.facebook.react.uimanager.ViewProps.PADDING_END;
import static com.facebook.react.uimanager.ViewProps.PADDING_HORIZONTAL;
import static com.facebook.react.uimanager.ViewProps.PADDING_LEFT;
import static com.facebook.react.uimanager.ViewProps.PADDING_RIGHT;
import static com.facebook.react.uimanager.ViewProps.PADDING_START;
import static com.facebook.react.uimanager.ViewProps.PADDING_TOP;
import static com.facebook.react.uimanager.ViewProps.PADDING_VERTICAL;
import static com.facebook.react.uimanager.ViewProps.POSITION;
import static com.facebook.react.uimanager.ViewProps.RIGHT;
import static com.facebook.react.uimanager.ViewProps.START;
import static com.facebook.react.uimanager.ViewProps.TOP;
import static com.facebook.react.uimanager.ViewProps.WIDTH;

public class PainterShadowNode extends LayoutShadowNode {

    @SuppressWarnings({"unused", "EmptyMethod"})
    @ReactPropGroup(
            names = {
                    ALIGN_ITEMS,
                    COLLAPSABLE,
                    FLEX_BASIS,
                    FLEX_DIRECTION,
                    FLEX_GROW,
                    FLEX_SHRINK,
                    FLEX_WRAP,
                    JUSTIFY_CONTENT,
                    OVERFLOW,
                    ALIGN_CONTENT,
                    DISPLAY,

                    /* dimensions */
//                    MIN_WIDTH,
//                    MAX_WIDTH,
//                    MIN_HEIGHT,
//                    MAX_HEIGHT,

                    /* othres */
                    BACKGROUND_COLOR,
                    ELEVATION,
                    /* margins */
                    MARGIN,
                    MARGIN_VERTICAL,
                    MARGIN_HORIZONTAL,
                    MARGIN_LEFT,
                    MARGIN_RIGHT,
                    MARGIN_TOP,
                    MARGIN_BOTTOM,
                    MARGIN_START,
                    MARGIN_END,

                    /* paddings */
                    PADDING,
                    PADDING_VERTICAL,
                    PADDING_HORIZONTAL,
                    PADDING_LEFT,
                    PADDING_RIGHT,
                    PADDING_TOP,
                    PADDING_BOTTOM,
                    PADDING_START,
                    PADDING_END,

                    BORDER_WIDTH,
                    BORDER_START_WIDTH,
                    BORDER_END_WIDTH,
                    BORDER_TOP_WIDTH,
                    BORDER_BOTTOM_WIDTH,
                    BORDER_LEFT_WIDTH,
                    BORDER_RIGHT_WIDTH,
            }
    )
    public void ignoreLayoutProps(int index, Dynamic value) {}


    @Override
    public Integer getHeightMeasureSpec() {
        return super.getHeightMeasureSpec();
    }
}