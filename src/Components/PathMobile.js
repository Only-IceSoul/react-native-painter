import React from 'react'
import { Platform, requireNativeComponent,StyleSheet } from "react-native";


const PathView = requireNativeComponent("PathView",null);

export default class Path extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return <PathView {...others}
         style={Platform.OS === 'android' 
         ? [{transform: style?.transform }] 
         : [{transform:style?.transform},StyleSheet.absoluteFillObject]} />
        
    } 
}