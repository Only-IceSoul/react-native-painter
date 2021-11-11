import React from 'react'


const clamp = (num) =>{
    return num < 0 ? 0 : (num > 1 ? 1 : num) 
}

//text-after-edge -> descender
//text-before-edge-> ascender
//hanging -> capheight
//central -> center
//middle -> middle
//none -> ""

const getBaselineWeb = (b)=>{
    var result = ""
    switch(b){
        case "descender":
            result = "text-after-edge"
            break
        case "ascender":
            result = "text-before-edge"
            break
        case "capHeight":
            result = "hanging"
            break
        case "center":
            result = "central"
            break
        case "middle":
            result = "middle"
            break
        default:
            result = ""
            break
    }

    return result
}

const CircleWeb = (props)=>{

    const {

        x,
        y,
        text,
        font,
        fontSize,
        fontStyle,
        baseline,
        textAnchor,
        verticalOffset,
        horizontalOffset,
        
        isChildMask,

        viewBox,
        align,
        aspect,

        fill,
        fillOpacity,

        stroke,
        strokeOpacity,
        strokeWidth,
                
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
    
       style,
        ...rest 
    } = props


    //MARK: Props

    const lx = x === undefined ? 0 : x
    const ly = y === undefined ? 0 : y
    const content = text === undefined ? "" : text
    const typeface = font === undefined ? "" : font
    const fontStyl = fontStyle === 'italic' ? "italic" : "normal"
    const fontWeg = fontStyle === "bold" ? "bold" : "normal"
    const fontSiz = fontSize === undefined ? 12 : fontSize
    const basel = baseline === undefined ? "" : getBaselineWeb(baseline)
    const textAn = textAnchor === undefined ? "" : textAnchor
 
    const isMasking = isChildMask === undefined ? false : isChildMask

    //MARK : Paintable


    const fc = fill === undefined ? '' : fill
    const fo = fillOpacity === undefined ? "" : `${fillOpacity}`
    
    const stc = stroke === undefined ? '' : stroke
    const so = strokeOpacity === undefined ? "" : `${strokeOpacity}`
    const sw = strokeWidth === undefined ? 1 : strokeWidth
   

    const shc = shadow === undefined ? 'rgba(0,0,0,1)'.split(",") : shadow.split(",")
    const sho = shadowOpacity === undefined ? 0 : clamp(shadowOpacity)
    const shr = shadowRadius === undefined ? 2 : shadowRadius
    const shox = shadowOffset === undefined ? (shadowOffsetX === undefined ? 2 : shadowOffsetX ) : shadowOffset
    const shoy =  shadowOffset === undefined ? (shadowOffsetY === undefined ? 2 : shadowOffsetY) : shadowOffset
    const shRect = shadowRect === undefined ? {x:-2, y:-2 , w:5,h:5, units:'objectBoundingBox'} : shadowRect

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
  

    const keyFilter = `jjlfshadowfilter`

    const filterShadowProp = sho > 0 ? `url(#${keyFilter})` : ""

    const isFillTransparent = fc === `rgba(0, 0, 0, 0)` || fc === "transparent"


   
    return(
        <g  opacity={op} mask={`url(#${mk})`}  transform={transform}>

    
        <svg style={{width:'100%',height:'100%',overflow:'visible'}} viewBox={viewBox} 
             preserveAspectRatio={`${align} ${aspect}`}>
        
        {  sho > 0 &&
            <defs>
                <filter id={keyFilter}
                    filterUnits={`${shRect.units}`}
                x={`${shRect.x}`} y={`${shRect.y}`} width={`${shRect.w}`} height={`${shRect.h}`}>
                    <feDropShadow dx={`${shox}`} dy={`${shoy}`} stdDeviation={`${shr}`} floodColor={`${shc[0]},${shc[1]},${shc[2]},${sho * parseFloat(shc[3])}`} />
                    </filter>

            </defs>
        }

       
            <text x={lx} y={ly} fontFamily={typeface} fontSize={fontSiz} fontStyle={fontStyl} fontWeight={fontWeg} textAnchor={textAn}
            dominantBaseline={basel}
           
           filter={!isFillTransparent   ? filterShadowProp : ""} 
           fill={fc} fillOpacity={fo}  
           stroke={"none"} 
           >
                        {content}
            </text>
           {stc !== "" ? 
                <g   filter={isFillTransparent ? filterShadowProp : ""}   >

                    <text x={lx} y={ly} fontFamily={typeface} fontSize={fontSiz} fontStyle={fontStyl} fontWeight={fontWeg} textAnchor={textAn}
                          dominantBaseline={basel}
                        fill={"none"}
                        stroke={stc} strokeOpacity={so}
                        strokeWidth={`${sw}`} 
                    >

                            {content}
                        </text>
              
                </g>
           : null
           }
      
    </svg>
    </g>
    )
}

export default CircleWeb