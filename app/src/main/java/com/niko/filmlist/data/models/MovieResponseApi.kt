package com.niko.filmlist.data.models

import com.google.gson.annotations.SerializedName

data class MovieResponseApi(
    @SerializedName("docs")
    val filmsList : List<FilmApi>
)
