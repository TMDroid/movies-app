package de.dannyb.moviesapp.movies.view

import androidx.recyclerview.widget.RecyclerView
import de.dannyb.moviesapp.common.view.loadImage
import de.dannyb.moviesapp.data.DiscoverMovieModel
import de.dannyb.moviesapp.databinding.RowMovieBinding

class MoviesViewHolder(
    private val binding: RowMovieBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: DiscoverMovieModel, movieClickListener: (DiscoverMovieModel) -> Unit) =
        with(binding) {
            movieTitle.text = movie.title
            movieCover.loadImage(movie.poster)

            root.setOnClickListener {
                movieClickListener.invoke(movie)
            }
        }
}
