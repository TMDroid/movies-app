package de.dannyb.moviesapp.networking

import de.dannyb.moviesapp.data.network.DiscoverMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Locale

interface MoviesDbService {

    @GET("discover/movie")
    suspend fun discover(
        @Query("page") page: Int,
        @Query("language") language: String = Locale.getDefault().toLanguageTag()
    ): DiscoverMoviesResponse
}
