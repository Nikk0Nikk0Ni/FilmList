package com.niko.filmlist.domain.useCases

import com.niko.filmlist.domain.models.MovieReponse
import com.niko.filmlist.domain.models.ReviewResponse
import com.niko.filmlist.domain.repositorys.MovieRepository
import io.reactivex.rxjava3.core.Single

class GetReviews(private val repository : MovieRepository) {
    operator fun invoke(id: Int,page: Int): Single<ReviewResponse>{
        return repository.getReviews(id,page)
    }
}