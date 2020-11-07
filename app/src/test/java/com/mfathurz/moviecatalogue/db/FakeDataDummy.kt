package com.mfathurz.moviecatalogue.db

import com.mfathurz.moviecatalogue.data.remote.model.GenreItem
import com.mfathurz.moviecatalogue.data.remote.model.MovieResultsItem
import com.mfathurz.moviecatalogue.data.remote.model.TVResultsItem

object FakeDataDummy {
    fun generateDummyMovies(): ArrayList<MovieResultsItem> {
        val listMovies = ArrayList<MovieResultsItem>()

        listMovies.add(
            MovieResultsItem(
                "he work of billionaire tech CEO Donovan Chalmers is so valuable that he hires mercenaries to protect it, and a terrorist group kidnaps his daughter just to get it.",
                "en",
                "Hard Kill",
                "Hard Kill",
                listOf(28, 53),
                "/ugZW8ocsrfgI95pnQ7wrmKDxIe.jpg",
                "/86L8wqGMDbwURPni2t7FQ0nDjsH.jpg",
                "2020-10-23",
                2960.755,
                4.3,
                724989,
                57
            )
        )

        return listMovies
    }

    fun generateDummyTVShows(): ArrayList<TVResultsItem> {
        val listTVShows = ArrayList<TVResultsItem>()

        listTVShows.add(
            TVResultsItem(
                "2019-11-12",
                "After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.",
                "en",
                listOf(
                    10765,
                    10759,
                    37
                ),
                "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
                listOf("US"),
                "/9ijMGlJKqcslswWUzTEwScm82Gs.jpg",
                "The Mandalorian",
                0.0,
                0.0,
                "The Mandalorian",
                82856,
                1910
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