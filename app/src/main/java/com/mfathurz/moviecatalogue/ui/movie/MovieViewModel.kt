package com.mfathurz.moviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import com.mfathurz.moviecatalogue.db.DataMovie
import com.mfathurz.moviecatalogue.model.MovieEntity

class MovieViewModel :ViewModel(){
    fun getAllMovies() : List<MovieEntity> = DataMovie.queryAllMovies()
}