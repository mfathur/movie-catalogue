package com.mfathurz.moviecatalogue.ui.tv

import androidx.lifecycle.ViewModel
import com.mfathurz.moviecatalogue.db.MovieRepository
import com.mfathurz.moviecatalogue.db.local.DataTVShow
import com.mfathurz.moviecatalogue.model.TVShowEntity

class TVShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getAllTVShows(): List<TVShowEntity> = DataTVShow.queryAllTVShows()
}