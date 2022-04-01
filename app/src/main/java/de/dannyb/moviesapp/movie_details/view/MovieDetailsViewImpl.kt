package de.dannyb.moviesapp.movie_details.view

import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import de.dannyb.moviesapp.R
import de.dannyb.moviesapp.common.view.BaseObservableViewMvp
import de.dannyb.moviesapp.common.view.loadImage
import de.dannyb.moviesapp.data.FullMovieModel
import de.dannyb.moviesapp.data.MovieGenreModel
import de.dannyb.moviesapp.data.MovieReviewModel
import de.dannyb.moviesapp.databinding.FragmentMovieDetailsBinding

class MovieDetailsViewImpl(
    private val binding: FragmentMovieDetailsBinding
) : MovieDetailsViewMvp, BaseObservableViewMvp<MovieDetailsViewMvp.Listener>() {

    override val rootView: View
        get() = binding.root

    private val context: Context get() = rootView.context

    private val reviewsAdapter = MovieReviewsListAdapter()

    override fun setupToolbar(activity: AppCompatActivity, fragment: Fragment) {
        with(activity) {
            with(binding.appBar.detailToolbar) {
                setSupportActionBar(this)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.title = ""

                setNavigationOnClickListener {
                    fragment.findNavController().navigateUp()
                }
            }
        }
    }

    override suspend fun addReviews(pagingData: PagingData<MovieReviewModel>) {
        reviewsAdapter.submitData(pagingData)
    }

    override fun displaySelectedMovie(movie: FullMovieModel) {
        with(binding.movieDetails) {
            movieTitle.text = movie.title
            movieDescription.text = movie.overview
            movieYear.text = buildMovieYear(movie)
            movieRating.text = buildMovieRating(movie)
            movieGenres.text = buildMovieGenres(movie.genres)

            movieCover.loadImage(movie.poster)

            movieReviews.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = reviewsAdapter
            }
        }

        binding.appBar.movieBackdrop.loadImage(movie.backdrop)
        binding.appBar.toolbarLayout.title = movie.title
    }

    private fun buildMovieRating(movie: FullMovieModel) = context.getString(
        R.string.movie_rating__label, movie.votes.toString()
    )

    private fun buildMovieYear(movie: FullMovieModel) = context.getString(
        R.string.movie_year__label, movie.releaseDate.split("-").first()
    )

    private fun buildMovieGenres(genres: List<MovieGenreModel>): CharSequence {
        return context.getString(
            R.string.movie_genres__label, genres.map { it.name }.joinToString(", ")
        )
    }
}
