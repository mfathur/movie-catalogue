package com.mfathurz.moviecatalogue.ui.favorite.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.core.data.source.local.entity.TVShowEntity
import com.mfathurz.moviecatalogue.core.data.RepositoryImpl
import com.mfathurz.moviecatalogue.core.domain.model.TVShow
import com.mfathurz.moviecatalogue.core.domain.usecase.TVShowUseCase

class FavoriteTVShowViewModel(private val tvShowUseCase: TVShowUseCase) : ViewModel() {
    val favoriteTVShows: LiveData<PagedList<TVShow>>
        get() = tvShowUseCase.getPagedFavoriteTVShows()
}