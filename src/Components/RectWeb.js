import React from 'react'
import Attributes from 'react-native-painter/src/Components/Constants';

const clamp = (num) =>{
    return num < 0 ? 0 : (num > 1 ? 1 : num) 
}


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
    + " L " + x + " " + (y+tlr)
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


const RectWeb =  React.forwardRef((props,ref)=>{
    const myRef = ref ? ref : useRef()
  
    const {
        x,
        y,
        w,
        h,
        rtl,
        rtr,
        rbr,
        rbl,


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

    
    const lx = x === undefined ? 0 : x
    const ly = y === undefined ? 0 : y
    const lw = w === undefined ? 0 : w
    const lh = h === undefined ? 0 : h

    const radiustl = rtl === undefined ? 0 : rtl
    const radiustr = rtr === undefined ? 0 : rtr
    const radiusbr = rbr === undefined ? 0 : rbr
    const radiusbl = rbl === undefined ? 0 : rbl
    const path = lw > 0 && lh > 0 ?  getRoundedRect(lx,ly,lw,lh,radiustl,radiustr,radiusbl,radiusbr) : ""

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
   
    
    const isFillTransparent = fc === `rgba(0, 0, 0, 0)` || fc === "transparent"

    const maskStroke = ss !== 0 || se !== 1
    const maskStroke2 = maskStroke && se < 1

    const renderStroke = ss < se ? stc : ""

   
    return(
        <g ref={myRef} opacity={op}  mask={`url(#${mk})`}  transform={transform}  
        curstrokestart={ss}  curstrokeend={se} curdasharray={dasharr}  curdashclipvalue={dashClip}
        curstroke={stc} curtransform={`${rotation} ${rotationOX} ${rotationOY} ${scaleX} ${scaleY} ${scaleOX} ${scaleOY} ${dx} ${dy}`}
        curx={lx} cury={ly} curh={lh} curw={lw} currtl={radiustl} currtr={radiustr} currbl={radiusbl} currbr={radiusbr}
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

                <path 
                  id={`${keyElemet}${Attributes.elements.strokeMask1}${userKey}`}
                  d={path} fill={'none'}  
                    stroke={'white'} strokeWidth={`${sw}`}
                    strokeLinecap={cap} strokeLinejoin={join} strokeMiterlimit={miterLimit}
                    strokeDasharray={dasharr} strokeDashoffset={`${ss * (-dasharr)}`} />

            </mask>
    

   
            <mask id={keyMaskStroke2} maskUnits="userSpaceOnUse" >


                <path
                 id={`${keyElemet}${Attributes.elements.strokeMask2}${userKey}`}
                 d={path} fill={'none'}  
                stroke={'white'} strokeWidth={`${sw}`}
                strokeLinecap={cap} strokeLinejoin={join} strokeMiterlimit={miterLimit}
                strokeDasharray={dasharr} strokeDashoffset={`${(1 - se) * dasharr}`} />



               
                <>
                    <path 
                        id={`${keyElemet}${Attributes.elements.strokeMask3}${userKey}`}
                    d={ path} fill={'none'}  
                    stroke={ss > 0 && se < 1 ? 'black' : "white"} strokeWidth={`${sw}`}
                    strokeLinecap={cap} strokeLinejoin={join} strokeMiterlimit={miterLimit}
                    strokeDasharray={dasharr} strokeDashoffset={`${ (  (1 - ss) + dashClip  ) * dasharr}`} />

                    <path 
                        id={`${keyElemet}${Attributes.elements.strokeMask4}${userKey}`}
                    d={path} fill={'none'}  
                    stroke={ss > 0 && se < 1 ? 'black' : "white"} strokeWidth={`${sw}`}
                    strokeLinecap={cap} strokeLinejoin={join} strokeMiterlimit={miterLimit}
                    strokeDasharray={dasharr} strokeDashoffset={`${ (se + dashClip) * (-dasharr) }`} />

                </>
            

            </mask>
      
       
    
       
           <path id={`${keyElemet}${Attributes.elements.fill}${userKey}`}
           d={path}  filter={!isFillTransparent ? filterShadowProp : ""} fill={fc} fillRule={fr} fillOpacity={fo}  
           stroke={"none"} />

          
                <g   id={`${keyElemet}${Attributes.elements.gShadow}${userKey}`}
                     filter={isFillTransparent  ? filterShadowProp : ""}   >
                <g  id={`${keyElemet}${Attributes.elements.gMask}${userKey}`}
                    mask={ maskStroke2 ? `url(#${keyMaskStroke2})` :  "" }
                >
                    <path  
                          id={`${keyElemet}${Attributes.elements.stroke}${userKey}`}
                        mask={ maskStroke ? `url(#${keyMaskStroke})` : ""}
                        d={path} 
                        fill={"none"}
                        stroke={renderStroke} strokeOpacity={so}
                        strokeWidth={`${sw}`} 
                        strokeLinecap={cap} strokeLinejoin={join} strokeMiterlimit={miterLimit}
                        strokeDasharray={``} strokeDashoffset={`0`}
                    />
               </g>
               </g>
           
      
    </svg>
    </g>
    )
})



class Rect extends React.PureComponent{

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
    
        var lw = 0
        var lh = 0
        var lx = 0
        var ly = 0
        var radiustl = 0
        var radiustr = 0
        var radiusbl = 0
        var radiusbr = 0

     
        switch(k){
            case "x":
            case "y":
            case "w":
            case "h":
            case "rtl":
            case "rtr":
            case "rbl":
            case "rbr":
                lw = parseFloat(this.getAttribute(Attributes.curW,this.myRef.current))
                lh = parseFloat(this.getAttribute(Attributes.curH,this.myRef.current))
                lx = parseFloat(this.getAttribute(Attributes.curX,this.myRef.current))
                ly = parseFloat(this.getAttribute(Attributes.curY,this.myRef.current))
                radiustl = parseFloat(this.getAttribute(Attributes.curRtl,this.myRef.current))
                radiustr = parseFloat(this.getAttribute(Attributes.curRtr,this.myRef.current))
                radiusbl = parseFloat(this.getAttribute(Attributes.curRbl,this.myRef.current))
                radiusbr = parseFloat(this.getAttribute(Attributes.curRbr,this.myRef.current))
            break;
            default:
                return false
        }
        switch(k){
            case "x":
                lx = value
                this.updateAttribute(Attributes.curX,value,this.myRef.current)
                break;
            case "y":
                ly = value
                this.updateAttribute(Attributes.curY,value,this.myRef.current)
                break;
            case "w":
                lw = value
                this.updateAttribute(Attributes.curW,value,this.myRef.current)
                break;
            case "h":
                lh = value
                this.updateAttribute(Attributes.curH,value,this.myRef.current)
                break;
            case "rtl":
                radiustl = value
                this.updateAttribute(Attributes.curRtl,value,this.myRef.current)
                break;
            case "rtr":
                radiustr = value
                this.updateAttribute(Attributes.curRtr,value,this.myRef.current)
                break;
            case "rbl":
                radiusbl = value
                this.updateAttribute(Attributes.curRbl,value,this.myRef.current)
                break;
            case "rbr":
                radiusbr = value
                this.updateAttribute(Attributes.curRbr,value,this.myRef.current)
            break;
        }
        

        let path = lw > 0 && lh > 0 ?  getRoundedRect(lx,ly,lw,lh,radiustl,radiustr,radiusbl,radiusbr) : ""
        let elfill = this.getElement(Attributes.elements.fill)
        let elstroke = this.getElement(Attributes.elements.stroke)
        let el1 = this.getElement(Attributes.elements.strokeMask1)
        let el2 = this.getElement(Attributes.elements.strokeMask2)
        let el3 = this.getElement(Attributes.elements.strokeMask3)
        let el4 = this.getElement(Attributes.elements.strokeMask4)
        let name = Attributes.d
        this.updateAttribute(name,path,elfill)
        this.updateAttribute(name,path,elstroke)
        this.updateAttribute(name,path,el1)
        this.updateAttribute(name,path,el2)
        this.updateAttribute(name,path,el3)
        this.updateAttribute(name,path,el4)
        
        return true
    }

    handleCommonProps(k,value){
        if(k.includes('fill')){
            if(k === 'fill'){
                let keyFilter = `${this.keyElemet}${Attributes.elements.filter}${this.props.painterKey}`
                let filterShadowProp = `url(#${keyFilter})`
                let isFillTransparent =  value === `rgba(0, 0, 0, 0)` || value === "transparent"
                let elFill = this.getElement(Attributes.elements.fill)
                this.updateAttribute(Attributes[k],value,elFill)
                this.updateAttribute(Attributes.filter,!isFillTransparent ? filterShadowProp : "",elFill)
                this.updateAttribute(Attributes.filter,isFillTransparent ? filterShadowProp : "",this.getElement(Attributes.elements.gShadow))
            }else{
                this.updateAttribute(Attributes[k],value,this.getElement(Attributes.elements.fill))
            }
            
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
        return <RectWeb ref={this.myRef} {...this.props} />
    }
}

export default Rect