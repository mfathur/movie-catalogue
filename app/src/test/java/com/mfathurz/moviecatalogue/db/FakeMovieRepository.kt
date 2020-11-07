package com.mfathurz.moviecatalogue.db

import com.mfathurz.moviecatalogue.data.remote.GenreDataSource
import com.mfathurz.moviecatalogue.data.remote.GenreSource
import com.mfathurz.moviecatalogue.data.remote.api.ApiConfig
import com.mfathurz.moviecatalogue.data.remote.model.GenreItem
import com.mfathurz.moviecatalogue.data.remote.model.MovieResultsItem
import com.mfathurz.moviecatalogue.data.remote.model.TVResultsItem

class FakeMovieRepository(private val genreDataSource: GenreDataSource) : GenreSource {

    suspend fun getPopularMovies(): List<MovieResultsItem>? {
        try {
            val response = ApiConfig.getApiService().queryPopularMovies()
            if (response.isSuccessful) {
                val data = response.body()
                data?.let { movieResponse ->
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
            val response = ApiConfig.getApiService().queryPopularTVShows()
            if (response.isSuccessful) {
                val data = response.body()
                data?.let {
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