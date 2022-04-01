package de.dannyb.moviesapp.movie_details.paging // ktlint-disable filename

import de.dannyb.moviesapp.common.MoviesGenericPagingDataSource
import de.dannyb.moviesapp.data.MovieReviewModel
import de.dannyb.moviesapp.networking.MoviesDbService

class MovieReviewsPagingDataSource(
    moviesDbService: MoviesDbService,
    private val movieId: Int
) : MoviesGenericPagingDataSource<Int, MovieReviewModel>(moviesDbService) {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieReviewModel> {
        val page = params.key ?: 1

        return try {
            val moviesResponse = moviesDbService.getReviews(movieId, page)

            val prevKey = getPreviousPageNumber(moviesResponse.page)
            val nextKey = getNextPageNumber(moviesResponse.page, moviesResponse.totalPages)

            LoadResult.Page(
                data = moviesResponse.results,
                prevKey = prevKey,
                nextKey = nextKey,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
