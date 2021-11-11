# Text

The element is used to define text.

```JS
    <Painter style={styles.Painter} 
    >
        <Text />      
    </Painter>
```

## Props

| Name | description | type | default |
| --- | --- | --- | --- |
| text | content | String | "" |
| font | which font will be used to render the text [CustomFontMobile](./guidefont.md) , web svg fontfamily| String | default 
| fontStyle | the font style for the text. | String | normal |
| fontSize | the size of the font | Number | 12|
| x | define x-axis coordinate | Number | 0 |
| y | define y-axis coordinate | Number | 0 |
| baseline | define the baseline| String | none |
| textAnchor | define the text anchor ( web )| String | "" |
| verticalOffset | ( mobile )A percentage value 0: 0% - 1:100% | Number | 0 |
| horizontalOffset | ( mobile ) A percentage value 0: 0% - 1:100% | Number | 0 |
