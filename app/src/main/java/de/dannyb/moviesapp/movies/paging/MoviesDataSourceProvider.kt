package de.dannyb.moviesapp.movies.paging

import de.dannyb.moviesapp.movies.view.MoviesType
import de.dannyb.moviesapp.networking.MoviesDbService

class MoviesDataSourceProvider(
    private val moviesDbService: MoviesDbService
) {

    fun provide(type: MoviesType): MoviesPagingDataSource {
        return when (type) {
            MoviesType.LATEST -> LatestMoviesPagingDataSource(moviesDbService)
            MoviesType.MOST_POPULAR -> MostPopularMoviesPagingDataSource(moviesDbService)
        }
    }
}
