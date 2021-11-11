import React from 'react'
import { Platform, requireNativeComponent ,StyleSheet} from "react-native";

import GViewNone from './GNone';
const GViewH = requireNativeComponent("GViewH",null);

export default class GMobileH extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return Platform.OS === 'android' ?
        <GViewH {...others} style={[{transform: style?.transform }] } />
        
         : <GViewNone {...others} style={[{transform:style?.transform},StyleSheet.absoluteFillObject]} />
        
    } 
}