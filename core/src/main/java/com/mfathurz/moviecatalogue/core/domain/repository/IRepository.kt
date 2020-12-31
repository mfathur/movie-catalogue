package com.mfathurz.moviecatalogue.core.domain.repository

import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.core.data.Resource
import com.mfathurz.moviecatalogue.core.data.source.remote.model.GenreItem
import com.mfathurz.moviecatalogue.core.domain.model.Movie
import com.mfathurz.moviecatalogue.core.domain.model.TVShow
import io.reactivex.Flowable

interface IRepository {

    fun getPopularMovies(): Flowable<Resource<List<Movie>>>

    fun insertFavoriteMovie(movie: Movie)

    fun deleteFavoriteMovie(movie: Movie)

    fun getPagedFavoriteMovies(): Flowable<PagedList<Movie>>

    fun getAllFavoriteMovies(): List<Movie>

    fun getMovieGenres(): List<GenreItem>

    fun getPopularTVShows(): Flowable<Resource<List<TVShow>>>

    fun insertFavoriteTVShow(tvShow: TVShow)

    fun deleteFavoriteTVShow(tvShow: TVShow)

    fun getAllFavoriteTVShow(): List<TVShow>

    fun getPagedFavoriteTVShows(): Flowable<PagedList<TVShow>>

    fun getTVShowGenres(): List<GenreItem>

}