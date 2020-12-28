package com.mfathurz.moviecatalogue.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.core.data.source.remote.model.GenreItem
import com.mfathurz.moviecatalogue.core.domain.model.TVShow

interface TVShowUseCase {
    suspend fun getPopularTVShows(): List<TVShow>?

    suspend fun insertFavoriteTVShow(tvShow: TVShow)

    suspend fun deleteFavoriteTVShow(tvShow: TVShow)

    fun getAllFavoriteTVShow(): List<TVShow>

    fun getPagedFavoriteTVShows(): LiveData<PagedList<TVShow>>

    fun getTVShowGenres(): List<GenreItem>
}