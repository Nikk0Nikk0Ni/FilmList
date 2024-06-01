package com.niko.filmlist.data.models

import com.google.gson.annotations.SerializedName

data class TrailerApi(
    @SerializedName("url")
    val url : String,
    @SerializedName("name")
    val name : String
)