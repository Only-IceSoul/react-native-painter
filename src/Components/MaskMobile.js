import React from 'react'
import { Platform, requireNativeComponent,StyleSheet } from "react-native";

const MaskView = requireNativeComponent("MaskView",null);

export default class Mask extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return <MaskView {...others}
         style={Platform.OS === 'android' 
         ? [] 
         : [StyleSheet.absoluteFillObject]} />
    } 
}