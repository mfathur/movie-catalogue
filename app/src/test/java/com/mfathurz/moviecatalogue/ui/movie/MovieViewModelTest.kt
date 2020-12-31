package com.mfathurz.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mfathurz.moviecatalogue.data.remote.model.MovieResultsItem
import com.mfathurz.moviecatalogue.db.FakeDataDummy
import com.mfathurz.moviecatalogue.db.MovieRepository
import com.mfathurz.moviecatalogue.home.movie.MovieViewModel
import com.mfathurz.moviecatalogue.util.CoroutineTestRule
import com.mfathurz.moviecatalogue.util.LifeCycleTestOwner
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`


@ExperimentalCoroutinesApi
class MovieViewModelTest {

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @get:Rule
    var instantTaskRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieViewModel
    private lateinit var lifeCycleTestOwner: LifeCycleTestOwner

    private var movieRepository: MovieRepository = mock()
    private var observer: Observer<List<MovieResultsItem>> = mock()

    private val dummyMovies = FakeDataDummy.generateDummyMovies()

    @Before
    fun setUp() {
        lifeCycleTestOwner = LifeCycleTestOwner()
        lifeCycleTestOwner.onCreate()
        viewModel = MovieViewModel(movieRepository)
        viewModel.popularMovies.observe(lifeCycleTestOwner, observer)
    }

    @After
    fun tearDown() {
        lifeCycleTestOwner.onDestroy()
    }

    @Test
    fun getPopularMovies() = coroutineTestRule.runBlockingTest {
        lifeCycleTestOwner.onResume()
        `when`(movieRepository.getPopularMovies()).thenReturn(dummyMovies)
        viewModel.getPopularMovies()
        verify(observer).onChanged(dummyMovies)
        verifyNoMoreInteractions(observer)
    }
}
