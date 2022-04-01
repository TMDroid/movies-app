package de.dannyb.moviesapp.movies

import de.dannyb.moviesapp.common.ModuleDefinition
import de.dannyb.moviesapp.movies.paging.MoviesPagingDataSource
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MoviesModule : ModuleDefinition {
    override fun invoke() = module {

        viewModel { MoviesViewModel(get(), get()) }

        factory { MoviesPagingDataSource(get()) }
    }
}
