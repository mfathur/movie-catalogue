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
    fun queryAllFavoriteMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM tv_show")
    fun queryAllFavoriteTVShows(): Flow<List<TVShowEntity>>

    @Delete
    suspend fun deleteFavoriteMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteFavoriteTVShow(tvShow: TVShowEntity)
}