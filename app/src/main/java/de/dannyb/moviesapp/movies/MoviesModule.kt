package de.dannyb.moviesapp.movies

import de.dannyb.moviesapp.common.ModuleDefinition
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MoviesModule : ModuleDefinition {
    override fun invoke() = module {

        viewModel { MoviesViewModel(get()) }
    }
}
