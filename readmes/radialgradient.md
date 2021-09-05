# RadialGradient

```JS
  import { RadialGradient , Color} from 'react-native-painter'
    <Painter style={styles.Painter} 
    >
        <RadialGradient />      
    </Painter>
```

## Props

| Name | description | type | default |
| --- | --- | --- | --- |
| x | left position | Number | 0 |
| y | top position | Number | 0 |
| w | the width | Number | 0 |
| h | the height | Number | 0 |
| cx | the x coordinates of the center  | Number | 0.5 |
| cy | the x coordinates of the center | Number | 0.5 |
| rx | rx attribute defines the horizontal radius | Number | 0.5 |
| ry | ry attribute defines the vertical radius | Number | 0.5 |
| colors | The colors to be distributed.There must be at least 2 colors in the array.  | Color[] | [white,black] |
| positions | The relative position of each corresponding color in the colors array. (percentage values)   | Number[] | null |