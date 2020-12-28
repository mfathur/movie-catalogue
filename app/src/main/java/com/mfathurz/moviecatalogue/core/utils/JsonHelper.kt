package com.mfathurz.moviecatalogue.core.utils

import android.content.Context
import com.google.gson.Gson
import com.mfathurz.moviecatalogue.core.data.source.remote.model.GenreItem
import com.mfathurz.moviecatalogue.core.data.source.remote.model.GenreResponse
import org.json.JSONException
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parseFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun loadMovieGenres(): List<GenreItem> {
        val list = ArrayList<GenreItem>()
        try {
            val responseObject = parseFileToString("movie_genre.json").toString()
            val gson = Gson()
            val obj = gson.fromJson(responseObject, GenreResponse::class.java)

            list.addAll(obj.genres)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadTVShowGenres(): List<GenreItem> {
        val list = ArrayList<GenreItem>()
        try {
            val responseObject = parseFileToString("tv_genre.json").toString()
            val gson = Gson()
            val obj = gson.fromJson(responseObject, GenreResponse::class.java)

            list.addAll(obj.genres)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

}