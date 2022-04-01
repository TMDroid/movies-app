package de.dannyb.moviesapp.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.dannyb.moviesapp.data.FullMovieModel

class MovieDetailsViewModel : ViewModel() {

    private val _movie = MutableLiveData<FullMovieModel>()
    val movie: LiveData<FullMovieModel> get() = _movie

    fun setMovie(movie: FullMovieModel) {
        _movie.postValue(movie)
    }
}
