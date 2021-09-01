# MaskG

The MaskG element is a container that is used to group other elements but only within a Mask.  
Attributes are inherited by its children.

```jsx
    <Painter style={styles.Painter} 
    >
        <Mask>
            <MaskG >
                <Line />
                <Circle />
                <Path />
            </MaskG>   
        </Mask>  
    </Painter>
```



