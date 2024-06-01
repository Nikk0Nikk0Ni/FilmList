package com.niko.filmlist.data.models

import com.google.gson.annotations.SerializedName

data class PosterApi(
    @SerializedName("url")
    val url: String?
)
