package com.example.movies.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.core.application.common.AbstractQueryHandler
import com.example.core.domain.DataError
import com.example.core.domain.Result
import com.example.core.domain.RootError
import com.example.core.presentation.viewmodels.AbstractViewModel
import com.example.movies.application.movie.handlers.MoviesQuery
import com.example.movies.domain.MovieResponse
import com.example.movies.presentation.states.MoviesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MoviesViewModel @Inject constructor(
    private val moviesQueryHandler: AbstractQueryHandler<MoviesQuery, MovieResponse, Result<MovieResponse, RootError>>,
) : AbstractViewModel<MoviesUiState>(initialUIState = MoviesUiState()) {

    init {
        loadMovies()
    }

    private fun loadMovies(page: Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            val request = MoviesQuery(page = page)

            moviesQueryHandler.handle(request).collect {
                when (it) {
                    is Result.Success -> {
                        val result = it.data.results
                        _uiState.value = MoviesUiState(result)
                    }
                    is Result.Error -> {
                        val errorMessage = when(it.error) {
                            DataError.Network.NO_INTERNET -> "No internet"
                            DataError.Network.SERVER_ERROR -> "An Error occured"
                            DataError.Network.UNKNOWN -> "An unknown error occured"
                            else -> "An unknown error occured"
                        }
                        _uiState.value = MoviesUiState(emptyList())
                        onError(errorMessage)
                    }
                    is Result.Loading -> onLoading()
                }
            }
        }
    }
}
