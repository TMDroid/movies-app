package de.dannyb.moviesapp.movies

import de.dannyb.moviesapp.common.ModuleDefinition
import de.dannyb.moviesapp.movies.paging.LatestMoviesPagingDataSource
import de.dannyb.moviesapp.movies.paging.MostPopularMoviesPagingDataSource
import de.dannyb.moviesapp.movies.paging.MoviesDataSourceProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MoviesModule : ModuleDefinition {
    override fun invoke() = module {

        viewModel { MoviesViewModel(get(), get()) }

        factory { LatestMoviesPagingDataSource(get()) }
        factory { MostPopularMoviesPagingDataSource(get()) }

        factory { MoviesDataSourceProvider(get()) }
    }
}
