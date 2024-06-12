package com.example.movies.infrastructure.di

import com.example.core.application.common.AbstractQueryHandler
import com.example.core.domain.constants.Constants.TIME_OUT
import com.example.core.domain.Result
import com.example.core.domain.RootError
import com.example.core.infrastructure.annotations.AppScope
import com.example.movies.BuildConfig
import com.example.movies.application.movie.handlers.MovieDetailsQueryHandler
import com.example.movies.application.movie.handlers.MovieQuery
import com.example.movies.application.movie.handlers.MoviesQuery
import com.example.movies.application.movie.handlers.MoviesQueryHandler
import com.example.movies.application.movie.repositories.IMovieRepository
import com.example.movies.application.movie.repositories.MovieRepository
import com.example.movies.domain.MovieDetails
import com.example.movies.domain.MovieResponse
import com.example.movies.infrastructure.ApiKeyInterceptor
import com.example.movies.infrastructure.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class) // hilt , dagger2, koin
object MovieModule {

    @AppScope
    @Provides
    fun okHttp(logger: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .callTimeout(TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(logger)
            .addInterceptor(ApiKeyInterceptor())
            .build()

    @AppScope
    @Provides
    fun retrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @AppScope
    @Provides
    fun logger(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @AppScope
    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

    @Provides
    fun provideMovieRepository(api: MovieApi): IMovieRepository = MovieRepository(api)

    @Provides
    fun provideMoviesQueryHandler(repository: IMovieRepository)
            : AbstractQueryHandler<MoviesQuery, MovieResponse, Result<MovieResponse, RootError>> =
        MoviesQueryHandler(repository)

    @Provides
    fun provideMovieDetailsQueryHandler(repository: IMovieRepository)
            : AbstractQueryHandler<MovieQuery, MovieDetails, Result<MovieDetails, RootError>> =
        MovieDetailsQueryHandler(repository)
}
