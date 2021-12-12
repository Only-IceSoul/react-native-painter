import React, { useRef } from 'react'
import Attributes from './Constants'


const clamp = (num) =>{
    return num < 0 ? 0 : (num > 1 ? 1 : num) 
}

const LineWeb =  React.forwardRef((props,ref)=>{
    const myRef = ref ? ref : useRef()
  
    const {

        
        x1,
        y1,
        x2,
        y2,

        painterKey,
        viewBox,
        align,
        aspect,

        isChildMask,

    
        fill,
        fillRule,
        fillOpacity,

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

    const px1 = x1 === undefined ? 0 : x1
    const py1 = y1 === undefined ? 0 : y1
    const px2 = x2 === undefined ? 0 : x2
    const py2 = y2 === undefined ? 0 : y2

    //MARK : Paintable

    const isMasking = isChildMask === undefined ? false : isChildMask

    const dashClip = dashClipValue === undefined ? 0 : dashClipValue
    const dasharr = dashArray === undefined ? 0 : dashArray

    const fc = fill === undefined ? '' : fill
    const fo = fillOpacity === undefined ? "" : `${fillOpacity}`
    const fr = fillRule === undefined ? "" : fillRule
    
    const cap = strokeCap === undefined ? "" : strokeCap

    const ss = strokeStart === undefined ? 0 : clamp(strokeStart)  
    const se = strokeEnd === undefined ? 1 :  clamp(strokeEnd)

    const join = strokeJoin === undefined ? "" : strokeJoin
    const miterLimit = strokeMiter === undefined ? "" : `${strokeMiter}`
    const stc = stroke === undefined ? '' : stroke
    const so = strokeOpacity === undefined ? "" : `${strokeOpacity}`
    const sw = strokeWidth === undefined ? 1 : strokeWidth
   

    const shc = shadow === undefined ? 'rgba(0,0,0,0)'.split(",") : shadow.split(",")
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

    const keyElemet = Attributes.elements.prefix
    const keyFilter = `${keyElemet}${Attributes.elements.filter}${userKey}`
    const keyMaskStroke = `${keyElemet}${Attributes.elements.mask}${userKey}`
    const keyMaskStroke2 = `${keyElemet}${Attributes.elements.mask2}${userKey}`

    const filterShadowProp = `url(#${keyFilter})`

    const maskStroke = ss !== 0 || se !== 1
    const maskStroke2 = maskStroke && se < 1

    const renderStroke = ss < se ? stc : ""


   
    return(
        <g ref={myRef} opacity={op}  mask={`url(#${mk})`}  transform={transform}  
        curstrokestart={ss}  curstrokeend={se} curdasharray={dasharr}  curdashclipvalue={dashClip}
        curstroke={stc} curtransform={`${rotation} ${rotationOX} ${rotationOY} ${scaleX} ${scaleY} ${scaleOX} ${scaleOY} ${dx} ${dy}`}
        >

    
        <svg style={{width:'100%',height:'100%',overflow:'visible'}} viewBox={viewBox} 
             preserveAspectRatio={`${align} ${aspect}`}>

     
            <defs>
                <filter id={keyFilter}
                    filterUnits={shRect.units}
                    x={shRect.x} y={shRect.y} width={shRect.w} height={shRect.h}>
                    <feDropShadow 
                    id={`${keyElemet}${Attributes.elements.shadow}${userKey}`}
                    dx={shox} dy={shoy} stdDeviation={shr} 
                    floodColor={shc}
                  
                     />
                </filter>

            </defs>

       
      


      
            <mask id={keyMaskStroke} maskUnits="userSpaceOnUse" >

            <line x1={px1} x2={px2} y1={py1} y2={py2}
            
                  id={`${keyElemet}${Attributes.elements.strokeMask1}${userKey}`}
                   fill={'none'}  
                    stroke={'white'} strokeWidth={sw}
                    strokeLinecap={cap} strokeLinejoin={join} strokeMiterlimit={miterLimit}
                    strokeDasharray={dasharr} strokeDashoffset={`${ss * (-dasharr)}`} />

            </mask>
    

   
            <mask id={keyMaskStroke2} maskUnits="userSpaceOnUse" >


            <line x1={px1} x2={px2} y1={py1} y2={py2}
                 id={`${keyElemet}${Attributes.elements.strokeMask2}${userKey}`}
                  fill={'none'}  
                stroke={'white'} strokeWidth={sw}
                strokeLinecap={cap} strokeLinejoin={join} strokeMiterlimit={miterLimit}
                strokeDasharray={dasharr} strokeDashoffset={`${(1 - se) * dasharr}`} />



               
                <>
                <line x1={px1} x2={px2} y1={py1} y2={py2}
                        id={`${keyElemet}${Attributes.elements.strokeMask3}${userKey}`}
                     fill={'none'}  
                    stroke={ss > 0 && se < 1 ? 'black' : "white"} strokeWidth={sw}
                    strokeLinecap={cap} strokeLinejoin={join} strokeMiterlimit={miterLimit}
                    strokeDasharray={dasharr} strokeDashoffset={`${ (  (1 - ss) + dashClip  ) * dasharr}`} />

                <line x1={px1} x2={px2} y1={py1} y2={py2}
                        id={`${keyElemet}${Attributes.elements.strokeMask4}${userKey}`}
                    fill={'none'}  
                    stroke={ss > 0 && se < 1 ? 'black' : "white"} strokeWidth={sw}
                    strokeLinecap={cap} strokeLinejoin={join} strokeMiterlimit={miterLimit}
                    strokeDasharray={dasharr} strokeDashoffset={`${ (se + dashClip) * (-dasharr) }`} />

                </>
            

            </mask>
      
          
                <g   id={`${keyElemet}${Attributes.elements.gShadow}${userKey}`}
                     filter={filterShadowProp}   >
                <g  id={`${keyElemet}${Attributes.elements.gMask}${userKey}`}
                    mask={ maskStroke2 ? `url(#${keyMaskStroke2})` :  "" }
                >
                       <line x1={px1} x2={px2} y1={py1} y2={py2}
                          id={`${keyElemet}${Attributes.elements.stroke}${userKey}`}
                        mask={ maskStroke ? `url(#${keyMaskStroke})` : ""}
                        fill={"none"}
                        stroke={renderStroke} strokeOpacity={so}
                        strokeWidth={sw} 
                        strokeLinecap={cap} strokeLinejoin={join} strokeMiterlimit={miterLimit}
                        strokeDasharray={``} strokeDashoffset={`0`}
                    />
               </g>
               </g>
           
      
    </svg>
    </g>
    )
})


class Line extends React.PureComponent{

    constructor(props){
        super(props)
        this.currentProps = Object.assign({},props)
        this.keyElemet = Attributes.elements.prefix
        this.myRef = React.createRef()
        this.setNativeProps = this.setNativeProps.bind(this)
        this.updateProp = this.updateProp.bind(this)
        this.handleProps = this.handleProps.bind(this)
        this.handleCommonProps = this.handleCommonProps.bind(this)
        this.handleShadow = this.handleShadow.bind(this)
        this.updateAttribute = this.updateAttribute.bind(this)
        this.getAttribute = this.getAttribute.bind(this)
        this.getElement = this.getElement.bind(this)

        this.setStrokeStart = this.setStrokeStart.bind(this)
        this.setStrokeEnd = this.setStrokeEnd.bind(this)
        this.setDashArray = this.setDashArray.bind(this)
        this.setDashClipValue = this.setDashClipValue.bind(this)
      
    }


 
    //direct manipulation
    setNativeProps(object){

        if(this.myRef.current && this.props.painterKey){
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
        
       let isProp = this.handleProps(k,value)

       if(!isProp){
           this.handleCommonProps(k,value)
       }
        
    }

    handleProps(k,value){
        switch(k){
            case "x1":
            case "x2":
            case "y1":
            case "y2":
              
                let elstroke = this.getElement(Attributes.elements.stroke)
                let el1 = this.getElement(Attributes.elements.strokeMask1)
                let el2 = this.getElement(Attributes.elements.strokeMask2)
                let el3 = this.getElement(Attributes.elements.strokeMask3)
                let el4 = this.getElement(Attributes.elements.strokeMask4)
               
     
                this.updateAttribute(Attributes[k],value,elstroke)
                this.updateAttribute(Attributes[k],value,el1)
                this.updateAttribute(Attributes[k],value,el2)
                this.updateAttribute(Attributes[k],value,el3)
                this.updateAttribute(Attributes[k],value,el4)
             
            return true
        }
 
         return false
        

    
    }

    handleCommonProps(k,value){
        if(k.includes('fill')){
           
            
        }else if(k.includes('stroke')){

           if(k === 'stroke'){
                let ss = parseFloat(this.getAttribute(Attributes.curStrokeStart,this.myRef.current))
                let se = parseFloat(this.getAttribute(Attributes.curStrokeEnd,this.myRef.current))
                this.updateAttribute(Attributes.stroke,ss < se ? value : "",this.getElement(Attributes.elements.stroke))
                this.updateAttribute(Attributes.curStroke,value,this.myRef.current)
           }else if(k === 'strokeOpacity'){
                this.updateAttribute(Attributes.strokeOpacity,value,this.getElement(Attributes.elements.stroke))
           }else if(k === 'strokeStart'){
                this.setStrokeStart(value)
           }else if(k === 'strokeEnd'){
                this.setStrokeEnd(value)
           }else{
                let name = Attributes[k]
                this.updateAttribute(name,value,this.getElement(Attributes.elements.stroke))
                this.updateAttribute(name,value,this.getElement(Attributes.elements.strokeMask1))
                this.updateAttribute(name,value,this.getElement(Attributes.elements.strokeMask2))
                this.updateAttribute(name,value,this.getElement(Attributes.elements.strokeMask3))
                this.updateAttribute(name,value,this.getElement(Attributes.elements.strokeMask4))
           }

        }else if(k.includes('shadow')){
             this.handleShadow(k,value)
        }else if(k === 'opacity'){
            this.updateAttribute(Attributes[k],value,this.myRef.current)
        }else if(k === 'mask'){
            this.updateAttribute(Attributes[k],`url(#${value})`,this.myRef.current)
        }else if(k === 'dashArray'){
            this.setDashArray(value)
        }else if(k === 'dashClipValue'){
            this.setDashClipValue(value)
        }//transform
        else{
            var container = this.myRef.current
            let t = this.getAttribute(Attributes.curTransform,container).split(' ')
            var valueT = ""
            var currT = ""
            switch(k){
                case "transX":
                        valueT = `rotate(${t[0]} ${t[1]} ${t[2]}) translate(${t[5]} ${t[6]}) scale(${t[3]} ${t[4]}) translate(${-t[5]} ${-t[6]}) translate(${value} ${t[8]})`
                        currT = `${t[0]} ${t[1]} ${t[2]} ${t[3]} ${t[4]} ${t[5]} ${t[6]} ${value} ${t[8]}`
                   break;
                case "transY":
                        valueT = `rotate(${t[0]} ${t[1]} ${t[2]}) translate(${t[5]} ${t[6]}) scale(${t[3]} ${t[4]}) translate(${-t[5]} ${-t[6]}) translate(${t[7]} ${value})`
                        currT = `${t[0]} ${t[1]} ${t[2]} ${t[3]} ${t[4]} ${t[5]} ${t[6]} ${t[7]} ${value}`
                    break;
                case "rot":
                         valueT = `rotate(${value} ${t[1]} ${t[2]}) translate(${t[5]} ${t[6]}) scale(${t[3]} ${t[4]}) translate(${-t[5]} ${-t[6]}) translate(${t[7]} ${t[8]})`
                         currT = `${value} ${t[1]} ${t[2]} ${t[3]} ${t[4]} ${t[5]} ${t[6]} ${t[7]} ${t[8]}`
                     break;
                case "rotO":
                         valueT = `rotate(${t[0]} ${value} ${value}) translate(${t[5]} ${t[6]}) scale(${t[3]} ${t[4]}) translate(${-t[5]} ${-t[6]}) translate(${t[7]} ${t[8]})`
                         currT = `${t[0]} ${value} ${value} ${t[3]} ${t[4]} ${t[5]} ${t[6]} ${t[7]} ${t[8]}`
                     break;
                case "rotOx":
                         valueT = `rotate(${t[0]} ${value} ${t[2]}) translate(${t[5]} ${t[6]}) scale(${t[3]} ${t[4]}) translate(${-t[5]} ${-t[6]}) translate(${t[7]} ${t[8]})`
                         currT = `${t[0]} ${value} ${t[2]} ${t[3]} ${t[4]} ${t[5]} ${t[6]} ${t[7]} ${t[8]}`
                    break;
                case "rotOy":
                         valueT = `rotate(${t[0]} ${t[1]} ${value}) translate(${t[5]} ${t[6]}) scale(${t[3]} ${t[4]}) translate(${-t[5]} ${-t[6]}) translate(${t[7]} ${t[8]})`
                         currT = `${t[0]} ${t[1]} ${value} ${t[3]} ${t[4]} ${t[5]} ${t[6]} ${t[7]} ${t[8]}`
                     break;
                case "sc":
                         valueT = `rotate(${t[0]} ${t[1]} ${t[2]}) translate(${t[5]} ${t[6]}) scale(${value} ${value}) translate(${-t[5]} ${-t[6]}) translate(${t[7]} ${t[8]})`
                         currT = `${t[0]} ${t[1]} ${t[2]} ${value} ${value} ${t[5]} ${t[6]} ${t[7]} ${t[8]}`
                     break;
                case "scX":
                         valueT = `rotate(${t[0]} ${t[1]} ${t[2]}) translate(${t[5]} ${t[6]}) scale(${value} ${t[4]}) translate(${-t[5]} ${-t[6]}) translate(${t[7]} ${t[8]})`
                         currT = `${t[0]} ${t[1]} ${t[2]} ${value} ${t[4]} ${t[5]} ${t[6]} ${t[7]} ${t[8]}`
                     break;
                case "scY":
                         valueT = `rotate(${t[0]} ${t[1]} ${t[2]}) translate(${t[5]} ${t[6]}) scale(${t[3]} ${value}) translate(${-t[5]} ${-t[6]}) translate(${t[7]} ${t[8]})`
                         currT = `${t[0]} ${t[1]} ${t[2]} ${t[3]} ${value} ${t[5]} ${t[6]} ${t[7]} ${t[8]}`
                    break;
                case "scO":
                         valueT = `rotate(${t[0]} ${t[1]} ${t[2]}) translate(${value} ${value}) scale(${t[3]} ${t[4]}) translate(${-value} ${-value}) translate(${t[7]} ${t[8]})`
                         currT = `${t[0]} ${t[1]} ${t[2]} ${t[3]} ${t[4]} ${value} ${value} ${t[7]} ${t[8]}`
                    break;
                case "scOx":
                         valueT = `rotate(${t[0]} ${t[1]} ${t[2]}) translate(${value} ${t[6]}) scale(${t[3]} ${t[4]}) translate(${-value} ${-t[6]}) translate(${t[7]} ${t[8]})`
                         currT = `${t[0]} ${t[1]} ${t[2]} ${t[3]} ${t[4]} ${value} ${t[6]} ${t[7]} ${t[8]}`
                    break;
                case "scOy":
                         valueT = `rotate(${t[0]} ${t[1]} ${t[2]}) translate(${t[5]} ${value}) scale(${t[3]} ${t[4]}) translate(${-t[5]} ${-value}) translate(${t[7]} ${t[8]})`
                         currT = `${t[0]} ${t[1]} ${t[2]} ${t[3]} ${t[4]} ${t[5]} ${value} ${t[7]} ${t[8]}`
                    break;
            }

            if(currT.length > 0 && valueT.length > 0 ){
                this.updateAttribute(Attributes.transform,valueT,container) 
                 this.updateAttribute(Attributes.curTransform,currT,container) 
            }
        }
    }

    handleShadow(k,value){
        switch(k){
            case "shadow":
            case "shadowRadius":
            case "shadowOffsetX":
            case "shadowOffsetY":
                this.updateAttribute(Attributes[k],value,this.getElement(Attributes.elements.shadow)) 
                break;
            case "shadowOffset":
                this.updateAttribute(Attributes.shadowOffsetX,value,this.getElement(Attributes.elements.shadow)) 
                this.updateAttribute(Attributes.shadowOffsetY,value,this.getElement(Attributes.elements.shadow)) 
                break;
            case "shadowRect":
                let e = this.getElement(Attributes.elements.filter)
                this.updateAttribute(Attributes.shadowRect.units,value.units,e)
                this.updateAttribute(Attributes.shadowRect.x,value.x,e)
                this.updateAttribute(Attributes.shadowRect.y,value.y,e)
                this.updateAttribute(Attributes.shadowRect.w,value.w,e)
                this.updateAttribute(Attributes.shadowRect.h,value.h,e) 
                break
        }
    }

    setDashArray(value){
        let oldValue = parseFloat(this.getAttribute(Attributes.curDashArray,this.myRef.current))

        if(value !== oldValue){
            this.updateAttribute(Attributes.curDashArray,value,this.myRef.current)

            let ss = parseFloat(this.getAttribute(Attributes.curStrokeStart,this.myRef.current))
            let se = parseFloat(this.getAttribute(Attributes.curStrokeEnd,this.myRef.current))
            let dashClip = parseFloat(this.getAttribute(Attributes.curDashClipValue,this.myRef.current))

            let el1 = this.getElement(Attributes.elements.strokeMask1)
            let el2 = this.getElement(Attributes.elements.strokeMask2)
            let el3 = this.getElement(Attributes.elements.strokeMask3)
            let el4 = this.getElement(Attributes.elements.strokeMask4)

            let name = Attributes.strokeDasharray
            this.updateAttribute(name,value,el1)
            this.updateAttribute(name,value,el2)
            this.updateAttribute(name,value,el3)
            this.updateAttribute(name,value,el4)

            let name2 = Attributes.strokeDashoffset
            this.updateAttribute(name2,ss * (-value),el1)
            this.updateAttribute(name2,(1 - se) * value,el2)
            this.updateAttribute(name2, (  (1 - ss) + dashClip  ) * value,el3)
            this.updateAttribute(name2,(se + dashClip) * (-value),el4)
          
        }
    }

    setDashClipValue(value){
        let oldValue = parseFloat(this.getAttribute(Attributes.curDashClipValue,this.myRef.current))

        if(value !== oldValue){
            this.updateAttribute(Attributes.curDashClipValue,value,this.myRef.current)

            let ss = parseFloat(this.getAttribute(Attributes.curStrokeStart,this.myRef.current))
            let se = parseFloat(this.getAttribute(Attributes.curStrokeEnd,this.myRef.current))
            let dasharr = parseFloat(this.getAttribute(Attributes.curDashArray,this.myRef.current))

            let el3 = this.getElement(Attributes.elements.strokeMask3)
            let el4 = this.getElement(Attributes.elements.strokeMask4)

            let name = Attributes.strokeDashoffset
            this.updateAttribute(name, (  (1 - ss) + value  ) * dasharr,el3)
            this.updateAttribute(name,(se + value) * (-dasharr),el4)

          
        }
    }

    setStrokeEnd(value){
        let se = clamp(value)
        let oldValue = parseFloat(this.getAttribute(Attributes.curStrokeEnd,this.myRef.current))

        if(se !== oldValue){
            //current props
            this.updateAttribute(Attributes.curStrokeEnd,se,this.myRef.current)

            let stc = this.getAttribute(Attributes.curStroke,this.myRef.current)
            let ss = parseFloat(this.getAttribute(Attributes.curStrokeStart,this.myRef.current))
            let dasharr = parseFloat(this.getAttribute(Attributes.curDashArray,this.myRef.current))
            let dashClip =  parseFloat(this.getAttribute(Attributes.curDashClipValue,this.myRef.current))

            let maskStroke = ss !== 0 || se !== 1
            let maskStroke2 = maskStroke && se < 1
            let keyMaskStroke = `${this.keyElemet}${Attributes.elements.mask}${this.props.painterKey}`
            let keyMaskStroke2 = `${this.keyElemet}${Attributes.elements.mask2}${this.props.painterKey}`
        

            //stroke
            let el = this.getElement(Attributes.elements.stroke)
            this.updateAttribute(Attributes.stroke,ss < se ? stc : "",el)
            this.updateAttribute(Attributes.mask,maskStroke ? `url(#${keyMaskStroke})` : "",el)
 
            //g mask
            let elg2 = this.getElement(Attributes.elements.gMask)
            this.updateAttribute(Attributes.mask,maskStroke2 ? `url(#${keyMaskStroke2})` :  "" ,elg2)

            //mask

            let elm2 = this.getElement(Attributes.elements.strokeMask2)
            this.updateAttribute(Attributes.strokeDashoffset,`${( (1 - se) * (dasharr) )}`,elm2)

            let withDashClip = ss > 0 && se < 1
            
            let elm3 = this.getElement(Attributes.elements.strokeMask3)
            this.updateAttribute(Attributes.stroke, withDashClip ? 'black' : "white",elm3)
        

            let elm4 = this.getElement(Attributes.elements.strokeMask4)
            this.updateAttribute(Attributes.stroke,withDashClip ?  'black' : "white",elm4)
            this.updateAttribute(Attributes.strokeDashoffset,`${( (se + dashClip) * (-dasharr) )}`,elm4)
        }
    }

    setStrokeStart(value){
        let ss = clamp(value)
        let oldValue = parseFloat(this.getAttribute(Attributes.curStrokeStart,this.myRef.current))
        
        if(ss !== oldValue){
            this.updateAttribute(Attributes.curStrokeStart,ss,this.myRef.current)
            //current props
            let stc = this.getAttribute(Attributes.curStroke,this.myRef.current)
            let se =  parseFloat(this.getAttribute(Attributes.curStrokeEnd,this.myRef.current))
            let dasharr = parseFloat(this.getAttribute(Attributes.curDashArray,this.myRef.current))
            let dashClip =  parseFloat(this.getAttribute(Attributes.curDashClipValue,this.myRef.current))
          
            let maskStroke = ss !== 0 || se !== 1
            let maskStroke2 = maskStroke && se < 1
            let keyMaskStroke = `${this.keyElemet}${Attributes.elements.mask}${this.props.painterKey}`
            let keyMaskStroke2 = `${this.keyElemet}${Attributes.elements.mask2}${this.props.painterKey}`
        

            //stroke
            let el = this.getElement(Attributes.elements.stroke)
            this.updateAttribute(Attributes.stroke,ss < se ? stc : "",el)
            this.updateAttribute(Attributes.mask,maskStroke ? `url(#${keyMaskStroke})` : "",el)

            //g mask
            let elg2 =  this.getElement(Attributes.elements.gMask)
            this.updateAttribute(Attributes.mask,maskStroke2 ? `url(#${keyMaskStroke2})` :  "" ,elg2)
            //mask

            let elm1 = this.getElement(Attributes.elements.strokeMask1)
            this.updateAttribute(Attributes.strokeDashoffset,`${( (ss) * (-dasharr) )}`,elm1)

            let withDashClip = ss > 0 && se < 1

            let elm3 = this.getElement(Attributes.elements.strokeMask3)
            this.updateAttribute(Attributes.stroke, withDashClip ? "black" : "white",elm3)
            this.updateAttribute(Attributes.strokeDashoffset,`${( (  (1 - ss) + dashClip  ) * (dasharr) )}`,elm3)

            let elm4 = this.getElement(Attributes.elements.strokeMask4)
            this.updateAttribute(Attributes.stroke,withDashClip ? "black" : "white",elm4)
        }

    }

    
    getElement(id){
        return this.myRef.current.querySelector(`#${this.keyElemet}${id}${this.props.painterKey}`)
     }
    updateAttribute(k,value,element){
        if(element) element.setAttribute(k,value)
    }
    getAttribute(k,element){
        if(element)  {
            let v = element.getAttribute(k)
            return  v ? v : ""
        }
        return ""
    }
    render(){
        return <LineWeb ref={this.myRef} {...this.props} />
    }
}

export default Line
