# Painter

Android Layer Type: S(Software < api 28 >= None), H(Hardware)

```JS

  import { Painter,PainterS , PainterH } from 'react-native-painter'
  
    <Painter style={styles.Painter} 
        viewBox={[0,0,24,24] } align='xMidYMid' aspect='meet'
    >
            //elements
    </Painter>
```

## Props

| Name | description | type | default |
| --- | --- | --- | --- |
| viewBox | defines the position and dimension, in user space, of an Painter viewport.     | Array[Number] (4)| undefined |
| align | the aligment     | String | xMidYMid |
| aspect | the aspect ratio    | String | meet |