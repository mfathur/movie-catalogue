package com.mfathurz.moviecatalogue.data.remote.api

import com.mfathurz.moviecatalogue.BuildConfig
import com.mfathurz.moviecatalogue.data.remote.model.MovieResponse
import com.mfathurz.moviecatalogue.data.remote.model.TVShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun queryPopularMovies(
        @Query("api_key")
        apiKey: String = BuildConfig.MOVIE_DB_API_KEY
    ): Response<MovieResponse>

    @GET("tv/popular")
    suspend fun queryPopularTVShows(
        @Query("api_key")
        apiKey: String = BuildConfig.MOVIE_DB_API_KEY
    ): Response<TVShowResponse>

}