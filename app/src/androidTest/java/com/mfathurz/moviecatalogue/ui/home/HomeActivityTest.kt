package com.mfathurz.moviecatalogue.ui.home


import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.db.DataDummy
import com.mfathurz.moviecatalogue.util.EspressoIdlingResource
import com.mfathurz.moviecatalogue.util.UtilsHelper
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    private var dummyMovies = DataDummy.generateDummyMovies()
    private var dummyTVShows = DataDummy.generateDummyTVShows()

    @get:Rule
    val activityRule = ActivityScenarioRule(HomeActivity::class.java)


    @Before
    fun setUp() {
        IdlingRegistry.getInstance()
            .register(EspressoIdlingResource.getEspressoTestIdlingResource())
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
                dummyMovies.size
            )
        )
    }

    @Test
    fun loadDataRecyclerMovie() {
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.imgPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.txtTitle)).check(matches(withText(dummyMovies[0].title)))
        onView(withId(R.id.txtCategory)).check(matches(withText(getMovieGenreText(dummyMovies[0].genreIds))))
        onView(withId(R.id.txtReleasedDate)).check(
            matches(
                withText(
                    UtilsHelper.changeDateFormat(
                        dummyMovies[0].releaseDate
                    )
                )
            )
        )
        onView(withId(R.id.txtPopularity)).check(matches(withText(dummyMovies[0].popularity.toString())))
        onView(withId(R.id.txtLanguage)).check(matches(withText(dummyMovies[0].originalLanguage)))
        onView(withId(R.id.txtRating)).check(matches(withText(dummyMovies[0].voteAverage.toString())))
        onView(withId(R.id.txtOverview)).check(matches(withText(dummyMovies[0].overview)))
    }

    @Test
    fun checkRecyclerViewTVShowData() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rvTVShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTVShow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTVShows.size
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
        onView(withId(R.id.txtTitle)).check(matches(withText(dummyTVShows[0].name)))
        onView(withId(R.id.txtCategory)).check(matches(withText(getTVShowGenreText(dummyTVShows[0].genreIds))))
        onView(withId(R.id.txtReleasedDate)).check(
            matches(
                withText(
                    UtilsHelper.changeDateFormat(
                        dummyTVShows[0].firstAirDate
                    )
                )
            )
        )
        onView(withId(R.id.txtPopularity)).check(matches(withText(dummyTVShows[0].popularity.toString())))
        onView(withId(R.id.txtLanguage)).check(matches(withText(dummyTVShows[0].originalLanguage)))
        onView(withId(R.id.txtRating)).check(matches(withText(dummyTVShows[0].voteAverage.toString())))
        onView(withId(R.id.txtOverview)).check(matches(withText(dummyTVShows[0].overview)))
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


}