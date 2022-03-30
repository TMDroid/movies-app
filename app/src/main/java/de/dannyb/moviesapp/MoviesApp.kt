package de.dannyb.moviesapp

import android.app.Application
import de.dannyb.moviesapp.movies.MoviesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import timber.log.Timber

class MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@MoviesApp)
            modules(getKoinModules())
        }
    }

    private fun getKoinModules(): List<Module> = listOf(
        MoviesModule
    ).map { it.invoke() }
}
