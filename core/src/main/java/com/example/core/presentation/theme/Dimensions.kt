package com.example.core.presentation.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

interface Dimensions {
    val paddingTooSmall: Dp
    val paddingExtraSmall: Dp
    val paddingSmall: Dp
    val paddingNormal: Dp
    val paddingLarge: Dp
    val paddingExtraLarge: Dp
    val borderWidthSmall: Dp
    val borderWidthNormal: Dp
    val iconSizeNormal: Dp
}

val normalDimensions: Dimensions = object : Dimensions {
    override val paddingTooSmall: Dp
        get() = 2.dp
    override val paddingExtraSmall: Dp
        get() = 4.dp
    override val paddingSmall: Dp
        get() = 8.dp
    override val paddingNormal: Dp
        get() = 16.dp
    override val paddingLarge: Dp
        get() = 24.dp
    override val paddingExtraLarge: Dp
        get() = 32.dp
    override val borderWidthSmall: Dp
        get() = 1.dp
    override val borderWidthNormal: Dp
        get() = 2.dp
    override val iconSizeNormal: Dp
        get() = 22.dp
}