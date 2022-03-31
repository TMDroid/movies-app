package de.dannyb.moviesapp.data.network

import com.google.gson.annotations.SerializedName
import de.dannyb.moviesapp.data.Movie

data class DiscoverMoviesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<Movie>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int,
)
