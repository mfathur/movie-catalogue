package com.mfathurz.moviecatalogue.ui.detail

import com.mfathurz.moviecatalogue.db.local.DataMovie
import com.mfathurz.moviecatalogue.db.local.DataTVShow
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private var dummyMovies = DataMovie.queryAllMovies()[0]
    private var dummyTVShow = DataTVShow.queryAllTVShows()[0]

    @Before
    fun init() {
        viewModel = DetailViewModel()
        viewModel.setMovieData(dummyMovies)
        viewModel.setTVShowData(dummyTVShow)
    }

    @Test
    fun getMovieData() {
        val movie = viewModel.getMovieData()
        assertNotNull(movie)
        assertEquals(dummyMovies.imageUrl, movie.imageUrl)
        assertEquals(dummyMovies.title, movie.title)
        assertEquals(dummyMovies.category, movie.category)
        assertEquals(dummyMovies.releaseDate, movie.releaseDate)
        assertEquals(dummyMovies.director, movie.director)
        assertEquals(dummyMovies.language, movie.language)
        assertEquals(dummyMovies.time, movie.time)
        assertEquals(dummyMovies.overview, movie.overview)
        assertEquals(dummyMovies.status, movie.status)
        assertEquals(dummyMovies.casters, movie.casters)
    }

    @Test
    fun getTVShowData() {
        val tvShow = viewModel.getTVShowData()
        assertNotNull(tvShow)
        assertEquals(dummyTVShow.imageUrl, tvShow.imageUrl)
        assertEquals(dummyTVShow.title, tvShow.title)
        assertEquals(dummyTVShow.category, tvShow.category)
        assertEquals(dummyTVShow.releaseDate, tvShow.releaseDate)
        assertEquals(dummyTVShow.creator, tvShow.creator)
        assertEquals(dummyTVShow.language, tvShow.language)
        assertEquals(dummyTVShow.time, tvShow.time)
        assertEquals(dummyTVShow.overview, tvShow.overview)
        assertEquals(dummyTVShow.status, tvShow.status)
        assertEquals(dummyTVShow.casters, tvShow.casters)
    }
}