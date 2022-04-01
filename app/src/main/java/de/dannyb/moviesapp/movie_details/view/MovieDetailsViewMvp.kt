package de.dannyb.moviesapp.movie_details.view

import de.dannyb.moviesapp.common.view.ObservableViewMvp
import de.dannyb.moviesapp.common.view.ViewMvp
import de.dannyb.moviesapp.data.FullMovieModel

interface MovieDetailsViewMvp : ObservableViewMvp<MovieDetailsViewMvp.Listener> {

    interface Listener : ViewMvp.Listener

    fun displaySelectedMovie(movie: FullMovieModel)
}
