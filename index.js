// main index.js
import React from 'react'
import { requireNativeComponent } from 'react-native';
import ExtractColor from './src/Color'
import ExtractColorWorklet from './src/ColorWorklet'

const MaskGView = requireNativeComponent("MaskGView",null);
const MaskView = requireNativeComponent("MaskView",null);
const RectView = requireNativeComponent("RectView",null);
const CircleView = requireNativeComponent("CircleView",null);
const TextView = requireNativeComponent("TextView",null);
const LineView = requireNativeComponent("LineView",null);
const GView = requireNativeComponent("GViewS",null);
const GViewHardware = requireNativeComponent("GView",null);
const PathView = requireNativeComponent("PathView",null);

export class Line extends React.PureComponent {
    render(){
        return <LineView {...this.props}/>
        
    } 
}

export class GS extends React.PureComponent {
    render(){
        return <GView {...this.props}  />
        
    } 
}
export class G extends React.PureComponent {
    render(){
        return <GViewHardware {...this.props}  />
        
    } 
}
export class Path extends React.PureComponent {
    render(){
        return <PathView {...this.props} />
        
    } 
}
export class Text extends React.PureComponent {
    render(){
        return <TextView {...this.props} />
        
    } 
}

export class Circle extends React.PureComponent {
    render(){
        return <CircleView {...this.props} />
        
    } 
}

export class Rect extends React.PureComponent {
    render(){
        return <RectView {...this.props} />
        
    } 
}


export class Mask extends React.PureComponent {
    render(){
        return <MaskView {...this.props} />
        
    } 
}
export class MaskG extends React.PureComponent {
    render(){
        return <MaskGView {...this.props} />
        
    } 
}


export const PainterS = requireNativeComponent("PainterS",null);
export const Painter = requireNativeComponent("Painter",null);
export const Color = ExtractColor
export const ColorWorklet = ExtractColorWorklet