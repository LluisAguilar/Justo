package com.android.code.challenge.justo.data.repository

import com.android.code.challenge.justo.core.App
import com.android.code.challenge.justo.data.repository.datasource.RemoteDataSource
import com.android.code.challenge.justo.data.retrofit.JustoService
import com.android.code.challenge.justo.domain.helpers.ResultWrapper
import com.android.code.challenge.justo.domain.model.UserProfileDomain
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class JustoRepositoryImp : JustoRepository {


    @Inject
    lateinit var mJustoService: JustoService
    @Inject
    lateinit var mRemoteDataSource: RemoteDataSource


    init {
        App.justoRepositoryComponent.inject(this)
    }

    override suspend fun getUserResponse(dispatcher: CoroutineDispatcher): ResultWrapper<UserProfileDomain> {
        return  mRemoteDataSource.getUserProfileResponse(dispatcher) { mJustoService.getUserProfileResponse()}


    }
}