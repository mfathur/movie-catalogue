package com.mfathurz.moviecatalogue.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.mfathurz.moviecatalogue.core.data.source.remote.api.ApiResponse
import com.mfathurz.moviecatalogue.core.data.source.remote.api.ApiService
import com.mfathurz.moviecatalogue.core.data.source.remote.model.GenreItem
import com.mfathurz.moviecatalogue.core.data.source.remote.model.MovieResponse
import com.mfathurz.moviecatalogue.core.data.source.remote.model.TVShowResponse
import com.mfathurz.moviecatalogue.core.utils.JsonHelper
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class RemoteDataSource constructor(
    private val jsonHelper: JsonHelper,
    private val apiService: ApiService
) {

    fun getAllMovieGenres(): List<GenreItem> = jsonHelper.loadMovieGenres()

    fun getAllTVShowGenres(): List<GenreItem> = jsonHelper.loadTVShowGenres()

    @SuppressLint("CheckResult")
    fun getPopularMovies(): Flowable<ApiResponse<List<MovieResponse>>> {
        val result = PublishSubject.create<ApiResponse<List<MovieResponse>>>()

        val request = apiService.queryPopularMovies()

        request.subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.results
                result.onNext(
                    if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray)
                    else ApiResponse.Empty
                )
            }, { error ->
                result.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("remoteGetPopularMovies", error.toString())
            })

        return result.toFlowable(BackpressureStrategy.BUFFER)

    }


    @SuppressLint("CheckResult")
    fun getPopularTVShows(): Flowable<ApiResponse<List<TVShowResponse>>> {
        val result = PublishSubject.create<ApiResponse<List<TVShowResponse>>>()

        val request = apiService.queryPopularTVShows()
        request.subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.results
                result.onNext(
                    if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray)
                    else ApiResponse.Empty
                )
            }, { error ->
                result.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("remoteGetPopularTVShows", error.toString())
            })

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }
}
