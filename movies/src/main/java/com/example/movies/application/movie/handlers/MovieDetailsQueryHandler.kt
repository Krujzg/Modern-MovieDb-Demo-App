package com.example.movies.application.movie.handlers

import com.example.core.application.common.AbstractQueryHandler
import com.example.core.application.common.interfaces.Query
import com.example.core.domain.Result
import com.example.core.domain.RootError
import com.example.movies.application.movie.repositories.IMovieRepository
import com.example.movies.domain.MovieDetails
import javax.inject.Inject

internal class MovieDetailsQueryHandler @Inject constructor(private val repository: IMovieRepository) :
    AbstractQueryHandler<MovieQuery, MovieDetails, Result<MovieDetails, RootError>>() {

    override suspend fun handleQuery(request: MovieQuery): Result<MovieDetails, RootError> {
        val response = repository.getMovie(request.id)
        return getResultOnResponse(response)
    }
}

data class MovieQuery(val id: Int) : Query