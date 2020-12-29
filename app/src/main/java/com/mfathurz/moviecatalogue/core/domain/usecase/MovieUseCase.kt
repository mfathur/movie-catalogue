package com.mfathurz.moviecatalogue.core.domain.usecase

import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.core.Resource
import com.mfathurz.moviecatalogue.core.data.source.remote.model.GenreItem
import com.mfathurz.moviecatalogue.core.domain.model.Movie
import io.reactivex.Flowable

interface MovieUseCase {

    fun getPopularMovies(): Flowable<Resource<List<Movie>>>

    fun insertFavoriteMovie(movie: Movie)

    fun deleteFavoriteMovie(movie: Movie)

    fun getPagedFavoriteMovies(): Flowable<PagedList<Movie>>

    fun getAllFavoriteMovies(): List<Movie>

    fun getMovieGenres(): List<GenreItem>
}