package com.example.easysend.network.api

import com.example.easysend.di.UserCache
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject


/**
 * A {@see RequestInterceptor} that adds an auth token to requests
 */
class AuthInterceptor @Inject constructor(
    private val userCache: UserCache
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request =
            chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer ${userCache.getTokenAccess()}")
                .addHeader("Accept", "application/json")
                .build()
        return chain.proceed(request)
    }
}
