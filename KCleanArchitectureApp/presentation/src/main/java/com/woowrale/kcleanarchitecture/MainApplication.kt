package com.woowrale.kcleanarchitecture

import android.app.Application
import com.woowrale.kcleanarchitecture.di.components.DaggerMainComponent
import com.woowrale.kcleanarchitecture.di.components.MainComponent
import com.woowrale.kcleanarchitecture.di.modules.MainModule


class MainApplication : Application() {

    lateinit var mainComponent: MainComponent

    override fun onCreate() {
        super.onCreate()
        mainComponent = DaggerMainComponent.builder()
            .mainModule(MainModule(this))
            .build()
    }
}