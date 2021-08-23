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
  
   
    scale?:{ x:number,y:number }
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

interface PathProps extends CommonProps , ViewProps{
    d?:string  
}
interface GProps extends CommonProps , ViewProps{

}

declare class LineComponent extends React.Component<LineProps> {}
declare const LineBase: Constructor<NativeMethods> & typeof LineComponent;

declare class PathComponent extends React.Component<PathProps> {}
declare const PathBase: Constructor<NativeMethods> & typeof PathComponent;

declare class GComponent extends React.Component<GProps> {}
declare const GBase: Constructor<NativeMethods> & typeof GComponent;

declare class PainterComponent extends React.Component<PainterProps> {}
declare const PainterBase: Constructor<NativeMethods> & typeof PainterComponent;

export class Line extends LineBase {}
export class Path extends PathBase {}
export class G extends GBase {}
export class GS extends GBase {}
export class Painter extends PainterBase {}
export class PainterS extends PainterBase {}
export function Color(color:number | number[] | string): number; 