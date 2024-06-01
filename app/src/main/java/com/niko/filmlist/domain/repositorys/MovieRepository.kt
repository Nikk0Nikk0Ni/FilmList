package com.niko.filmlist.domain.repositorys

import com.niko.filmlist.domain.models.Film
import com.niko.filmlist.domain.models.MovieReponse
import com.niko.filmlist.domain.models.ReviewResponse
import com.niko.filmlist.domain.models.TrailerResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface MovieRepository {
    fun getMovies(page: Int): Single<MovieReponse>
    fun getTrailers(id: Int): Single<TrailerResponse>
    fun getReviews(id:Int,page: Int): Single<ReviewResponse>
}