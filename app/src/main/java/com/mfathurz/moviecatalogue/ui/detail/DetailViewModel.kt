package com.mfathurz.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfathurz.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.mfathurz.moviecatalogue.core.data.source.local.entity.TVShowEntity
import com.mfathurz.moviecatalogue.core.data.source.remote.model.GenreItem
import com.mfathurz.moviecatalogue.core.data.source.remote.model.MovieResultsItem
import com.mfathurz.moviecatalogue.core.data.source.remote.model.TVResultsItem
import com.mfathurz.moviecatalogue.core.data.RepositoryImpl
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DetailViewModel(private val movieRepository: RepositoryImpl) : ViewModel() {

    private var _movieEntity: MovieEntity? = null
    private var _tvShowEntity: TVShowEntity? = null
    private var _isFavorite = MutableLiveData<Boolean>()
    private var _dataType: Int? = null


    val isFavorite: LiveData<Boolean>
        get() = _isFavorite

    fun setFavorite(state: Boolean) {
        _isFavorite.value = state
    }

    fun setMovieData(movieResultsItem: MovieEntity) {
        _movieEntity = movieResultsItem
    }

    fun setTVShowData(tvResultsItem: TVShowEntity) {
        _tvShowEntity = tvResultsItem
    }

    fun setDataType(type: Int) {
        _dataType = type
    }

    fun getMovieData(): MovieEntity = _movieEntity as MovieEntity

    fun getTVShowData(): TVShowEntity = _tvShowEntity as TVShowEntity

    fun getDataType(): Int = _dataType as Int

    fun getMovieGenres(): List<GenreItem> = movieRepository.getMovieGenres()

    fun getTVShowGenres(): List<GenreItem> = movieRepository.getTVShowGenres()

    fun isAlreadyFavorite(type: Int) = viewModelScope.launch {
        if (type == DATA_MOVIE || type == LOCAL_DATA_MOVIE) {
            val deferredFavoriteMovies = async(IO) {
                movieRepository.getAllFavoriteMovies()
            }
            val favoriteMovies = deferredFavoriteMovies.await()

            for (item in favoriteMovies) {
                if (item == _movieEntity) {
                    _isFavorite.value = true
                    break
                }
            }
        } else if (type == DATA_TV_SHOW || type == LOCAL_DATA_TV_SHOW) {
            val deferredFavoriteTVShows = async(IO) {
                movieRepository.getAllFavoriteTVShow()
            }
            val favoriteTVShows = deferredFavoriteTVShows.await()
            for (item in favoriteTVShows) {
                if (item == _tvShowEntity) {
                    _isFavorite.value = true
                    break
                }
            }
        }

    }

    fun deleteFavoriteMovie(movie: MovieEntity) = viewModelScope.launch {
        movieRepository.deleteFavoriteMovie(movie)
    }

    fun deleteFavoriteTVShow(tvShow: TVShowEntity) = viewModelScope.launch {
        movieRepository.deleteFavoriteTVShow(tvShow)
    }

    fun insertFavoriteMovie(movie: MovieEntity) = viewModelScope.launch {
        movieRepository.insertFavoriteMovie(movie)
    }

    fun insertFavoriteTVShow(tvShow: TVShowEntity) = viewModelScope.launch {
        movieRepository.insertFavoriteTVShow(tvShow)
    }


    fun changeMovieResultItemToEntity(data: MovieResultsItem) {
        _movieEntity = MovieEntity(
            data.id,
            data.overview,
            data.originalLanguage,
            data.originalTitle,
            data.title,
            data.genreIds,
            data.posterPath,
            data.backdropPath,
            data.releaseDate,
            data.popularity,
            data.voteAverage,
            data.voteCount
        )
    }

    fun changeTVShowResultItemToEntity(data: TVResultsItem) {
        _tvShowEntity = TVShowEntity(
            data.id,
            data.firstAirDate,
            data.overview,
            data.originalLanguage,
            data.genreIds,
            data.posterPath,
            data.originCountry,
            data.backdropPath,
            data.originalName,
            data.popularity,
            data.voteAverage,
            data.name,
            data.voteCount
        )
    }

    companion object {
        private const val DATA_MOVIE = 1
        private const val DATA_TV_SHOW = 2
        private const val LOCAL_DATA_MOVIE = 3
        private const val LOCAL_DATA_TV_SHOW = 4
    }
}