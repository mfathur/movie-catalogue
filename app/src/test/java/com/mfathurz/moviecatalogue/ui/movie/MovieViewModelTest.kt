package com.mfathurz.moviecatalogue.ui.movie

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @Before
    fun init() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getAllMovies() {
        val listMovies = viewModel.getAllMovies()
        assertNotNull(listMovies)
        assertEquals(10, listMovies.size)
    }
}