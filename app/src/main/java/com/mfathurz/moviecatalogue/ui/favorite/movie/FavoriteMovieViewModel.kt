package com.mfathurz.moviecatalogue.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.data.local.room.entity.MovieEntity
import com.mfathurz.moviecatalogue.db.MovieRepository

class FavoriteMovieViewModel(private val repository: MovieRepository) : ViewModel() {
    val favoriteMovies: LiveData<PagedList<MovieEntity>>
        get() = repository.getPagedFavoriteMovies()
}