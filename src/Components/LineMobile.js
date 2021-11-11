import React from 'react'
import { Platform, requireNativeComponent,StyleSheet } from "react-native";

const LineView = requireNativeComponent("LineView",null);

export default class Line extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return <LineView {...others}
         style={Platform.OS === 'android' 
         ? [{transform: style?.transform }] 
         : [{transform:style?.transform},StyleSheet.absoluteFillObject]} />
        
    } 
}