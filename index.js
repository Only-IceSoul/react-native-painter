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
const GViewH = requireNativeComponent("GViewH",null);
const PathView = requireNativeComponent("PathView",null);
const EllipseView = requireNativeComponent("EllipseView",null);
const LinearGradientView = requireNativeComponent("JJPainterLinearGradientView",null);
const RadialGradientView = requireNativeComponent("JJPainterRadialGradientView",null);
const ImageView = requireNativeComponent("JJPainterImageView",null);

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
export class GH extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return Platform.OS === 'android' ?
        <GViewH {...others} style={[{transform: style?.transform }] } />
        
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
export class Ellipse extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return <EllipseView {...others}
         style={Platform.OS === 'android' 
         ? [{transform: style?.transform }] 
         : [{transform:style?.transform},StyleSheet.absoluteFillObject]} />
        
    } 
}

export class LinearGradient extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return <LinearGradientView {...others}
         style={Platform.OS === 'android' 
         ? [{transform: style?.transform }] 
         : [{transform:style?.transform},StyleSheet.absoluteFillObject]} />
        
    } 
}

export class RadialGradient extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return <RadialGradientView {...others}
         style={Platform.OS === 'android' 
         ? [{transform: style?.transform }] 
         : [{transform:style?.transform},StyleSheet.absoluteFillObject]} />
        
    } 
}
export class Image extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return <ImageView {...others}
         style={Platform.OS === 'android' 
         ? [{transform: style?.transform }] 
         : [{transform:style?.transform},StyleSheet.absoluteFillObject]} />
        
    } 
}


const PainterSoftware = requireNativeComponent("PainterS",null);
const PainterHarwdare = requireNativeComponent("PainterH",null);
const PainterNone = requireNativeComponent("Painter",null);
export const PainterS = Platform.OS === 'android' ? PainterSoftware : PainterNone
export const Painter = PainterNone
export const PainterH = Platform.OS === 'android' ? PainterHarwdare : PainterNone
export const Color = ExtractColor
export const ColorWorklet = ExtractColorWorklet