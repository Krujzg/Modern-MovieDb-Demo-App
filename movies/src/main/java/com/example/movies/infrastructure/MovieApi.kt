package com.example.movies.infrastructure

import com.example.movies.domain.movieentity.MovieDetails
import com.example.movies.domain.movieentity.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    suspend fun getMovies(@Query("page") page: Int): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") movieId: Int): Response<MovieDetails>
}