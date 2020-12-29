package com.mfathurz.moviecatalogue.ui.tv

import androidx.lifecycle.*
import com.mfathurz.moviecatalogue.core.Resource
import com.mfathurz.moviecatalogue.core.domain.model.TVShow
import com.mfathurz.moviecatalogue.core.domain.usecase.TVShowUseCase
import kotlinx.coroutines.launch

class TVShowViewModel(private val tvShowUseCase: TVShowUseCase) : ViewModel() {

    val popularTVShows: LiveData<Resource<List<TVShow>>>
        get() = LiveDataReactiveStreams.fromPublisher(tvShowUseCase.getPopularTVShows())
}