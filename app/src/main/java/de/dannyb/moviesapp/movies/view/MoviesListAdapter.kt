package de.dannyb.moviesapp.movies.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import de.dannyb.moviesapp.data.DiscoverMovieModel
import de.dannyb.moviesapp.databinding.RowMovieBinding

class MoviesListAdapter(
    private val movieClickListener: (DiscoverMovieModel) -> Unit
) : PagingDataAdapter<DiscoverMovieModel, MoviesViewHolder>(MovieComparator) {

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

    private object MovieComparator : DiffUtil.ItemCallback<DiscoverMovieModel>() {
        override fun areItemsTheSame(oldItem: DiscoverMovieModel, newItem: DiscoverMovieModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: DiscoverMovieModel, newItem: DiscoverMovieModel) =
            oldItem == newItem
    }
}
