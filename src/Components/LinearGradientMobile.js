import React from 'react'
import { Platform, requireNativeComponent,StyleSheet } from "react-native";

const LinearGradientView = requireNativeComponent("JJPainterLinearGradientView",null);

export default class LinearGradient extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return <LinearGradientView {...others}
         style={Platform.OS === 'android' 
         ? [{transform: style?.transform }] 
         : [{transform:style?.transform},StyleSheet.absoluteFillObject]} />
        
    } 
}
