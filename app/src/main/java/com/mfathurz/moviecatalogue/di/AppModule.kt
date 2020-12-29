package com.mfathurz.moviecatalogue.di

import com.mfathurz.moviecatalogue.core.domain.usecase.MovieUseCase
import com.mfathurz.moviecatalogue.core.domain.usecase.MovieUseCaseImpl
import com.mfathurz.moviecatalogue.core.domain.usecase.TVShowUseCase
import com.mfathurz.moviecatalogue.core.domain.usecase.TVShowUseCaseImpl
import com.mfathurz.moviecatalogue.ui.detail.DetailViewModel
import com.mfathurz.moviecatalogue.ui.favorite.movie.FavoriteMovieViewModel
import com.mfathurz.moviecatalogue.ui.favorite.tv.FavoriteTVShowViewModel
import com.mfathurz.moviecatalogue.ui.movie.MovieViewModel
import com.mfathurz.moviecatalogue.ui.tv.TVShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieUseCaseImpl(get()) }
    factory<TVShowUseCase> { TVShowUseCaseImpl(get()) }
}

val viewModelModule = module {
    viewModel { DetailViewModel(get(),get()) }
    viewModel { MovieViewModel(get()) }
    viewModel { TVShowViewModel(get()) }
    viewModel { FavoriteMovieViewModel(get()) }
    viewModel { FavoriteTVShowViewModel(get()) }
}