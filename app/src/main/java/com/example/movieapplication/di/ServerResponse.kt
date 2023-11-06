package com.example.movieapplication.di

import com.example.movieapplication.data.Movie

data class ServerResponse(
    var page: Int,
    var results: List<Movie>
)
