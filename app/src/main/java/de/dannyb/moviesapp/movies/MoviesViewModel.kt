package de.dannyb.moviesapp.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.dannyb.moviesapp.data.Movie
import de.dannyb.moviesapp.networking.MoviesDbService
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val moviesDbService: MoviesDbService
) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    fun fetchMovies() = viewModelScope.launch {
        val movies = moviesDbService.discover()
        _movies.postValue(movies.results)
    }
}
