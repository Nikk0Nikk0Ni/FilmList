package com.niko.filmlist.data.models

import com.google.gson.annotations.SerializedName

data class TrailersApi(
    @SerializedName("trailers")
    val trailers : List<TrailerApi>?
)
