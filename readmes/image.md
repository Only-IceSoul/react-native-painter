# Image

```JS
    import { Image } from 'react-native-painter'
    import { Image as ImageReact } from 'react-native'  
    
    <Painter style={styles.Painter} 
    >
        <Image source={Platform.os === "web" ? require('./myImage.jpg') : `static;${ImageReact.resolveAssetSource(require('./myImage.jpg')).uri}`} />   

    </Painter>
```

## Props

| Name | description | type | default |
| --- | --- | --- | --- |
| x | left position | Number | 0 |
| y | top position | Number | 0 |
| w | the width | Number | 0 |
| h | the height | Number | 0 |
| align | Alignment value | String | xMidYMid |
| aspect |   Meet or slice  | String | meet |
| source | uri to load the image  | String | undefined |



---

### `source: string`
WEB:  
 
  href: value

MOBILE:   
uri to load the image from. e.g. 
`https://facebook........./logo_og.png`.    

static ("static;${uri}")    

base64String ("base64,${value}")    

network --> ("https://.....")  

---