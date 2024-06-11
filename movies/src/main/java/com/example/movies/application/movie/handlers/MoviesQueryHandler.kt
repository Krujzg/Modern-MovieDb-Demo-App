package com.example.movies.application.movie.handlers

import com.example.core.application.common.AbstractQueryHandler
import com.example.core.application.common.interfaces.Query
import com.example.core.domain.Result
import com.example.core.domain.RootError
import com.example.movies.application.movie.repositories.IMovieRepository
import com.example.movies.domain.MovieResponse
import javax.inject.Inject

internal class MoviesQueryHandler @Inject constructor(private val repository: IMovieRepository) :
    AbstractQueryHandler<MoviesQuery, MovieResponse, Result<MovieResponse, RootError>>() {
    override suspend fun handleQuery(request: MoviesQuery): Result<MovieResponse, RootError> {
        val response = repository.getMovies(request.page)
        return getResultOnResponse(response)
    }
}

data class MoviesQuery(val page: Int) : Query