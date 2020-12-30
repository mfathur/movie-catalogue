package com.mfathurz.moviecatalogue.ui.movie

import androidx.lifecycle.*
import com.mfathurz.moviecatalogue.core.data.Resource
import com.mfathurz.moviecatalogue.core.domain.model.Movie
import com.mfathurz.moviecatalogue.core.domain.usecase.MovieUseCase

class MovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    val popularMovies: LiveData<Resource<List<Movie>>>
        get() = LiveDataReactiveStreams.fromPublisher(movieUseCase.getPopularMovies())
}