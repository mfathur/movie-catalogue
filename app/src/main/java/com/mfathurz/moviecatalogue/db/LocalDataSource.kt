package com.mfathurz.moviecatalogue.db

import com.mfathurz.moviecatalogue.db.room.MovieDao
import com.mfathurz.moviecatalogue.db.room.entity.MovieEntity
import com.mfathurz.moviecatalogue.db.room.entity.TVShowEntity
import kotlinx.coroutines.flow.Flow

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

    fun queryAllFavoriteTVShows(): Flow<List<TVShowEntity>> = movieDao.queryAllFavoriteTVShows()

    fun queryAllFavoriteMovies(): Flow<List<MovieEntity>> = movieDao.queryAllFavoriteMovies()

    suspend fun deleteFavoriteMovie(movie: MovieEntity) = movieDao.deleteFavoriteMovie(movie)

    suspend fun deleteFavoriteTVShow(tvShow: TVShowEntity) = movieDao.deleteFavoriteTVShow(tvShow)

}