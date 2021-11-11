import React from 'react'

const getPosition = (arr,index,length)=>{
    if(arr !== undefined){
        return `${arr[index] * 100}%`
    }
 
    if(length > 0){
        let l =  length - 1
        let sum = 1 / l
        return index == l ? "100%" : `${(index*sum) * 100}%`
    }

    return "0%"
}


const RadialGradientWeb = (props)=>{

    const {

        x,
        y,
        w,
        h,
        cx,
        cy,
        rx,
        ry,


        positions,
        colors,

        isChildMask,

        viewBox,
        align,
        aspect,

        mask,

        opacity,
        translateZ,

        transX,
        transY,
        transPercentageValue,

        rot,
        rotO,
        rotOx,
        rotOy,
        rotPercentageValue,

        sc,
        scX,
        scY,
        scO,
        scOx,
        scOy,
        scPercentageValue,
    
       style,
    } = props

    const lx = x === undefined ? 0 : x
    const ly = y === undefined ? 0 : y
    const lw = w === undefined ? 0 : w
    const lh = h === undefined ? 0 : h

    const centerX = cx === undefined ? 0.5 : cx
    const centerY = cy === undefined ? 0.5 : cy
    const radiusX = rx === undefined ? 0.5 : rx
    const radiusY = ry === undefined ? 0.5 : ry

    const colorsArr = colors === undefined ? ['white','black'] : colors

    const isMasking = isChildMask === undefined ? false : isChildMask

    const mk = mask === undefined ? "" : mask
    const op = opacity === undefined ? "" : `${opacity}`
   
    const dx = transX === undefined ? 0 : transX
    const dy = transY === undefined ? 0 : transY

    const scaleX = sc === undefined ? ( scX === undefined ? 1 : scX) : sc
    const scaleY = sc === undefined ?  (scY === undefined ? 1 : scY) : sc
    const scaleOX = scO === undefined ? ( scOx === undefined ? 0 : scOx) : scO
    const scaleOY = scO === undefined ?  (scOy === undefined ? 0 : scOy) : scO

    const rotation = rot === undefined ? 0 : rot
    const rotationOX = rotO === undefined ? ( rotOx === undefined ? 0 : rotOx) : rotO
    const rotationOY = rotO === undefined ?  (rotOy === undefined ? 0 : rotOy) : rotO


    const transform = `rotate(${rotation} ${rotationOX} ${rotationOY}) translate(${scaleOX} ${scaleOY}) scale(${scaleX} ${scaleY}) translate(${-scaleOX} ${-scaleOY}) translate(${dx} ${dy})`
  

    const keyGradient = "jjlfRadialGradient"

    return(
        <g opacity={op}    mask={ !isMasking ? `url(#${mk})` : "" } transform={transform}  >
            <svg style={{width:'100%',height:'100%',overflow:'visible'}} viewBox={viewBox} 
                    preserveAspectRatio={`${align} ${aspect}`}>

                 { colorsArr.length > 1  &&

                        <>
                        <defs>
                         
                            <radialGradient id={keyGradient}  
                            gradientUnits="objectBoundingBox"
                            gradientTransform={`translate(0.5 0.5) scale(1,${radiusY/radiusX}) translate(-0.5 -0.5)`}
                            cx={centerX}  cy={centerY} r={radiusX}  >
                            {colorsArr.map((color,index)=>{
                                return(
                                    <stop key={index} offset={getPosition(positions,index,colorsArr.length)} stopColor={color}  />
                                )
                            })}

                            </radialGradient>
                        </defs>


                        <rect x={lx} y={ly} width={lw} height={lh}  fill={`url(#${keyGradient})`}/>
                        </>

                 }
            </svg>
        </g>
    )

}

export default RadialGradientWeb