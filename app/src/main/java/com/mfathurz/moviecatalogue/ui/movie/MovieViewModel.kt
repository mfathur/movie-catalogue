package com.mfathurz.moviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import com.mfathurz.moviecatalogue.db.MovieRepository
import com.mfathurz.moviecatalogue.db.local.DataMovie
import com.mfathurz.moviecatalogue.model.MovieEntity

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getAllMovies(): List<MovieEntity> = DataMovie.queryAllMovies()
}