package com.android.code.challenge.justo.core

import android.app.Application
import com.android.code.challenge.justo.di.component.DaggerJustoRepositoryComponent
import com.android.code.challenge.justo.di.component.JustoRepositoryComponent
import com.android.code.challenge.justo.di.module.JustoRepositoryModule

class App : Application() {

    companion object {
        lateinit var justoRepositoryComponent: JustoRepositoryComponent
    }

    override fun onCreate() {
        super.onCreate()
        justoRepositoryComponent = DaggerJustoRepositoryComponent.builder().justoRepositoryModule(
            JustoRepositoryModule()
        ).build()
    }
}