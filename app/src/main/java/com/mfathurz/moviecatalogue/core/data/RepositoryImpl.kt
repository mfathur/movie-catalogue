package com.mfathurz.moviecatalogue.core.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.core.data.source.local.LocalDataSource
import com.mfathurz.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.mfathurz.moviecatalogue.core.data.source.local.entity.TVShowEntity
import com.mfathurz.moviecatalogue.core.data.source.remote.GenreDataSource
import com.mfathurz.moviecatalogue.core.data.source.remote.GenreSource
import com.mfathurz.moviecatalogue.core.data.source.remote.api.ApiConfig
import com.mfathurz.moviecatalogue.core.data.source.remote.model.GenreItem
import com.mfathurz.moviecatalogue.core.data.source.remote.model.MovieResultsItem
import com.mfathurz.moviecatalogue.core.data.source.remote.model.TVResultsItem
import com.mfathurz.moviecatalogue.core.utils.EspressoIdlingResource

class RepositoryImpl(
    private val genreDataSource: GenreDataSource,
    private val localDataSource: LocalDataSource
) : GenreSource {

    companion object {
        @Volatile
        private var instance: RepositoryImpl? = null

        fun getInstance(
            genreDataSource: GenreDataSource,
            localDataSource: LocalDataSource
        ): RepositoryImpl =
            instance ?: synchronized(this) {
                instance ?: RepositoryImpl(genreDataSource, localDataSource)
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
        return emptyList()
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