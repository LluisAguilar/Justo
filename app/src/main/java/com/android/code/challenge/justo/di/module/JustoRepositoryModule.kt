package com.android.code.challenge.justo.di.module

import com.android.code.challenge.justo.data.repository.JustoRepositoryImp
import com.android.code.challenge.justo.data.repository.datasource.RemoteDataSource
import com.android.code.challenge.justo.data.retrofit.JustoClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class JustoRepositoryModule {

    @Provides
    @Singleton
    fun providedRetrofitService() = JustoClient.instance!!.getService()

    @Provides
    @Singleton
    fun provideRemoteDataSource() = RemoteDataSource()

}