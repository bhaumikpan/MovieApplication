package com.example.movieapplication.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapplication.data.Movie
import com.example.movieapplication.domain.MovieRepo
import com.example.movieapplication.domain.UseCaseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val useCaseRepo: UseCaseRepo
): ViewModel() {

    private val _data = MutableLiveData<Boolean>()
    val data: LiveData<Boolean>
        get() = _data

    private var page: String = "1"

    var listToDisplay = mutableListOf<Movie>()
    var cacheData = listOf<Movie>()

    fun getData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val list = useCaseRepo.getMovieListToDisplay(page)
                listToDisplay.addAll(list)
                _data.postValue(true)
            }
        }
    }

    fun loadMore() {
        val forPage = page.toInt() + 1
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                page = forPage.toString()
                val list = useCaseRepo.getMovieListToDisplay(forPage.toString())
                cacheData = listToDisplay
                listToDisplay.addAll(list)
                _data.postValue(true)
            }
        }
    }
}