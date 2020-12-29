package com.mfathurz.moviecatalogue.core.domain.usecase

import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.core.Resource
import com.mfathurz.moviecatalogue.core.data.source.remote.model.GenreItem
import com.mfathurz.moviecatalogue.core.domain.model.Movie
import com.mfathurz.moviecatalogue.core.domain.repository.IRepository
import io.reactivex.Flowable

class MovieUseCaseImpl(private val repository: IRepository) : MovieUseCase {

    override fun getPopularMovies(): Flowable<Resource<List<Movie>>> {
        return repository.getPopularMovies()
    }

    override fun insertFavoriteMovie(movie: Movie) {
        return repository.insertFavoriteMovie(movie)
    }

    override fun deleteFavoriteMovie(movie: Movie) {
        return repository.deleteFavoriteMovie(movie)
    }

    override fun getPagedFavoriteMovies(): Flowable<PagedList<Movie>> {
        return repository.getPagedFavoriteMovies()
    }

    override fun getAllFavoriteMovies(): List<Movie> {
        return repository.getAllFavoriteMovies()
    }

    override fun getMovieGenres(): List<GenreItem> {
        return repository.getMovieGenres()
    }
}