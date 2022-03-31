package de.dannyb.moviesapp.movies.view

import android.view.View
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import de.dannyb.moviesapp.R
import de.dannyb.moviesapp.common.view.BaseObservableViewMvp
import de.dannyb.moviesapp.data.Movie
import de.dannyb.moviesapp.databinding.FragmentMoviesBinding

class MoviesViewImpl(
    private val binding: FragmentMoviesBinding
) : MoviesViewMvp, BaseObservableViewMvp<MoviesViewMvp.Listener>() {

    override val rootView: View
        get() = binding.root

    private val moviesAdapter = MoviesListAdapter { movie ->
        listeners.forEach { it.onMovieClicked(movie) }
    }

    init {
        binding.moviesList.apply {
            layoutManager = GridLayoutManager(rootView.context, GRID_COUNT)
            adapter = moviesAdapter
        }

        binding.toolbar.title = rootView.context
            .getString(R.string.movies__title)
    }

    override suspend fun addMovies(pagingData: PagingData<Movie>) {
        moviesAdapter.submitData(pagingData)
    }

    companion object {
        private const val GRID_COUNT = 3
    }
}
