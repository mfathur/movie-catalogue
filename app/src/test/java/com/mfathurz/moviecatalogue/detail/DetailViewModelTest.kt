package com.mfathurz.moviecatalogue.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mfathurz.moviecatalogue.core.domain.usecase.MovieUseCase
import com.mfathurz.moviecatalogue.core.domain.usecase.TVShowUseCase
import com.mfathurz.moviecatalogue.db.FakeDataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DetailViewModel
    private var dummyMovie = FakeDataDummy.generateFavoriteDummyMovies()[0]
    private var dummyTVShow = FakeDataDummy.generateFavoriteDummyTVShows()[0]
    private var dummyTVShowGenres = FakeDataDummy.generateDummyTVShowGenres()
    private var dummyMovieGenres = FakeDataDummy.generateDummyMovieGenres()

    @Mock
    private lateinit var tvShowUseCase: TVShowUseCase

    @Mock
    private lateinit var movieUseCase: MovieUseCase

    @Before
    fun init() {
        viewModel = DetailViewModel(tvShowUseCase, movieUseCase)
        viewModel.setMovieData(dummyMovie)
        viewModel.setTVShowData(dummyTVShow)
    }

    @Test
    fun getMovieData() {
        val movie = viewModel.getMovieData()
        assertNotNull(movie)
        assertEquals(dummyMovie.posterPath, movie.posterPath)
        assertEquals(dummyMovie.title, movie.title)
        assertEquals(dummyMovie.genreIds, movie.genreIds)
        assertEquals(dummyMovie.releaseDate, movie.releaseDate)
        assertEquals(dummyMovie.backdropPath, movie.backdropPath)
        assertEquals(dummyMovie.originalLanguage, movie.originalLanguage)
        assertEquals(dummyMovie.id, movie.id)
        assertEquals(dummyMovie.overview, movie.overview)
        assertEquals(dummyMovie.popularity, movie.popularity, 0.0001)
        assertEquals(dummyMovie.voteAverage, movie.voteAverage, 0.0001)
        assertEquals(dummyMovie.voteCount, movie.voteCount)
        assertEquals(dummyMovie.originalTitle, movie.originalTitle)
    }

    @Test
    fun getTVShowData() {
        val tvShow = viewModel.getTVShowData()
        assertNotNull(tvShow)
        assertEquals(dummyTVShow.posterPath, tvShow.posterPath)
        assertEquals(dummyTVShow.firstAirDate, tvShow.firstAirDate)
        assertEquals(dummyTVShow.backdropPath, tvShow.backdropPath)
        assertEquals(dummyTVShow.name, tvShow.name)
        assertEquals(dummyTVShow.originCountry, tvShow.originCountry)
        assertEquals(dummyTVShow.originalLanguage, tvShow.originalLanguage)
        assertEquals(dummyTVShow.originalName, tvShow.originalName)
        assertEquals(dummyTVShow.overview, tvShow.overview)
        assertEquals(dummyTVShow.genreIds, tvShow.genreIds)
        assertEquals(dummyTVShow.popularity, tvShow.popularity, 0.0001)
        assertEquals(dummyTVShow.voteCount, tvShow.voteCount)
        assertEquals(dummyTVShow.id, tvShow.id)
        assertEquals(dummyTVShow.voteAverage, tvShow.voteAverage, 0.0001)
    }

    @Test
    fun getMovieGenres() {
        val listMovieGenres = dummyMovieGenres
        `when`(movieUseCase.getMovieGenres()).thenReturn(listMovieGenres)
        val movieGenres = viewModel.getMovieGenres()
        verify(movieUseCase).getMovieGenres()
        assertNotNull(movieGenres)
        assertEquals(1, movieGenres.size)
    }

    @Test
    fun getTVShowGenres() {
        val listTVShowGenres = dummyTVShowGenres
        `when`(tvShowUseCase.getTVShowGenres()).thenReturn(listTVShowGenres)
        val tvShowGenres = viewModel.getTVShowGenres()
        verify(tvShowUseCase).getTVShowGenres()
        assertNotNull(tvShowGenres)
        assertEquals(1, tvShowGenres.size)
    }
}