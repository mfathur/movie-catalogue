package com.mfathurz.moviecatalogue.ui.home


import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.db.DataMovie
import com.mfathurz.moviecatalogue.db.DataTVShow
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    private var dummyMovies = DataMovie.queryAllMovies()
    private var dummyTVShows = DataTVShow.queryAllTVShows()

    @get:Rule
    val activityRule = ActivityScenarioRule(HomeActivity::class.java)

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
        onView(withId(R.id.txtCast)).check(matches(withText(dummyMovies[0].casters)))
        onView(withId(R.id.txtCategory)).check(matches(withText(dummyMovies[0].category)))
        onView(withId(R.id.txtReleasedDate)).check(matches(withText(dummyMovies[0].releaseDate)))
        onView(withId(R.id.txtCreator)).check(matches(withText(dummyMovies[0].director)))
        onView(withId(R.id.txtStatus)).check(matches(withText(dummyMovies[0].status)))
        onView(withId(R.id.txtLanguage)).check(matches(withText(dummyMovies[0].language)))
        onView(withId(R.id.txtTime)).check(matches(withText(dummyMovies[0].time)))
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
        onView(withId(R.id.txtTitle)).check(matches(withText(dummyTVShows[0].title)))
        onView(withId(R.id.txtCast)).check(matches(withText(dummyTVShows[0].casters)))
        onView(withId(R.id.txtCategory)).check(matches(withText(dummyTVShows[0].category)))
        onView(withId(R.id.txtReleasedDate)).check(matches(withText(dummyTVShows[0].releaseDate)))
        onView(withId(R.id.txtCreator)).check(matches(withText(dummyTVShows[0].creator)))
        onView(withId(R.id.txtStatus)).check(matches(withText(dummyTVShows[0].status)))
        onView(withId(R.id.txtLanguage)).check(matches(withText(dummyTVShows[0].language)))
        onView(withId(R.id.txtTime)).check(matches(withText(dummyTVShows[0].time)))
        onView(withId(R.id.txtOverview)).check(matches(withText(dummyTVShows[0].overview)))
    }


}