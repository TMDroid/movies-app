package de.dannyb.moviesapp.movies.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import de.dannyb.moviesapp.data.Movie
import de.dannyb.moviesapp.networking.MoviesDbService

class MoviesPagingDataSource(
    private val moviesDbService: MoviesDbService
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1

        return try {
            val moviesResponse = moviesDbService.discover(page)

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

    private fun getNextPageNumber(currentPageNumber: Int, totalPages: Int): Int? {
        return if (currentPageNumber < totalPages) {
            currentPageNumber + 1
        } else null
    }

    private fun getPreviousPageNumber(currentPageNumber: Int): Int? {
        return if (currentPageNumber > 1) {
            currentPageNumber - 1
        } else null
    }
}
