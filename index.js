// main index.js
import React from 'react'
import { StyleSheet, View } from 'react-native';
import { requireNativeComponent } from 'react-native';
import ExtractColor from './src/Color'

const LineView = requireNativeComponent("LineView",null);
const GView = requireNativeComponent("GView",null);
const GViewHardware = requireNativeComponent("GViewHardware",null);
const PathView = requireNativeComponent("PathView",null);

export class Line extends React.PureComponent {
    render(){
        return <LineView {...this.props} style={StyleSheet.absoluteFill} />
        
    } 
}
export class GS extends React.PureComponent {
    render(){
        return <GView {...this.props} style={StyleSheet.absoluteFill} />
        
    } 
}
export class G extends React.PureComponent {
    render(){
        return <GViewHardware {...this.props} style={StyleSheet.absoluteFill} />
        
    } 
}
export class Path extends React.PureComponent {
    render(){
        return <PathView {...this.props} style={StyleSheet.absoluteFill} />
        
    } 
}

export const PainterS = requireNativeComponent("Painter",null);
export const Painter = requireNativeComponent("PainterHardware",null);
export const Color = ExtractColor