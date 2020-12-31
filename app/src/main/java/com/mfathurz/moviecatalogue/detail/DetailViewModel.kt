package com.mfathurz.moviecatalogue.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfathurz.moviecatalogue.core.data.source.remote.model.GenreItem
import com.mfathurz.moviecatalogue.core.domain.model.Movie
import com.mfathurz.moviecatalogue.core.domain.model.TVShow
import com.mfathurz.moviecatalogue.core.domain.usecase.MovieUseCase
import com.mfathurz.moviecatalogue.core.domain.usecase.TVShowUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DetailViewModel(
    private val tvShowUseCase: TVShowUseCase,
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    private var _movie: Movie? = null
    private var _tvShow: TVShow? = null
    private var _isFavorite = MutableLiveData<Boolean>()
    private var _dataType: Int? = null

    val isFavorite: LiveData<Boolean>
        get() = _isFavorite

    fun setFavorite(state: Boolean) {
        _isFavorite.value = state
    }

    fun setMovieData(movieResultsItem: Movie) {
        _movie = movieResultsItem
    }

    fun setTVShowData(tvResultsItem: TVShow) {
        _tvShow = tvResultsItem
    }

    fun setDataType(type: Int) {
        _dataType = type
    }

    fun getMovieData(): Movie = _movie as Movie

    fun getTVShowData(): TVShow = _tvShow as TVShow

    fun getDataType(): Int = _dataType as Int

    fun getMovieGenres(): List<GenreItem> = movieUseCase.getMovieGenres()

    fun getTVShowGenres(): List<GenreItem> = tvShowUseCase.getTVShowGenres()

    fun isAlreadyFavorite(type: Int) = viewModelScope.launch {
        if (type == DATA_MOVIE) {
            val deferredFavoriteMovies = async(IO) {
                movieUseCase.getAllFavoriteMovies()
            }
            val favoriteMovies = deferredFavoriteMovies.await()

            for (item in favoriteMovies) {
                if (item == _movie) {
                    _isFavorite.value = true
                    break
                }
            }
        } else if (type == DATA_TV_SHOW) {
            val deferredFavoriteTVShows = async(IO) {
                tvShowUseCase.getAllFavoriteTVShow()
            }
            val favoriteTVShows = deferredFavoriteTVShows.await()
            for (item in favoriteTVShows) {
                if (item == _tvShow) {
                    _isFavorite.value = true
                    break
                }
            }
        }
    }

    fun insertFavoriteMovie(movie: Movie) = viewModelScope.launch {
        movieUseCase.insertFavoriteMovie(movie)
    }

    fun deleteFavoriteMovie(movie: Movie) = viewModelScope.launch {
        movieUseCase.deleteFavoriteMovie(movie)
    }

    fun insertFavoriteTVShow(tvShow: TVShow) = viewModelScope.launch {
        tvShowUseCase.insertFavoriteTVShow(tvShow)
    }

    fun deleteFavoriteTVShow(tvShow: TVShow) = viewModelScope.launch {
        tvShowUseCase.deleteFavoriteTVShow(tvShow)
    }

    companion object {
        private const val DATA_MOVIE = 1
        private const val DATA_TV_SHOW = 2
    }
}