package de.dannyb.moviesapp

import android.app.Application
import de.dannyb.moviesapp.common.ModuleDefinition
import de.dannyb.moviesapp.movies.MoviesModule
import de.dannyb.moviesapp.networking.NetworkingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@MoviesApp)
            modules(getKoinModules().map { it.invoke() })
        }
    }

    private fun getKoinModules(): List<ModuleDefinition> = listOf(
        MoviesModule,
        NetworkingModule,
    )
}
