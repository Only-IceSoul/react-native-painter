import React, { useEffect, useLayoutEffect, useRef } from 'react'


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

const TextWeb = React.forwardRef((props,ref)=>{

    const myRef = ref ? ref : useRef()
    const textRef = useRef()
    const textRef2 = useRef()
    const containerRef = useRef()
    const shadowRef = useRef()
    const shadowFilter = useRef()

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
        painterKey,
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
    const userKey = painterKey === undefined ? "" : painterKey

    const keyFilter = `jjlfshadowfilter${userKey}`

    const filterShadowProp = sho > 0 ? `url(#${keyFilter})` : ""

    const isFillTransparent = fc === `rgba(0, 0, 0, 0)` || fc === "transparent"


    useLayoutEffect(()=>{
        myRef.current = {
            text1:textRef,
            text2:textRef2,
            container:containerRef,
            shadow:shadowRef,
            shadowFilter:shadowFilter
        }
    },[stc])

   
    return(
        <g  ref={containerRef} opacity={op} mask={`url(#${mk})`}  transform={transform} 
         currenttransform={`${rotation} ${rotationOX} ${rotationOY} ${scaleX} ${scaleY} ${scaleOX} ${scaleOY} ${dx} ${dy}`}>

    
        <svg style={{width:'100%',height:'100%',overflow:'visible'}} viewBox={viewBox} 
             preserveAspectRatio={`${align} ${aspect}`}>
        
        {  sho > 0 &&
            <defs>
                <filter id={keyFilter} ref={shadowFilter}
                    filterUnits={`${shRect.units}`}
                    x={`${shRect.x}`} y={`${shRect.y}`} width={`${shRect.w}`} height={`${shRect.h}`}>
                    <feDropShadow ref={shadowRef} dx={shox} dy={shoy} stdDeviation={shr} 
                     floodColor={`${shc[0]},${shc[1]},${shc[2]},${sho * parseFloat(shc[3])})`} 
                     currentopacity={sho}
                     />
                </filter>

            </defs>
        }

       
            <text ref={textRef}
             x={lx} y={ly} fontFamily={typeface} fontSize={fontSiz} fontStyle={fontStyl} fontWeight={fontWeg} textAnchor={textAn}
            dominantBaseline={basel}
           
           filter={!isFillTransparent   ? filterShadowProp : ""} 
           fill={fc} fillOpacity={fo}  
           stroke={"none"} 
           >
                        {content}
            </text>
           {stc !== "" ? 
                <g   filter={isFillTransparent ? filterShadowProp : ""}   >

                    <text 
                    x={lx} y={ly} fontFamily={typeface} fontSize={fontSiz} fontStyle={fontStyl} fontWeight={fontWeg} textAnchor={textAn}
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
})

export default class Text extends React.PureComponent{

    
    constructor(props){
        super(props)

        this.myRef = React.createRef()
        this.setNativeProps = this.setNativeProps.bind(this)
        this.updateProp = this.updateProp.bind(this)
        this.updateTextAtributte = this.updateTextAtributte.bind(this)
        this.updateAttribute = this.updateAttribute.bind(this)
     
      
    }


    setNativeProps(object){
        if(this.myRef){
    
            let keys = Object.keys(object)
            for(var i = 0; i < keys.length;i++){
                let k = keys[i]
                let value = object[k]
                this.updateProp(k,value)
               
            }

            let isStyle = object.hasOwnProperty('style')
            if(isStyle){
                let keysStyle = Object.keys(object.style)
                for(var i = 0; i < keysStyle.length;i++){
                    let k = keysStyle[i]
                    let value = object.style[k]
                    this.updateProp(k,value)
                   
                }
            }
          
        }
    }

    updateProp(k,value){
        var container = this.myRef.current.container.current
        switch(k){
            case "text":
                if(this.myRef.current.text1.current) this.myRef.current.text1.current.textContent = value
                if(this.myRef.current.text2.current) this.myRef.current.text2.current.textContent = value
                break;
            case "fontSize":
                this.updateTextAtributte('font-size',value)
                break
            case "fontStyle":
                if(value === 'bold') {
                    this.updateTextAtributte('font-weight',value) 
                    this.updateTextAtributte('font-style','normal')
                }else{
                    this.updateTextAtributte('font-weight','normal') 
                    this.updateTextAtributte('font-style',value)
                }
                break
            case "font":
                this.updateTextAtributte('font-family',value)
                break
            case "baseline":
                this.updateTextAtributte('dominant-baseline',getBaselineWeb(value))
                break;
            case "x":
            case "y":
                 this.updateTextAtributte(k,value)
                break
            case "textAnchor":
                this.updateTextAtributte('text-anchor',value)
                break
            //common Props
            case "opacity":
                this.updateAttribute(k,clamp(value),container) 
                break
            case "fill":
                this.updateTextAtributte('text-anchor',value,true,false)
                break;
            case "fillOpacity":
                this.updateTextAtributte('fill-opacity',value,true,false)
                break;
                //stroke
            case "stroke":
                this.updateTextAtributte('fill-opacity',value,false,true)
                break;
            case "strokeOpacity":
                this.updateTextAtributte('stroke-opacity',value,false,true)
                 break;
            case "strokeWidth":
                this.updateTextAtributte('stroke-width',value,false,true)
                break;
                //shadow
            case "shadow":
                if(this.myRef.current.shadow.current){ 
                    let o =  this.myRef.current.shadow.current.hasAttribute('currentopacity') ? this.myRef.current.shadow.current.getAttribute('currentopacity') : "1"
                    if(value.includes('rgba')){
                        let colors = value.split(',')
                        let cs = `${colors[0]},${colors[1]},${colors[2]},${parseFloat(o) * parseFloat(colors[3])})`
                        this.updateAttribute('flood-color',cs,this.myRef.current.shadow.current)
                    }
                   
                 }
                break;
            case "shadowOpacity":
                 if(this.myRef.current.shadow.current){
                    let c =  this.myRef.current.shadow.current.getAttribute('flood-color')
                    if(c.includes('rgba')){
                        let colors = c.split(',')
                        let cs = `${colors[0]},${colors[1]},${colors[2]},${value * parseFloat(colors[3])})`
                        this.updateAttribute('flood-color',cs,this.myRef.current.shadow.current)
                        this.updateAttribute('currentopacity',value,this.myRef.current.shadow.current)
                    }
                   
                 }
               
                break;
            case "shadowRadius":
                this.updateAttribute('stdDeviation',value,this.myRef.current.shadow.current) 
                break;
            case "shadowOffset":
                this.updateAttribute('dx',value,this.myRef.current.shadow.current) 
                this.updateAttribute('dy',value,this.myRef.current.shadow.current) 
                break;
            case "shadowOffsetX":
                this.updateAttribute('dx',value,this.myRef.current.shadow.current) 
                break;
            case "shadowOffsetY":
                this.updateAttribute('dy',value,this.myRef.current.shadow.current) 
                break;
            case "shadowRect":
                this.updateAttribute('filterUnits',value.units,this.myRef.current.shadowFilter.current)
                this.updateAttribute('x',value.x,this.myRef.current.shadowFilter.current)
                this.updateAttribute('y',value.y,this.myRef.current.shadowFilter.current)
                this.updateAttribute('width',value.w,this.myRef.current.shadowFilter.current)
                this.updateAttribute('height',value.h,this.myRef.current.shadowFilter.current) 
                break
            case "transX":
              
                 if(container){
                     let t = container.getAttribute('currenttransform').split(' ')
                     let valueT = `rotate(${t[0]} ${t[1]} ${t[2]}) translate(${t[5]} ${t[6]}) scale(${t[3]} ${t[4]}) translate(${-t[5]} ${-t[6]}) translate(${value} ${t[8]})`
                     let currT = `${t[0]} ${t[1]} ${t[2]} ${t[3]} ${t[4]} ${t[5]} ${t[6]} ${value} ${t[8]}`
                    this.updateAttribute('transform',valueT,container) 
                    this.updateAttribute('currenttransform',currT,container) 
                 }
                
                break;
            case "transY":
          
                if(container){
                    let t = container.getAttribute('currenttransform').split(' ')
                    let valueT = `rotate(${t[0]} ${t[1]} ${t[2]}) translate(${t[5]} ${t[6]}) scale(${t[3]} ${t[4]}) translate(${-t[5]} ${-t[6]}) translate(${t[7]} ${value})`
                    let currT = `${t[0]} ${t[1]} ${t[2]} ${t[3]} ${t[4]} ${t[5]} ${t[6]} ${t[7]} ${value}`
                   this.updateAttribute('transform',valueT,container) 
                   this.updateAttribute('currenttransform',currT,container) 
                }
                break;
            case "rot":
                if(container){
                    let t = container.getAttribute('currenttransform').split(' ')
                    let valueT = `rotate(${value} ${t[1]} ${t[2]}) translate(${t[5]} ${t[6]}) scale(${t[3]} ${t[4]}) translate(${-t[5]} ${-t[6]}) translate(${t[7]} ${t[8]})`
                    let currT = `${value} ${t[1]} ${t[2]} ${t[3]} ${t[4]} ${t[5]} ${t[6]} ${t[7]} ${t[8]}`
                   this.updateAttribute('transform',valueT,container) 
                   this.updateAttribute('currenttransform',currT,container) 
                }
                 break;
            case "rotO":
                if(container){
                    let t = container.getAttribute('currenttransform').split(' ')
                    let valueT = `rotate(${t[0]} ${value} ${value}) translate(${t[5]} ${t[6]}) scale(${t[3]} ${t[4]}) translate(${-t[5]} ${-t[6]}) translate(${t[7]} ${t[8]})`
                    let currT = `${t[0]} ${value} ${value} ${t[3]} ${t[4]} ${t[5]} ${t[6]} ${t[7]} ${t[8]}`
                   this.updateAttribute('transform',valueT,container) 
                   this.updateAttribute('currenttransform',currT,container) 
                }
                 break;
            case "rotOx":
                if(container){
                    let t = container.getAttribute('currenttransform').split(' ')
                    let valueT = `rotate(${t[0]} ${value} ${t[2]}) translate(${t[5]} ${t[6]}) scale(${t[3]} ${t[4]}) translate(${-t[5]} ${-t[6]}) translate(${t[7]} ${t[8]})`
                    let currT = `${t[0]} ${value} ${t[2]} ${t[3]} ${t[4]} ${t[5]} ${t[6]} ${t[7]} ${t[8]}`
                   this.updateAttribute('transform',valueT,container) 
                   this.updateAttribute('currenttransform',currT,container) 
                }
                break;
            case "rotOy":
                if(container){
                    let t = container.getAttribute('currenttransform').split(' ')
                    let valueT = `rotate(${t[0]} ${t[1]} ${value}) translate(${t[5]} ${t[6]}) scale(${t[3]} ${t[4]}) translate(${-t[5]} ${-t[6]}) translate(${t[7]} ${t[8]})`
                    let currT = `${t[0]} ${t[1]} ${value} ${t[3]} ${t[4]} ${t[5]} ${t[6]} ${t[7]} ${t[8]}`
                   this.updateAttribute('transform',valueT,container) 
                   this.updateAttribute('currenttransform',currT,container) 
                }
                 break;
            case "sc":
                if(container){
                    let t = container.getAttribute('currenttransform').split(' ')
                    let valueT = `rotate(${t[0]} ${t[1]} ${t[2]}) translate(${t[5]} ${t[6]}) scale(${value} ${value}) translate(${-t[5]} ${-t[6]}) translate(${t[7]} ${t[8]})`
                    let currT = `${t[0]} ${t[1]} ${t[2]} ${value} ${value} ${t[5]} ${t[6]} ${t[7]} ${t[8]}`
                   this.updateAttribute('transform',valueT,container) 
                   this.updateAttribute('currenttransform',currT,container) 
                }
                 break;
            case "scX":
                if(container){
                    let t = container.getAttribute('currenttransform').split(' ')
                    let valueT = `rotate(${t[0]} ${t[1]} ${t[2]}) translate(${t[5]} ${t[6]}) scale(${value} ${t[4]}) translate(${-t[5]} ${-t[6]}) translate(${t[7]} ${t[8]})`
                    let currT = `${t[0]} ${t[1]} ${t[2]} ${value} ${t[4]} ${t[5]} ${t[6]} ${t[7]} ${t[8]}`
                   this.updateAttribute('transform',valueT,container) 
                   this.updateAttribute('currenttransform',currT,container) 
                }
                 break;
            case "scY":
                if(container){
                    let t = container.getAttribute('currenttransform').split(' ')
                    let valueT = `rotate(${t[0]} ${t[1]} ${t[2]}) translate(${t[5]} ${t[6]}) scale(${t[3]} ${value}) translate(${-t[5]} ${-t[6]}) translate(${t[7]} ${t[8]})`
                    let currT = `${t[0]} ${t[1]} ${t[2]} ${t[3]} ${value} ${t[5]} ${t[6]} ${t[7]} ${t[8]}`
                   this.updateAttribute('transform',valueT,container) 
                   this.updateAttribute('currenttransform',currT,container) 
                }
                break;
            case "scO":
                if(container){
                    let t = container.getAttribute('currenttransform').split(' ')
                    let valueT = `rotate(${t[0]} ${t[1]} ${t[2]}) translate(${value} ${value}) scale(${t[3]} ${t[4]}) translate(${-value} ${-value}) translate(${t[7]} ${t[8]})`
                    let currT = `${t[0]} ${t[1]} ${t[2]} ${t[3]} ${t[4]} ${value} ${value} ${t[7]} ${t[8]}`
                   this.updateAttribute('transform',valueT,container) 
                   this.updateAttribute('currenttransform',currT,container) 
                }
                break;
            case "scOx":
                if(container){
                    let t = container.getAttribute('currenttransform').split(' ')
                    let valueT = `rotate(${t[0]} ${t[1]} ${t[2]}) translate(${value} ${t[6]}) scale(${t[3]} ${t[4]}) translate(${-value} ${-t[6]}) translate(${t[7]} ${t[8]})`
                    let currT = `${t[0]} ${t[1]} ${t[2]} ${t[3]} ${t[4]} ${value} ${t[6]} ${t[7]} ${t[8]}`
                   this.updateAttribute('transform',valueT,container) 
                   this.updateAttribute('currenttransform',currT,container) 
                }
                break;
            case "scOy":
                if(container){
                    let t = container.getAttribute('currenttransform').split(' ')
                    let valueT = `rotate(${t[0]} ${t[1]} ${t[2]}) translate(${t[5]} ${value}) scale(${t[3]} ${t[4]}) translate(${-t[5]} ${-value}) translate(${t[7]} ${t[8]})`
                    let currT = `${t[0]} ${t[1]} ${t[2]} ${t[3]} ${t[4]} ${t[5]} ${value} ${t[7]} ${t[8]}`
                   this.updateAttribute('transform',valueT,container) 
                   this.updateAttribute('currenttransform',currT,container) 
                }
                break;
         }
    }
    updateTextAtributte(k,value,ignoreStroke,ignoreFill){
        if(this.myRef.current.text1.current && !ignoreFill ) this.myRef.current.text1.current.setAttribute(k,value)
        if(this.myRef.current.text2.current && !ignoreStroke) this.myRef.current.text2.current.setAttribute(k,value)
    }
    updateAttribute(k,value,element){
        if(element) element.setAttribute(k,value)
    }

    render(){
        return <TextWeb ref={this.myRef} {...this.props} />
    }
}