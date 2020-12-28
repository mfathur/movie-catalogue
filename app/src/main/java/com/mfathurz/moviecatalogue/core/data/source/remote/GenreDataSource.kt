package com.mfathurz.moviecatalogue.core.data.source.remote

import com.mfathurz.moviecatalogue.core.data.source.remote.model.GenreItem
import com.mfathurz.moviecatalogue.core.utils.JsonHelper

class GenreDataSource private constructor(private val jsonHelper: JsonHelper) {
    companion object {
        @Volatile
        private var instance: GenreDataSource? = null

        fun getInstance(helper: JsonHelper): GenreDataSource =
            instance ?: synchronized(this) {
                instance ?: GenreDataSource(helper)
            }
    }

    fun getAllMovieGenres(): List<GenreItem> = jsonHelper.loadMovieGenres()

    fun getAllTVShowGenres(): List<GenreItem> = jsonHelper.loadTVShowGenres()
}
