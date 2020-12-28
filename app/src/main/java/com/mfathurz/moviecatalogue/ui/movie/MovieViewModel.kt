package com.mfathurz.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfathurz.moviecatalogue.core.domain.model.Movie
import com.mfathurz.moviecatalogue.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.launch

class MovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    private val _popularMovies = MutableLiveData<List<Movie>>()
    private val _isLoading = MutableLiveData<Boolean>()

    val popularMovies: LiveData<List<Movie>>
        get() = _popularMovies

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun getPopularMovies() = viewModelScope.launch {
        _isLoading.value = true
        _popularMovies.postValue(movieUseCase.getPopularMovies())
        _isLoading.value = false
    }

}