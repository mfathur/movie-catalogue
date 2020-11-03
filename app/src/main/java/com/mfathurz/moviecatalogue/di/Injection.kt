package com.mfathurz.moviecatalogue.di

import com.mfathurz.moviecatalogue.db.MovieRepository

object Injection {
    fun provideRepository() : MovieRepository{
        return MovieRepository.getInstance()
    }
}