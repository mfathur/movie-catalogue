package com.mfathurz.moviecatalogue.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mfathurz.moviecatalogue.data.remote.model.TVResultsItem
import com.mfathurz.moviecatalogue.db.FakeDataDummy
import com.mfathurz.moviecatalogue.db.MovieRepository
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
class TVShowViewModelTest {

    @get:Rule
    var instantTaskRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    private lateinit var viewModel: TVShowViewModel
    private lateinit var lifeCycleTestOwner: LifeCycleTestOwner

    private var movieRepository: MovieRepository = mock()
    private var observer: Observer<List<TVResultsItem>> = mock()

    private val dummyTVShows = FakeDataDummy.generateDummyTVShows()

    @Before
    fun setUp() {
        lifeCycleTestOwner = LifeCycleTestOwner()
        lifeCycleTestOwner.onCreate()
        viewModel = TVShowViewModel(movieRepository)
        viewModel.popularTVShows.observe(lifeCycleTestOwner, observer)
    }

    @After
    fun tearDown() {
        lifeCycleTestOwner.onDestroy()
    }

    @Test
    fun getPopularTVShows() = coroutineTestRule.runBlockingTest {
        lifeCycleTestOwner.onResume()
        `when`(movieRepository.getPopularTVShows()).thenReturn(dummyTVShows)
        viewModel.getPopularTVShows()
        verify(observer).onChanged(dummyTVShows)
        verifyNoMoreInteractions(observer)
    }
}