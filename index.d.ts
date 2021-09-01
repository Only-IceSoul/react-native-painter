import React from "react";
import { Constructor, NativeMethods, ViewProps } from "react-native";

type ColorType = number | string;

interface PainterProps extends ViewProps {
    viewBox?:number[] 
    aspect?: 'meet' | 'slice' | 'none'
    align?:'xMinYMin'|
    'xMidYMin' |
    'xMaxYMin' |
    'xMinYMid' |
    'xMidYMid' |
    'xMaxYMid' |
    'xMinYMax' |
    'xMidYMax' |
    'xMaxYMax' |
    'none'
}

//atributo dasharray y todo el drawable view, actualizar inset con dip en android, traer el color de gradient
interface CommonProps {
  
    mask?:string
    opacity?:number

    scale?:{ x:number,y:number , ox?:number ,oy?:number ,percentageValue?:boolean }
    rotate?: {
        a:number,
        x?:number,
        y?:number,
        percentageValue?:boolean
    }
    translate?:{
        x:number,
        y:number,
        percentageValue?:boolean
    }

    
    shadow?: ColorType
    shadowOffset?:{
        x:number,
        y:number,
        percentageValue?:boolean
    }
    shadowOpacity?: number
    shadowRadius?:number

    stroke?: ColorType
    strokeOpacity?:number
    strokeWidth?:number
    strokeStart?:number
    strokeEnd?:number
    strokeCap?:'butt' | 'round' | 'square'
    strokeJoin?: 'bevel' | 'miter' | 'round'
    strokeMiter?:number

    fill?:ColorType
    fillRule?: 'evenodd' | 'nonzero'
    fillOpacity?:number
}

interface LineProps extends CommonProps , ViewProps{
    x1?:number
    y1?:number
    x2?:number
    y2?:number
}

interface TextProps extends CommonProps , ViewProps{
    x?:number
    y?:number
    text?:string
    fontSize?:number
    fontStyle?:'normal' | 'bold' | 'italic'
    font?: 'default' | string
    baseline?:'ascender' | 'middle' | 'descender' | 'none'  | 'center' | 'capHeight'
    verticalOffset?:number
    horizontalOffset?:number

}

interface CircleProps extends CommonProps , ViewProps{
    cx?:number
    cy?:number
    r?:number
}


interface PathProps extends CommonProps , ViewProps{
    d?:string  
}
interface GProps extends CommonProps , ViewProps{

}
interface RectProps extends CommonProps , ViewProps{
    x?:number
    y?:number
    w?:number
    h?:number
    rtl?:number
    rtr?:number
    rbr?:number
    rbl?:number
}
interface MaskProps extends ViewProps{
    name:string
}
interface MaskGProps extends CommonProps , ViewProps{

}

declare class MaskGComponent extends React.Component<MaskGProps> {}
declare const MaskGBase: Constructor<NativeMethods> & typeof MaskGComponent;

declare class MaskComponent extends React.Component<MaskProps> {}
declare const MaskBase: Constructor<NativeMethods> & typeof MaskComponent;

declare class RectComponent extends React.Component<RectProps> {}
declare const RectBase: Constructor<NativeMethods> & typeof RectComponent;

declare class CircleComponent extends React.Component<CircleProps> {}
declare const CircleBase: Constructor<NativeMethods> & typeof CircleComponent;

declare class TextComponent extends React.Component<TextProps> {}
declare const TextBase: Constructor<NativeMethods> & typeof TextComponent;

declare class LineComponent extends React.Component<LineProps> {}
declare const LineBase: Constructor<NativeMethods> & typeof LineComponent;

declare class PathComponent extends React.Component<PathProps> {}
declare const PathBase: Constructor<NativeMethods> & typeof PathComponent;

declare class GComponent extends React.Component<GProps> {}
declare const GBase: Constructor<NativeMethods> & typeof GComponent;

declare class PainterComponent extends React.Component<PainterProps> {}
declare const PainterBase: Constructor<NativeMethods> & typeof PainterComponent;

export class MaskG extends MaskGBase {}
export class Mask extends MaskBase {}
export class Rect extends RectBase {}
export class Circle extends CircleBase {}
export class Text extends TextBase {}
export class Line extends LineBase {}
export class Path extends PathBase {}
export class G extends GBase {}
export class GS extends GBase {}
export class Painter extends PainterBase {}
// export class PainterS extends PainterBase {}
export function ColorWorklet(color:number | number[] | string): number; 
export function Color(color:number | number[] | string): number; 