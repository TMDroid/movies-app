package de.dannyb.moviesapp.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import de.dannyb.moviesapp.R
import de.dannyb.moviesapp.common.viewBinding
import de.dannyb.moviesapp.data.Movie
import de.dannyb.moviesapp.databinding.FragmentMoviesBinding
import de.dannyb.moviesapp.movies.view.MoviesViewImpl
import de.dannyb.moviesapp.movies.view.MoviesViewMvp
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MoviesFragment : Fragment(R.layout.fragment_movies), MoviesViewMvp.Listener {

    private val viewModel by viewModel<MoviesViewModel>()
    private val binding by viewBinding { FragmentMoviesBinding.bind(it) }

    private lateinit var viewMvp: MoviesViewMvp

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewMvp = MoviesViewImpl(binding)

        observeLiveData()
    }

    private fun observeLiveData() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {

            viewModel.moviesFlow.collectLatest { pagingData ->
                viewMvp.addMovies(pagingData)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewMvp.registerListener(this)
    }

    override fun onPause() {
        super.onPause()
        viewMvp.unregisterListener(this)
    }

    override fun onMovieClicked(movie: Movie) {
        Timber.i("selected movie $movie")
    }
}
