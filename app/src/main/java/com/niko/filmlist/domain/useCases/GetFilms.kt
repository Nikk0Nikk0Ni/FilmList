package com.niko.filmlist.domain.useCases

import com.niko.filmlist.domain.models.MovieReponse
import com.niko.filmlist.domain.repositorys.MovieRepository
import io.reactivex.rxjava3.core.Single

class GetFilms(private val repository: MovieRepository) {
    operator fun invoke(page: Int): Single<MovieReponse>{
        return repository.getMovies(page)
    }
}