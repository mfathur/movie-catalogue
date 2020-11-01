package com.mfathurz.moviecatalogue.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TVShowEntity(
    val title: String,
    val imageUrl: Int,
    val overview: String,
    val creator: String,
    val releaseDate: String,
    val category: String,
    val status: String,
    val time: String,
    val language: String,
    val casters: String
) : Parcelable