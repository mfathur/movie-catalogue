package com.mfathurz.moviecatalogue.ui.favorite.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.data.local.room.entity.TVShowEntity
import com.mfathurz.moviecatalogue.db.MovieRepository

class FavoriteTVShowViewModel(private val repository: MovieRepository) : ViewModel() {
    val favoriteTVShows: LiveData<PagedList<TVShowEntity>>
        get() = repository.getPagedFavoriteTVShows()
}