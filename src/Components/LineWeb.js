import React from 'react'


const clamp = (num) =>{
    return num < 0 ? 0 : (num > 1 ? 1 : num) 
}


const LineWeb = (props)=>{

    const {

        //MARK: Props

        x1,
        y1,
        x2,
        y2,

        //MARK: Paintable
        isChildMask,
        painterKey,
        viewBox,
        align,
        aspect,

        stroke,
        strokeOpacity,
        strokeWidth,
        strokeStart,
        strokeEnd,
        dashArray,
        dashClipValue,
        strokeCap,
        strokeJoin,
        strokeMiter,
                
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

        rot,
        rotO,
        rotOx,
        rotOy,

        sc,
        scX,
        scY,
        scO,
        scOx,
        scOy,
    } = props


    //MARK: Props

    const px1 = x1 === undefined ? 0 : x1
    const py1 = y1 === undefined ? 0 : y1
    const px2 = x2 === undefined ? 0 : x2
    const py2 = y2 === undefined ? 0 : y2
 

    const isMasking = isChildMask === undefined ? false : isChildMask

    //MARK : Paintable

    const dashClip = dashClipValue === undefined ? 0 : dashClipValue
    const dasharr = dashArray === undefined ? 0 : dashArray
    
    const cap = strokeCap === undefined ? "" : strokeCap

    const ss = strokeStart === undefined ? 0 : clamp(strokeStart)  
    const se = strokeEnd === undefined ? 1 :  clamp(strokeEnd)

    const join = strokeJoin === undefined ? "" : strokeJoin
    const miterLimit = strokeMiter === undefined ? "" : `${strokeMiter}`
    const stc = stroke === undefined ? '' : stroke
    const so = strokeOpacity === undefined ? "" : `${strokeOpacity}`
    const sw = strokeWidth === undefined ? 1 : strokeWidth
   

    const shc = shadow === undefined ? 'rgba(0,0,0,1)'.split(",") : shadow.split(",")
    const sho = shadowOpacity === undefined ? 0 : clamp(shadowOpacity)
    const shr = shadowRadius === undefined ? 2 : shadowRadius
    const shox = shadowOffset === undefined ? (shadowOffsetX === undefined ? 2 : shadowOffsetX ) : shadowOffset
    const shoy =  shadowOffset === undefined ? (shadowOffsetY === undefined ? 2 : shadowOffsetY) : shadowOffset
    const shRect = shadowRect === undefined ? {x:-2, y:-2 , w:5,h:5, units:'objectBoundingBox'} : shadowRect

    const userKey = painterKey === undefined ? "" : painterKey

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
  

    const keyFilter = `jjlfshadowfilter${userKey}`
    const keyMaskStroke = `jjlfMaskfilterNotButt${userKey}`
    const keyMaskStroke2 = `jjlfMaskfilterNotButt2${userKey}`
    const filterShadowProp = sho > 0 ? `url(#${keyFilter})` : ""
    

    const maskStroke = ss !== 0 || se !== 1
    const maskStroke2 = maskStroke && se < 1
   
    return(
        <g  opacity={op}  mask={`url(#${mk})`}   transform={transform}>

    
        <svg style={{width:'100%',height:'100%',overflow:'visible'}} viewBox={viewBox} 
             preserveAspectRatio={`${align} ${aspect}`}>
        
        { sho > 0 &&
            <defs>
                <filter id={keyFilter}
                    filterUnits={`${shRect.units}`}
                x={`${shRect.x}`} y={`${shRect.y}`} width={`${shRect.w}`} height={`${shRect.h}`}>
                    <feDropShadow dx={`${shox}`} dy={`${shoy}`} stdDeviation={`${shr}`} floodColor={`${shc[0]},${shc[1]},${shc[2]},${sho * parseFloat(shc[3])}`} />
                    </filter>

            </defs>
        }
 


      { maskStroke &&  
            <mask id={keyMaskStroke} maskUnits="userSpaceOnUse" >

                <line x1={px1} x2={px2} y1={py1} y2={py2}
                   
                     fill={'none'}  
                    stroke={'white'} strokeWidth={`${sw}`}
                    strokeLinecap={cap} strokeLinejoin={join} strokeMiterlimit={miterLimit}
                    strokeDasharray={`${dasharr}`} strokeDashoffset={`${( (ss) * (-dasharr) )}`} />

            </mask>
      }

       { maskStroke2 && 
            <mask id={keyMaskStroke2} maskUnits="userSpaceOnUse" >



                <line x1={px1} x2={px2} y1={py1} y2={py2}
                
                fill={'none'}  
                stroke={'white'} strokeWidth={`${sw}`}
                strokeLinecap={cap} strokeLinejoin={join} strokeMiterlimit={miterLimit}
                strokeDasharray={`${dasharr}`} strokeDashoffset={`${( (1 - se) * (dasharr) )}`} />



                { ss > 0 && se < 1 && 
                    <>
                         <line x1={px1} x2={px2} y1={py1} y2={py2}
                         fill={'none'}  
                        stroke={'black'} strokeWidth={`${sw}`}
                        strokeLinecap={cap} strokeLinejoin={join} strokeMiterlimit={miterLimit}
                        strokeDasharray={`${dasharr}`} strokeDashoffset={`${( (  (1 - ss) + dashClip  ) * (dasharr) )}`} />

                        <line x1={px1} x2={px2} y1={py1} y2={py2}
                        fill={'none'}  
                        stroke={'black'} strokeWidth={`${sw}`}
                        strokeLinecap={cap} strokeLinejoin={join} strokeMiterlimit={miterLimit}
                        strokeDasharray={`${dasharr}`} strokeDashoffset={`${( (se + dashClip) * (-dasharr) )}`} />

                    </>
                } 

            </mask>
       }
       
    
           {ss < se ? 
                <g  filter={ filterShadowProp}   >
                <g   
                mask={ maskStroke2 ? `url(#${keyMaskStroke2})` :  "" }
                >
                     <line x1={px1} x2={px2} y1={py1} y2={py2}
                     
                        mask={ maskStroke ? `url(#${keyMaskStroke})` : ""}
                        fill={"none"}
                        stroke={stc} strokeOpacity={so}
                        strokeWidth={`${sw}`} 
                        strokeLinecap={cap} strokeLinejoin={join} strokeMiterlimit={miterLimit}
                        strokeDasharray={``} strokeDashoffset={`0`}
                    />
               </g>
               </g>
             
           : null
           }
      
    </svg>
    </g>
    )
}

export default LineWeb