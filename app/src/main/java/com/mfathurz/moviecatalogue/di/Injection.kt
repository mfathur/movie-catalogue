package com.mfathurz.moviecatalogue.di

import android.content.Context
import com.mfathurz.moviecatalogue.core.data.source.local.LocalDataSource
import com.mfathurz.moviecatalogue.core.data.source.local.room.MovieDatabase
import com.mfathurz.moviecatalogue.core.data.source.remote.GenreDataSource
import com.mfathurz.moviecatalogue.core.data.RepositoryImpl
import com.mfathurz.moviecatalogue.core.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): RepositoryImpl {
        val database = MovieDatabase.getInstance(context)

        val localDataSource = LocalDataSource.getInstance(database.movieDao())

        val genreDataSource = GenreDataSource.getInstance(JsonHelper(context))

        return RepositoryImpl.getInstance(genreDataSource, localDataSource)
    }
}