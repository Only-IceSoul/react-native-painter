import React, { useRef } from 'react'
import Attributes from './Constants'

const MaskGWeb =  React.forwardRef((props,ref)=>{
    const myRef = ref ? ref : useRef()
  
    const {

        children,

        painterKey,
        viewBox,
        align,
        aspect,

        isChildMask,

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
        ...rest 
    } = props


    //MARK : Paintable

    const isMasking = isChildMask === undefined ? false : isChildMask

    const fo = fillOpacity === undefined ? "" : `${fillOpacity}`
    const fr = fillRule === undefined ? "" : fillRule
    
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
  
    return(
        <g ref={myRef}
        fillRule={fr} fillOpacity={fo}
         opacity={op}  mask={`url(#${mk})`}  transform={transform}  
             curtransform={`${rotation} ${rotationOX} ${rotationOY} ${scaleX} ${scaleY} ${scaleOX} ${scaleOY} ${dx} ${dy}`}
        >

    
                    {React.Children.map(children,(child,index)=>{
                                
                                return React.cloneElement(child,
                                {
                                    viewBox,
                                    align,
                                    aspect,
                                    isChildMask: true

                                } )
                            })}
    </g>
    )
})


class MaskG extends React.PureComponent{

    constructor(props){
        super(props)
        this.currentProps = Object.assign({},props)
        this.keyElemet = Attributes.elements.prefix
        this.myRef = React.createRef()
        this.setNativeProps = this.setNativeProps.bind(this)
        this.updateProp = this.updateProp.bind(this)
        this.handleCommonProps = this.handleCommonProps.bind(this)
        this.updateAttribute = this.updateAttribute.bind(this)
        this.getAttribute = this.getAttribute.bind(this)
    


      
    }


 
    //direct manipulation
    setNativeProps(object){ 
    
        if(this.myRef.current){
         
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
      this.handleCommonProps(k,value)
    }



    handleCommonProps(k,value){
        if(k.includes('fill')){
            if(k !== 'fill'){
                this.updateAttribute(Attributes[k],value,this.myRef.current)
            }
        }else if(k === 'opacity'){
            this.updateAttribute(Attributes[k],value,this.myRef.current)
        }else if(k === 'mask'){
            this.updateAttribute(Attributes[k],`url(#${value})`,this.myRef.current)
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
        return <MaskGWeb ref={this.myRef} {...this.props} />
    }
}

export default MaskG
