# LinearGradient

```JS
  import { LinearGradient , Color} from 'react-native-painter'
    <Painter style={styles.Painter} 
    >
        <LinearGradient />      
    </Painter>
```

## Props

| Name | description | type | default |
| --- | --- | --- | --- |
| x | left position | Number | 0 |
| y | top position | Number | 0 |
| w | the width | Number | 0 |
| h | the height | Number | 0 |
| startPoint | the start position of the gradient (percentage values) | Number | 0.5 - 0|
| endPoint |the end position of the gradient (percentage values) | Number | 0.5 - 1|
| colors | The colors to be distributed.There must be at least 2 colors in the array.  | Color[] | [white,black] |
| positions | The relative position of each corresponding color in the colors array. (percentage values)   | Number[] | null |

