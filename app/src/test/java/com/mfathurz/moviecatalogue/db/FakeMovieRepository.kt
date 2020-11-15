package com.mfathurz.moviecatalogue.db

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.data.local.LocalDataSource
import com.mfathurz.moviecatalogue.data.local.room.entity.MovieEntity
import com.mfathurz.moviecatalogue.data.local.room.entity.TVShowEntity
import com.mfathurz.moviecatalogue.data.remote.GenreDataSource
import com.mfathurz.moviecatalogue.data.remote.GenreSource
import com.mfathurz.moviecatalogue.data.remote.api.ApiConfig
import com.mfathurz.moviecatalogue.data.remote.model.GenreItem
import com.mfathurz.moviecatalogue.data.remote.model.MovieResultsItem
import com.mfathurz.moviecatalogue.data.remote.model.TVResultsItem
import com.mfathurz.moviecatalogue.util.EspressoIdlingResource

class FakeMovieRepository(
    private val genreDataSource: GenreDataSource,
    private val localDataSource: LocalDataSource
) : GenreSource {

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
        return emptyList()
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
        return emptyList()
    }

    fun getPagedFavoriteTVShows(): LiveData<PagedList<TVShowEntity>> {

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(6)
            .setPageSize(5)
            .build()

        return LivePagedListBuilder(
            localDataSource.queryAllDataSourceFavoriteTVShows(),
            config
        ).build()
    }


    fun getPagedFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(6)
            .setPageSize(5)
            .build()

        return LivePagedListBuilder(
            localDataSource.queryAllDataSourceFavoriteMovies(),
            config
        ).build()
    }

    fun getAllFavoriteMovies(): List<MovieEntity> = localDataSource.queryAllFavoriteMovies()

    fun getAllFavoriteTVShow(): List<TVShowEntity> = localDataSource.queryAllFavoriteTVShow()

    suspend fun insertFavoriteMovie(movie: MovieEntity) {
        localDataSource.insertFavoriteMovie(movie)
    }

    suspend fun insertFavoriteTVShow(tvShow: TVShowEntity) {
        localDataSource.insertFavoriteTVShow(tvShow)
    }

    suspend fun deleteFavoriteMovie(movie: MovieEntity) {
        localDataSource.deleteFavoriteMovie(movie)
    }

    suspend fun deleteFavoriteTVShow(tvShow: TVShowEntity) {
        localDataSource.deleteFavoriteTVShow(tvShow)
    }

    override fun getMovieGenres(): List<GenreItem> = genreDataSource.getAllMovieGenres()

    override fun getTVShowGenres(): List<GenreItem> = genreDataSource.getAllTVShowGenres()

}