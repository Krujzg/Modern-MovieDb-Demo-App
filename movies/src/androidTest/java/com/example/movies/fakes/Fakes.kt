package com.example.movies.fakes

import com.example.movies.domain.MovieDetails
import com.example.movies.domain.MovieResponse
import com.example.movies.infrastructure.MovieApi
import okhttp3.Interceptor
import okhttp3.Response

class FakeApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // Fake implementation of the interceptor
        return chain.proceed(chain.request())
    }
}

class FakeMovieApi : MovieApi {
    override suspend fun getMovies(page: Int): retrofit2.Response<MovieResponse> {
        return retrofit2.Response.success(MovieResponse(page = 1, results = listOf()))
    }

    override suspend fun getMovie(movieId: Int): retrofit2.Response<MovieDetails> {
        return retrofit2.Response.success(MovieDetails(
            id = 8357,
            isAdult = false,
            backDropPath = "indoctum",
            budget = 2716,
            originalLanguage = "pharetra",
            originalTitle = "senserit",
            overview = "convallis",
            popularity = 12.13,
            posterPath = "pro",
            releaseDate = "ponderum",
            revenue = 4522,
            runtime = 3272,
            title = "suspendisse",
            isThereAVideo = false,
            voteAverage = 14.15,
            voteCount = 5712
        ))
    }
}