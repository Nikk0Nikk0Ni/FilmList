package com.niko.filmlist.data.models

import com.google.gson.annotations.SerializedName

data class TrailerResponseApi(
    @SerializedName("videos")
    val videos : TrailersApi?
)
