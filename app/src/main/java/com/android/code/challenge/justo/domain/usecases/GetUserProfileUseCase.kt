package com.android.code.challenge.justo.domain.usecases

import com.android.code.challenge.justo.data.repository.JustoRepositoryImp
import com.android.code.challenge.justo.domain.helpers.ResultWrapper
import com.android.code.challenge.justo.domain.model.UserProfileDomain
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(private val justoRepositoryImp: JustoRepositoryImp) {

    suspend fun getUserProfile(): ResultWrapper<UserProfileDomain> {
        return justoRepositoryImp.getUserResponse()
    }


}