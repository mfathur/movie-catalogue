package com.mfathurz.moviecatalogue.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mfathurz.moviecatalogue.data.remote.GenreDataSource
import com.mfathurz.moviecatalogue.data.remote.api.ApiService
import com.mfathurz.moviecatalogue.util.CoroutineTestRule
import com.mfathurz.moviecatalogue.util.Utils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
class MovieRepositoryTest {
    private val genreDataSource = mock(GenreDataSource::class.java)
    private val movieRepository = FakeMovieRepository(genreDataSource)

    private val dummyMovieGenres = FakeDataDummy.generateDummyMovieGenres()
    private val dummyTVShowGenres = FakeDataDummy.generateDummyTVShowGenres()

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        apiService = Utils.getFakeApiServiceInstance(mockWebServer, "https://api.themoviedb.org/3/")
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getPopularMovies() = runBlocking {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(Utils.readTestResourceFile("dummy_popular_movies_response.json"))

        mockWebServer.enqueue(response)

        val movieResponse = apiService.queryPopularMovies().body()
        val popularMovies = movieResponse?.results

        assertNotNull(popularMovies)
        assertEquals(20, popularMovies?.size)
    }

    @Test
    fun getPopularTVShows() = runBlocking {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(Utils.readTestResourceFile("dummy_popular_tv_shows_response.json"))

        mockWebServer.enqueue(response)

        val tvShowResponse = apiService.queryPopularTVShows().body()
        val popularTVShows = tvShowResponse?.results

        assertNotNull(popularTVShows)
        assertEquals(20, popularTVShows?.size)

    }

    @Test
    fun getMovieGenres() {
        `when`(genreDataSource.getAllMovieGenres()).thenReturn(dummyMovieGenres)
        val listMovieGenres = movieRepository.getMovieGenres()
        verify(genreDataSource).getAllMovieGenres()
        assertNotNull(listMovieGenres)
        assertEquals(1, listMovieGenres.size)
    }

    @Test
    fun getTVShowGenres() {
        `when`(genreDataSource.getAllTVShowGenres()).thenReturn(dummyTVShowGenres)
        val listTVShowGenre = movieRepository.getTVShowGenres()
        verify(genreDataSource).getAllTVShowGenres()
        assertNotNull(listTVShowGenre)
        assertEquals(1, listTVShowGenre.size)
    }
}