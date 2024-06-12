package com.example.movies.domain.movieentity

import com.google.gson.annotations.SerializedName
import java.util.Locale

data class MovieDetails(
    @SerializedName("id") val id : Int,
    @SerializedName("adult") val isAdult: Boolean,
    @SerializedName("backdrop_path") val backDropPath : String,
    @SerializedName("budget") val budget: Int,
    @SerializedName("original_language") val originalLanguage : String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath : String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("revenue") val revenue: Int,
    @SerializedName("runtime") val runtime : Int,
    @SerializedName("title") val title : String,
    @SerializedName("video") val isThereAVideo: Boolean,
    @SerializedName("vote_average") val voteAverage:Double,
    @SerializedName("vote_count") val voteCount: Int,
) {
    fun getPosterUrl(): String {
        return "https://image.tmdb.org/t/p/w500$posterPath"
    }

    fun getFormattedVoteAverage() : String {
        return String.format(Locale.US, "%.2f", voteAverage)
    }
}