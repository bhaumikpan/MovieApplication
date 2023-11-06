package com.example.movieapplication.domain

import android.util.Log
import com.example.movieapplication.data.Movie
import javax.inject.Inject

class UseCaseRepoImpl @Inject constructor(
    private val api: MovieRepo
) : UseCaseRepo {
    override suspend fun getMovieListToDisplay(page: String): List<Movie> {
        val answer = mutableListOf<Movie>()
        kotlin.runCatching {
            api.getMoviesList(page)
        }.onSuccess { list ->
            answer.addAll(list.results)
        }.onFailure {
            Log.e("Error", it.message.toString())
        }
        return answer
    }
}