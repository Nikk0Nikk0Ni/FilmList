package com.niko.filmlist.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rating(
    val kp : Float?
): Parcelable
