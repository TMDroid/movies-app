package de.dannyb.moviesapp.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.dannyb.moviesapp.data.Movie

class MoviesViewModel : ViewModel() {

    private val moviesList = listOf(
        Movie("Die Hard"),
        Movie("Die Hard 2"),
        Movie("100 ways to die"),
    )

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    fun fetchMovies() {
        _movies.postValue(moviesList)
    }
}
