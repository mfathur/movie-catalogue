package com.mfathurz.moviecatalogue.ui.favorite.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.core.data.source.local.entity.TVShowEntity
import com.mfathurz.moviecatalogue.core.data.RepositoryImpl

class FavoriteTVShowViewModel(private val repository: RepositoryImpl) : ViewModel() {
    val favoriteTVShows: LiveData<PagedList<TVShowEntity>>
        get() = repository.getPagedFavoriteTVShows()
}