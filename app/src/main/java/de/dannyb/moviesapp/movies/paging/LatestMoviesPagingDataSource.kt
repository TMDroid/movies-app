package de.dannyb.moviesapp.movies.paging

import de.dannyb.moviesapp.movies.view.MoviesType
import de.dannyb.moviesapp.networking.MoviesDbService

class LatestMoviesPagingDataSource(
    moviesDbService: MoviesDbService
) : MoviesPagingDataSource(moviesDbService) {

    override fun getMoviesType(): MoviesType {
        return MoviesType.LATEST
    }
}
