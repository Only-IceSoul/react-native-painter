import React from 'react'
import { Platform, requireNativeComponent,StyleSheet } from "react-native";


const MaskGView = requireNativeComponent("MaskGView",null);


export default class MaskG extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return <MaskGView {...others}
         style={Platform.OS === 'android' 
         ? [{transform: style?.transform }] 
         : [{transform:style?.transform},StyleSheet.absoluteFillObject]} />
        
    } 
}