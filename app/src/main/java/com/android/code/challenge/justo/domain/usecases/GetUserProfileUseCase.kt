package com.android.code.challenge.justo.domain.usecases

import com.android.code.challenge.justo.data.repository.JustoRepositoryImp
import com.android.code.challenge.justo.domain.helpers.ResultWrapper
import com.android.code.challenge.justo.domain.model.UserProfileDomain

class GetUserProfileUseCase() {

    private val mJustoRepository = JustoRepositoryImp()


    suspend fun getUserProfile(): ResultWrapper<UserProfileDomain> {
        return mJustoRepository.getUserResponse()
    }


}