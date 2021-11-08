package com.android.code.challenge.justo.di.module

import com.android.code.challenge.justo.data.api.JustoServiceHelper
import com.android.code.challenge.justo.data.api.JustoServiceImpl
import com.android.code.challenge.justo.data.helper.StringDataCommonHelper.Companion.BASE_URL
import com.android.code.challenge.justo.data.repository.JustoRepositoryImp
import com.android.code.challenge.justo.data.repository.datasource.RemoteDataSource
import com.android.code.challenge.justo.data.retrofit.JustoService
import com.android.code.challenge.justo.domain.usecases.GetUserProfileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object JustoClientModule {

    @Provides
    fun providesBaseUrl() = BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
            //.addInterceptor(AuthInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL : String) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideJustoService(retrofit: Retrofit) = retrofit.create(JustoService::class.java)

    @Singleton
    @Provides
    fun provideJustoServiceHelper(justoServiceImpl: JustoServiceImpl): JustoServiceHelper = justoServiceImpl

    @Singleton
    @Provides
    fun provideRemoteDataSource() = RemoteDataSource()

    @Singleton
    @Provides
    fun provideRepository(justoServiceHelper: JustoServiceHelper, remoteDataSource: RemoteDataSource) = JustoRepositoryImp(justoServiceHelper, remoteDataSource)

    @Singleton
    @Provides
    fun provideUseCase(justoRepositoryImp: JustoRepositoryImp) = GetUserProfileUseCase(justoRepositoryImp)

}