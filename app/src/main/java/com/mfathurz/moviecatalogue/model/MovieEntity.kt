package com.mfathurz.moviecatalogue.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieEntity(
    val title: String,
    val imageUrl: Int,
    val overview: String,
    val director: String,
    val releaseDate: String,
    val category: String,
    val status: String,
    val time: String,
    val language: String,
    val casters: String
) : Parcelable