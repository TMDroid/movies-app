package de.dannyb.moviesapp.common

import androidx.paging.PagingSource
import androidx.paging.PagingState
import de.dannyb.moviesapp.networking.MoviesDbService

abstract class MoviesGenericPagingDataSource<Key : Any, Value : Any>(
    protected val moviesDbService: MoviesDbService
) : PagingSource<Key, Value>() {

    override fun getRefreshKey(state: PagingState<Key, Value>): Key? {
        return null
    }

    protected fun getNextPageNumber(currentPageNumber: Int, totalPages: Int): Int? {
        return if (currentPageNumber < totalPages) {
            currentPageNumber + 1
        } else null
    }

    protected fun getPreviousPageNumber(currentPageNumber: Int): Int? {
        return if (currentPageNumber > 1) {
            currentPageNumber - 1
        } else null
    }
}
