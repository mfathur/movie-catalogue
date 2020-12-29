package com.mfathurz.moviecatalogue.core.domain.usecase

import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.core.Resource
import com.mfathurz.moviecatalogue.core.data.source.remote.model.GenreItem
import com.mfathurz.moviecatalogue.core.domain.model.TVShow
import com.mfathurz.moviecatalogue.core.domain.repository.IRepository
import io.reactivex.Flowable

class TVShowUseCaseImpl(private val repository: IRepository) : TVShowUseCase {

    override fun getPopularTVShows(): Flowable<Resource<List<TVShow>>> {
        return repository.getPopularTVShows()
    }

    override fun insertFavoriteTVShow(tvShow: TVShow) {
        return repository.insertFavoriteTVShow(tvShow)
    }

    override fun deleteFavoriteTVShow(tvShow: TVShow) {
        return repository.deleteFavoriteTVShow(tvShow)
    }

    override fun getAllFavoriteTVShow(): List<TVShow> {
        return repository.getAllFavoriteTVShow()
    }

    override fun getPagedFavoriteTVShows(): Flowable<PagedList<TVShow>> {
        return repository.getPagedFavoriteTVShows()
    }

    override fun getTVShowGenres(): List<GenreItem> {
        return repository.getTVShowGenres()
    }

}