package de.dannyb.moviesapp.networking

import de.dannyb.moviesapp.data.FullMovieModel
import de.dannyb.moviesapp.data.network.DiscoverMoviesResponse
import de.dannyb.moviesapp.data.network.MovieReviewsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface MoviesDbService {

    @GET("discover/movie")
    suspend fun discover(
        @Query("page") page: Int,
        @Query("language") language: String = Locale.getDefault().toLanguageTag(),
//        @Query("sort_by") sortBy: String = "release_date.desc",
    ): DiscoverMoviesResponse

    @GET("movie/{id}")
    suspend fun getDetails(
        @Path("id") id: Int
    ): FullMovieModel

    @GET("movie/{id}/reviews")
    suspend fun getReviews(
        @Path("id") id: Int,
        @Query("page") page: Int,
    ): MovieReviewsResponse
}
