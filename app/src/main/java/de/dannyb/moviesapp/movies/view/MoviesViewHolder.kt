package de.dannyb.moviesapp.movies.view

import androidx.recyclerview.widget.RecyclerView
import de.dannyb.moviesapp.data.Movie
import de.dannyb.moviesapp.databinding.RowMovieBinding

class MoviesViewHolder(
    private val binding: RowMovieBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie, movieClickListener: (Movie) -> Unit) {
        binding.movieTitle.text = movie.name

        binding.root.setOnClickListener {
            movieClickListener.invoke(movie)
        }
    }
}
