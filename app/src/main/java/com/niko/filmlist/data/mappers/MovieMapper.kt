package com.niko.filmlist.data.mappers

import com.niko.filmlist.data.models.FilmApi
import com.niko.filmlist.data.models.MovieResponseApi
import com.niko.filmlist.data.models.PosterApi
import com.niko.filmlist.data.models.RatingApi
import com.niko.filmlist.data.models.ReviewApi
import com.niko.filmlist.data.models.ReviewResponseApi
import com.niko.filmlist.data.models.TrailerResponseApi
import com.niko.filmlist.data.models.TrailersApi
import com.niko.filmlist.domain.models.Film
import com.niko.filmlist.domain.models.MovieReponse
import com.niko.filmlist.domain.models.Poster
import com.niko.filmlist.domain.models.Rating
import com.niko.filmlist.domain.models.Review
import com.niko.filmlist.domain.models.ReviewResponse
import com.niko.filmlist.domain.models.Trailer
import com.niko.filmlist.domain.models.TrailerResponse
import com.niko.filmlist.domain.models.Trailers

object MovieMapper {
    fun mapMovieResponseApiToMovieResponse(response: MovieResponseApi): MovieReponse {
        return MovieReponse(
            response.filmsList.map {
                Film(
                    id = it.id,
                    name = it.name,
                    year = it.year,
                    description = it.description,
                    rating = Rating(it.rating?.kp),
                    poster = Poster(it.poster?.url)
                )
            }
        )
    }

    fun mapReviewResponseApiToReviewResponse(review: ReviewResponseApi): ReviewResponse {
        return ReviewResponse(
            review.reviewList.map {
                Review(
                    id = it.id,
                    title = it.title,
                    type = it.type,
                    review = it.review,
                    author = it.author
                )
            }
        )
    }

    fun mapTrailerRespApiToTrailerResp(response: TrailerResponseApi?): TrailerResponse {
        return TrailerResponse(
            mapTrailersApiToTrailers(response?.videos)
        )
    }

    fun mapFilmToFilmApi(film: Film): FilmApi {
        return FilmApi(
            film.id, film.name, film.year, film.description, RatingApi(film.rating?.kp),
            PosterApi(film.poster?.url)
        )
    }

    private fun mapTrailersApiToTrailers(trailers: TrailersApi?): Trailers {
        return Trailers(
            trailers?.trailers?.map {
                Trailer(it.url, it.name)
            }
        )
    }
}