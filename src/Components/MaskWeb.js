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
                        var p = child.props
                            return React.cloneElement(child, child.type.name === "ImageWeb" ? 
                            {viewBox,
                                alignParent:align,aspectParent:aspect,
                                isChildMask: true
                            } :
                            {viewBox,align,aspect,
                                isChildMask: true
                            } )
                        })}

        </mask>
      
    )
}

export default MaskWeb