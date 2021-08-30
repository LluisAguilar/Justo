package com.android.code.challenge.justo.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.android.code.challenge.justo.data.repository.JustoRepository
import com.android.code.challenge.justo.data.retrofit.response.UserProfile

class UserProfileViewModel : ViewModel() {


    private val mJustoRepository = JustoRepository()

    fun getUserProfile() : LiveData<UserProfile> {
        return mJustoRepository.getUserProfile()
    }

}