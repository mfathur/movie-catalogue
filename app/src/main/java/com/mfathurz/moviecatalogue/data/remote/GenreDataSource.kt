package com.mfathurz.moviecatalogue.data.remote

import com.mfathurz.moviecatalogue.data.remote.model.GenreItem
import com.mfathurz.moviecatalogue.util.JsonHelper

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
