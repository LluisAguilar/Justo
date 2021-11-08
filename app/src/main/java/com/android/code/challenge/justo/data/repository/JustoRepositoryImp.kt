package com.android.code.challenge.justo.data.repository

import com.android.code.challenge.justo.data.api.JustoServiceHelper
import com.android.code.challenge.justo.data.repository.datasource.RemoteDataSource
import com.android.code.challenge.justo.domain.helpers.ResultWrapper
import com.android.code.challenge.justo.domain.model.UserProfileDomain
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class JustoRepositoryImp @Inject constructor(private val justoServiceHelper: JustoServiceHelper, private val remoteDataSource: RemoteDataSource)  : JustoRepository {


    override suspend fun getUserResponse(dispatcher: CoroutineDispatcher): ResultWrapper<UserProfileDomain> {
        return  remoteDataSource.getUserProfileResponse(dispatcher) { justoServiceHelper.getUserProfileResponse()}


    }
}