package de.dannyb.moviesapp.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import de.dannyb.moviesapp.data.FullMovieModel
import de.dannyb.moviesapp.data.MovieReviewModel
import de.dannyb.moviesapp.movie_details.paging.MovieReviewsPagingDataSource
import de.dannyb.moviesapp.networking.MoviesDbService
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val moviesDbService: MoviesDbService
) : ViewModel() {

    private val _movie = MutableLiveData<FullMovieModel>()
    val movie: LiveData<FullMovieModel> get() = _movie

    private val _movieIdFlow = MutableStateFlow(-1)

    val reviewsFlow: Flow<PagingData<MovieReviewModel>> =
        _movieIdFlow
            .debounce(100)
            .filter { it > 0 }
            .flatMapLatest { movieId ->
                Pager(
                    config = PagingConfig(pageSize = 20, prefetchDistance = 5),
                    pagingSourceFactory = {
                        MovieReviewsPagingDataSource(moviesDbService, movieId)
                    }
                ).flow.cachedIn(viewModelScope)
            }

    fun setMovie(movie: FullMovieModel) = viewModelScope.launch {
        _movie.postValue(movie)
        _movieIdFlow.emit(movie.id)
    }
}
