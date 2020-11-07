package com.mfathurz.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import com.mfathurz.moviecatalogue.data.remote.model.GenreItem
import com.mfathurz.moviecatalogue.data.remote.model.MovieResultsItem
import com.mfathurz.moviecatalogue.data.remote.model.TVResultsItem
import com.mfathurz.moviecatalogue.db.MovieRepository

class DetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private var _movieResultsItem: MovieResultsItem? = null
    private var _tvResultsItem: TVResultsItem? = null
    private var _dataType: Int? = null

    fun setMovieData(movieResultsItem: MovieResultsItem) {
        _movieResultsItem = movieResultsItem
    }

    fun setTVShowData(tvResultsItem: TVResultsItem) {
        _tvResultsItem = tvResultsItem
    }

    fun setDataType(type: Int) {
        _dataType = type
    }

    fun getMovieData(): MovieResultsItem = _movieResultsItem as MovieResultsItem

    fun getTVShowData(): TVResultsItem = _tvResultsItem as TVResultsItem

    fun getDataType(): Int = _dataType as Int

    fun getMovieGenres(): List<GenreItem> = movieRepository.getMovieGenres()

    fun getTVShowGenres(): List<GenreItem> = movieRepository.getTVShowGenres()
}