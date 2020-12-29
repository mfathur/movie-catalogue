package com.mfathurz.moviecatalogue.ui.favorite.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.core.domain.model.TVShow
import com.mfathurz.moviecatalogue.core.domain.usecase.TVShowUseCase

class FavoriteTVShowViewModel(private val tvShowUseCase: TVShowUseCase) : ViewModel() {
    val favoriteTVShows: LiveData<PagedList<TVShow>>
        get() = LiveDataReactiveStreams.fromPublisher(tvShowUseCase.getPagedFavoriteTVShows())
}