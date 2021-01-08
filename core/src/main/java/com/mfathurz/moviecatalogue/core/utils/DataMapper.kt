package com.mfathurz.moviecatalogue.core.utils

import androidx.paging.DataSource
import com.mfathurz.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.mfathurz.moviecatalogue.core.data.source.local.entity.TVShowEntity
import com.mfathurz.moviecatalogue.core.data.source.remote.model.MovieResponse
import com.mfathurz.moviecatalogue.core.data.source.remote.model.TVShowResponse
import com.mfathurz.moviecatalogue.core.domain.model.Movie
import com.mfathurz.moviecatalogue.core.domain.model.TVShow

object DataMapper {

    fun mapTVShowEntitiesToDomain(input: List<TVShowEntity>): List<TVShow> {
        val list = ArrayList<TVShow>()
        input.map {
            list.add(
                TVShow(
                    id = it.id,
                    firstAirDate = it.firstAirDate,
                    overview = it.overview,
                    originalLanguage = it.originalLanguage,
                    genreIds = it.genreIds,
                    posterPath = it.posterPath,
                    originCountry = it.originCountry,
                    backdropPath = it.backdropPath,
                    originalName = it.originalName,
                    popularity = it.popularity,
                    voteAverage = it.voteAverage,
                    name = it.name,
                    voteCount = it.voteCount
                )
            )
        }
        return list
    }

    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<Movie> {
        val list = ArrayList<Movie>()
        input.map {
            list.add(
                Movie(
                    id = it.id,
                    overview = it.overview,
                    originalLanguage = it.originalLanguage,
                    originalTitle = it.originalTitle,
                    title = it.title,
                    genreIds = it.genreIds,
                    posterPath = it.posterPath,
                    backdropPath = it.backdropPath,
                    releaseDate = it.releaseDate,
                    popularity = it.popularity,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount
                )
            )
        }
        return list
    }

    fun mapMovieDomainToEntity(movie: Movie): MovieEntity {
        return MovieEntity(
            id = movie.id,
            overview = movie.overview,
            originalLanguage = movie.originalLanguage,
            originalTitle = movie.originalTitle,
            title = movie.title,
            genreIds = movie.genreIds,
            posterPath = movie.posterPath,
            backdropPath = movie.backdropPath,
            releaseDate = movie.releaseDate,
            popularity = movie.popularity,
            voteAverage = movie.voteAverage,
            voteCount = movie.voteCount
        )
    }

    fun mapTVShowDomainToEntity(tvShow: TVShow): TVShowEntity {
        return TVShowEntity(
            id = tvShow.id,
            firstAirDate = tvShow.firstAirDate,
            overview = tvShow.overview,
            originalLanguage = tvShow.originalLanguage,
            genreIds = tvShow.genreIds,
            posterPath = tvShow.posterPath,
            originCountry = tvShow.originCountry,
            backdropPath = tvShow.backdropPath ?: "",
            originalName = tvShow.originalName,
            popularity = tvShow.popularity,
            voteAverage = tvShow.voteAverage,
            name = tvShow.name,
            voteCount = tvShow.voteCount
        )
    }

    fun mapTVShowResponsesToDomain(input: List<TVShowResponse>): List<TVShow> {
        val list = ArrayList<TVShow>()
        input.map {
            list.add(
                TVShow(
                    id = it.id,
                    firstAirDate = it.firstAirDate,
                    overview = it.overview,
                    originalLanguage = it.originalLanguage,
                    genreIds = it.genreIds,
                    posterPath = it.posterPath,
                    originCountry = it.originCountry,
                    backdropPath = it.backdropPath,
                    originalName = it.originalName,
                    popularity = it.popularity,
                    voteAverage = it.voteAverage,
                    name = it.name,
                    voteCount = it.voteCount
                )
            )
        }
        return list
    }

    fun mapMovieResponsesToDomain(input: List<MovieResponse>): List<Movie> {
        val list = ArrayList<Movie>()
        input.map {
            list.add(
                Movie(
                    id = it.id,
                    overview = it.overview,
                    originalLanguage = it.originalLanguage,
                    originalTitle = it.originalTitle,
                    title = it.title,
                    genreIds = it.genreIds,
                    posterPath = it.posterPath,
                    backdropPath = it.backdropPath,
                    releaseDate = it.releaseDate,
                    popularity = it.popularity,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount
                )
            )
        }
        return list
    }

    fun mapMovieDataSourceFactoryToDomain(factory: DataSource.Factory<Int, MovieEntity>)
            : DataSource.Factory<Int, Movie> {

        return factory.map {
            Movie(
                id = it.id,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                title = it.title,
                genreIds = it.genreIds,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount
            )
        }
    }

    fun mapTVShowDataSourceFactoryToDomain(factory: DataSource.Factory<Int, TVShowEntity>)
            : DataSource.Factory<Int, TVShow> {

        return factory.map {
            TVShow(
                id = it.id,
                firstAirDate = it.firstAirDate,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                genreIds = it.genreIds,
                posterPath = it.posterPath,
                originCountry = it.originCountry,
                backdropPath = it.backdropPath,
                originalName = it.originalName,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                name = it.name,
                voteCount = it.voteCount
            )
        }
    }


}