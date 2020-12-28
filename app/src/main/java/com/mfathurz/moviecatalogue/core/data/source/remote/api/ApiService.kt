package com.mfathurz.moviecatalogue.core.data.source.remote.api

import com.mfathurz.moviecatalogue.BuildConfig
import com.mfathurz.moviecatalogue.core.data.source.remote.model.ListMovieResponse
import com.mfathurz.moviecatalogue.core.data.source.remote.model.ListTVShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun queryPopularMovies(
        @Query("api_key")
        apiKey: String = BuildConfig.MOVIE_DB_API_KEY
    ): Response<ListMovieResponse>

    @GET("tv/popular")
    suspend fun queryPopularTVShows(
        @Query("api_key")
        apiKey: String = BuildConfig.MOVIE_DB_API_KEY
    ): Response<ListTVShowResponse>

}