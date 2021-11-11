import React from 'react'


const clamp = (num) =>{
    return num < 0 ? 0 : (num > 1 ? 1 : num) 
}
const ImageWeb = (props)=>{

    const {

        x,
        y,
        w,
        h,
        source,
        align,
        aspect,
        fill,

        isChildMask,

        viewBox,
        alignParent,
        aspectParent,

        shadow,
        shadowOpacity,
        shadowRadius,
        shadowOffset,
        shadowOffsetX,
        shadowOffsetY,
        shadowPercentageValue,
        shadowRect,


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

    } = props

    const lx = x === undefined ? 0 : x
    const ly = y === undefined ? 0 : y
    const lw = w === undefined ? 0 : w
    const lh = h === undefined ? 0 : h

    const src = source === undefined ? "" : source
    const fc = fill === undefined ? '' : fill
    const asp = aspect === undefined ? "meet" : aspect
    const alg = align === undefined ? "xMidYMid" : align


    const shc = shadow === undefined ? 'rgba(0,0,0,1)'.split(",") : shadow.split(",")
    const sho = shadowOpacity === undefined ? 0 : clamp(shadowOpacity)
    const shr = shadowRadius === undefined ? 2 : shadowRadius
    const shox = shadowOffset === undefined ? (shadowOffsetX === undefined ? 2 : shadowOffsetX ) : shadowOffset
    const shoy =  shadowOffset === undefined ? (shadowOffsetY === undefined ? 2 : shadowOffsetY) : shadowOffset
    const shRect = shadowRect === undefined ? {x:-2, y:-2 , w:5,h:5, units:'objectBoundingBox'} : shadowRect

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
  

    const keyShadow = "jjlfFilterShadow"
    const filterShadowProp = sho > 0 ? `url(#${keyShadow})` : ""

    return(
        <g opacity={op}    mask={ !isMasking ? `url(#${mk})` : "" } transform={transform}  >
            <svg style={{width:'100%',height:'100%',overflow:'visible'}} viewBox={viewBox} 
                    preserveAspectRatio={`${alignParent} ${aspectParent}`}>
                        { sho > 0 &&
                            <defs>
                                <filter id={keyShadow}
                                    filterUnits={`${shRect.units}`}
                                x={`${shRect.x}`} y={`${shRect.y}`} width={`${shRect.w}`} height={`${shRect.h}`}>
                                    <feDropShadow dx={`${shox}`} dy={`${shoy}`} stdDeviation={`${shr}`} floodColor={`${shc[0]},${shc[1]},${shc[2]},${sho * parseFloat(shc[3])}`} />
                                    </filter>

                            </defs>
                        }

                   
                    <g filter={ filterShadowProp }   >
                           
                           <rect x={lx} y={ly} width={lw} height={lh}   fill={fc}/>
                    </g>
                    <image x={lx} y={ly} width={lw} height={lh} preserveAspectRatio={`${alg} ${asp}`} href={src}  />

            </svg>
        </g>
    )

}

export default ImageWeb