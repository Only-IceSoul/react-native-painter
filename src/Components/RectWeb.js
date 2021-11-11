import React from 'react'
import PathWeb from './PathWeb'


const rectData = (x,y,w,h)=>{
    return 'M ' + x + " " + y
     + " L " + (x+w) + " " + y
     + " L " + (x+w) + " " + (y+h)
     + " L " + x + " " + (y+h)
     + " L " + x + " " + y
     + " Z";
}

const roundedRectData = function (x,y,w, h, tlr, trr, brr, blr) {
    return "M " + x + " " + (y+(h - blr)) 
    + " L " + x + " " + (y+blr)
    + ' A ' + tlr + ' ' + tlr  + ' 0 0 1 ' + (tlr + x) + ' ' + y
    + ' L ' + ((w - trr) + x) + ' ' + y
    + ' A ' + trr + ' ' + trr + ' 0 0 1 ' + (w + x) + ' ' + (trr + y)
    + ' L ' + (w + x)  + ' ' + ((h - brr) + y)
    + ' A ' + brr + ' ' + brr + ' 0 0 1 ' + ((w - brr) + x) + ' ' + (h + y)
    + ' L ' + (blr + x) + ' ' + (h + y)
    + ' A ' + blr + ' ' + blr + ' 0 0 1 '+ x + ' ' + ((h - blr) + y)
    + ' Z';

    // return  'M '+ x + ' '  + (tlr + y)
    //   + ' A ' + tlr + ' ' + tlr  + ' 0 0 1 ' + (tlr + x) + ' ' + y
    //   + ' L ' + ((w - trr) + x) + ' ' + y
    //   + ' A ' + trr + ' ' + trr + ' 0 0 1 ' + (w + x) + ' ' + (trr + y)
    //   + ' L ' + (w + x)  + ' ' + ((h - brr) + y)
    //   + ' A ' + brr + ' ' + brr + ' 0 0 1 ' + ((w - brr) + x) + ' ' + (h + y)
    //   + ' L ' + (blr + x) + ' ' + (h + y)
    //   + ' A ' + blr + ' ' + blr + ' 0 0 1 '+ x + ' ' + ((h - blr) + y)
    //   + ' Z';
  };

const getRoundedRect = (x,y,w,h,rtl,rtr,rbl,rbr)=>{

    return rtl <= 0 && rtr <= 0 && rbl <= 0 && rbr <= 0 ? rectData(x,y,w,h) : roundedRectData(x,y,w,h,rtl,rtr,rbr,rbl)
}

const RectWeb = (props)=>{

    const {

        x,
        y,
        w,
        h,
        rtl,
        rtr,
        rbr,
        rbl,
        ...rest 
    } = props

    const lx = x === undefined ? 0 : x
    const ly = y === undefined ? 0 : y
    const lw = w === undefined ? 0 : w
    const lh = h === undefined ? 0 : h

    const radiustl = rtl === undefined ? 0 : rtl
    const radiustr = rtr === undefined ? 0 : rtr
    const radiusbr = rbr === undefined ? 0 : rbr
    const radiusbl = rbl === undefined ? 0 : rbl

   const path = lw > 0 && lh > 0 ?  getRoundedRect(lx,ly,lw,lh,radiustl,radiustr,radiusbl,radiusbr) : ""


    return(
            <PathWeb  {...rest} d={path}/>
    )
}

export default RectWeb