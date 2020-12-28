package com.mfathurz.moviecatalogue.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.core.data.source.local.entity.TVShowEntity
import com.mfathurz.moviecatalogue.core.data.source.remote.model.GenreItem
import com.mfathurz.moviecatalogue.core.data.source.remote.model.TVResultsItem

interface TVShowUseCase {
    suspend fun getPopularTVShows(): List<TVResultsItem>?

    suspend fun insertFavoriteTVShow(tvShow: TVShowEntity)

    suspend fun deleteFavoriteTVShow(tvShow: TVShowEntity)

    fun getAllFavoriteTVShow(): List<TVShowEntity>

    fun getPagedFavoriteTVShows(): LiveData<PagedList<TVShowEntity>>

    fun getTVShowGenres(): List<GenreItem>
}