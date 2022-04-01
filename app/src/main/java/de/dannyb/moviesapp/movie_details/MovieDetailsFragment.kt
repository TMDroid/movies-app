package de.dannyb.moviesapp.movie_details

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import de.dannyb.moviesapp.R
import de.dannyb.moviesapp.common.viewBinding
import de.dannyb.moviesapp.databinding.FragmentMovieDetailsBinding
import de.dannyb.moviesapp.movie_details.view.MovieDetailsViewImpl
import de.dannyb.moviesapp.movie_details.view.MovieDetailsViewMvp
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsFragment :
    Fragment(R.layout.fragment_movie_details),
    MovieDetailsViewMvp.Listener {

    private val args: MovieDetailsFragmentArgs by navArgs()

    private val viewModel by viewModel<MovieDetailsViewModel>()
    private val binding by viewBinding { FragmentMovieDetailsBinding.bind(it) }

    private lateinit var viewMvp: MovieDetailsViewMvp

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewMvp = MovieDetailsViewImpl(binding)

        setupToolbar()
        setTransparentStatusBar()

        observeLiveData()
        viewModel.setMovie(args.movie)
    }

    private fun setTransparentStatusBar() {
        requireActivity().window.statusBarColor = Color.TRANSPARENT
    }

    private fun observeLiveData() {
        viewModel.movie.observe(viewLifecycleOwner) {
            viewMvp.displaySelectedMovie(it)
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

    private fun setupToolbar() {
        with((requireActivity() as AppCompatActivity)) {
            with(binding.appBar.detailToolbar) {
                setSupportActionBar(this)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.title = ""

                setNavigationOnClickListener {
                    findNavController().navigateUp()
                }
            }
        }
    }
}
