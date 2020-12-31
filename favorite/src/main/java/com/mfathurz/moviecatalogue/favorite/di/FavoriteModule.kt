package com.mfathurz.moviecatalogue.favorite.di

import com.mfathurz.moviecatalogue.favorite.movie.FavoriteMovieViewModel
import com.mfathurz.moviecatalogue.favorite.tv.FavoriteTVShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteMovieViewModel(get()) }
    viewModel { FavoriteTVShowViewModel(get()) }
}