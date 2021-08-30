package com.android.code.challenge.justo.data.repository

import androidx.lifecycle.LiveData
import com.android.code.challenge.justo.data.repository.datasource.RemoteDataSource
import com.android.code.challenge.justo.data.retrofit.response.UserProfile

class JustoRepository() {

    private var mRemoteDataSource : RemoteDataSource

    init {
        mRemoteDataSource = RemoteDataSource()
    }

    fun getUserProfile() : LiveData<UserProfile>{
        return  mRemoteDataSource.getUserProfile()
    }
}