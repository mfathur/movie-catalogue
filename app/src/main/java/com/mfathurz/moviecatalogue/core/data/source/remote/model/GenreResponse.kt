package com.mfathurz.moviecatalogue.core.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @field:SerializedName("genres")
    val genres: List<GenreItem>
)

data class GenreItem(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String
)

