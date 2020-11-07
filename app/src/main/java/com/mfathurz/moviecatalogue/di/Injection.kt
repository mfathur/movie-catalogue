package com.mfathurz.moviecatalogue.di

import android.content.Context
import com.mfathurz.moviecatalogue.data.remote.GenreDataSource
import com.mfathurz.moviecatalogue.db.MovieRepository
import com.mfathurz.moviecatalogue.util.JsonHelper

object Injection {
    fun provideRepository(context: Context): MovieRepository {
        val genreDataSource = GenreDataSource.getInstance(JsonHelper(context))
        return MovieRepository.getInstance(genreDataSource)
    }
}