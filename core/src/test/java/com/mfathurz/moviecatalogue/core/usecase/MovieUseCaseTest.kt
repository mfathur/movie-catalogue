package com.mfathurz.moviecatalogue.core.usecase

import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.core.data.FakeDataDummy
import com.mfathurz.moviecatalogue.core.domain.model.Movie
import com.mfathurz.moviecatalogue.core.domain.repository.IRepository
import com.mfathurz.moviecatalogue.core.domain.usecase.MovieUseCase
import com.mfathurz.moviecatalogue.core.domain.usecase.MovieUseCaseImpl
import io.reactivex.Flowable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieUseCaseTest {

    private lateinit var movieUseCase: MovieUseCase

    @Mock
    private lateinit var repository: IRepository

    @Mock
    private lateinit var dummyPagedMovies : PagedList<Movie>

    private val dummyMovieGenres = FakeDataDummy.generateDummyMovieGenres()
    private val dummyFavoriteMovies = FakeDataDummy.generateDummyFavoriteMovies()
    private val dummyMovies = FakeDataDummy.generateDummyMovies()


    @Before
    fun setUp() {
        movieUseCase = MovieUseCaseImpl(repository)
    }

    @Test
    fun `should get movie genres from repository`() {
        `when`(repository.getMovieGenres()).thenReturn(dummyMovieGenres)
        val movieGenres = movieUseCase.getMovieGenres()
        verify(repository).getMovieGenres()
        verifyNoMoreInteractions(repository)
        assertNotNull(movieGenres)
        assertEquals(1, movieGenres.size)
    }

    @Test
    fun `should get favorite movies from repository`() {
        `when`(repository.getAllFavoriteMovies()).thenReturn(dummyFavoriteMovies)
        val favMovies = movieUseCase.getAllFavoriteMovies()
        verify(repository).getAllFavoriteMovies()
        verifyNoMoreInteractions(repository)
        assertNotNull(favMovies)
        assertEquals(1, favMovies.size)
    }

    @Test
    fun `should get popular movies from repository`() {
        `when`(repository.getPopularMovies()).thenReturn(Flowable.just(dummyMovies))
        val popularMovies = movieUseCase.getPopularMovies()
        verify(repository).getPopularMovies()
        verifyNoMoreInteractions(repository)
        assertNotNull(popularMovies)
    }

    @Test
    fun `should get paged favorite movies from repository`() {
        val pagedListFavoriteMovies = Flowable.just(dummyPagedMovies)
        `when`(repository.getPagedFavoriteMovies()).thenReturn(pagedListFavoriteMovies)
        val pagedData = movieUseCase.getPagedFavoriteMovies()
        verify(repository).getPagedFavoriteMovies()
        verifyNoMoreInteractions(repository)
        assertNotNull(pagedData)
    }
}