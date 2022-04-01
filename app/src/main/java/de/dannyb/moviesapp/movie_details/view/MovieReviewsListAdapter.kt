package de.dannyb.moviesapp.movie_details.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import de.dannyb.moviesapp.data.MovieReviewModel
import de.dannyb.moviesapp.databinding.RowReviewBinding

class MovieReviewsListAdapter :
    PagingDataAdapter<MovieReviewModel, MovieReviewViewHolder>(MovieReviewComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieReviewViewHolder {
        return MovieReviewViewHolder(
            RowReviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieReviewViewHolder, position: Int) {
        getItem(position)?.let { review ->
            holder.bind(review)
        }
    }

    private object MovieReviewComparator : DiffUtil.ItemCallback<MovieReviewModel>() {
        override fun areItemsTheSame(oldItem: MovieReviewModel, newItem: MovieReviewModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MovieReviewModel, newItem: MovieReviewModel) =
            oldItem == newItem
    }
}
