package com.mfathurz.moviecatalogue.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.mfathurz.moviecatalogue.data.local.LocalDataSource
import com.mfathurz.moviecatalogue.data.local.room.entity.MovieEntity
import com.mfathurz.moviecatalogue.data.local.room.entity.TVShowEntity
import com.mfathurz.moviecatalogue.data.remote.GenreDataSource
import com.mfathurz.moviecatalogue.data.remote.api.ApiService
import com.mfathurz.moviecatalogue.util.CoroutineTestRule
import com.mfathurz.moviecatalogue.util.PagedListUtil
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

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    private val genreDataSource = mock(GenreDataSource::class.java)
    private val localDataSource = mock(LocalDataSource::class.java)
    private val movieRepository = FakeMovieRepository(genreDataSource, localDataSource)

    private val dummyMovieGenres = FakeDataDummy.generateDummyMovieGenres()
    private val dummyTVShowGenres = FakeDataDummy.generateDummyTVShowGenres()
    private val dummyFavoriteMovies = FakeDataDummy.generateDummyFavoriteMovies()
    private val dummyFavoriteTVShows = FakeDataDummy.generateDummyFavoriteTVShows()

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService

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

    @Test
    fun getAllFavoriteMovies() {
        `when`(localDataSource.queryAllFavoriteMovies()).thenReturn(dummyFavoriteMovies)
        val favoriteMovies = movieRepository.getAllFavoriteMovies()
        verify(localDataSource).queryAllFavoriteMovies()
        assertNotNull(favoriteMovies)
        assertEquals(1, favoriteMovies.size)
    }

    @Test
    fun getAllFavoriteTVShows() {
        `when`(localDataSource.queryAllFavoriteTVShow()).thenReturn(dummyFavoriteTVShows)
        val favoriteTVShows = movieRepository.getAllFavoriteTVShow()
        verify(localDataSource).queryAllFavoriteTVShow()
        assertNotNull(favoriteTVShows)
        assertEquals(1, favoriteTVShows.size)
    }

    @Test
    fun getPagedFavoriteMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(localDataSource.queryAllDataSourceFavoriteMovies()).thenReturn(dataSourceFactory)
        movieRepository.getPagedFavoriteMovies()
        val favoriteMovies = PagedListUtil.mockPagedList(dummyFavoriteMovies)
        verify(localDataSource).queryAllDataSourceFavoriteMovies()
        assertNotNull(favoriteMovies)
        assertEquals(1, favoriteMovies.size)
    }

    @Test
    fun getPagedFavoriteTVShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVShowEntity>
        `when`(localDataSource.queryAllDataSourceFavoriteTVShows()).thenReturn(dataSourceFactory)
        movieRepository.getPagedFavoriteTVShows()
        val favoriteTVShow = PagedListUtil.mockPagedList(dummyFavoriteTVShows)
        verify(localDataSource).queryAllDataSourceFavoriteTVShows()
        assertNotNull(favoriteTVShow)
        assertEquals(1, favoriteTVShow.size)
    }
}