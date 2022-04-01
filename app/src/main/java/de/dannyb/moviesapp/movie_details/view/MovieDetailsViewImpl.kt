package de.dannyb.moviesapp.movie_details.view

import android.content.Context
import android.view.View
import de.dannyb.moviesapp.R
import de.dannyb.moviesapp.common.view.BaseObservableViewMvp
import de.dannyb.moviesapp.common.view.loadImage
import de.dannyb.moviesapp.data.FullMovieModel
import de.dannyb.moviesapp.data.MovieGenreModel
import de.dannyb.moviesapp.databinding.FragmentMovieDetailsBinding

class MovieDetailsViewImpl(
    private val binding: FragmentMovieDetailsBinding
) : MovieDetailsViewMvp, BaseObservableViewMvp<MovieDetailsViewMvp.Listener>() {

    override val rootView: View
        get() = binding.root

    private val context: Context get() = rootView.context

    override fun displaySelectedMovie(movie: FullMovieModel) {
        with(binding.movieDetails) {
            movieTitle.text = movie.title
            movieDescription.text = movie.overview
            movieYear.text = buildMovieYear(movie)
            movieRating.text = buildMovieRating(movie)
            movieGenres.text = buildMovieGenres(movie.genres)

            movieCover.loadImage(movie.poster)
        }

        binding.appBar.movieBackdrop.loadImage(movie.backdrop)
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
