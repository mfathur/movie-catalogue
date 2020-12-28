package com.mfathurz.moviecatalogue.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.core.data.source.local.entity.TVShowEntity
import com.mfathurz.moviecatalogue.core.data.source.remote.model.GenreItem
import com.mfathurz.moviecatalogue.core.data.source.remote.model.TVResultsItem
import com.mfathurz.moviecatalogue.core.domain.repository.IRepository

class TVShowInteractor(private val repository: IRepository) : TVShowUseCase {

    override suspend fun getPopularTVShows(): List<TVResultsItem>? {
        return repository.getPopularTVShows()
    }

    override suspend fun insertFavoriteTVShow(tvShow: TVShowEntity) {
        return repository.insertFavoriteTVShow(tvShow)
    }

    override suspend fun deleteFavoriteTVShow(tvShow: TVShowEntity) {
        return repository.deleteFavoriteTVShow(tvShow)
    }

    override fun getAllFavoriteTVShow(): List<TVShowEntity> {
        return repository.getAllFavoriteTVShow()
    }

    override fun getPagedFavoriteTVShows(): LiveData<PagedList<TVShowEntity>> {
        return repository.getPagedFavoriteTVShows()
    }

    override fun getTVShowGenres(): List<GenreItem> {
        return repository.getTVShowGenres()
    }

}