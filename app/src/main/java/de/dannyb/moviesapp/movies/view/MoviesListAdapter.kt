package de.dannyb.moviesapp.movies.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import de.dannyb.moviesapp.data.Movie
import de.dannyb.moviesapp.databinding.RowMovieBinding

class MoviesListAdapter(
    private val movieClickListener: (Movie) -> Unit
) : PagingDataAdapter<Movie, MoviesViewHolder>(MovieComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            RowMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie, movieClickListener)
        }
    }

    private object MovieComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem == newItem
    }
}
