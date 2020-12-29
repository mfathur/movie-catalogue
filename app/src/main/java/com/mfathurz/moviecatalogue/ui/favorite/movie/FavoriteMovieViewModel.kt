package com.mfathurz.moviecatalogue.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.core.domain.model.Movie
import com.mfathurz.moviecatalogue.core.domain.usecase.MovieUseCase

class FavoriteMovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovies: LiveData<PagedList<Movie>>
        get() = LiveDataReactiveStreams.fromPublisher(movieUseCase.getPagedFavoriteMovies())
}