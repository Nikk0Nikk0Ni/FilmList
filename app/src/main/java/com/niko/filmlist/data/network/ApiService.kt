package com.niko.filmlist.data.network

import com.niko.filmlist.data.models.MovieResponseApi
import com.niko.filmlist.data.models.ReviewResponseApi
import com.niko.filmlist.data.models.TrailerResponseApi
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

private const val API1 = "YMP4CPE-MJQ4KB7-HEACNWP-J2484YV"
private const val API2 = "BHANBKF-D84MFK3-KYGKDZT-5YCNM0G"
private const val KEY = "X-API-KEY:"

interface ApiService {

    @Headers("$KEY $API1")
    @GET("/v1.4/movie?rating.kp=1-10")
    fun getMovies(@Query("page") page: Int): Single<MovieResponseApi>

    @Headers("$KEY $API1")
    @GET("/v1.4/movie/{id}")
    fun getTrailers(@Path("id") id: Int): Single<TrailerResponseApi>

    @Headers("$KEY $API1")
    @GET("/v1.4/review")
    fun getReviewById(@Query("movieId") id: Int,@Query("page") page: Int): Single<ReviewResponseApi>
}