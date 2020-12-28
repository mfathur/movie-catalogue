package com.mfathurz.moviecatalogue.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.core.data.source.remote.model.GenreItem
import com.mfathurz.moviecatalogue.core.domain.model.TVShow
import com.mfathurz.moviecatalogue.core.domain.repository.IRepository

class TVShowInteractor(private val repository: IRepository) : TVShowUseCase {

    override suspend fun getPopularTVShows(): List<TVShow>? {
        return repository.getPopularTVShows()
    }

    override suspend fun insertFavoriteTVShow(tvShow: TVShow) {
        return repository.insertFavoriteTVShow(tvShow)
    }

    override suspend fun deleteFavoriteTVShow(tvShow: TVShow) {
        return repository.deleteFavoriteTVShow(tvShow)
    }

    override fun getAllFavoriteTVShow(): List<TVShow> {
        return repository.getAllFavoriteTVShow()
    }

    override fun getPagedFavoriteTVShows(): LiveData<PagedList<TVShow>> {
        return repository.getPagedFavoriteTVShows()
    }

    override fun getTVShowGenres(): List<GenreItem> {
        return repository.getTVShowGenres()
    }

}