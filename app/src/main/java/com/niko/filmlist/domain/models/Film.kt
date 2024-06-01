package com.niko.filmlist.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val id : Int,
    val name : String?,
    val year : Int?,
    val description : String?,
    val rating: Rating?,
    val poster: Poster?
): Parcelable
