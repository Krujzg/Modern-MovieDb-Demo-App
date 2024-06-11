package com.example.movies

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidedValue
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.core.presentation.theme.MovieTheme
import org.junit.Rule

abstract class BaseComposeTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    protected fun withCompositionLocals(
        vararg extraProviders: ProvidedValue<*>,
        content: @Composable () -> Unit
    ) {
        rule.setContent {
            MovieTheme {
                CompositionLocalProvider(*extraProviders, content = content)
            }
        }
    }
}