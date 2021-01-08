package com.mfathurz.moviecatalogue.core

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mfathurz.moviecatalogue.core.data.FakeDataDummy
import com.mfathurz.moviecatalogue.core.data.RepositoryImpl
import com.mfathurz.moviecatalogue.core.data.source.local.LocalDataSource
import com.mfathurz.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.mfathurz.moviecatalogue.core.data.source.remote.api.ApiService
import com.mfathurz.moviecatalogue.core.util.Utils
import com.mfathurz.moviecatalogue.core.utils.DataMapper
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
class RepositoryImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var localDataSource: LocalDataSource

    private lateinit var repositoryImpl: RepositoryImpl

    private val dummyMovieGenres = FakeDataDummy.generateDummyMovieGenres()
    private val dummyTVShowGenres = FakeDataDummy.generateDummyTVShowGenres()
    private var dummyFavoriteMovies = FakeDataDummy.generateDummyFavoriteMovies()
    private val dummyFavoriteTVShows = FakeDataDummy.generateDummyFavoriteTVShows()

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        repositoryImpl = RepositoryImpl(remoteDataSource, localDataSource)
        mockWebServer = MockWebServer()
        mockWebServer.start()

        apiService = Utils.getFakeApiServiceInstance(mockWebServer, "https://api.themoviedb.org/3/")
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should get movie genres from json file`() {
        `when`(remoteDataSource.getAllMovieGenres()).thenReturn(dummyMovieGenres)
        val listMovieGenres = repositoryImpl.getMovieGenres()
        verify(remoteDataSource).getAllMovieGenres()
        assertNotNull(listMovieGenres)
        assertEquals(1, listMovieGenres.size)
    }

    @Test
    fun `should get tv show genres from json file`() {
        `when`(remoteDataSource.getAllTVShowGenres()).thenReturn(dummyTVShowGenres)
        val listTVShowGenre = repositoryImpl.getTVShowGenres()
        verify(remoteDataSource).getAllTVShowGenres()
        assertNotNull(listTVShowGenre)
        assertEquals(1, listTVShowGenre.size)
    }

    @Test
    fun `should get all favorite movies from database`() {
        `when`(localDataSource.queryAllFavoriteMovies()).thenReturn(
            listOf(
                DataMapper.mapMovieDomainToEntity(
                    dummyFavoriteMovies[0]
                )
            )
        )
        val favoriteMovies = repositoryImpl.getAllFavoriteMovies()
        verify(localDataSource).queryAllFavoriteMovies()
        assertNotNull(favoriteMovies)
        assertEquals(1, favoriteMovies.size)
    }

    @Test
    fun `should get all favorite tv shows from database`() {
        `when`(localDataSource.queryAllFavoriteTVShow()).thenReturn(
            listOf(
                DataMapper.mapTVShowDomainToEntity(
                    dummyFavoriteTVShows[0]
                )
            )
        )
        val favoriteTvShow = repositoryImpl.getAllFavoriteTVShow()
        verify(localDataSource).queryAllFavoriteTVShow()
        assertNotNull(favoriteTvShow)
        assertEquals(1, favoriteTvShow.size)
    }

    @Test
    fun `should get popular movies from network`() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(Utils.readTestResourceFile("dummy_popular_movies_response.json"))
        mockWebServer.enqueue(response)
        apiService.queryPopularMovies().test().assertNoErrors().assertComplete()
    }

    @Test
    fun `should get popular tv shows from network`() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(Utils.readTestResourceFile("dummy_popular_tv_shows_response.json"))

        mockWebServer.enqueue(response)
        apiService.queryPopularTVShows().test().assertComplete().assertNoErrors()
    }
}