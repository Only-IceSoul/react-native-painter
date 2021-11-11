import React from 'react'
import { Platform, requireNativeComponent,StyleSheet } from "react-native";

const RectView = requireNativeComponent("RectView",null);

export default class Rect extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return <RectView {...others}
         style={Platform.OS === 'android' 
         ? [{transform: style?.transform }] 
         : [{transform:style?.transform},StyleSheet.absoluteFillObject]} />
        
    } 
}