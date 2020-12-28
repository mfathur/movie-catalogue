package com.mfathurz.moviecatalogue.core.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mfathurz.moviecatalogue.core.Resource
import com.mfathurz.moviecatalogue.core.data.source.local.LocalDataSource
import com.mfathurz.moviecatalogue.core.data.source.remote.GenreDataSource
import com.mfathurz.moviecatalogue.core.data.source.remote.GenreSource
import com.mfathurz.moviecatalogue.core.data.source.remote.api.ApiConfig
import com.mfathurz.moviecatalogue.core.data.source.remote.model.GenreItem
import com.mfathurz.moviecatalogue.core.domain.model.Movie
import com.mfathurz.moviecatalogue.core.domain.model.TVShow
import com.mfathurz.moviecatalogue.core.domain.repository.IRepository
import com.mfathurz.moviecatalogue.core.utils.DataMapper

class RepositoryImpl(
    private val genreDataSource: GenreDataSource,
    private val localDataSource: LocalDataSource
) : GenreSource, IRepository {

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

    override suspend fun getPopularMovies(): List<Movie> {
        try {
//            EspressoIdlingResource.increment()
            val response = ApiConfig.getApiService().queryPopularMovies()
            if (response.isSuccessful) {
                val data = response.body()
                data?.let { movieResponse ->
//                    EspressoIdlingResource.decrement()

                    return DataMapper.mapMovieResponsesToDomain(movieResponse.results)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return emptyList()
    }

    override suspend fun getPopularTVShows(): List<TVShow> {
        try {
//            EspressoIdlingResource.increment()
            val response = ApiConfig.getApiService().queryPopularTVShows()
            if (response.isSuccessful) {
                val data = response.body()
                data?.let {
//                    EspressoIdlingResource.decrement()
                    return DataMapper.mapTVShowResponsesToDomain(it.results)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return emptyList()
    }

    override fun getPagedFavoriteTVShows(): LiveData<PagedList<TVShow>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(6)
            .setPageSize(5)
            .build()

        return LivePagedListBuilder(
            DataMapper.mapTVShowDataSourceFactoryToDomain(
                localDataSource.queryAllDataSourceFavoriteTVShows()
            ),
            config
        ).build()
    }


    override fun getPagedFavoriteMovies(): LiveData<PagedList<Movie>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(6)
            .setPageSize(5)
            .build()

        return LivePagedListBuilder(
            DataMapper.mapMovieDataSourceFactoryToDomain(
                localDataSource.queryAllDataSourceFavoriteMovies()
            ),
            config
        ).build()
    }

    override fun getAllFavoriteMovies(): List<Movie> =
        DataMapper.mapMovieEntitiesToDomain(localDataSource.queryAllFavoriteMovies())

    override fun getAllFavoriteTVShow(): List<TVShow> =
        DataMapper.mapTVShowEntitiesToDomain(localDataSource.queryAllFavoriteTVShow())

    override suspend fun insertFavoriteMovie(movie: Movie) {
        val movieEntity = DataMapper.mapMovieDomainToEntity(movie)
        localDataSource.insertFavoriteMovie(movieEntity)
    }

    override suspend fun insertFavoriteTVShow(tvShow: TVShow) {
        val tvShowEntity = DataMapper.mapTVShowDomainToEntity(tvShow)
        localDataSource.insertFavoriteTVShow(tvShowEntity)
    }

    override suspend fun deleteFavoriteMovie(movie: Movie) {
        val movieEntity = DataMapper.mapMovieDomainToEntity(movie)
        localDataSource.deleteFavoriteMovie(movieEntity)
    }

    override suspend fun deleteFavoriteTVShow(tvShow: TVShow) {
        val tvShowEntity = DataMapper.mapTVShowDomainToEntity(tvShow)
        localDataSource.deleteFavoriteTVShow(tvShowEntity)
    }

    override fun getMovieGenres(): List<GenreItem> = genreDataSource.getAllMovieGenres()

    override fun getTVShowGenres(): List<GenreItem> = genreDataSource.getAllTVShowGenres()

}