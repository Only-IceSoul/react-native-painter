import React from 'react'


const MaskWeb = (props)=>{

    const {

        viewBox,
        align,
        aspect,
        name,
       children
    } = props


    const idMask = name === undefined ? "" : name
    
    return(
       
        <mask id={idMask}  maskUnits="userSpaceOnUse" >

                    {React.Children.map(children,(child,index)=>{
                      
                            return React.cloneElement(child, 
                            {
                                viewBox,
                                align,
                                aspect,
                                isChildMask: true
                            } )
                        })}

        </mask>
      
    )
}

export default MaskWeb