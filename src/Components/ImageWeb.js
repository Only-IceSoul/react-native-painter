import React, { useRef } from 'react'
import Attributes from './Constants'


const clamp = (num) =>{
    return num < 0 ? 0 : (num > 1 ? 1 : num) 
}

const ImageWeb =  React.forwardRef((props,ref)=>{
    const myRef = ref ? ref : useRef()
  
    const {

        x,
        y,
        w,
        h,
        source,
        alignImage,
        aspectImage,

        painterKey,
        viewBox,
        align,
        aspect,

        isChildMask,

    
        fill,
       
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

    const lx = x === undefined ? 0 : x
    const ly = y === undefined ? 0 : y
    const lw = w === undefined ? 0 : w
    const lh = h === undefined ? 0 : h

    const src = source === undefined ? "" : source
    const asp = aspectImage === undefined ? "meet" : aspectImage
    const alg = alignImage === undefined ? "xMidYMid" : alignImage

    //MARK : Paintable

    const isMasking = isChildMask === undefined ? false : isChildMask

    const fc = fill === undefined ? 'black' : fill

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


    const filterShadowProp = `url(#${keyFilter})`
   
   
    return(
        <g ref={myRef} opacity={op}  mask={`url(#${mk})`}  transform={transform}  
     curtransform={`${rotation} ${rotationOX} ${rotationOY} ${scaleX} ${scaleY} ${scaleOX} ${scaleOY} ${dx} ${dy}`}
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

               <g filter={ filterShadowProp }   >
                           
                           <rect 
                            id={`${keyElemet}${Attributes.elements.fill}${userKey}`}
                            x={lx} y={ly} width={lw} height={lh}   fill={fc}/>
                    </g>
                <image 
                        id={`${keyElemet}${Attributes.elements.image}${userKey}`}
                x={lx} y={ly} width={lw} height={lh} preserveAspectRatio={`${alg} ${asp}`} href={src}  />
           
      
    </svg>
    </g>
    )
})


class Image extends React.PureComponent{

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
            case "x":
            case "y":
            case "w":
            case "h":
                this.updateAttribute(Attributes[k],value,this.getElement(Attributes.elements.fill))
                this.updateAttribute(Attributes[k],value,this.getElement(Attributes.elements.image))
                return true
            case 'source':
                this.updateAttribute(Attributes.href,value,this.getElement(Attributes.elements.image))
                return true 
            case "alignImage":
                   let imgEl = this.getElement(Attributes.elements.image)
                    let cur = this.getAttribute(Attributes.preserveAspectRatio,imgEl).split(' ')
                    let aspect = cur.length >= 2 ? cur[1] : "none"
                    this.updateAttribute(Attributes.preserveAspectRatio,`${value} ${aspect}`,imgEl)
                return true
            case "aspectImage":
                let img = this.getElement(Attributes.elements.image)
                let cura = this.getAttribute(Attributes.preserveAspectRatio,img).split(' ')
                let align = cura.length >= 2 ? cura[0] : "none"
                this.updateAttribute(Attributes.preserveAspectRatio,`${align} ${value}`,img)
                 return true
         
        }
 
         return false
        

    
    }

    handleCommonProps(k,value){
       
        if(k === 'fill'){
            this.updateAttribute(Attributes.fill,value,this.getElement(Attributes.elements.fill))
        }else if(k.includes('shadow')){
             this.handleShadow(k,value)
        }else if(k === 'opacity'){
            this.updateAttribute(Attributes.opacity,value,this.myRef.current)
        }else if(k === 'mask'){
            this.updateAttribute(Attributes.mask,`url(#${value})`,this.myRef.current)
        }
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
        return <ImageWeb ref={this.myRef} {...this.props} />
    }
}

export default Image
