package de.dannyb.moviesapp.movie_details

import de.dannyb.moviesapp.common.ModuleDefinition
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MovieDetailsModule : ModuleDefinition {
    override fun invoke() = module {

        viewModel { MovieDetailsViewModel() }
    }
}
