package com.niko.filmlist.domain.useCases

import com.niko.filmlist.domain.models.Trailer
import com.niko.filmlist.domain.models.TrailerResponse
import com.niko.filmlist.domain.repositorys.MovieRepository
import io.reactivex.rxjava3.core.Single

class GetTrailers(private val repository: MovieRepository) {
    operator fun invoke(id:Int): Single<TrailerResponse> {
        return repository.getTrailers(id)
    }
}