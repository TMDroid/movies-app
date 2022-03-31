package de.dannyb.moviesapp.data.network

import de.dannyb.moviesapp.data.Movie

data class DiscoverMoviesResponse(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int,
)
