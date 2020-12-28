package com.mfathurz.moviecatalogue.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfathurz.moviecatalogue.core.domain.model.TVShow
import com.mfathurz.moviecatalogue.core.domain.usecase.TVShowUseCase
import kotlinx.coroutines.launch

class TVShowViewModel(private val tvShowUseCase: TVShowUseCase) : ViewModel() {
    private val _popularTVShows = MutableLiveData<List<TVShow>>()
    private val _isLoading = MutableLiveData<Boolean>()

    val popularTVShows: LiveData<List<TVShow>>
        get() = _popularTVShows

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun getPopularTVShows() = viewModelScope.launch {
        _isLoading.value = true
        _popularTVShows.postValue(tvShowUseCase.getPopularTVShows())
        _isLoading.value = false
    }

}