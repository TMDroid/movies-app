package de.dannyb.moviesapp.movies.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.dannyb.moviesapp.data.Movie
import de.dannyb.moviesapp.databinding.RowMovieBinding

class MoviesListAdapter(
    private val movieClickListener: (Movie) -> Unit
) : RecyclerView.Adapter<MoviesViewHolder>() {

    private val moviesList = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            RowMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(moviesList[position], movieClickListener)
    }

    override fun getItemCount(): Int = moviesList.size

    fun setMovies(movies: List<Movie>) {
        moviesList.clear()
        moviesList.addAll(movies)
        notifyDataSetChanged()
    }
}
