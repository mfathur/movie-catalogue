package com.mfathurz.moviecatalogue.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TVShowEntity(
    var title: String,
    var imageUrl: Int,
    var overview: String,
    var creator: String,
    var releaseDate: String,
    var category: String,
    var status: String,
    var time: String,
    var language: String,
    var casters: String
) : Parcelable