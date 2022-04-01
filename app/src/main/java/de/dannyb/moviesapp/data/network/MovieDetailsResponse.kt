package de.dannyb.moviesapp.data.network

import com.google.gson.annotations.SerializedName
import de.dannyb.moviesapp.data.DiscoverMovieModel

data class MovieDetailsResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<DiscoverMovieModel>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int,
)
