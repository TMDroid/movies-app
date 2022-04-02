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
import de.dannyb.moviesapp.movies.paging.MoviesDataSourceProvider
import de.dannyb.moviesapp.movies.view.MoviesType
import de.dannyb.moviesapp.networking.MoviesDbService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val moviesDataSourceProvider: MoviesDataSourceProvider,
    private val moviesDbService: MoviesDbService
) : ViewModel() {

    private val _selectedMovie = SingleLiveEvent<FullMovieModel>()
    val selectedMovie: LiveData<FullMovieModel> get() = _selectedMovie

    private val _selectedMoviesType = MutableStateFlow(MoviesType.LATEST)

    val moviesFlow: Flow<PagingData<DiscoverMovieModel>> =
        _selectedMoviesType.flatMapLatest { type ->
            Pager(
                config = PagingConfig(pageSize = 20, prefetchDistance = 5),
                pagingSourceFactory = { moviesDataSourceProvider.provide(type) }
            ).flow.cachedIn(viewModelScope)
        }

    fun selectMovie(id: Int) = viewModelScope.launch {
        val details = moviesDbService.getDetails(id)
        _selectedMovie.postValue(details)
    }

    fun setMoviesType(type: MoviesType) = viewModelScope.launch {
        _selectedMoviesType.emit(type)
    }
}
