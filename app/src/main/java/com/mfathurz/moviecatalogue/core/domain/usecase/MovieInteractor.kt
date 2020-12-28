package com.mfathurz.moviecatalogue.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.core.data.source.remote.model.GenreItem
import com.mfathurz.moviecatalogue.core.domain.model.Movie
import com.mfathurz.moviecatalogue.core.domain.repository.IRepository

class MovieInteractor(private val repository: IRepository) : MovieUseCase {

    override suspend fun getPopularMovies(): List<Movie>? {
        return repository.getPopularMovies()
    }

    override suspend fun insertFavoriteMovie(movie: Movie) {
        return repository.insertFavoriteMovie(movie)
    }

    override suspend fun deleteFavoriteMovie(movie: Movie) {
        return repository.deleteFavoriteMovie(movie)
    }

    override fun getPagedFavoriteMovies(): LiveData<PagedList<Movie>> {
        return repository.getPagedFavoriteMovies()
    }

    override fun getAllFavoriteMovies(): List<Movie> {
        return repository.getAllFavoriteMovies()
    }

    override fun getMovieGenres(): List<GenreItem> {
        return repository.getMovieGenres()
    }
}