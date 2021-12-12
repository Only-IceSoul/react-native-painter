import React, { useRef } from 'react'
import Attributes from './Constants'

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


const clamp = (num) =>{
    return num < 0 ? 0 : (num > 1 ? 1 : num) 
}

const LinearGradientWeb =  React.forwardRef((props,ref)=>{
    const myRef = ref ? ref : useRef()
  
    const {

        x,
        y,
        w,
        h,
        startPoint,
        endPoint,
        positions,
        colors,

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

    const lx = x === undefined ? 0 : x
    const ly = y === undefined ? 0 : y
    const lw = w === undefined ? 0 : w
    const lh = h === undefined ? 0 : h

    const s = startPoint === undefined ? { x:0.5, y:0 } :  startPoint
    const e = endPoint === undefined ?  { x:0.5, y:1 } :  endPoint

    const colorsArr = colors === undefined ? ['white','black'] : colors

    //MARK : Paintable

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
  
    const userKey = painterKey === undefined ? "" : painterKey

    const keyElemet = Attributes.elements.prefix
    const keyGradient = `${keyElemet}${Attributes.elements.gradient}${userKey}`

   
    return(
        <g ref={myRef} opacity={op}  mask={`url(#${mk})`}  transform={transform}  
      
         curtransform={`${rotation} ${rotationOX} ${rotationOY} ${scaleX} ${scaleY} ${scaleOX} ${scaleOY} ${dx} ${dy}`}
         curcolors={`${colorsArr.join(';-;')}`}  curpositions={`${positions === undefined ? "" : positions.join(' ')}`}
        >

    
        <svg style={{width:'100%',height:'100%',overflow:'visible'}} viewBox={viewBox} 
             preserveAspectRatio={`${align} ${aspect}`}>

     
                      
                        
                <defs>
                    <linearGradient id={keyGradient}
                    x1={`${s.x * 100}%`} y1={`${s.y * 100}%`} x2={`${e.x * 100}%`} y2={`${e.y * 100}%`}
                    >
                        {colorsArr.map((v,index)=>{
                            return   <stop key={index} offset={getPosition(positions,index,colorsArr.length)} stopColor={v} />
                        })}
                    
                    </linearGradient>

                </defs> 


                <rect   
                    id={`${keyElemet}${Attributes.elements.fill}${userKey}`}
                x={lx} y={ly} width={lw} height={lh}  fill={`url(#${keyGradient})`}/>
                        

                        
    
           
      
        </svg>
    </g>
    )
})


class LinearGradient extends React.PureComponent{

    constructor(props){
        super(props)
        this.currentProps = Object.assign({},props)
        this.keyElemet = Attributes.elements.prefix
        this.myRef = React.createRef()
        this.setNativeProps = this.setNativeProps.bind(this)
        this.updateProp = this.updateProp.bind(this)
        this.handleProps = this.handleProps.bind(this)
        this.handleCommonProps = this.handleCommonProps.bind(this)
        this.updateAttribute = this.updateAttribute.bind(this)
        this.getAttribute = this.getAttribute.bind(this)
        this.getElement = this.getElement.bind(this)

       
    }


 
    //direct manipulation, experimental
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
                let elfill = this.getElement(Attributes.elements.fill)
                this.updateAttribute(Attributes[k],value,elfill)
                return true
            case "startPoint":
            case "endPoint":
                let grad = this.getElement(Attributes.elements.gradient)
                this.updateAttribute(Attributes[k]["x"],`${value.x * 100}%`,grad)
                this.updateAttribute(Attributes[k]["y"],`${value.y * 100}%`,grad)
                return true
            case "colors":
                let gra = this.getElement(Attributes.elements.gradient)
                gra.innerHTML = ""
                if(value.length > 0){
                     let curPo = this.getAttribute(Attributes.curPositions,this.myRef.current)
                     let pos = curPo.length >= 1 ? curPo.split(' ') : undefined
                     let html = ""
                    for(var i = 0; i < value.length ; i++){
                        let c = value[i]
                        html += `<stop offset="${getPosition(pos,i,value.length)}" stop-color="${c}" /> \n`
                    }
                    gra.innerHTML = html
                    this.updateAttribute(Attributes.curColors,value.join(';-;'),this.myRef.current)
                }
                return true
            case "positions":
                let elG = this.getElement(Attributes.elements.gradient)
                let curColors = this.getAttribute(Attributes.curColors,this.myRef.current)
                let cols = curColors.length >= 1 ? curColors.split(';-;') : []
          
                elG.innerHTML = ""
                if(cols.length > 0){
                    let html = ""
                   for(var i = 0; i < cols.length ; i++){
                       let c = cols[i]
                       html += `<stop offset="${getPosition(value,i,cols.length)}" stop-color="${c}" /> \n`
                   }
                   elG.innerHTML = html
                   this.updateAttribute(Attributes.curPositions,value.join(' '),this.myRef.current)
               }
                return true
        }
 
         return false
        
    }

    handleCommonProps(k,value){
        if(k.includes('fill')){
        }else if(k.includes('stroke')){
        }else if(k.includes('shadow')){
        }else if(k === 'opacity'){
            this.updateAttribute(Attributes[k],value,this.myRef.current)
        }else if(k === 'mask'){
            this.updateAttribute(Attributes[k],`url(#${value})`,this.myRef.current)
        }else if(k === 'dashArray'){
        }else if(k === 'dashClipValue'){
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
        return <LinearGradientWeb ref={this.myRef} {...this.props} />
    }
}

export default LinearGradient
