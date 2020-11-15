package com.mfathurz.moviecatalogue.data.local

import androidx.paging.DataSource
import com.mfathurz.moviecatalogue.data.local.room.MovieDao
import com.mfathurz.moviecatalogue.data.local.room.entity.MovieEntity
import com.mfathurz.moviecatalogue.data.local.room.entity.TVShowEntity

class LocalDataSource private constructor(private val movieDao: MovieDao) {

    companion object {
        @Volatile
        private var instance: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(movieDao)
            }
    }

    suspend fun insertFavoriteMovie(movie: MovieEntity) = movieDao.insertFavoriteMovie(movie)

    suspend fun insertFavoriteTVShow(tvShow: TVShowEntity) = movieDao.insertFavoriteTVShow(tvShow)

    fun queryAllDataSourceFavoriteTVShows(): DataSource.Factory<Int, TVShowEntity> =
        movieDao.queryAllDataSourceFavoriteTVShows()

    fun queryAllDataSourceFavoriteMovies(): DataSource.Factory<Int, MovieEntity> =
        movieDao.queryAllDataSourceFavoriteMovies()

    suspend fun deleteFavoriteMovie(movie: MovieEntity) = movieDao.deleteFavoriteMovie(movie)

    suspend fun deleteFavoriteTVShow(tvShow: TVShowEntity) = movieDao.deleteFavoriteTVShow(tvShow)

    fun queryAllFavoriteMovies(): List<MovieEntity> = movieDao.queryAllFavoriteMovies()

    fun queryAllFavoriteTVShow(): List<TVShowEntity> = movieDao.queryAllFavoriteTVShow()

}