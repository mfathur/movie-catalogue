package com.mfathurz.moviecatalogue.core.data.source.remote

import com.mfathurz.moviecatalogue.core.data.source.remote.model.GenreItem

interface GenreSource {
    fun getMovieGenres(): List<GenreItem>

    fun getTVShowGenres(): List<GenreItem>
}