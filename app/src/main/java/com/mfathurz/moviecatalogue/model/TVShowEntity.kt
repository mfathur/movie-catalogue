package com.mfathurz.moviecatalogue.model

data class TVShowEntity(
    var title: String,
    var imageUrl: Int,
    var overview: String,
    var director: String,
    var releaseDate: String,
    var category: String,
    var status: String,
    var time: String,
    var language: String,
    var casters: String
)