package com.android.code.challenge.justo.data.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val requestBuilder = request.newBuilder()

        requestBuilder.addHeader("Accept", "")
        requestBuilder.addHeader("Content-Type", "")

        request = requestBuilder.build()

        return chain.proceed(request)
    }
}