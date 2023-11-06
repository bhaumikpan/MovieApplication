package com.example.movieapplication.domain

import android.util.Log
import com.example.movieapplication.data.Movie
import com.example.movieapplication.di.IoDispatcher
import com.example.movieapplication.di.ServerResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepoImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val api: MovieRepo
) : MovieRepo {
    override suspend fun getMoviesList(page: String): ServerResponse {
        val answer = ServerResponse(0, mutableListOf())
        withContext(dispatcher) {
            kotlin.runCatching {
                api.getMoviesList(page)
            }.onSuccess { response ->
                answer.page = response.page
                answer.results = response.results
            }.onFailure {
                Log.d("Error", it.message.toString())
            }
        }
        return answer
    }
}