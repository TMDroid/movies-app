package de.dannyb.moviesapp.networking

import de.dannyb.moviesapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class MoviesDbApiAuthenticationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .header("Authorization", "Bearer ${BuildConfig.TOKEN}")
            .header("Accept", "application/json")
            .build()

        return chain.proceed(newRequest)
    }
}
