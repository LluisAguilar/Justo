package com.android.code.challenge.justo.data.retrofit

import com.android.code.challenge.justo.data.StringDataCommonHelper.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class JustoClient {

    private val justoService: JustoService
    private val retrofit: Retrofit

    companion object {
        var instance: JustoClient? = null
            get() {
                if (field == null) instance = JustoClient()
                return field
            }
    }

    init {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder
            //.addInterceptor(AuthInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        val client = okHttpClientBuilder.build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        justoService = retrofit.create(JustoService::class.java)
    }

    fun getService() = justoService
}