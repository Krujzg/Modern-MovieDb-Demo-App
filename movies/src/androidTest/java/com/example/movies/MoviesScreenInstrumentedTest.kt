package com.example.movies

import android.app.Application
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.runner.AndroidJUnitRunner
import com.example.movies.domain.movieentity.Movie
import com.example.movies.infrastructure.di.MovieModule
import com.example.movies.presentation.screens.MoviesScreen
import com.example.movies.presentation.states.MoviesUiState
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(MovieModule::class)
class MoviesScreenInstrumentedTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun moviesScreenShouldBeDisplayedWhenUiStateIsNotEmpty() {
        val uiState = MoviesUiState(
            listOf(
                Movie(
                    id = 0,
                    releaseDate = "2020.10.10.",
                    title = "Fake",
                    posterPath = ""
                )
            )
        )
        composeRule.setContent {
            MoviesScreen(uiState = uiState, onEvent = {})
        }

        composeRule.onNodeWithTag("MoviesScreen").assertExists()
    }

    @Test
    fun moviesScreenShouldNotBeDisplayedWhenUiStateIsEmpty() {
        val uiState = MoviesUiState()
        composeRule.setContent {
            MoviesScreen(uiState = uiState, onEvent = {})
        }

        composeRule.onNodeWithTag("MoviesScreen").assertDoesNotExist()
    }
}

class HiltTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}