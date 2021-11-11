import React from 'react'
import { Platform ,StyleSheet} from "react-native";

import GViewNone from './GNone';



export default class GMobile extends React.PureComponent {
    render(){
        const {style,...others} = this.props
        return <GViewNone {...others}
         style={Platform.OS === 'android' 
         ? [{transform: style?.transform }] 
         : [{transform:style?.transform},StyleSheet.absoluteFillObject]} />
        
    } 
}