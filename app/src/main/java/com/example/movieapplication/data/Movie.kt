package com.example.movieapplication.data

import com.google.gson.annotations.SerializedName

data class Movie(
    val adult: Boolean,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    val id: Int,

    val title: String,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_title")
    val originalTitle: String,

    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("media_type")
    val mediaType: String,

    val popularity: Double
) {
   fun getBackdropPathWithUrl(): String {
       return "https://image.tmdb.org/t/p/original/$backdropPath"
   }

    fun getPosterPathWithUrl(): String {
        return "https://image.tmdb.org/t/p/w800/$posterPath"
    }
}
