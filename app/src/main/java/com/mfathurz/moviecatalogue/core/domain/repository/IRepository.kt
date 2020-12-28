package com.mfathurz.moviecatalogue.core.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.mfathurz.moviecatalogue.core.data.source.local.entity.TVShowEntity
import com.mfathurz.moviecatalogue.core.data.source.remote.model.GenreItem
import com.mfathurz.moviecatalogue.core.data.source.remote.model.MovieResultsItem
import com.mfathurz.moviecatalogue.core.data.source.remote.model.TVResultsItem

interface IRepository {

    suspend fun getPopularMovies(): List<MovieResultsItem>?

    suspend fun insertFavoriteMovie(movie: MovieEntity)

    suspend fun deleteFavoriteMovie(movie: MovieEntity)

    fun getPagedFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun getAllFavoriteMovies(): List<MovieEntity>

    fun getMovieGenres(): List<GenreItem>

    suspend fun getPopularTVShows(): List<TVResultsItem>?

    suspend fun insertFavoriteTVShow(tvShow: TVShowEntity)

    suspend fun deleteFavoriteTVShow(tvShow: TVShowEntity)

    fun getAllFavoriteTVShow(): List<TVShowEntity>

    fun getPagedFavoriteTVShows(): LiveData<PagedList<TVShowEntity>>

    fun getTVShowGenres(): List<GenreItem>

}