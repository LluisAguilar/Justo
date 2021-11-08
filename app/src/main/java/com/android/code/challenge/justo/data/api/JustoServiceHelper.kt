package com.android.code.challenge.justo.data.api

import com.android.code.challenge.justo.domain.model.UserProfileDomain

interface JustoServiceHelper {

    suspend fun getUserProfileResponse(): UserProfileDomain

}