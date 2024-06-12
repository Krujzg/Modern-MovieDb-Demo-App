package com.example.movies.presentation.states

import com.example.core.presentation.states.AbstractUIState
import com.example.movies.domain.movieentity.Movie
import com.example.movies.domain.movieentity.MovieDetails

data class MovieDetailsUiState(
    val movie: MovieDetails? = null
) : AbstractUIState()

data class MoviesUiState(
    val movies: List<Movie> = emptyList()
) : AbstractUIState()