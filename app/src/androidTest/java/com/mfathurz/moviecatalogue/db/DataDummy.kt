package com.mfathurz.moviecatalogue.db

import com.mfathurz.moviecatalogue.data.remote.model.GenreItem
import com.mfathurz.moviecatalogue.data.remote.model.MovieResultsItem
import com.mfathurz.moviecatalogue.data.remote.model.TVResultsItem

object DataDummy {
    fun generateDummyMovies(): ArrayList<MovieResultsItem> {
        val listMovies = ArrayList<MovieResultsItem>()

        listMovies.add(
            MovieResultsItem(
                "The work of billionaire tech CEO Donovan Chalmers is so valuable that he hires mercenaries to protect it, and a terrorist group kidnaps his daughter just to get it.",
                "en",
                "Hard Kill",
                "Hard Kill",
                listOf(28, 53),
                "/ugZW8ocsrfgI95pnQ7wrmKDxIe.jpg",
                "/86L8wqGMDbwURPni2t7FQ0nDjsH.jpg",
                "2020-10-23",
                2025.862,
                4.8,
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
                2090.631,
                8.4,
                "The Mandalorian",
                82856,
                1910
            )
        )
        return listTVShows
    }

    fun generateDummyMovieGenres(): ArrayList<GenreItem> {
        val listMovieGenres = ArrayList<GenreItem>()

        listMovieGenres.add(GenreItem(28, "Action"))
        listMovieGenres.add(GenreItem(12, "Adventure"))
        listMovieGenres.add(GenreItem(16, "Animation"))
        listMovieGenres.add(GenreItem(35, "Comedy"))
        listMovieGenres.add(GenreItem(80, "Crime"))
        listMovieGenres.add(GenreItem(99, "Documentary"))
        listMovieGenres.add(GenreItem(18, "Drama"))
        listMovieGenres.add(GenreItem(10751, "Fantasy"))
        listMovieGenres.add(GenreItem(36, "History"))
        listMovieGenres.add(GenreItem(27, "Horror"))
        listMovieGenres.add(GenreItem(10402, "Music"))
        listMovieGenres.add(GenreItem(9648, "Mystery"))
        listMovieGenres.add(GenreItem(10749, "Romance"))
        listMovieGenres.add(GenreItem(878, "Science Fiction"))
        listMovieGenres.add(GenreItem(10770, "TV Movie"))
        listMovieGenres.add(GenreItem(53, "Thriller"))
        listMovieGenres.add(GenreItem(10752, "War"))
        listMovieGenres.add(GenreItem(37, "Western"))

        return listMovieGenres
    }

    fun generateDummyTVShowGenres(): ArrayList<GenreItem> {
        val listTVShowGenres = ArrayList<GenreItem>()

        listTVShowGenres.add(GenreItem(10759, "Action & Adventure"))
        listTVShowGenres.add(GenreItem(16, "Animation"))
        listTVShowGenres.add(GenreItem(35, "Comedy"))
        listTVShowGenres.add(GenreItem(80, "Crime"))
        listTVShowGenres.add(GenreItem(99, "Documentary"))
        listTVShowGenres.add(GenreItem(18, "Drama"))
        listTVShowGenres.add(GenreItem(10751, "Family"))
        listTVShowGenres.add(GenreItem(10762, "Kids"))
        listTVShowGenres.add(GenreItem(9648, "Mystery"))
        listTVShowGenres.add(GenreItem(10763, "News"))
        listTVShowGenres.add(GenreItem(10764, "Reality"))
        listTVShowGenres.add(GenreItem(10765, "Sci-Fi & Fantasy"))
        listTVShowGenres.add(GenreItem(10766, "Soap"))
        listTVShowGenres.add(GenreItem(10767, "Talk"))
        listTVShowGenres.add(GenreItem(10768, "War & Politics"))
        listTVShowGenres.add(GenreItem(37, "Western"))


        return listTVShowGenres
    }
}


