package com.mfathurz.moviecatalogue.core.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.core.Resource
import com.mfathurz.moviecatalogue.core.data.source.remote.model.GenreItem
import com.mfathurz.moviecatalogue.core.domain.model.Movie
import com.mfathurz.moviecatalogue.core.domain.model.TVShow

interface IRepository {

    suspend fun getPopularMovies(): List<Movie>?

    suspend fun insertFavoriteMovie(movie: Movie)

    suspend fun deleteFavoriteMovie(movie: Movie)

    fun getPagedFavoriteMovies(): LiveData<PagedList<Movie>>

    fun getAllFavoriteMovies(): List<Movie>

    fun getMovieGenres(): List<GenreItem>

    suspend fun getPopularTVShows(): List<TVShow>?

    suspend fun insertFavoriteTVShow(tvShow: TVShow)

    suspend fun deleteFavoriteTVShow(tvShow: TVShow)

    fun getAllFavoriteTVShow(): List<TVShow>

    fun getPagedFavoriteTVShows(): LiveData<PagedList<TVShow>>

    fun getTVShowGenres(): List<GenreItem>

}