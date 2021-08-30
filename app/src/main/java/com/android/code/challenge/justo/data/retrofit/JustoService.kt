package com.android.code.challenge.justo.data.retrofit

import com.android.code.challenge.justo.data.retrofit.response.UserProfile
import retrofit2.Call
import retrofit2.http.GET

interface JustoService {

    @GET("/api")
    fun getUserProfile(): Call<UserProfile>
}