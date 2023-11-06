package com.example.movieapplication.domain

import com.example.movieapplication.data.Movie

interface UseCaseRepo {
    suspend fun getMovieListToDisplay(page: String): List<Movie>
}