package com.niko.filmlist.data.models

import com.google.gson.annotations.SerializedName

data class RatingApi(
    @SerializedName("kp")
    val kp : Float?
)
