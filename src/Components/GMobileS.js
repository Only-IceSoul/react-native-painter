import React from 'react'
import { Platform, requireNativeComponent ,StyleSheet} from "react-native";


import GViewNone from './GNone';
const GViewS = requireNativeComponent("GViewS",null);

export default class GMobileS extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return Platform.OS === 'android' ?
        <GViewS {...others} style={[{transform: style?.transform }] } />
        
         : <GViewNone {...others} style={[{transform:style?.transform},StyleSheet.absoluteFillObject]} />
        
    } 
}