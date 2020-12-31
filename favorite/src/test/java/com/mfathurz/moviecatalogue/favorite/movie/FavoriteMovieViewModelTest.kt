package com.mfathurz.moviecatalogue.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.core.data.RepositoryImpl
import com.mfathurz.moviecatalogue.core.domain.model.Movie
import com.mfathurz.moviecatalogue.util.LifeCycleTestOwner
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieViewModelTest : KoinTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: FavoriteMovieViewModel
    private lateinit var lifeCycleTestOwner: LifeCycleTestOwner

    private val repositoryImpl : RepositoryImpl by inject()
    private var observer: Observer<PagedList<Movie>> = mock()
    private var pagedList: PagedList<Movie> = mock()

    @Before
    fun setUp() {

        viewModel =
            FavoriteMovieViewModel()
    }

    @After
    fun tearDown(){
        stopKoin()
    }

    @Test
    fun getFavoriteMovies() {
        val dummyFavoriteMovies = pagedList

        `when`(dummyFavoriteMovies.size).thenReturn(6)
        val favoriteMovies = MutableLiveData<PagedList<Movie>>()
        favoriteMovies.value = dummyFavoriteMovies

        `when`(repositoryImpl.getPagedFavoriteMovies()).thenReturn(favoriteMovies)
        val movieEntities = viewModel.favoriteMovies.value
        verify(repositoryImpl).getPagedFavoriteMovies()

        assertNotNull(movieEntities)
        assertEquals(6, movieEntities?.size)

        viewModel.favoriteMovies.observeForever(observer)
        verify(observer).onChanged(dummyFavoriteMovies)
    }
}