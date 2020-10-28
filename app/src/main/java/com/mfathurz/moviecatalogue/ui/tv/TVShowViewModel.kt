package com.mfathurz.moviecatalogue.ui.tv

import androidx.lifecycle.ViewModel
import com.mfathurz.moviecatalogue.db.DataTVShow
import com.mfathurz.moviecatalogue.model.TVShowEntity

class TVShowViewModel : ViewModel(){
    fun getAllTVShows(): List<TVShowEntity> = DataTVShow.queryAllTVShows()
}