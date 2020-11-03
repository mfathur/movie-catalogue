package com.mfathurz.moviecatalogue.db

import android.util.Log
import com.mfathurz.moviecatalogue.db.remote.api.ApiConfig
import com.mfathurz.moviecatalogue.db.remote.model.MovieResultsItem
import com.mfathurz.moviecatalogue.db.remote.model.TVResultsItem

class MovieRepository {
    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository()
            }
    }

    suspend fun getPopularMovies(): List<MovieResultsItem>?{
        try {
            val response = ApiConfig.getApiService().queryPopularMovies()
            if (response.isSuccessful){
                val data = response.body()
                data?.let {
                    return data.results
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
        return null
    }

    suspend fun getPopularTVShows(): List<TVResultsItem>?{
        try {
            val response = ApiConfig.getApiService().queryPopularTVShows()
            if (response.isSuccessful){
                val data = response.body()
                data?.let {
                    return data.results
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
        return null
    }

}