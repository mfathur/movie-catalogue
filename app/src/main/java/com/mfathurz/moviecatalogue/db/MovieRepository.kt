package com.mfathurz.moviecatalogue.db

import com.mfathurz.moviecatalogue.data.remote.GenreDataSource
import com.mfathurz.moviecatalogue.data.remote.GenreSource
import com.mfathurz.moviecatalogue.data.remote.api.ApiConfig
import com.mfathurz.moviecatalogue.data.remote.model.GenreItem
import com.mfathurz.moviecatalogue.data.remote.model.MovieResultsItem
import com.mfathurz.moviecatalogue.data.remote.model.TVResultsItem
import com.mfathurz.moviecatalogue.util.EspressoIdlingResource

class MovieRepository(private val genreDataSource: GenreDataSource) : GenreSource {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(genreDataSource: GenreDataSource): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(genreDataSource)
            }
    }

    suspend fun getPopularMovies(): List<MovieResultsItem>? {
        try {
            EspressoIdlingResource.increment()
            val response = ApiConfig.getApiService().queryPopularMovies()
            if (response.isSuccessful) {
                val data = response.body()
                data?.let { movieResponse ->
                    EspressoIdlingResource.decrement()
                    return movieResponse.results
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    suspend fun getPopularTVShows(): List<TVResultsItem>? {
        try {
            EspressoIdlingResource.increment()
            val response = ApiConfig.getApiService().queryPopularTVShows()
            if (response.isSuccessful) {
                val data = response.body()
                data?.let {
                    EspressoIdlingResource.decrement()
                    return data.results
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    override fun getMovieGenres(): List<GenreItem> = genreDataSource.getAllMovieGenres()

    override fun getTVShowGenres(): List<GenreItem> = genreDataSource.getAllTVShowGenres()

}