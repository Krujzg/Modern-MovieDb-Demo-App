package com.example.movies.application.movie.repositories

import com.example.movies.domain.MovieDetails
import com.example.movies.domain.MovieResponse
import com.example.movies.infrastructure.MovieApi
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class MovieRepository @Inject constructor(private val api: MovieApi) : IMovieRepository {
    override suspend fun getMovies(page: Int): Response<MovieResponse> {
        return api.getMovies(page)
    }

    override suspend fun getMovie(movieId: Int): Response<MovieDetails> {
        return api.getMovie(movieId)
    }
}

interface IMovieRepository {
    suspend fun getMovies(page: Int): Response<MovieResponse>
    suspend fun getMovie(movieId: Int): Response<MovieDetails>
}