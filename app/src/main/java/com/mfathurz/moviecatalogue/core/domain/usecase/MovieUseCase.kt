package com.mfathurz.moviecatalogue.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.core.data.source.remote.model.GenreItem
import com.mfathurz.moviecatalogue.core.domain.model.Movie

interface MovieUseCase {

    suspend fun getPopularMovies(): List<Movie>?

    suspend fun insertFavoriteMovie(movie: Movie)

    suspend fun deleteFavoriteMovie(movie: Movie)

    fun getPagedFavoriteMovies(): LiveData<PagedList<Movie>>

    fun getAllFavoriteMovies(): List<Movie>

    fun getMovieGenres(): List<GenreItem>
}