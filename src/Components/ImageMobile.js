import React from 'react'
import { Platform, requireNativeComponent,StyleSheet } from "react-native";

const ImageView = requireNativeComponent("JJPainterImageView",null);

export default class Image extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return <ImageView {...others} clipToBounds={true}
         style={Platform.OS === 'android' 
         ? [{transform: style?.transform }] 
         : [{transform:style?.transform},StyleSheet.absoluteFillObject]} />
        
    } 
}
