package com.mfathurz.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfathurz.moviecatalogue.data.remote.model.MovieResultsItem
import com.mfathurz.moviecatalogue.db.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _popularMovies = MutableLiveData<List<MovieResultsItem>>()
    private val _isLoading = MutableLiveData<Boolean>()

    val popularMovies: LiveData<List<MovieResultsItem>>
        get() = _popularMovies

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun getPopularMovies() = viewModelScope.launch {
        _isLoading.value = true
        _popularMovies.postValue(movieRepository.getPopularMovies())
        _isLoading.value = false
    }

}