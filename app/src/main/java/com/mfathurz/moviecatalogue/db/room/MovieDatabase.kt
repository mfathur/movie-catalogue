package com.mfathurz.moviecatalogue.db.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mfathurz.moviecatalogue.db.room.entity.MovieEntity
import com.mfathurz.moviecatalogue.db.room.entity.TVShowEntity
import com.mfathurz.moviecatalogue.util.RoomTypeConverter

@Database(entities = [TVShowEntity::class, MovieEntity::class], version = 1, exportSchema = false)
@TypeConverters(*[RoomTypeConverter::class])
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var instance: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context,
                    MovieDatabase::class.java,
                    "movie_tv_show.db"
                ).build()
            }
    }
}