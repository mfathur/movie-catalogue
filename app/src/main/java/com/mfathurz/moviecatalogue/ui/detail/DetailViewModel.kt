package com.mfathurz.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import com.mfathurz.moviecatalogue.db.MovieRepository
import com.mfathurz.moviecatalogue.model.MovieEntity
import com.mfathurz.moviecatalogue.model.TVShowEntity

class DetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private var movieEntity: MovieEntity? = null
    private var tvShowEntity: TVShowEntity? = null
    private var dataType: Int? = null

    fun setMovieData(movieEntity: MovieEntity?) {
        this.movieEntity = movieEntity
    }

    fun getMovieData(): MovieEntity = movieEntity as MovieEntity

    fun setTVShowData(tvShowEntity: TVShowEntity?) {
        this.tvShowEntity = tvShowEntity
    }

    fun getTVShowData(): TVShowEntity = tvShowEntity as TVShowEntity

    fun setDataType(type: Int) {
        dataType = type
    }

    fun getDataType(): Int = dataType as Int
}