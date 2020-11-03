package com.mfathurz.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfathurz.moviecatalogue.db.MovieRepository
import com.mfathurz.moviecatalogue.db.remote.model.MovieResultsItem
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _popularMovies = MutableLiveData<List<MovieResultsItem>>()
    private val _isLoading = MutableLiveData<Boolean>()

    init {
        setPopularMovies()
    }

    private fun setPopularMovies() = viewModelScope.launch {
        _isLoading.value = true
        _popularMovies.postValue(movieRepository.getPopularMovies())
        _isLoading.value = false
    }

    fun getPopularMovies(): LiveData<List<MovieResultsItem>> = _popularMovies

    fun getLoadingState(): LiveData<Boolean> = _isLoading
}