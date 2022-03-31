package de.dannyb.moviesapp.movies.view

import androidx.paging.PagingData
import de.dannyb.moviesapp.common.view.ObservableViewMvp
import de.dannyb.moviesapp.common.view.ViewMvp
import de.dannyb.moviesapp.data.Movie

interface MoviesViewMvp : ObservableViewMvp<MoviesViewMvp.Listener> {

    interface Listener : ViewMvp.Listener {

        fun onMovieClicked(movie: Movie)

    }

    suspend fun addMovies(pagingData: PagingData<Movie>)
}
