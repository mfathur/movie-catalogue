package com.mfathurz.moviecatalogue.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfathurz.moviecatalogue.db.MovieRepository
import com.mfathurz.moviecatalogue.db.local.DataTVShow
import com.mfathurz.moviecatalogue.db.local.model.TVShowEntity
import com.mfathurz.moviecatalogue.db.remote.model.TVResultsItem
import kotlinx.coroutines.launch

class TVShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private val _popularTVShows = MutableLiveData<List<TVResultsItem>>()
    private val _isLoading = MutableLiveData<Boolean>()

    init {
        setPopularTVShows()
    }

    private fun setPopularTVShows() = viewModelScope.launch {
        _isLoading.value = true
        _popularTVShows.postValue(movieRepository.getPopularTVShows())
        _isLoading.value = false
    }

    fun getPopularTVShows(): LiveData<List<TVResultsItem>> = _popularTVShows

    fun getLoadingState(): LiveData<Boolean> = _isLoading

    fun getAllTVShows(): List<TVShowEntity> = DataTVShow.queryAllTVShows()
}