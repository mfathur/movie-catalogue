package com.mfathurz.moviecatalogue.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.data.local.room.entity.MovieEntity
import com.mfathurz.moviecatalogue.db.MovieRepository
import com.mfathurz.moviecatalogue.util.LifeCycleTestOwner
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: FavoriteMovieViewModel
    private lateinit var lifeCycleTestOwner: LifeCycleTestOwner

    private var movieRepository: MovieRepository = mock()
    private var observer: Observer<PagedList<MovieEntity>> = mock()
    private var pagedList: PagedList<MovieEntity> = mock()

    @Before
    fun setUp() {
        viewModel = FavoriteMovieViewModel(movieRepository)
    }

    @Test
    fun getFavoriteMovies() {
        val dummyFavoriteMovies = pagedList

        `when`(dummyFavoriteMovies.size).thenReturn(6)
        val favoriteMovies = MutableLiveData<PagedList<MovieEntity>>()
        favoriteMovies.value = dummyFavoriteMovies

        `when`(movieRepository.getPagedFavoriteMovies()).thenReturn(favoriteMovies)
        val movieEntities = viewModel.favoriteMovies.value
        verify(movieRepository).getPagedFavoriteMovies()

        assertNotNull(movieEntities)
        assertEquals(6, movieEntities?.size)

        viewModel.favoriteMovies.observeForever(observer)
        verify(observer).onChanged(dummyFavoriteMovies)
    }
}