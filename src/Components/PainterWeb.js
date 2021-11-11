import React, { useEffect, useMemo, useState } from 'react'




const Painter = (props)=>{

    const {
        d,
        viewBox,
        aspect,
        align,
        children,
       style,
        ...rest 
    } = props


    const styleObject = useMemo(()=>{
        if (typeof style === 'number') return StyleSheet.flatten(style) 
        if(Array.isArray(style)){
           var styleJs = {}
           style.forEach((v)=>{
             if(typeof v === 'number'){
                let ss = StyleSheet.flatten(style) 
                Object.assign(styleJs,ss)
             }else{
               Object.assign(styleJs,v)
             }
           })
 
           return styleJs
        }
        return style
      },[style])

      const vb = viewBox === undefined ? undefined : `${viewBox[0]} ${viewBox[1]} ${viewBox[2]} ${viewBox[3]}`
      const asp = aspect === undefined ? "meet" : aspect
      const alg = align === undefined ? "xMidYMid" : align

    
  

    return(
        <div  {...rest} style={styleObject}>
              <svg style={{width:'100%',height:'100%',overflow:'visible'}} viewBox={vb} 
                             preserveAspectRatio={`${alg} ${asp}`}>
                                
                              {React.Children.map(children,(child,index)=>{
                                      var p = child.props
                                        return React.cloneElement(child, child.type.name === "ImageWeb" ? 
                                        {viewBox:vb,
                                              alignParent:alg,aspectParent:asp
                                        } :
                                        {viewBox: vb,align:alg,aspect:asp

                                        } )
                                    })}

                            

              </svg>
        </div>
    )
}

export default Painter