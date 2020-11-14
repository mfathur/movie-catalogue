package com.mfathurz.moviecatalogue.di

import android.content.Context
import com.mfathurz.moviecatalogue.data.remote.GenreDataSource
import com.mfathurz.moviecatalogue.db.LocalDataSource
import com.mfathurz.moviecatalogue.db.MovieRepository
import com.mfathurz.moviecatalogue.db.room.MovieDatabase
import com.mfathurz.moviecatalogue.util.JsonHelper

object Injection {
    fun provideRepository(context: Context): MovieRepository {
        val database = MovieDatabase.getInstance(context)

        val localDataSource = LocalDataSource.getInstance(database.movieDao())

        val genreDataSource = GenreDataSource.getInstance(JsonHelper(context))

        return MovieRepository.getInstance(genreDataSource,localDataSource)
    }
}