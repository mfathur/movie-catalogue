package com.mfathurz.moviecatalogue.core.data

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.mfathurz.moviecatalogue.core.data.source.local.LocalDataSource
import com.mfathurz.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.mfathurz.moviecatalogue.core.data.source.remote.api.ApiResponse
import com.mfathurz.moviecatalogue.core.data.source.remote.model.GenreItem
import com.mfathurz.moviecatalogue.core.domain.model.Movie
import com.mfathurz.moviecatalogue.core.domain.model.TVShow
import com.mfathurz.moviecatalogue.core.domain.repository.IRepository
import com.mfathurz.moviecatalogue.core.utils.DataMapper
import com.mfathurz.moviecatalogue.core.utils.EspressoIdlingResource
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IRepository {

    override fun getPopularMovies(): Flowable<Resource<List<Movie>>> {
        val popularMovies =
            PublishSubject.create<Resource<List<Movie>>>()
        val compositeDisposable = CompositeDisposable()
        EspressoIdlingResource.increment()
        popularMovies.onNext(
            Resource.Loading(
                emptyList()
            )
        )

        compositeDisposable.add(remoteDataSource.getPopularMovies()
            .subscribeOn(Schedulers.computation())
            .take(1)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                compositeDisposable.dispose()
                EspressoIdlingResource.decrement()
            }
            .subscribe { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        val dataArray = DataMapper.mapMovieResponsesToDomain(response.data)
                        popularMovies.onNext(Resource.Success(dataArray))
                    }

                    is ApiResponse.Error -> {
                        popularMovies.onNext(Resource.Error(response.errorMessage))
                    }

                    is ApiResponse.Empty -> {
                        popularMovies.onNext(Resource.Success(emptyList()))
                    }
                }
            })

        return popularMovies.toFlowable(BackpressureStrategy.BUFFER)
    }

    override fun getPopularTVShows(): Flowable<Resource<List<TVShow>>> {
        val popularTVShows =
            PublishSubject.create<Resource<List<TVShow>>>()

        val compositeDisposable = CompositeDisposable()

        EspressoIdlingResource.increment()
        popularTVShows.onNext(Resource.Loading(emptyList()))
        compositeDisposable.add(
            remoteDataSource.getPopularTVShows()
                .subscribeOn(Schedulers.computation())
                .take(1)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {
                    compositeDisposable.dispose()
                    EspressoIdlingResource.decrement()
                }
                .subscribe { response ->
                    when (response) {
                        is ApiResponse.Success -> {
                            val dataArray = DataMapper.mapTVShowResponsesToDomain(response.data)
                            popularTVShows.onNext(Resource.Success(dataArray))
                        }

                        is ApiResponse.Error -> {
                            popularTVShows.onNext(Resource.Error(response.errorMessage)
                            )
                        }

                        is ApiResponse.Empty -> {
                            popularTVShows.onNext(Resource.Success(emptyList())
                            )
                        }
                    }
                }
        )

        return popularTVShows.toFlowable(BackpressureStrategy.BUFFER)
    }

    override fun getPagedFavoriteTVShows(): Flowable<PagedList<TVShow>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(6)
            .setPageSize(5)
            .build()

        return RxPagedListBuilder(
            DataMapper.mapTVShowDataSourceFactoryToDomain(
                localDataSource.queryAllDataSourceFavoriteTVShows()
            ),
            config
        ).buildFlowable(BackpressureStrategy.BUFFER)
    }


    override fun getPagedFavoriteMovies(): Flowable<PagedList<Movie>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(6)
            .setPageSize(5)
            .build()

        return RxPagedListBuilder(
            DataMapper.mapMovieDataSourceFactoryToDomain(
                localDataSource.queryAllDataSourceFavoriteMovies()
            ),
            config
        ).buildFlowable(BackpressureStrategy.BUFFER)
    }

    override fun getAllFavoriteMovies(): List<Movie> =
        DataMapper.mapMovieEntitiesToDomain(localDataSource.queryAllFavoriteMovies())

    override fun getAllFavoriteTVShow(): List<TVShow> =
        DataMapper.mapTVShowEntitiesToDomain(localDataSource.queryAllFavoriteTVShow())

    override fun insertFavoriteMovie(movie: Movie) {
        val movieEntity = DataMapper.mapMovieDomainToEntity(movie)
        localDataSource.insertFavoriteMovie(movieEntity)
    }

    override fun insertFavoriteTVShow(tvShow: TVShow) {
        val tvShowEntity = DataMapper.mapTVShowDomainToEntity(tvShow)
        localDataSource.insertFavoriteTVShow(tvShowEntity)
    }

    override fun deleteFavoriteMovie(movie: Movie) {
        val movieEntity = DataMapper.mapMovieDomainToEntity(movie)
        localDataSource.deleteFavoriteMovie(movieEntity)
    }

    override fun deleteFavoriteTVShow(tvShow: TVShow) {
        val tvShowEntity = DataMapper.mapTVShowDomainToEntity(tvShow)
        localDataSource.deleteFavoriteTVShow(tvShowEntity)
    }

    override fun getMovieGenres(): List<GenreItem> = remoteDataSource.getAllMovieGenres()

    override fun getTVShowGenres(): List<GenreItem> = remoteDataSource.getAllTVShowGenres()

}