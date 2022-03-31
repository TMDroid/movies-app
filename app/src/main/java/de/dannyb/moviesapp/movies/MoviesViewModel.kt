package de.dannyb.moviesapp.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import de.dannyb.moviesapp.data.Movie
import de.dannyb.moviesapp.movies.paging.MoviesPagingDataSource
import kotlinx.coroutines.flow.Flow

class MoviesViewModel(
    private val moviesPagingDataSource: MoviesPagingDataSource
) : ViewModel() {

    val moviesFlow: Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 5),
            pagingSourceFactory = { moviesPagingDataSource }
        ).flow.cachedIn(viewModelScope)
}
