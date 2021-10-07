
React Native:

[link](https://mehrankhandev.medium.com/ultimate-guide-to-use-custom-fonts-in-react-native-77fcdf859cf4)


With Expo-font:

[installation](https://docs.expo.dev/versions/latest/sdk/font/)

update expo-font/ios/EXFont/EXFontLoader.m

```
 UM_EXPORT_METHOD_AS(loadAsync,
                    loadAsyncWithFontFamilyName:(NSString *)fontFamilyName
                    withLocalUri:(NSString *)path
                    resolver:(UMPromiseResolveBlock)resolve
                    rejecter:(UMPromiseRejectBlock)reject)
{
    
    ...


   + CFErrorRef error;
   + if (! CTFontManagerRegisterGraphicsFont(font, &error)) {
   +     reject(@"E_FONT_CREATION_FAILED",
   +            [NSString stringWithFormat:@"Could not create font from loaded data for '%@'", fontFamilyName],
               nil);
    +       return;
    +}

  [_manager setFont:[[EXFont alloc] initWithCGFont:font] forName:fontFamilyName];
  resolve(nil);
}
```