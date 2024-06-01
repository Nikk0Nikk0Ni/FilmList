package com.niko.filmlist.data.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class FilmApi(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String?,
    @SerializedName("year")
    val year : Int?,
    @SerializedName("description")
    val description : String?,
    @SerializedName("rating")
    val rating: RatingApi?,
    @SerializedName("poster")
    val poster: PosterApi?
)
