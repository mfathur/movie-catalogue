package com.mfathurz.moviecatalogue.di

import android.content.Context
import com.mfathurz.moviecatalogue.core.data.RepositoryImpl
import com.mfathurz.moviecatalogue.core.data.source.local.LocalDataSource
import com.mfathurz.moviecatalogue.core.data.source.local.room.MovieDatabase
import com.mfathurz.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.mfathurz.moviecatalogue.core.data.source.remote.api.ApiConfig
import com.mfathurz.moviecatalogue.core.domain.usecase.MovieUseCase
import com.mfathurz.moviecatalogue.core.domain.usecase.MovieUseCaseImpl
import com.mfathurz.moviecatalogue.core.domain.usecase.TVShowUseCase
import com.mfathurz.moviecatalogue.core.domain.usecase.TVShowUseCaseImpl
import com.mfathurz.moviecatalogue.core.utils.JsonHelper

object Injection {
    private fun provideRepository(context: Context): RepositoryImpl {
        val database = MovieDatabase.getInstance(context)

        val localDataSource = LocalDataSource.getInstance(database.movieDao())

        val genreDataSource =
            RemoteDataSource.getInstance(JsonHelper(context), ApiConfig.getApiService())

        return RepositoryImpl.getInstance(genreDataSource, localDataSource)
    }

    fun provideMovieUseCase(context: Context): MovieUseCase {
        val repository = provideRepository(context)
        return MovieUseCaseImpl(repository)
    }

    fun provideTVShowUseCase(context: Context): TVShowUseCase {
        val repository = provideRepository(context)
        return TVShowUseCaseImpl(repository)
    }
}