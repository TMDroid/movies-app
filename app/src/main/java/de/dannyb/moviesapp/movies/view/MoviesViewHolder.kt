package de.dannyb.moviesapp.movies.view

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import de.dannyb.moviesapp.data.Movie
import de.dannyb.moviesapp.databinding.RowMovieBinding

class MoviesViewHolder(
    private val binding: RowMovieBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie, movieClickListener: (Movie) -> Unit) {
        binding.movieTitle.text = movie.title

        Glide.with(binding.movieCover.context)
            .load("${BASE_COVER_PATH}${movie.posterPath}")
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.movieCover)

        binding.root.setOnClickListener {
            movieClickListener.invoke(movie)
        }
    }

    companion object {
        private const val BASE_COVER_PATH = "https://image.tmdb.org/t/p/w300"
    }
}
