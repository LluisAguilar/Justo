package com.android.code.challenge.justo.di.component

import com.android.code.challenge.justo.di.module.JustoRepositoryModule
import com.android.code.challenge.justo.data.repository.JustoRepositoryImp
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [JustoRepositoryModule::class])
interface JustoRepositoryComponent {

    fun inject(justoRepository: JustoRepositoryImp)

}