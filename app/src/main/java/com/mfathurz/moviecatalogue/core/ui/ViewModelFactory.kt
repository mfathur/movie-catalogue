package com.mfathurz.moviecatalogue.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mfathurz.moviecatalogue.core.domain.usecase.MovieUseCase
import com.mfathurz.moviecatalogue.core.domain.usecase.TVShowUseCase
import com.mfathurz.moviecatalogue.di.Injection
import com.mfathurz.moviecatalogue.ui.detail.DetailViewModel
import com.mfathurz.moviecatalogue.ui.favorite.movie.FavoriteMovieViewModel
import com.mfathurz.moviecatalogue.ui.favorite.tv.FavoriteTVShowViewModel
import com.mfathurz.moviecatalogue.ui.movie.MovieViewModel
import com.mfathurz.moviecatalogue.ui.tv.TVShowViewModel

class ViewModelFactory private constructor(
    private val movieUseCase: MovieUseCase,
    private val tvShowUseCase: TVShowUseCase
) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideMovieUseCase(context),
                    Injection.provideTVShowUseCase(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(tvShowUseCase, movieUseCase) as T
            }

            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(movieUseCase) as T
            }

            modelClass.isAssignableFrom(TVShowViewModel::class.java) -> {
                TVShowViewModel(tvShowUseCase) as T
            }

            modelClass.isAssignableFrom(FavoriteTVShowViewModel::class.java) -> {
                FavoriteTVShowViewModel(tvShowUseCase) as T
            }

            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java) -> {
                FavoriteMovieViewModel(movieUseCase) as T
            }

            else -> throw  Throwable("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}