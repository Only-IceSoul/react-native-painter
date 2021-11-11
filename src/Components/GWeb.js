import React from 'react'



const clamp = (num) =>{
    return num < 0 ? 0 : (num > 1 ? 1 : num) 
}


const GWeb = (props)=>{

    const {

        viewBox,
        align,
        aspect,

        fillRule,
        fillOpacity,

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
       children,
        ...rest 
    } = props

    
 
    const fo = fillOpacity === undefined ? "" : `${fillOpacity}`
    const fr = fillRule === undefined ? "" : fillRule

    const op = opacity === undefined ? "" : `${opacity}`
    const mk = mask === undefined ? "" : mask

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
  

    return(
        <g {...rest}   
         fillRule={fr} fillOpacity={fo}
        opacity={op}
        mask={`url(#${mk})`}
        transform={transform}  >

             {React.Children.map(children,(child,index)=>{
                               var p = child.props
                                 return React.cloneElement(child, child.type.name === "ImageWeb" ? 
                                 {viewBox,
                                        alignParent:align,aspectParent:aspect
                                 } :
                                 {viewBox,align,aspect,
            
                                 } )
                             })}

        </g>
    )
}

export default GWeb