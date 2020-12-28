package com.mfathurz.moviecatalogue.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.mfathurz.moviecatalogue.core.data.RepositoryImpl

class FavoriteMovieViewModel(private val repository: RepositoryImpl) : ViewModel() {
    val favoriteMovies: LiveData<PagedList<MovieEntity>>
        get() = repository.getPagedFavoriteMovies()
}