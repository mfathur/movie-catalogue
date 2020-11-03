package com.mfathurz.moviecatalogue.db.remote.api

import com.mfathurz.moviecatalogue.BuildConfig
import com.mfathurz.moviecatalogue.db.local.model.MovieEntity
import com.mfathurz.moviecatalogue.db.remote.model.MovieResponse
import com.mfathurz.moviecatalogue.db.remote.model.TVShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/{id}")
    suspend fun queryDetailMovie(
        @Path("id") id: Int,

        @Query("api_key")
        apiKey: String = BuildConfig.MOVIE_DB_API_KEY

    ): Response<MovieEntity>

    @GET("tv/{id}")
    suspend fun queryDetailTVShow(
        @Path("id") id: Int,

        @Query("api_key")
        apiKey: String = BuildConfig.MOVIE_DB_API_KEY
    ): Response<MovieEntity>

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