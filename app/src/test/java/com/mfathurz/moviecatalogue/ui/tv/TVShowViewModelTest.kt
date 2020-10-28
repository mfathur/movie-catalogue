package com.mfathurz.moviecatalogue.ui.tv

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class TVShowViewModelTest {

    private lateinit var viewModel: TVShowViewModel

    @Before
    fun init() {
        viewModel = TVShowViewModel()
    }

    @Test
    fun getAllTVShows() {
        val listTVShow = viewModel.getAllTVShows()
        assertNotNull(listTVShow)
        assertEquals(10, listTVShow.size)
    }
}