package com.niko.filmlist.data.models

import com.google.gson.annotations.SerializedName

data class ReviewResponseApi(
    @SerializedName("docs")
    val reviewList : List<ReviewApi>
)