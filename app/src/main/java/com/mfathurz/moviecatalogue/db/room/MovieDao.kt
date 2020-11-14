package com.mfathurz.moviecatalogue.db.room

import androidx.room.*
import com.mfathurz.moviecatalogue.db.room.entity.MovieEntity
import com.mfathurz.moviecatalogue.db.room.entity.TVShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteTVShow(tvShow: TVShowEntity)

    @Query("SELECT * FROM movie")
    suspend fun queryAllFavoriteMovies(): List<MovieEntity>

    @Query("SELECT * FROM tv_show")
    suspend fun queryAllFavoriteTVShows(): List<TVShowEntity>

    @Delete
    suspend fun deleteFavoriteMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteFavoriteTVShow(tvShow: TVShowEntity)
}