package de.dannyb.moviesapp.movies.view

import de.dannyb.moviesapp.common.view.ObservableViewMvp
import de.dannyb.moviesapp.common.view.ViewMvp
import de.dannyb.moviesapp.data.Movie

interface MoviesViewMvp : ObservableViewMvp<MoviesViewMvp.Listener> {

    interface Listener : ViewMvp.Listener {

        fun onMovieClicked(movie: Movie)

    }

    fun setMovies(movies: List<Movie>)
}
