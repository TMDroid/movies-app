package de.dannyb.moviesapp.networking

import de.dannyb.moviesapp.data.network.DiscoverMoviesResponse
import retrofit2.http.GET

interface MoviesDbService {

    @GET("discover/movie")
    suspend fun discover(): DiscoverMoviesResponse
}
