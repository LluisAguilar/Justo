package com.android.code.challenge.justo.data.api

import com.android.code.challenge.justo.data.retrofit.JustoService
import com.android.code.challenge.justo.domain.model.UserProfileDomain
import javax.inject.Inject

class JustoServiceImpl @Inject constructor(
    private val justoService: JustoService
): JustoServiceHelper {
    override suspend fun getUserProfileResponse() : UserProfileDomain = justoService.getUserProfileResponse()
}