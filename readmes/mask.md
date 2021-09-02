# Mask

A mask is defined with a ‘Mask’ element. A mask is used referenced using the ‘name’ property.

### The element must be at the top of the painter as first elements. ###

```JS

  import { Mask } from 'react-native-painter'
    <Painter style={styles.Painter} 
    >       
       //top
        <Mask name="myMask" >
            <Line />
        </Mask>   
        //bottom
        <Line mask="myMask"/>
        <Line />
        <Line />
        <Line />
        <Line />

    </Painter>
```

## Props

| Name | description | type | default |
| --- | --- | --- | --- |
| name | Not animatable | String | undefined |


