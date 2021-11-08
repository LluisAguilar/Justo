package com.android.code.challenge.justo.data.retrofit


import com.android.code.challenge.justo.domain.model.UserProfileDomain
import retrofit2.http.GET

interface JustoService {

    @GET("/api")
    suspend fun getUserProfileResponse(): UserProfileDomain
}