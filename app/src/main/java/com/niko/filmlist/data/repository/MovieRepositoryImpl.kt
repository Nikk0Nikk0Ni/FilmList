package com.niko.filmlist.data.repository

import com.niko.filmlist.data.mappers.MovieMapper
import com.niko.filmlist.data.network.ApiFactory
import com.niko.filmlist.domain.models.MovieReponse
import com.niko.filmlist.domain.models.ReviewResponse
import com.niko.filmlist.domain.models.TrailerResponse
import com.niko.filmlist.domain.repositorys.MovieRepository
import io.reactivex.rxjava3.core.Single

class MovieRepositoryImpl : MovieRepository {
    override fun getMovies(page: Int): Single<MovieReponse> {
        return ApiFactory.api_serv.getMovies(page)
            .map { MovieMapper.mapMovieResponseApiToMovieResponse(it) }
    }

    override fun getTrailers(id: Int): Single<TrailerResponse> {
        return ApiFactory.api_serv.getTrailers(id)
            .map { MovieMapper.mapTrailerRespApiToTrailerResp(it) }
    }

    override fun getReviews(id: Int,page: Int): Single<ReviewResponse> {
        return ApiFactory.api_serv.getReviewById(id,page)
            .map { MovieMapper.mapReviewResponseApiToReviewResponse(it) }
    }
}