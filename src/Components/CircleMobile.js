import React from 'react'
import { Platform, requireNativeComponent,StyleSheet } from "react-native";

const CircleView = requireNativeComponent("CircleView",null);

export default class Circle extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return <CircleView {...others}
         style={Platform.OS === 'android' 
         ? [{transform: style?.transform }] 
         : [{transform:style?.transform},StyleSheet.absoluteFillObject]} />
        
    } 
}