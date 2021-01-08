package com.mfathurz.moviecatalogue.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mfathurz.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.mfathurz.moviecatalogue.core.data.source.local.entity.TVShowEntity
import com.mfathurz.moviecatalogue.core.utils.RoomTypeConverter

@Database(entities = [TVShowEntity::class, MovieEntity::class], version = 1, exportSchema = false)
@TypeConverters(RoomTypeConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

}