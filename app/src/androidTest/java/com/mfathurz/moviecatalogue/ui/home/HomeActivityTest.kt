package com.mfathurz.moviecatalogue.ui.home


import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.data.remote.api.ApiConfig
import com.mfathurz.moviecatalogue.data.remote.model.MovieResultsItem
import com.mfathurz.moviecatalogue.data.remote.model.TVResultsItem
import com.mfathurz.moviecatalogue.db.DataDummy
import com.mfathurz.moviecatalogue.util.EspressoIdlingResource
import com.mfathurz.moviecatalogue.util.UtilsHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    private var dummyMovies: ArrayList<MovieResultsItem>? = null
    private var dummyTVShows: ArrayList<TVResultsItem>? = null

    @get:Rule
    val activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance()
            .register(EspressoIdlingResource.getEspressoTestIdlingResource())
        dummyMovies = getMovies()
        dummyTVShows = getTVShows()
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance()
            .unregister(EspressoIdlingResource.getEspressoTestIdlingResource())
    }

    @Test
    fun checkRecyclerViewMoviesData() {
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies!!.size
            )
        )
    }

    @Test
    fun loadDataRecyclerMovie() {
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click())
        )
        onView(withId(R.id.imgPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.txtTitle)).check(matches(withText(dummyMovies!![1].title)))
        onView(withId(R.id.txtCategory)).check(matches(withText(getMovieGenreText(dummyMovies!![1].genreIds))))
        onView(withId(R.id.txtReleasedDate)).check(
            matches(
                withText(
                    UtilsHelper.changeDateFormat(
                        dummyMovies!![1].releaseDate
                    )
                )
            )
        )
        onView(withId(R.id.txtPopularity)).check(matches(withText(dummyMovies!![1].popularity.toString())))
        onView(withId(R.id.txtLanguage)).check(matches(withText(dummyMovies!![1].originalLanguage)))
        onView(withId(R.id.txtRating)).check(matches(withText(dummyMovies!![1].voteAverage.toString())))
        onView(withId(R.id.txtOverview)).check(matches(withText(dummyMovies!![1].overview)))
    }

    @Test
    fun checkRecyclerViewTVShowData() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rvTVShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTVShow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTVShows!!.size
            )
        )
    }

    @Test
    fun loadDataRecyclerTVShow() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rvTVShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTVShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.imgPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.txtTitle)).check(matches(withText(dummyTVShows!![0].name)))
        onView(withId(R.id.txtCategory)).check(matches(withText(getTVShowGenreText(dummyTVShows!![0].genreIds))))
        onView(withId(R.id.txtReleasedDate)).check(
            matches(
                withText(
                    UtilsHelper.changeDateFormat(
                        dummyTVShows!![0].firstAirDate
                    )
                )
            )
        )
        onView(withId(R.id.txtPopularity)).check(matches(withText(dummyTVShows!![0].popularity.toString())))
        onView(withId(R.id.txtLanguage)).check(matches(withText(dummyTVShows!![0].originalLanguage)))
        onView(withId(R.id.txtRating)).check(matches(withText(dummyTVShows!![0].voteAverage.toString())))
        onView(withId(R.id.txtOverview)).check(matches(withText(dummyTVShows!![0].overview)))
    }

    @Test
    fun loadFavoriteMovies() {
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.btnFavorite)).check(matches(isDisplayed()))
        onView(withId(R.id.btnFavorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.action_favorite)).perform(click())
        onView(withId(R.id.rvFavoriteMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvFavoriteMovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.imgPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.txtTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.txtCategory)).check(matches(isDisplayed()))
        onView(withId(R.id.txtReleasedDate)).check(
            matches(
                isDisplayed()
            )
        )
        onView(withId(R.id.txtPopularity)).check(matches(isDisplayed()))
        onView(withId(R.id.txtLanguage)).check(matches(isDisplayed()))
        onView(withId(R.id.txtRating)).check(matches(isDisplayed()))
        onView(withId(R.id.txtOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.btnFavorite)).check(matches(isDisplayed()))
        onView(withId(R.id.btnFavorite)).perform(click())
        onView(withText("DELETE")).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())

    }

    @Test
    fun loadFavoriteTVShows() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rvTVShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTVShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )

        onView(withId(R.id.btnFavorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.action_favorite)).perform(click())
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rvFavoriteTVShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvFavoriteTVShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.imgPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.txtTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.txtCategory)).check(matches(isDisplayed()))
        onView(withId(R.id.txtReleasedDate)).check(
            matches(
                isDisplayed()
            )
        )
        onView(withId(R.id.txtPopularity)).check(matches(isDisplayed()))
        onView(withId(R.id.txtLanguage)).check(matches(isDisplayed()))
        onView(withId(R.id.txtRating)).check(matches(isDisplayed()))
        onView(withId(R.id.txtOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.btnFavorite)).check(matches(isDisplayed()))
        onView(withId(R.id.btnFavorite)).perform(click())
        onView(withText("DELETE")).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }

    private fun getTVShowGenreText(genreIds: List<Int>?): String {
        val genres = DataDummy.generateDummyTVShowGenres()
        var genre = ""
        genreIds?.forEach { genreId ->
            for (item in genres) {
                if (item.id == genreId) {
                    genre += item.name + " "
                }
            }
        }

        return genre
    }

    private fun getMovieGenreText(genreIds: List<Int>?): String {
        val genres = DataDummy.generateDummyMovieGenres()
        var genre = ""
        genreIds?.forEach { genreId ->
            for (item in genres) {
                if (item.id == genreId) {
                    genre += item.name + " "
                }
            }
        }

        return genre
    }

    private fun getMovies(): ArrayList<MovieResultsItem> {
        val listMovies = ArrayList<MovieResultsItem>()
        CoroutineScope(IO).launch {
            val response = async {
                ApiConfig.getApiService().queryPopularMovies().body()
            }

            val data = response.await()
            data?.let {
                listMovies.addAll(it.results)
            }
        }
        return listMovies
    }

    private fun getTVShows(): ArrayList<TVResultsItem> {
        val listMovies = ArrayList<TVResultsItem>()
        CoroutineScope(IO).launch {
            val response = async {
                ApiConfig.getApiService().queryPopularTVShows().body()
            }

            val data = response.await()
            data?.let {
                listMovies.addAll(it.results)
            }
        }
        return listMovies
    }
}