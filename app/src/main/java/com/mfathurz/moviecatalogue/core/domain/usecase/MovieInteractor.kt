package com.mfathurz.moviecatalogue.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.mfathurz.moviecatalogue.core.data.source.remote.model.GenreItem
import com.mfathurz.moviecatalogue.core.data.source.remote.model.MovieResultsItem
import com.mfathurz.moviecatalogue.core.domain.repository.IRepository

class MovieInteractor(private val repository: IRepository) : MovieUseCase {

    override suspend fun getPopularMovies(): List<MovieResultsItem>? {
        return repository.getPopularMovies()
    }

    override suspend fun insertFavoriteMovie(movie: MovieEntity) {
        return repository.insertFavoriteMovie(movie)
    }

    override suspend fun deleteFavoriteMovie(movie: MovieEntity) {
        return repository.deleteFavoriteMovie(movie)
    }

    override fun getPagedFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        return repository.getPagedFavoriteMovies()
    }

    override fun getAllFavoriteMovies(): List<MovieEntity> {
        return repository.getAllFavoriteMovies()
    }

    override fun getMovieGenres(): List<GenreItem> {
        return repository.getMovieGenres()
    }
}