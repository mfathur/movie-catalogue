package com.mfathurz.moviecatalogue.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfathurz.moviecatalogue.core.data.source.remote.model.TVResultsItem
import com.mfathurz.moviecatalogue.core.data.RepositoryImpl
import kotlinx.coroutines.launch

class TVShowViewModel(private val movieRepository: RepositoryImpl) : ViewModel() {
    private val _popularTVShows = MutableLiveData<List<TVResultsItem>>()
    private val _isLoading = MutableLiveData<Boolean>()

    val popularTVShows: LiveData<List<TVResultsItem>>
        get() = _popularTVShows

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun getPopularTVShows() = viewModelScope.launch {
        _isLoading.value = true
        _popularTVShows.postValue(movieRepository.getPopularTVShows())
        _isLoading.value = false
    }

}