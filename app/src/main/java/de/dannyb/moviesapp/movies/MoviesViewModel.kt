package de.dannyb.moviesapp.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import de.dannyb.moviesapp.common.SingleLiveEvent
import de.dannyb.moviesapp.data.DiscoverMovieModel
import de.dannyb.moviesapp.data.FullMovieModel
import de.dannyb.moviesapp.movies.paging.MoviesPagingDataSource
import de.dannyb.moviesapp.networking.MoviesDbService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val moviesPagingDataSource: MoviesPagingDataSource,
    private val moviesDbService: MoviesDbService
) : ViewModel() {

    private val _selectedMovie = SingleLiveEvent<FullMovieModel>()
    val selectedMovie: LiveData<FullMovieModel> get() = _selectedMovie

    val moviesFlow: Flow<PagingData<DiscoverMovieModel>> =
        Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 5),
            pagingSourceFactory = { moviesPagingDataSource }
        ).flow.cachedIn(viewModelScope)

    fun selectMovie(id: Int) = viewModelScope.launch {
        val details = moviesDbService.getDetails(id)
        _selectedMovie.postValue(details)
    }
}
