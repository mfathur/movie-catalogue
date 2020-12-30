package com.mfathurz.moviecatalogue.core.data.source.remote.api

import com.mfathurz.moviecatalogue.core.BuildConfig
import com.mfathurz.moviecatalogue.core.data.source.remote.model.ListMovieResponse
import com.mfathurz.moviecatalogue.core.data.source.remote.model.ListTVShowResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun queryPopularMovies(
        @Query("api_key")
        apiKey: String = BuildConfig.MOVIE_DB_API_KEY
    ): Flowable<ListMovieResponse>

    @GET("tv/popular")
    fun queryPopularTVShows(
        @Query("api_key")
        apiKey: String = BuildConfig.MOVIE_DB_API_KEY
    ): Flowable<ListTVShowResponse>

}