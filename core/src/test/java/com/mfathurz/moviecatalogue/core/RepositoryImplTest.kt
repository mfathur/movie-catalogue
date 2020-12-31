package com.mfathurz.moviecatalogue.core

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.mfathurz.moviecatalogue.core.data.RepositoryImpl
import com.mfathurz.moviecatalogue.core.data.source.local.LocalDataSource
import com.mfathurz.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.mfathurz.moviecatalogue.core.data.source.local.entity.TVShowEntity
import com.mfathurz.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.mfathurz.moviecatalogue.core.data.source.remote.api.ApiService
import com.mfathurz.moviecatalogue.core.di.databaseModule
import com.mfathurz.moviecatalogue.core.di.networkModule
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class RepositoryImplTest : KoinTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repositoryImpl: RepositoryImpl by inject()

    private val dummyMovieGenres = FakeDataDummy.generateDummyMovieGenres()
    private val dummyTVShowGenres = FakeDataDummy.generateDummyTVShowGenres()
    private val dummyFavoriteMovies = FakeDataDummy.generateDummyFavoriteMovies()
    private val dummyFavoriteTVShows = FakeDataDummy.generateDummyFavoriteTVShows()

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        loadKoinModules(
            listOf(
                databaseModule,
                networkModule
            )
        )
        mockWebServer = MockWebServer()
        mockWebServer.start()

        apiService = Utils.getFakeApiServiceInstance(mockWebServer, "https://api.themoviedb.org/3/")
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        stopKoin()
    }


    @Test
    fun getMovieGenres() {
        `when`(get<RemoteDataSource>().getAllMovieGenres()).thenReturn(dummyMovieGenres)
        val listMovieGenres = repositoryImpl.getMovieGenres()
        verify(get<RemoteDataSource>()).getAllMovieGenres()
        assertNotNull(listMovieGenres)
        assertEquals(1, listMovieGenres.size)
    }

    @Test
    fun getTVShowGenres() {
        `when`(get<RemoteDataSource>().getAllTVShowGenres()).thenReturn(dummyTVShowGenres)
        val listTVShowGenre = repositoryImpl.getTVShowGenres()
        verify(get<RemoteDataSource>()).getAllTVShowGenres()
        assertNotNull(listTVShowGenre)
        assertEquals(1, listTVShowGenre.size)
    }

    @Test
    fun getAllFavoriteMovies() {
        `when`(get<LocalDataSource>().queryAllFavoriteMovies()).thenReturn(dummyFavoriteMovies)
        val favoriteMovies = repositoryImpl.getAllFavoriteMovies()
        verify(get<LocalDataSource>()).queryAllFavoriteMovies()
        assertNotNull(favoriteMovies)
        assertEquals(1, favoriteMovies.size)
    }

    @Test
    fun getAllFavoriteTVShows() {
        `when`(get<LocalDataSource>().queryAllFavoriteTVShow()).thenReturn(dummyFavoriteTVShows)
        val favoriteTVShows = repositoryImpl.getAllFavoriteTVShow()
        verify(get<LocalDataSource>()).queryAllFavoriteTVShow()
        assertNotNull(favoriteTVShows)
        assertEquals(1, favoriteTVShows.size)
    }

    @Test
    fun getPagedFavoriteMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(get<LocalDataSource>().queryAllDataSourceFavoriteMovies()).thenReturn(
            dataSourceFactory
        )
        repositoryImpl.getPagedFavoriteMovies()
        val favoriteMovies = PagedListUtil.mockPagedList(dummyFavoriteMovies)
        verify(get<LocalDataSource>()).queryAllDataSourceFavoriteMovies()
        assertNotNull(favoriteMovies)
        assertEquals(1, favoriteMovies.size)
    }

    @Test
    fun getPagedFavoriteTVShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVShowEntity>
        `when`(get<LocalDataSource>().queryAllDataSourceFavoriteTVShows()).thenReturn(
            dataSourceFactory
        )
        repositoryImpl.getPagedFavoriteTVShows()
        val favoriteTVShow = PagedListUtil.mockPagedList(dummyFavoriteTVShows)
        verify(get<LocalDataSource>()).queryAllDataSourceFavoriteTVShows()
        assertNotNull(favoriteTVShow)
        assertEquals(1, favoriteTVShow.size)
    }
}