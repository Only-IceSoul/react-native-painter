// main index.js
import React from 'react'
import { Platform, requireNativeComponent, StyleSheet } from 'react-native';
import ExtractColor from './src/Color'

import PainterComponent  from './src/Painter'
import PainterSComponent  from './src/PainterS'
import PainterHComponent  from './src/PainterH'

import GComponent  from './src/G'
import GSComponent  from './src/GS'
import GHComponent  from './src/GH'


import PathComponent  from './src/Path'
import RectComponent  from './src/Rect'
import CircleComponent  from './src/Circle'
import MaskComponent  from './src/Mask'
import MaskGComponent  from './src/MaskG'
import LineComponent from './src/Line'
import EllipseComponent from './src/Ellipse'
import LinearGradientComponent from './src/LinearGradient'
import RadialGradientComponent from './src/RadialGradient'
import ImageComponent from './src/Image'
import TextComponent from './src/Text'



export const G = GComponent
export const GS = GSComponent
export const GH =  GHComponent

export const Path = PathComponent
export const Rect = RectComponent
export const Circle = CircleComponent
export const Mask = MaskComponent
export const MaskG = MaskGComponent
export const Line = LineComponent
export const Ellipse = EllipseComponent
export const LinearGradient = LinearGradientComponent
export const RadialGradient = RadialGradientComponent
export const Image = ImageComponent
export const Text = TextComponent

export const PainterS = PainterSComponent
export const Painter = PainterComponent
export const PainterH =  PainterHComponent
export const Color = ExtractColor