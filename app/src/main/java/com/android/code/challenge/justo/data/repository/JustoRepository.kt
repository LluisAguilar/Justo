package com.android.code.challenge.justo.data.repository

import com.android.code.challenge.justo.domain.helpers.ResultWrapper
import com.android.code.challenge.justo.domain.model.UserProfileDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface JustoRepository {

    suspend fun getUserResponse(dispatcher: CoroutineDispatcher = Dispatchers.IO): ResultWrapper<UserProfileDomain>
}