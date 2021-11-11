import React from 'react'
import { Platform, requireNativeComponent,StyleSheet } from "react-native";

const EllipseView = requireNativeComponent("EllipseView",null);


export default class Ellipse extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return <EllipseView {...others}
         style={Platform.OS === 'android' 
         ? [{transform: style?.transform }] 
         : [{transform:style?.transform},StyleSheet.absoluteFillObject]} />
        
    } 
}
