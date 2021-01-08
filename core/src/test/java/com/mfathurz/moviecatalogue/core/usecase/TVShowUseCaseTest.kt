package com.mfathurz.moviecatalogue.core.usecase

import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.core.data.FakeDataDummy
import com.mfathurz.moviecatalogue.core.domain.model.TVShow
import com.mfathurz.moviecatalogue.core.domain.repository.IRepository
import com.mfathurz.moviecatalogue.core.domain.usecase.TVShowUseCase
import com.mfathurz.moviecatalogue.core.domain.usecase.TVShowUseCaseImpl
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
class TVShowUseCaseTest {

    private lateinit var tvShowUseCase: TVShowUseCase

    @Mock
    private lateinit var repository: IRepository

    @Mock
    private lateinit var pagedFavTVShow : PagedList<TVShow>

    private val dummyTvShowGenres = FakeDataDummy.generateDummyTVShowGenres()
    private val dummyTvShow = FakeDataDummy.generateDummyTVShows()
    private val dummyFavoriteTvShow = FakeDataDummy.generateDummyFavoriteTVShows()

    @Before
    fun setUp() {
        tvShowUseCase = TVShowUseCaseImpl(repository)
    }

    @Test
    fun `should get tv show genres from repository`() {
        `when`(repository.getTVShowGenres()).thenReturn(dummyTvShowGenres)
        val tvShowGenres = tvShowUseCase.getTVShowGenres()
        verify(repository).getTVShowGenres()
        verifyNoMoreInteractions(repository)
        assertNotNull(tvShowGenres)
        assertEquals(1, tvShowGenres.size)
    }

    @Test
    fun `should get popular tv show from repository`() {
        `when`(repository.getPopularTVShows()).thenReturn(Flowable.just(dummyTvShow))
        val popularTvShows = tvShowUseCase.getPopularTVShows()
        verify(repository).getPopularTVShows()
        verifyNoMoreInteractions(repository)
        assertNotNull(popularTvShows)
    }

    @Test
    fun `should get favorite tv show from repository`() {
        `when`(repository.getAllFavoriteTVShow()).thenReturn(dummyFavoriteTvShow)
        val favoriteTvShows = tvShowUseCase.getAllFavoriteTVShow()
        verify(repository).getAllFavoriteTVShow()
        verifyNoMoreInteractions(repository)
        assertNotNull(favoriteTvShows)
        assertEquals(1, dummyFavoriteTvShow.size)
    }

    @Test
    fun `should get paged favorite tv show from repository`() {
        val dummyPagedFavTvShow = pagedFavTVShow

        val pagedFavTvShow = Flowable.just(dummyPagedFavTvShow)
        `when`(repository.getPagedFavoriteTVShows()).thenReturn(pagedFavTvShow)
        val pagedData = tvShowUseCase.getPagedFavoriteTVShows()
        verify(repository).getPagedFavoriteTVShows()
        verifyNoMoreInteractions(repository)
        assertNotNull(pagedData)
    }
}