package com.example.movies

import com.example.core.application.common.AbstractQueryHandler
import com.example.core.domain.Result
import com.example.core.domain.RootError
import com.example.core.infrastructure.annotations.AppScope
import com.example.movies.application.movie.handlers.MovieDetailsQueryHandler
import com.example.movies.application.movie.handlers.MovieQuery
import com.example.movies.application.movie.handlers.MoviesQuery
import com.example.movies.application.movie.handlers.MoviesQueryHandler
import com.example.movies.application.movie.repositories.IMovieRepository
import com.example.movies.application.movie.repositories.MovieRepository
import com.example.movies.domain.movieentity.MovieDetails
import com.example.movies.domain.movieentity.MovieResponse
import com.example.movies.fakes.FakeApiKeyInterceptor
import com.example.movies.fakes.FakeMovieApi
import com.example.movies.infrastructure.MovieApi
import com.example.movies.infrastructure.di.MovieModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [MovieModule::class]  // Replace the original AppModule with this test module
)
object TestMovieModule {

    @AppScope
    @Provides
    fun provideFakeOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(FakeApiKeyInterceptor())
            .build()

    @Provides
    @AppScope
    fun provideFakeRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://fakeurl.com")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @AppScope
    @Provides
    fun logger(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @AppScope
    fun provideFakeMovieApi(retrofit: Retrofit): MovieApi = FakeMovieApi()

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
