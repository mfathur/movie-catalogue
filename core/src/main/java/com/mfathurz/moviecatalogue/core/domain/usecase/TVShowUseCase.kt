package com.mfathurz.moviecatalogue.core.domain.usecase

import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.core.data.Resource
import com.mfathurz.moviecatalogue.core.data.source.remote.model.GenreItem
import com.mfathurz.moviecatalogue.core.domain.model.TVShow
import io.reactivex.Flowable

interface TVShowUseCase {
    fun getPopularTVShows(): Flowable<Resource<List<TVShow>>>

    fun insertFavoriteTVShow(tvShow: TVShow)

    fun deleteFavoriteTVShow(tvShow: TVShow)

    fun getAllFavoriteTVShow(): List<TVShow>

    fun getPagedFavoriteTVShows(): Flowable<PagedList<TVShow>>

    fun getTVShowGenres(): List<GenreItem>
}