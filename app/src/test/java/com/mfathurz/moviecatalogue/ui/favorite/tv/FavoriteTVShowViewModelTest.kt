package com.mfathurz.moviecatalogue.ui.favorite.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.data.local.room.entity.TVShowEntity
import com.mfathurz.moviecatalogue.db.MovieRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`

class FavoriteTVShowViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: FavoriteTVShowViewModel

    private var movieRepository: MovieRepository = mock()
    private var observer: Observer<PagedList<TVShowEntity>> = mock()
    private var pagedList: PagedList<TVShowEntity> = mock()

    @Before
    fun setUp() {
        viewModel = FavoriteTVShowViewModel(movieRepository)
    }


    @Test
    fun getFavoriteTVShows() {
        val dummyFavoriteTVShows = pagedList

        `when`(dummyFavoriteTVShows.size).thenReturn(6)
        val favoriteTVShows = MutableLiveData<PagedList<TVShowEntity>>()
        favoriteTVShows.value = dummyFavoriteTVShows

        `when`(movieRepository.getPagedFavoriteTVShows()).thenReturn(favoriteTVShows)
        val tvShowEntities = viewModel.favoriteTVShows.value
        verify(movieRepository).getPagedFavoriteTVShows()
        assertNotNull(tvShowEntities)
        assertEquals(6, tvShowEntities?.size)

        viewModel.favoriteTVShows.observeForever(observer)
        verify(observer).onChanged(dummyFavoriteTVShows)
    }

}