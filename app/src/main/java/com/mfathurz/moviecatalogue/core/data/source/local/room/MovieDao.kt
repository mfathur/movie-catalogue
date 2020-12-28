package com.mfathurz.moviecatalogue.core.data.source.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.mfathurz.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.mfathurz.moviecatalogue.core.data.source.local.entity.TVShowEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteTVShow(tvShow: TVShowEntity)

    @Query("SELECT * FROM movie ORDER BY popularity DESC")
    fun queryAllDataSourceFavoriteMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_show ORDER BY popularity DESC")
    fun queryAllDataSourceFavoriteTVShows(): DataSource.Factory<Int, TVShowEntity>

    @Delete
    suspend fun deleteFavoriteMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteFavoriteTVShow(tvShow: TVShowEntity)

    @Query("SELECT * FROM movie ORDER BY popularity DESC")
    fun queryAllFavoriteMovies(): List<MovieEntity>

    @Query("SELECT * FROM tv_show ORDER BY popularity DESC")
    fun queryAllFavoriteTVShow(): List<TVShowEntity>
}