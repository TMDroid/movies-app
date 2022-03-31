package de.dannyb.moviesapp.networking

import de.dannyb.moviesapp.common.ModuleDefinition
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkingModule : ModuleDefinition {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    override fun invoke() = module {
        factory {
            OkHttpClient.Builder()
                .addInterceptor(get<MoviesDbApiAuthenticationInterceptor>())
                .addNetworkInterceptor(get<HttpLoggingInterceptor>())
                .build()
        }

        factory {
            Retrofit.Builder()
                .client(get())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        single<MoviesDbService> {
            get<Retrofit>().create(MoviesDbService::class.java)
        }

        factory { MoviesDbApiAuthenticationInterceptor() }

        factory { HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY } }
    }
}
