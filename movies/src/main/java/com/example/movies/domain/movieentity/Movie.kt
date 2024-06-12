package com.example.movies.domain.movieentity

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val id : Int,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("title") val title : String,
    @SerializedName("poster_path") val posterPath: String
) {
    fun getPosterUrl(): String {
        return if (posterPath.isEmpty()) {
            posterPath
        } else {
            "https://image.tmdb.org/t/p/w500$posterPath"
        }

    }
}