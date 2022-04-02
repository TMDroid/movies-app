package de.dannyb.moviesapp.movies.view

import androidx.paging.PagingData
import de.dannyb.moviesapp.common.view.ObservableViewMvp
import de.dannyb.moviesapp.common.view.ViewMvp
import de.dannyb.moviesapp.data.DiscoverMovieModel

interface MoviesViewMvp : ObservableViewMvp<MoviesViewMvp.Listener> {

    interface Listener : ViewMvp.Listener {

        fun onMovieClicked(movie: DiscoverMovieModel)

        fun loadMoviesType(type: MoviesType)

    }

    suspend fun addMovies(pagingData: PagingData<DiscoverMovieModel>)
}
