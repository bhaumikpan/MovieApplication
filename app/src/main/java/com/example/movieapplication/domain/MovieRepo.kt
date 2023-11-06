package com.example.movieapplication.domain

import com.example.movieapplication.data.Movie
import com.example.movieapplication.di.ServerResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieRepo {
    @GET("20/recommendations?language=en-US")
    suspend fun getMoviesList(@Query("page")  page: String): ServerResponse

}