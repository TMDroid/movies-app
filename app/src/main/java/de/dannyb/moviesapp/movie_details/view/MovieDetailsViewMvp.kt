package de.dannyb.moviesapp.movie_details.view

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.paging.PagingData
import de.dannyb.moviesapp.common.view.ObservableViewMvp
import de.dannyb.moviesapp.common.view.ViewMvp
import de.dannyb.moviesapp.data.FullMovieModel
import de.dannyb.moviesapp.data.MovieReviewModel

interface MovieDetailsViewMvp : ObservableViewMvp<MovieDetailsViewMvp.Listener> {

    interface Listener : ViewMvp.Listener

    fun displaySelectedMovie(movie: FullMovieModel)

    fun setupToolbar(activity: AppCompatActivity, fragment: Fragment)

    suspend fun addReviews(pagingData: PagingData<MovieReviewModel>)
}
