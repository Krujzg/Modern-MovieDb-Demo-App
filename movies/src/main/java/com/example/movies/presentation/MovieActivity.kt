package com.example.movies.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.core.presentation.theme.MovieTheme
import com.example.movies.presentation.event.UiEvent
import com.example.movies.presentation.screens.MovieDetailsPage
import com.example.movies.presentation.screens.MoviesScreen
import com.example.movies.presentation.viewmodels.MovieDetailsViewModel
import com.example.movies.presentation.viewmodels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : ComponentActivity() {

    private val moviesViewModel: MoviesViewModel by viewModels()
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = HomeScreen
                    ) {
                        composable<HomeScreen> {
                            val uiState = moviesViewModel.uiState.collectAsStateWithLifecycle()
                            MoviesScreen(
                                uiState = uiState.value,
                                onEvent = {
                                    movieDetailsViewModel.onEvent(it)
                                    if (it is UiEvent.OnMovieClicked) {
                                        navController.navigate(MovieDetailsScreen)
                                    }
                                },
                            )
                        }
                        composable<MovieDetailsScreen> {
                            val uiState = movieDetailsViewModel.uiState.collectAsStateWithLifecycle()
                            MovieDetailsPage(uiState = uiState.value)
                        }
                    }
                }
            }
        }
    }
}