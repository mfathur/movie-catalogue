@file:Suppress("SpellCheckingInspection")

package com.mfathurz.moviecatalogue.db

import com.mfathurz.moviecatalogue.core.data.source.remote.model.GenreItem
import com.mfathurz.moviecatalogue.core.domain.model.Movie
import com.mfathurz.moviecatalogue.core.domain.model.TVShow

object FakeDataDummy {

    fun generateFavoriteDummyMovies(): List<Movie> {
        val listMovies = ArrayList<Movie>()

        listMovies.add(
            Movie(
                overview = "he work of billionaire tech CEO Donovan Chalmers is so valuable that he hires mercenaries to protect it, and a terrorist group kidnaps his daughter just to get it.",
                originalLanguage = "en",
                title = "Hard Kill",
                originalTitle = "Hard Kill",
                genreIds = listOf(28, 53),
                posterPath = "/ugZW8ocsrfgI95pnQ7wrmKDxIe.jpg",
                backdropPath = "/86L8wqGMDbwURPni2t7FQ0nDjsH.jpg",
                releaseDate = "2020-10-23",
                popularity = 2960.755,
                voteAverage = 4.3,
                voteCount = 724989,
                id = 57
            )
        )

        return listMovies
    }

    fun generateFavoriteDummyTVShows(): List<TVShow> {
        val listTVShows = ArrayList<TVShow>()

        listTVShows.add(
            TVShow(
                firstAirDate = "2019-11-12",
                overview = "After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.",
                originalLanguage = "en",
                genreIds = listOf(
                    10765,
                    10759,
                    37
                ),
                posterPath = "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
                originCountry = listOf("US"),
                backdropPath = "/9ijMGlJKqcslswWUzTEwScm82Gs.jpg",
                originalName = "The Mandalorian",
                popularity = 0.0,
                voteAverage = 0.0,
                name = "The Mandalorian",
                id = 82856,
                voteCount = 1910
            )
        )
        return listTVShows
    }

    fun generateDummyMovieGenres(): ArrayList<GenreItem> {
        val listMovieGenres = ArrayList<GenreItem>()

        listMovieGenres.add(GenreItem(123, "Animation"))

        return listMovieGenres
    }

    fun generateDummyTVShowGenres(): ArrayList<GenreItem> {
        val listTVShowGenres = ArrayList<GenreItem>()

        listTVShowGenres.add(GenreItem(321, "Thriller"))

        return listTVShowGenres
    }


}