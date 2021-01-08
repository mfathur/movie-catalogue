package com.mfathurz.moviecatalogue

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mfathurz.moviecatalogue.core.data.source.remote.api.ApiService
import com.mfathurz.moviecatalogue.core.di.networkModule
import com.mfathurz.moviecatalogue.core.domain.model.Movie
import com.mfathurz.moviecatalogue.core.domain.model.TVShow
import com.mfathurz.moviecatalogue.core.utils.DataMapper
import com.mfathurz.moviecatalogue.core.utils.EspressoIdlingResource
import com.mfathurz.moviecatalogue.core.utils.Helpers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class MainActivityTest : KoinTest {
    private var dummyMovies: ArrayList<Movie>? = null
    private var dummyTVShows: ArrayList<TVShow>? = null

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val apiService: ApiService by inject()

    @Before
    fun setUp() {
        loadKoinModules(networkModule)
        IdlingRegistry.getInstance()
            .register(EspressoIdlingResource.getEspressoTestIdlingResource())
        dummyMovies = getMovies()
        dummyTVShows = getTVShows()
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance()
            .unregister(EspressoIdlingResource.getEspressoTestIdlingResource())
        stopKoin()
    }

    @Test
    fun loadDataRecyclerMovie() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click())
        )
        onView(withId(R.id.imgPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_title)).check(matches(withText(dummyMovies!![2].title)))
        onView(withId(R.id.txt_category)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_released_date)).check(
            matches(
                withText(
                    Helpers.changeDateFormat(
                        dummyMovies!![2].releaseDate
                    )
                )
            )
        )
        onView(withId(R.id.txt_popularity)).check(matches(withText(dummyMovies!![2].popularity.toString())))
        onView(withId(R.id.txt_language)).check(matches(withText(dummyMovies!![2].originalLanguage)))
        onView(withId(R.id.txt_rating)).check(matches(withText(dummyMovies!![2].voteAverage.toString())))
        onView(withId(R.id.txt_overview)).check(matches(withText(dummyMovies!![2].overview)))
    }

    private fun getMovies(): ArrayList<Movie> {
        val listMovies = ArrayList<Movie>()

        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(apiService.queryPopularMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .take(1)
            .doOnComplete {
                compositeDisposable.dispose()
            }.subscribe {
                listMovies.addAll(DataMapper.mapMovieResponsesToDomain(it.results))
            })

        return listMovies
    }

    private fun getTVShows(): ArrayList<TVShow> {
        val listMovies = ArrayList<TVShow>()
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(apiService.queryPopularTVShows()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .take(1)
            .doOnComplete {
                compositeDisposable.dispose()
            }
            .subscribe {
                listMovies.addAll(DataMapper.mapTVShowResponsesToDomain(it.results))
            })

        return listMovies
    }
}