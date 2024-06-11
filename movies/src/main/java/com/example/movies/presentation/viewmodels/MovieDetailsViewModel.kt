package com.example.movies.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.core.application.common.AbstractQueryHandler
import com.example.core.domain.DataError
import com.example.core.domain.Result
import com.example.core.domain.RootError
import com.example.core.presentation.viewmodels.AbstractViewModel
import com.example.movies.application.movie.handlers.MovieQuery
import com.example.movies.domain.MovieDetails
import com.example.movies.presentation.event.UiEvent
import com.example.movies.presentation.states.MovieDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsQueryHandler: AbstractQueryHandler<MovieQuery, MovieDetails, Result<MovieDetails, RootError>>
) : AbstractViewModel<MovieDetailsUiState>(initialUIState = MovieDetailsUiState()) {

    fun onEvent(event: UiEvent) {
        when (event) {
            is UiEvent.OnMovieClicked -> loadMovie(event.id)
        }
    }

    private fun loadMovie(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val request = MovieQuery(id)

            movieDetailsQueryHandler
                .handle(request)
                .collect {
                when (it) {
                    is Result.Success -> {
                        val result = it.data
                        _uiState.value = MovieDetailsUiState(result)
                    }
                    is Result.Error -> {
                        val errorMessage = when(it.error) {
                            DataError.Network.NO_INTERNET -> "No internet"
                            DataError.Network.SERVER_ERROR -> "An Error occurred"
                            DataError.Network.UNKNOWN -> "An unknown error occurred"
                            else -> "An unknown error occurred"
                        }
                        _uiState.value = MovieDetailsUiState(null)
                        onError(errorMessage)
                    }
                    is Result.Loading -> onLoading()
                }
            }
        }
    }
}