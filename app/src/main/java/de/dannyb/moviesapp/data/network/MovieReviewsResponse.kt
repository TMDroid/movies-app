package de.dannyb.moviesapp.data.network

import com.google.gson.annotations.SerializedName
import de.dannyb.moviesapp.data.MovieReviewModel

data class MovieReviewsResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieReviewModel>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int,
)
