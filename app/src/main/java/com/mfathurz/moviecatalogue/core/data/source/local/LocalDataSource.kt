package com.mfathurz.moviecatalogue.core.data.source.local

import androidx.paging.DataSource
import com.mfathurz.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.mfathurz.moviecatalogue.core.data.source.local.entity.TVShowEntity
import com.mfathurz.moviecatalogue.core.data.source.local.room.MovieDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LocalDataSource constructor(private val movieDao: MovieDao) {

//    companion object {
//        @Volatile
//        private var instance: LocalDataSource? = null
//
//        fun getInstance(movieDao: MovieDao): LocalDataSource =
//            instance ?: synchronized(this) {
//                instance ?: LocalDataSource(movieDao)
//            }
//    }

    fun insertFavoriteMovie(movie: MovieEntity) =
        movieDao.insertFavoriteMovie(movie).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe()!!

    fun deleteFavoriteMovie(movie: MovieEntity) =
        movieDao.deleteFavoriteMovie(movie).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe()!!

    fun queryAllDataSourceFavoriteTVShows(): DataSource.Factory<Int, TVShowEntity> =
        movieDao.queryAllDataSourceFavoriteTVShows()

    fun queryAllDataSourceFavoriteMovies(): DataSource.Factory<Int, MovieEntity> =
        movieDao.queryAllDataSourceFavoriteMovies()

    fun insertFavoriteTVShow(tvShow: TVShowEntity) =
        movieDao.insertFavoriteTVShow(tvShow).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe()!!

    fun deleteFavoriteTVShow(tvShow: TVShowEntity) =
        movieDao.deleteFavoriteTVShow(tvShow).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe()!!

    fun queryAllFavoriteMovies(): List<MovieEntity> = movieDao.queryAllFavoriteMovies()

    fun queryAllFavoriteTVShow(): List<TVShowEntity> = movieDao.queryAllFavoriteTVShow()

}