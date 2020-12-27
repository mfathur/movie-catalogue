package com.mfathurz.moviecatalogue.data.remote

import com.mfathurz.moviecatalogue.data.remote.model.GenreItem

interface GenreSource {
    fun getMovieGenres(): List<GenreItem>

    fun getTVShowGenres(): List<GenreItem>
}