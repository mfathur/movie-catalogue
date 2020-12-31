package com.mfathurz.moviecatalogue.core.data.source.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.mfathurz.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.mfathurz.moviecatalogue.core.data.source.local.entity.TVShowEntity
import io.reactivex.Completable

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteMovie(movie: MovieEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteTVShow(tvShow: TVShowEntity) : Completable

    @Query("SELECT * FROM movie ORDER BY popularity DESC")
    fun queryAllDataSourceFavoriteMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_show ORDER BY popularity DESC")
    fun queryAllDataSourceFavoriteTVShows(): DataSource.Factory<Int, TVShowEntity>

    @Delete
    fun deleteFavoriteMovie(movie: MovieEntity): Completable

    @Delete
    fun deleteFavoriteTVShow(tvShow: TVShowEntity): Completable

    @Query("SELECT * FROM movie ORDER BY popularity DESC")
    fun queryAllFavoriteMovies(): List<MovieEntity>

    @Query("SELECT * FROM tv_show ORDER BY popularity DESC")
    fun queryAllFavoriteTVShow(): List<TVShowEntity>
}