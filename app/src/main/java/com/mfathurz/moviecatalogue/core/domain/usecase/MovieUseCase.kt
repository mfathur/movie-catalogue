package com.mfathurz.moviecatalogue.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.mfathurz.moviecatalogue.core.data.source.remote.model.GenreItem
import com.mfathurz.moviecatalogue.core.data.source.remote.model.MovieResultsItem

interface MovieUseCase {

    suspend fun getPopularMovies(): List<MovieResultsItem>?

    suspend fun insertFavoriteMovie(movie: MovieEntity)

    suspend fun deleteFavoriteMovie(movie: MovieEntity)

    fun getPagedFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun getAllFavoriteMovies(): List<MovieEntity>

    fun getMovieGenres(): List<GenreItem>
}