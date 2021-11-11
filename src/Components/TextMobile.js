import React from 'react'
import { Platform, requireNativeComponent,StyleSheet } from "react-native";

const TextView = requireNativeComponent("TextView",null);

export default class Text extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return <TextView {...others}
         style={Platform.OS === 'android' 
         ? [{transform: style?.transform }] 
         : [{transform:style?.transform},StyleSheet.absoluteFillObject]} />
        
    } 
}
