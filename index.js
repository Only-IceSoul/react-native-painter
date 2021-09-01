// main index.js
import React from 'react'
import { Platform, requireNativeComponent, StyleSheet } from 'react-native';
import ExtractColor from './src/Color'
import ExtractColorWorklet from './src/ColorWorklet'

const MaskGView = requireNativeComponent("MaskGView",null);
const MaskView = requireNativeComponent("MaskView",null);
const RectView = requireNativeComponent("RectView",null);
const CircleView = requireNativeComponent("CircleView",null);
const TextView = requireNativeComponent("TextView",null);
const LineView = requireNativeComponent("LineView",null);
const GViewS = requireNativeComponent("GViewS",null);
const GView = requireNativeComponent("GView",null);
const PathView = requireNativeComponent("PathView",null);

export class Line extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return <LineView {...others}
         style={Platform.OS === 'android' 
         ? [{transform: style?.transform }] 
         : [{transform:style?.transform},StyleSheet.absoluteFillObject]} />
        
    } 
}

export class GS extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return Platform.OS === 'android' ?
        <GViewS {...others} style={[{transform: style?.transform }] } />
        
         : <GView {...others} style={[{transform:style?.transform},StyleSheet.absoluteFillObject]} />
        
    } 
}
export class G extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return <GView {...others}
         style={Platform.OS === 'android' 
         ? [{transform: style?.transform }] 
         : [{transform:style?.transform},StyleSheet.absoluteFillObject]} />
        
    } 
}
export class Path extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return <PathView {...others}
         style={Platform.OS === 'android' 
         ? [{transform: style?.transform }] 
         : [{transform:style?.transform},StyleSheet.absoluteFillObject]} />
        
    } 
}
export class Text extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return <TextView {...others}
         style={Platform.OS === 'android' 
         ? [{transform: style?.transform }] 
         : [{transform:style?.transform},StyleSheet.absoluteFillObject]} />
        
    } 
}

export class Circle extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return <CircleView {...others}
         style={Platform.OS === 'android' 
         ? [{transform: style?.transform }] 
         : [{transform:style?.transform},StyleSheet.absoluteFillObject]} />
        
    } 
}

export class Rect extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return <RectView {...others}
         style={Platform.OS === 'android' 
         ? [{transform: style?.transform }] 
         : [{transform:style?.transform},StyleSheet.absoluteFillObject]} />
        
    } 
}


export class Mask extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return <MaskView {...others}
         style={Platform.OS === 'android' 
         ? [] 
         : [StyleSheet.absoluteFillObject]} />
    } 
}
export class MaskG extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return <MaskGView {...others}
         style={Platform.OS === 'android' 
         ? [{transform: style?.transform }] 
         : [{transform:style?.transform},StyleSheet.absoluteFillObject]} />
        
    } 
}


// export const PainterS = requireNativeComponent("PainterS",null);
export const Painter = requireNativeComponent("Painter",null);
export const Color = ExtractColor
export const ColorWorklet = ExtractColorWorklet