package com.mfathurz.moviecatalogue.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mfathurz.moviecatalogue.core.data.RepositoryImpl
import com.mfathurz.moviecatalogue.di.Injection
import com.mfathurz.moviecatalogue.ui.detail.DetailViewModel
import com.mfathurz.moviecatalogue.ui.favorite.movie.FavoriteMovieViewModel
import com.mfathurz.moviecatalogue.ui.favorite.tv.FavoriteTVShowViewModel
import com.mfathurz.moviecatalogue.ui.movie.MovieViewModel
import com.mfathurz.moviecatalogue.ui.tv.TVShowViewModel

class ViewModelFactory private constructor(private val movieRepository: RepositoryImpl) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(movieRepository) as T
            }

            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(movieRepository) as T
            }

            modelClass.isAssignableFrom(TVShowViewModel::class.java) -> {
                TVShowViewModel(movieRepository) as T
            }

            modelClass.isAssignableFrom(FavoriteTVShowViewModel::class.java) -> {
                FavoriteTVShowViewModel(movieRepository) as T
            }

            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java) -> {
                FavoriteMovieViewModel(movieRepository) as T
            }

            else -> throw  Throwable("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}