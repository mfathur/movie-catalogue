package com.mfathurz.moviecatalogue.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TVShow(
    var id: Int = 0,
    val firstAirDate: String,
    val overview: String,
    val originalLanguage: String,
    val genreIds: List<Int>?,
    val posterPath: String,
    val originCountry: List<String>,
    val backdropPath: String,
    val originalName: String,
    val popularity: Double,
    val voteAverage: Double,
    val name: String,
    val voteCount: Int
) : Parcelable