package com.woowrale.openlibrary

import android.app.Application
import com.woowrale.openlibrary.di.components.DaggerMainComponent
import com.woowrale.openlibrary.di.components.MainComponent
import com.woowrale.openlibrary.di.modules.MainModule

class MainApplication : Application() {

    lateinit var mainComponent: MainComponent

    override fun onCreate() {
        super.onCreate()
        mainComponent = DaggerMainComponent.builder()
            .mainModule(MainModule(this))
            .build()
    }
}