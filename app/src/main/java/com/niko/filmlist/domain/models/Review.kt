package com.niko.filmlist.domain.models

data class Review(
    val id: Int,
    val title: String,
    val type: String,
    val review: String,
    val author : String
)