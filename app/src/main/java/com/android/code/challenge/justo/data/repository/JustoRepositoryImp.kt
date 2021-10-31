package com.android.code.challenge.justo.data.repository

import com.android.code.challenge.justo.data.repository.datasource.RemoteDataSource
import com.android.code.challenge.justo.data.retrofit.JustoClient
import com.android.code.challenge.justo.data.retrofit.JustoService
import com.android.code.challenge.justo.domain.helpers.ResultWrapper
import com.android.code.challenge.justo.domain.model.UserProfileDomain
import kotlinx.coroutines.CoroutineDispatcher

class JustoRepositoryImp() : JustoRepository {

    private var mRemoteDataSource : RemoteDataSource
    private val mJustoClient: JustoClient = JustoClient.instance!!
    private val mJustoService: JustoService = mJustoClient.getService()

    init {
        mRemoteDataSource = RemoteDataSource()
    }


    override suspend fun getUserResponse(dispatcher: CoroutineDispatcher): ResultWrapper<UserProfileDomain> {
        return  mRemoteDataSource.getUserProfileResponse(dispatcher) { mJustoService.getUserProfileResponse()}


    }
}