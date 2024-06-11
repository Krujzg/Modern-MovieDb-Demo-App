package com.example.core.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf

val DarkColorScheme = darkColorScheme(
    primary = Golden,
    onPrimary = DarkBlack,
    secondary = Golden,
    onSecondary = DarkBlack,
    background = DarkBlack,
    onBackground = Golden,
    surface = DarkBlack,
    onSurface = Golden
)

val LightColorScheme = lightColorScheme(
    primary = Black,
    onPrimary = White,
    secondary = Black,
    onSecondary = White,
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black
)

val LocalAppDimens = staticCompositionLocalOf {
    normalDimensions
}

object AppTheme {
    val dimens: Dimensions
        @Composable
        get() = LocalAppDimens.current

    @Composable
    fun colorScheme(darkTheme: Boolean = isSystemInDarkTheme()): ColorScheme {
        return when {
            darkTheme -> DarkColorScheme
            else -> LightColorScheme
        }
    }
}

@Composable
fun ProvideDimens(
    dimensions: Dimensions,
    content: @Composable () -> Unit
) {
    val dimensionSet = remember { dimensions }
    CompositionLocalProvider(LocalAppDimens provides dimensionSet, content = content)
}

@Composable
fun MovieTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    //Dimensions (calculate dimens here based on screen size)
    val dimensions = normalDimensions

    ProvideDimens(dimensions = dimensions) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}