import React from 'react'
import { Platform, requireNativeComponent,StyleSheet } from "react-native";

const RadialGradientView = requireNativeComponent("JJPainterRadialGradientView",null);


export default class RadialGradient extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return <RadialGradientView {...others}
         style={Platform.OS === 'android' 
         ? [{transform: style?.transform }] 
         : [{transform:style?.transform},StyleSheet.absoluteFillObject]} />
        
    } 
}