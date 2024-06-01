package com.niko.filmlist.data.models

import com.google.gson.annotations.SerializedName

data class ReviewApi(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("review")
    val review: String,
    @SerializedName("author")
    val author: String
)