package com.mfathurz.moviecatalogue.home.tv

import androidx.lifecycle.*
import com.mfathurz.moviecatalogue.core.data.Resource
import com.mfathurz.moviecatalogue.core.domain.model.TVShow
import com.mfathurz.moviecatalogue.core.domain.usecase.TVShowUseCase

class TVShowViewModel(private val tvShowUseCase: TVShowUseCase) : ViewModel() {
    val popularTVShows: LiveData<Resource<List<TVShow>>>
        get() = LiveDataReactiveStreams.fromPublisher(tvShowUseCase.getPopularTVShows())
}